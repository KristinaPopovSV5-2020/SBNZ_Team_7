package com.ftn.sbnz.service;

import com.ftn.sbnz.model.models.reports.FeedbackReport;

import java.util.List;

public interface ReportService {

    List<FeedbackReport> getFeedbackReport(Integer period);
}
