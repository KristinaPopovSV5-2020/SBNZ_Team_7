package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.dto.RevenueDTO;
import com.ftn.sbnz.dto.ThresholdValueDTO;
import com.ftn.sbnz.dto.reports.GlobalCounters;
import com.ftn.sbnz.dto.reports.UserShoppingReportDTO;
import com.ftn.sbnz.facts.ShoppingStatus;
import com.ftn.sbnz.model.models.products.Shopping;
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

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ReportServiceImpl implements ReportService {
    private final KieContainer kieContainer;
    private final ShoppingRepository shoppingRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportServiceImpl(KieContainer kieContainer, ShoppingRepository shoppingRepository, UserRepository userRepository) {
        this.kieContainer = kieContainer;
        this.shoppingRepository = shoppingRepository;
        this.userRepository = userRepository;
    }


    public List<RevenueDTO> generateRevenueReport() {
        KieSession kieSession = kieContainer.newKieSession("reportsKsession");
        SessionPseudoClock clock = kieSession.getSessionClock();

        Double totalRevenue = 0.0;
        kieSession.setGlobal("kSession", kieSession);
        kieSession.setGlobal("calculateRevenueExecuted", false);
        insertShoppingsIntoSession(kieSession, clock);

        kieSession.fireAllRules();

        System.out.println(totalRevenue);

//        QueryResults revenueResults = kieSession.getQueryResults("GetAllRevenueDTOs");
//        for (QueryResultsRow row : revenueResults) {
//            RevenueDTO revenueDTO = (RevenueDTO) row.get("$revenueDTO");
//            System.out.println(revenueDTO);
//        }
        QueryResults results = kieSession.getQueryResults("WeeklyShopping");
        System.out.println("size: " + results.size());
        for (QueryResultsRow row : results) {
            Shopping shopping = (Shopping) row.get("$shopping");
            System.out.println(shopping);
        }


        return null;
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


    private void insertShoppingsIntoSession(KieSession kieSession, SessionPseudoClock clock) {
        List<Shopping> shoppings = shoppingRepository.findAll();
        shoppings.forEach(shopping -> {
            long timeDiff = shopping.getDateTime().getTime() - clock.getCurrentTime();
            clock.advanceTime(timeDiff, TimeUnit.MILLISECONDS);
            ShoppingStatus status = new ShoppingStatus(shopping, false);
            kieSession.insert(status);
        });
    }
}
