package com.ftn.sbnz.controller;

import com.ftn.sbnz.dto.RevenueDTO;
import com.ftn.sbnz.dto.ThresholdValueDTO;
import com.ftn.sbnz.dto.reports.UserShoppingReportDTO;
import com.ftn.sbnz.service.ReportService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/api/reports", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReportController {


    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("")
    public ResponseEntity<List<RevenueDTO>> getRevenueData() {
        List<RevenueDTO> revenueDTOS = reportService.generateRevenueReport();
        return new ResponseEntity<>(revenueDTOS, HttpStatus.OK);
    }

    @GetMapping("/userShopping")
    public ResponseEntity<UserShoppingReportDTO> getUserShoppingReport(@RequestParam String userId, @RequestBody ThresholdValueDTO thresholdDTO) {
        UserShoppingReportDTO report = reportService.generateUserShoppingReport(new ObjectId(userId), thresholdDTO);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }


}
