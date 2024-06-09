package com.ftn.sbnz.service;


import com.ftn.sbnz.dto.reports.FeedbackNADto;
import com.ftn.sbnz.dto.reports.FeedbackUserDTO;
import com.ftn.sbnz.model.models.reports.FeedbackReport;
import com.ftn.sbnz.dto.ThresholdValueDTO;
import com.ftn.sbnz.dto.reports.DicountUsageReportDTO;
import com.ftn.sbnz.dto.reports.UserGiftReportDTO;
import com.ftn.sbnz.dto.reports.UserShoppingReportDTO;
import org.bson.types.ObjectId;


import java.util.List;

public interface ReportService {


    List<FeedbackReport> getFeedbackReport(String period);


    FeedbackUserDTO getUserFeedbackReport(String userId);

    FeedbackNADto getFeedbackProductReport(String productId);

    List<FeedbackNADto> getHighRatedProductsReport(String threshold);

    UserShoppingReportDTO generateUserShoppingReport(ObjectId userId, ThresholdValueDTO thresholdDTO);

    DicountUsageReportDTO generateDiscountUtilizationReport(ObjectId userId);

    List<UserGiftReportDTO> generateGiftReport(String giftName);

    List<UserGiftReportDTO> generateGiftReportIn30Days();

}
