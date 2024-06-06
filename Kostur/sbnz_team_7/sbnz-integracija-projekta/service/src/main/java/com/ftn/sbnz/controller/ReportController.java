package com.ftn.sbnz.controller;

import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.model.models.reports.FeedbackReport;
import com.ftn.sbnz.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.List;

@Controller
@RequestMapping(value = "/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity<List<FeedbackReport>> getFeedbackReport(@RequestParam Integer period){

        List<FeedbackReport> feedbackReports = reportService.getFeedbackReport(period);
        if (feedbackReports == null){
            throw new BadRequestException("There need to be at least five reviews for a report to be generated!");
        }else{
            return ResponseEntity.ok(feedbackReports);
        }

    }
}
