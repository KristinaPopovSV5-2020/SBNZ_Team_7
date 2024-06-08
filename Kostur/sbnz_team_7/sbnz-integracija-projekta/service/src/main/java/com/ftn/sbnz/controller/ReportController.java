package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.reports.FeedbackNADto;
import com.ftn.sbnz.dto.reports.FeedbackUserDTO;
import com.ftn.sbnz.dto.reports.ThresholdDTO;
import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.model.models.reports.FeedbackReport;
import com.ftn.sbnz.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @RequestMapping(path = "/feedback", method = RequestMethod.GET)
    public ResponseEntity<List<FeedbackReport>> getFeedbackPeriodReport(@RequestParam String period){
        List<FeedbackReport> feedbackReports = reportService.getFeedbackReport(period);
        if (feedbackReports == null){
            throw new BadRequestException("There need to be at least five reviews for a report to be generated!");
        }else{
            return ResponseEntity.ok(feedbackReports);
        }

    }

    @RequestMapping(path = "/feedback-user", method = RequestMethod.GET)
    public ResponseEntity<FeedbackUserDTO> getFeedbackUserReport(@RequestParam String userId){

        FeedbackUserDTO feedbackReports = reportService.getUserFeedbackReport(userId);
        return ResponseEntity.ok(feedbackReports);

    }

    @RequestMapping(path = "/feedback-product", method = RequestMethod.GET)
    public ResponseEntity<FeedbackNADto> getFeedbackPerProductReport(@RequestParam String productId){

        FeedbackNADto feedbackReports = reportService.getFeedbackProductReport(productId);
        return ResponseEntity.ok(feedbackReports);

    }

    @GetMapping( "/products-threshold")
    public ResponseEntity<List<FeedbackNADto>> getHighRatedProductsReport(@RequestParam("threshold") String threshold){
        List<FeedbackNADto> feedbackReports = reportService.getHighRatedProductsReport(threshold);
        return ResponseEntity.ok(feedbackReports);

    }
}
