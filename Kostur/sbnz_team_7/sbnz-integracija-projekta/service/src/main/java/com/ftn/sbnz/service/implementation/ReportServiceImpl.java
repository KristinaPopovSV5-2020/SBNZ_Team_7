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
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReportServiceImpl implements ReportService {

    private final KieContainer kieContainer;

    private final ProductRepository productRepository;

    private final ProductService productService;

    private final FeedbackRepository feedbackRepository;

    private final UserRepository userRepository;

    private final EventHandler eventHandler;

    public ReportServiceImpl(KieContainer kieContainer, ProductRepository productRepository, ProductService productService, FeedbackRepository feedbackRepository, UserRepository userRepository, EventHandler eventHandler) {
        this.kieContainer = kieContainer;
        this.productRepository = productRepository;
        this.productService = productService;
        this.feedbackRepository = feedbackRepository;
        this.userRepository = userRepository;
        this.eventHandler = eventHandler;
    }

    @Override
    public List<FeedbackReport> getFeedbackReport(String period) {
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
            return rankedReports;
        }

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
        return highlyRankedProducts;
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

    private void insertProductsIntoSession(KieSession kieSession) {
        List<Product> products = productRepository.findAll();
        products.forEach(product -> {
            kieSession.insert(product);
        });
    }
}
