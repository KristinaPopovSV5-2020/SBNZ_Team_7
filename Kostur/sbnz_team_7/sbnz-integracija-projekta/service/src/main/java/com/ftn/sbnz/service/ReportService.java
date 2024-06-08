package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.reports.FeedbackNADto;
import com.ftn.sbnz.dto.reports.FeedbackUserDTO;
import com.ftn.sbnz.model.models.reports.FeedbackReport;

import java.util.List;

public interface ReportService {

    List<FeedbackReport> getFeedbackReport(String period);


    FeedbackUserDTO getUserFeedbackReport(String userId);

    FeedbackNADto getFeedbackProductReport(String productId);

    List<FeedbackNADto> getHighRatedProductsReport(String threshold);
}
