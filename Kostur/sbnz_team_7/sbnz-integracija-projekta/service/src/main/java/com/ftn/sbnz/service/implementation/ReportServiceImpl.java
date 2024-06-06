package com.ftn.sbnz.service.implementation;

import com.ftn.sbnz.kafka.EventHandler;
import com.ftn.sbnz.model.models.Feedback;
import com.ftn.sbnz.model.models.reports.FeedbackReport;
import com.ftn.sbnz.service.ReportService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    private final EventHandler eventHandler;

    public ReportServiceImpl(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public List<FeedbackReport> getFeedbackReport(Integer period) {

        List<FeedbackReport> reports = eventHandler.processFeedbackReport();

        if (reports == null){
            return null;
        }else{
            return reports;
        }

    }
}
