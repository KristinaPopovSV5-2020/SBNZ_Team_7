package com.ftn.sbnz.controller;


import com.ftn.sbnz.dto.ThresholdValueDTO;
import com.ftn.sbnz.dto.reports.*;
import com.ftn.sbnz.exception.BadRequestException;
import com.ftn.sbnz.model.models.reports.FeedbackReport;
import com.ftn.sbnz.service.ReportService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/api/reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {


    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @RequestMapping(path = "/feedback", method = RequestMethod.GET)
    public ResponseEntity<List<FeedbackReport>> getFeedbackPeriodReport(@RequestParam String period) {
        List<FeedbackReport> feedbackReports = reportService.getFeedbackReport(period);
        if (feedbackReports == null) {
            throw new BadRequestException("There need to be at least five reviews for a report to be generated!");
        } else {
            return ResponseEntity.ok(feedbackReports);
        }

    }

    @RequestMapping(path = "/feedback-user", method = RequestMethod.GET)
    public ResponseEntity<FeedbackUserDTO> getFeedbackUserReport(@RequestParam String userId) {

        FeedbackUserDTO feedbackReports = reportService.getUserFeedbackReport(userId);
        return ResponseEntity.ok(feedbackReports);

    }

    @RequestMapping(path = "/feedback-product", method = RequestMethod.GET)
    public ResponseEntity<FeedbackNADto> getFeedbackPerProductReport(@RequestParam String productId) {

        FeedbackNADto feedbackReports = reportService.getFeedbackProductReport(productId);
        return ResponseEntity.ok(feedbackReports);

    }

    @GetMapping("/products-threshold")
    public ResponseEntity<List<FeedbackNADto>> getHighRatedProductsReport(@RequestParam("threshold") String threshold) {
        List<FeedbackNADto> feedbackReports = reportService.getHighRatedProductsReport(threshold);
        return ResponseEntity.ok(feedbackReports);

    }

    @PostMapping("/userShopping")
    public ResponseEntity<UserShoppingReportDTO> getUserShoppingReport(@RequestParam String userId, @RequestBody ThresholdValueDTO thresholdDTO) {
        UserShoppingReportDTO report = reportService.generateUserShoppingReport(new ObjectId(userId), thresholdDTO);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }


    @GetMapping("/discounts")
    public ResponseEntity<DicountUsageReportDTO> getDiscountReport(@RequestParam String userId) {
        DicountUsageReportDTO report = reportService.generateDiscountUtilizationReport(new ObjectId(userId));
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @GetMapping("/gifts")
    public ResponseEntity<List<UserGiftReportDTO>> getGiftsReport(@RequestBody GiftNameDTO giftNameDTO) {
        List<UserGiftReportDTO> dto = reportService.generateGiftReport(giftNameDTO.getGiftName());
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    @GetMapping("/gifts/lastMonth")
    public ResponseEntity<List<UserGiftReportDTO>> getLastMonthGiftsReport() {
        List<UserGiftReportDTO> dto = reportService.generateGiftReportIn30Days();
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }


}
