package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.reports.FeedbackGlobal;
import com.ftn.sbnz.dto.reports.FeedbackNADto;
import com.ftn.sbnz.dto.reports.FeedbackUserDTO;
import com.ftn.sbnz.facts.FeedbackStatus;
import com.ftn.sbnz.kafka.EventHandler;
import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.RatingHelper;
import com.ftn.sbnz.model.models.products.Product;
import com.ftn.sbnz.model.models.reports.FeedbackReport;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.repository.FeedbackRepository;
import com.ftn.sbnz.repository.ProductRepository;
import com.ftn.sbnz.repository.UserRepository;
import com.ftn.sbnz.service.ProductService;
import com.ftn.sbnz.service.ReportService;
import org.bson.types.ObjectId;
import org.drools.core.ClassObjectFilter;
import org.drools.core.time.SessionPseudoClock;
import com.ftn.sbnz.dto.ThresholdValueDTO;
import com.ftn.sbnz.dto.reports.*;
import com.ftn.sbnz.facts.ShoppingStatus;
import com.ftn.sbnz.model.models.Discount;
import com.ftn.sbnz.model.models.products.Shopping;
import com.ftn.sbnz.model.models.user.User;
import com.ftn.sbnz.repository.DiscountRepository;
import com.ftn.sbnz.repository.ShoppingRepository;
import com.ftn.sbnz.repository.UserRepository;
import com.ftn.sbnz.service.ReportService;
import org.bson.types.ObjectId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.concurrent.TimeUnit;

@Service
public class ReportServiceImpl implements ReportService {

    private final KieContainer kieContainer;

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final FeedbackRepository feedbackRepository;

    private final UserRepository userRepository;

    private final EventHandler eventHandler;

    private final ShoppingRepository shoppingRepository;

    private final DiscountRepository discountRepository;

    public ReportServiceImpl(KieContainer kieContainer, ProductRepository productRepository, ProductService productService, FeedbackRepository feedbackRepository, UserRepository userRepository, EventHandler eventHandler, ShoppingRepository shoppingRepository, DiscountRepository discountRepository) {
        this.kieContainer = kieContainer;
        this.productRepository = productRepository;
        this.productService = productService;
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.eventHandler = eventHandler;
        this.shoppingRepository = shoppingRepository;
        this.discountRepository = discountRepository;
    }

    public DicountUsageReportDTO generateDiscountUtilizationReport(ObjectId userId) {
        KieSession kieSession = kieContainer.newKieSession("reportsKsession");
        kieSession.setGlobal("globalUserId", userId.toString());
        kieSession.getAgenda().getAgendaGroup("discount-rules").setFocus();
        insertDiscountsIntoSession(kieSession);

        Integer num = kieSession.fireAllRules();
        System.out.println(num);


        DicountUsageReportDTO reportDTO = new DicountUsageReportDTO();
        QueryResults results = kieSession.getQueryResults("getDiscountUsages");
        for (QueryResultsRow row : results) {
            Object usage = row.get("usage");
            try {
                Method getNumOfDiscounts = usage.getClass().getMethod("getNumOfDiscounts");
                Method getNumOfUsed = usage.getClass().getMethod("getNumOfUsed");

                reportDTO.setTotalNumber((Integer) getNumOfDiscounts.invoke(usage));
                reportDTO.setTotalUsed((Integer) getNumOfUsed.invoke(usage));
                reportDTO.calculatePercentageUsed();

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        kieSession.dispose();
        return reportDTO;
    }

    public UserShoppingReportDTO generateUserShoppingReport(ObjectId userId, ThresholdValueDTO thresholdDTO) {
        KieSession kieSession = kieContainer.newKieSession("reportsKsession");
        SessionPseudoClock clock = kieSession.getSessionClock();
        GlobalCounters counters = new GlobalCounters();

        kieSession.setGlobal("counters", counters);
        kieSession.setGlobal("globalUserId", userId.toString());
        kieSession.setGlobal("thresholdValue", thresholdDTO);

        insertShoppingsIntoSession(kieSession, clock);

        Integer num = kieSession.fireAllRules();
        System.out.println(num);

        UserShoppingReportDTO report = new UserShoppingReportDTO();
        GlobalCounters countersResult = (GlobalCounters) kieSession.getGlobal("counters");
        report.setTotalValue(counters.getTotalShoppingValue());
        report.setTotalCount(counters.getTotalShoppingCount());
        report.setUserId(userId);
        report.setUserEmail(userRepository.findById(userId).get().getUsername());
        report.calculateAverage();

        kieSession.dispose();

        return report;
    }

    @Override
    public List<UserGiftReportDTO> generateGiftReport(String giftName) {
        KieSession kieSession = kieContainer.newKieSession("reportsKsession2");
        kieSession.getAgenda().getAgendaGroup("name-rule").setFocus();
        kieSession.setGlobal("globalGiftName", giftName);
        Map<User, UserGifts> userGiftsMap = new HashMap<>();
        kieSession.setGlobal("userGiftsMap", userGiftsMap);


        insertUsersAndGiftsIntoSession(kieSession);

        Integer num = kieSession.fireAllRules();
        System.out.println("Number of rules fired: " + num);

        List<UserGiftReportDTO> dtos = new ArrayList<>();
        userGiftsMap.forEach((user, userGifts) -> {
            System.out.println("User: " + user.getUsername());
            userGifts.getGifts().forEach(gift -> System.out.println("Gift: " + gift.getGiftName()));
            dtos.add(new UserGiftReportDTO(user.getUsername(), userGifts.getGifts().size()));

        });

        kieSession.dispose();
        return dtos;

    }

    @Override
    public List<UserGiftReportDTO> generateGiftReportIn30Days() {
        KieSession kieSession = kieContainer.newKieSession("reportsKsession2");
        kieSession.getAgenda().getAgendaGroup("date-rule").setFocus();

        Map<User, UserGifts> userGiftsMap = new HashMap<>();
        kieSession.setGlobal("userGiftsMap", userGiftsMap);


        insertUsersAndGiftsIntoSession(kieSession);

        Integer num = kieSession.fireAllRules();
        System.out.println("Number of rules fired: " + num);

        List<UserGiftReportDTO> dtos = new ArrayList<>();
        userGiftsMap.forEach((user, userGifts) -> {
            System.out.println("User: " + user.getUsername());
            List<String> names = new ArrayList<>();
            userGifts.getGifts().forEach(gift -> names.add(gift.getGiftName()));
            dtos.add(new UserGiftReportDTO(user.getUsername(), names));

        });

        kieSession.dispose();
        return dtos;

    }

    @Override
    public List<FeedbackReportDTO> getFeedbackReport(String period) {
        boolean canGenerateWeekly = false;
        List<FeedbackReport> rankedReports = new ArrayList<>();
        if (period.equals("7d")){
            boolean canGenerateReport = eventHandler.processCheckFeedbackReport();
            canGenerateWeekly = canGenerateReport;
        }
        if (canGenerateWeekly || !period.equals("7d")) {
            KieSession kieSession = kieContainer.newKieSession("reportsKsession");
            SessionPseudoClock clock = kieSession.getSessionClock();
            eventHandler.insertAllFeedbacksIntoSession(kieSession,clock);
            insertProductsIntoSession(kieSession);

            String queryName;
            switch (period) {
                case "7d":
                    queryName = "Calculate weekly average rating for each product";
                    break;
                case "30d":
                    queryName = "Calculate monthly average rating for each product";
                    break;
                case "365d":
                    queryName = "Calculate yearly average rating for each product";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid period: " + period);
            }

            QueryResults results = kieSession.getQueryResults(queryName);
            for (QueryResultsRow row : results) {
                Product product = (Product) row.get("$product");
                List<Feedback> feedbacks = (List<Feedback>) row.get("$feedbacks");
                double average = (double) row.get("$averageRating");

                FeedbackReport feedbackReport = new FeedbackReport(product.getId(), feedbacks, average);
                rankedReports.add(feedbackReport);
            }

        }

        if (rankedReports == null) {
            return null;
        } else {
            rankedReports.sort(Comparator.comparingDouble(FeedbackReport::getAverageRating).reversed());
            List<FeedbackReportDTO>  feedbackReportDTOS = convertToReportDTO(rankedReports);
            return feedbackReportDTOS;
        }

    }

    @Override
    public FeedbackNADto getFeedbackProductReport(String productId) {
        Product product = productRepository.findById(new ObjectId(productId)).orElseThrow(() -> new RuntimeException("Product not found"));
        KieSession kieSession = kieContainer.newKieSession("reportsKsession");
        SessionPseudoClock clock = kieSession.getSessionClock();
        insertFeedbackIntoSession(kieSession, clock);
        List<Integer> allRatings = new ArrayList<>();
        kieSession.setGlobal("allRatings",allRatings);
        kieSession.setGlobal("globalProductId", productId);
        kieSession.getAgenda().getAgendaGroup("report-fpp-rules").setFocus();
        Integer num = kieSession.fireAllRules();
        System.out.println(num);

        FeedbackNADto feedbackNADto = new FeedbackNADto();
        feedbackNADto.setName(product.getName());
        feedbackNADto.setProductId(product.getId());
        feedbackNADto.setNumberOfProducts(allRatings.size());
        allRatings = (List<Integer>) kieSession.getGlobal("allRatings");
        feedbackNADto.setAverage(calculateAverage(allRatings));

        kieSession.dispose();
        return feedbackNADto;
    }

    @Override
    public FeedbackUserDTO getUserFeedbackReport(String userId) {
        User user = userRepository.findById(new ObjectId(userId)).orElseThrow(() -> new RuntimeException("User not found"));
        KieSession kieSession = kieContainer.newKieSession("reportsKsession");
        SessionPseudoClock clock = kieSession.getSessionClock();
        insertFeedbackIntoSession(kieSession, clock);
        kieSession.setGlobal("globalUserId",user.getId().toString());
        kieSession.setGlobal("feedbackGlobal", new FeedbackGlobal());
        kieSession.getAgenda().getAgendaGroup("user-feedback-report-rules").setFocus();

        Integer num = kieSession.fireAllRules();
        System.out.println(num);

        FeedbackGlobal countersResult = (FeedbackGlobal) kieSession.getGlobal("feedbackGlobal");
        FeedbackUserDTO feedbackUserDTO = new FeedbackUserDTO(user.getId(),user.getUsername(),countersResult.getFeedbackCount());

        kieSession.dispose();

        return feedbackUserDTO;
    }

    @Override
    public List<FeedbackNADto> getHighRatedProductsReport(String threshold) {
        KieSession kieSession = kieContainer.newKieSession("reportsKsession");
        SessionPseudoClock clock = kieSession.getSessionClock();
        insertFeedbackIntoSession(kieSession, clock);
        insertProductsIntoSession(kieSession);
        kieSession.setGlobal("ratingThreshold", threshold);
        Integer num = kieSession.fireAllRules();
        System.out.println(num);

        List<FeedbackNADto> highlyRankedProducts = new ArrayList<>();
        Collection<?> objects = kieSession.getObjects(new ClassObjectFilter(FeedbackNADto.class));
        for (Object obj : objects) {
            FeedbackNADto productRating = (FeedbackNADto) obj;
            highlyRankedProducts.add(productRating);
        }
        kieSession.dispose();

        // Sort the list by average rating in descending order
        Collections.sort(highlyRankedProducts, new Comparator<FeedbackNADto>() {
            @Override
            public int compare(FeedbackNADto o1, FeedbackNADto o2) {
                return Double.compare(o2.getAverage(), o1.getAverage()); // Descending order
            }
        });
        return highlyRankedProducts;
    }

    private void insertUsersAndGiftsIntoSession(KieSession kieSession) {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            kieSession.insert(user);
            user.getGifts().forEach(kieSession::insert);
        });
    }


    private void insertDiscountsIntoSession(KieSession kieSession) {
        List<Discount> discounts = discountRepository.findAll();
        discounts.forEach(discount -> {
            kieSession.insert(discount);
        });

    }

    private void insertFeedbackIntoSession(KieSession kieSession, SessionPseudoClock clock) {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        feedbackList.forEach(feedback -> {
            long timeDiff = feedback.getDateTime().getTime() - clock.getCurrentTime();
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            FeedbackStatus status = new FeedbackStatus(feedback, false);
            kieSession.insert(status);
        });
    }

    private void insertShoppingsIntoSession(KieSession kieSession, SessionPseudoClock clock) {
        List<Shopping> shoppings = shoppingRepository.findAll();
        shoppings.forEach(shopping -> {
            long timeDiff = shopping.getDateTime().getTime() - clock.getCurrentTime();
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            ShoppingStatus status = new ShoppingStatus(shopping, false);
            kieSession.insert(status);
        });
    }

    private void insertProductsIntoSession(KieSession kieSession) {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            kieSession.insert(product);
        });
    }

    public static double calculateAverage(List<Integer> ratings) {
        if (ratings == null || ratings.isEmpty()) {
            return 0.0;
        }

        int sum = 0;
        for (int rating : ratings) {
            sum += rating;
        }

        return (double) sum / ratings.size();
    }

    private List<FeedbackReportDTO> convertToReportDTO(List<FeedbackReport> feedbackReports){
        List<FeedbackReportDTO> feedbackReportDTOS = new ArrayList<>();
        for(FeedbackReport feedbackReport : feedbackReports){
            FeedbackReportDTO  feedbackReportDTO = new FeedbackReportDTO();
            Product product = productRepository.findById(feedbackReport.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));
            feedbackReportDTO.setProductName(product.getName());
            List<FeedbackDTO> feedbackDTOS = new ArrayList<>();
            for (Feedback feedback: feedbackReport.getFeedbacks()){
                User user = userRepository.findById(feedback.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
                FeedbackDTO feedbackDTO = new FeedbackDTO(feedback, user.getUsername());
                feedbackDTOS.add(feedbackDTO);
            }
            Collections.sort(feedbackDTOS, new Comparator<FeedbackDTO>() {
                @Override
                public int compare(FeedbackDTO f1, FeedbackDTO f2) {
                    return f1.getDate().compareTo(f2.getDate());
                }
            });
            feedbackReportDTO.setFeedbacks(feedbackDTOS);
            feedbackReportDTO.setAverageRating(feedbackReport.getAverageRating());
            feedbackReportDTOS.add(feedbackReportDTO);
        }
        return feedbackReportDTOS;


    }



}
