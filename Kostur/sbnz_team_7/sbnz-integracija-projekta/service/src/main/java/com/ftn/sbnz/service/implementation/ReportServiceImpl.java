package com.ftn.sbnz.service.implementation;

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
import org.kie.api.time.SessionPseudoClock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ReportServiceImpl implements ReportService {
    private final KieContainer kieContainer;
    private final ShoppingRepository shoppingRepository;
    private final UserRepository userRepository;
    private final DiscountRepository discountRepository;


    @Autowired
    public ReportServiceImpl(KieContainer kieContainer, ShoppingRepository shoppingRepository, UserRepository userRepository, DiscountRepository discountRepository) {
        this.kieContainer = kieContainer;
        this.shoppingRepository = shoppingRepository;
        this.userRepository = userRepository;
        this.discountRepository = discountRepository;
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

    private void insertShoppingsIntoSession(KieSession kieSession, SessionPseudoClock clock) {
        List<Shopping> shoppings = shoppingRepository.findAll();
        shoppings.forEach(shopping -> {
            long timeDiff = shopping.getDateTime().getTime() - clock.getCurrentTime();
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            ShoppingStatus status = new ShoppingStatus(shopping, false);
            kieSession.insert(status);
        });
    }


    private void insertDiscountsIntoSession(KieSession kieSession) {
        List<Discount> discounts = discountRepository.findAll();
        discounts.forEach(discount -> {
            kieSession.insert(discount);
        });

    }

    private void insertUsersAndGiftsIntoSession(KieSession kieSession) {
        List<User> users = userRepository.findAll();
        users.forEach(user -> {
            kieSession.insert(user);
            user.getGifts().forEach(kieSession::insert);
        });
    }

}
