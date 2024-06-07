package com.ftn.sbnz.service;

import com.ftn.sbnz.dto.RevenueDTO;
import com.ftn.sbnz.dto.ThresholdValueDTO;
import com.ftn.sbnz.dto.reports.UserShoppingReportDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ReportService {
    List<RevenueDTO> generateRevenueReport();

    UserShoppingReportDTO generateUserShoppingReport(ObjectId userId, ThresholdValueDTO thresholdDTO);
}
