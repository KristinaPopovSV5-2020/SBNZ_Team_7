import { Injectable } from '@angular/core';
import { FeedbackReportThreshold, ThresholdValueDTO, UserShoppingReportDTO } from '../../dto/FeedbackReportThreshold';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ProductFeedbackDTO } from '../../dto/Product';
import { UserReportDTO, UserResponseReportDTO } from '../../dto/User';
import { DicountUsageReportDTO } from '../../dto/Discount';
import { GiftNameDTO, UserGiftReportDTO } from '../../dto/Gift';

@Injectable({
  providedIn: 'root'
})
export class ReportService {



  constructor(private http: HttpClient) { }

  getGiftsReport(giftNameDTO: GiftNameDTO): Observable<UserGiftReportDTO[]> {
    return this.http.post<UserGiftReportDTO[]>(`${environment.apiHost}api/reports/gifts`, giftNameDTO);
  }

  getDiscountUsageReport(userId: string): Observable<DicountUsageReportDTO> {
    return this.http.get<DicountUsageReportDTO>(`${environment.apiHost}api/reports/discounts?userId=${userId}`);
  }

  getFeedbackReportsThreshold(threshold:number) : Observable<FeedbackReportThreshold[]> {
    return this.http.get<FeedbackReportThreshold[]>(environment.apiHost + 'api/reports/products-threshold?threshold=' + threshold);
  }

  getFeedbackReportCAProduct(productId: string | undefined) : Observable<FeedbackReportThreshold> {
    return this.http.get<FeedbackReportThreshold>(environment.apiHost + 'api/reports/feedback-product?productId=' + productId);
  }

  getFeedbackReportPerUser(userId: string | undefined) : Observable<UserResponseReportDTO> {
    return this.http.get<UserResponseReportDTO>(environment.apiHost + 'api/reports/feedback-user?userId=' + userId);
  }

  getAllProducts() : Observable<ProductFeedbackDTO[]> {
    return this.http.get<ProductFeedbackDTO[]>(environment.apiHost + 'api/products/report');
  }

  getAllUsers() : Observable<UserReportDTO[]> {
    return this.http.get<UserReportDTO[]>(environment.apiHost + 'api/users/report');
  }


  getUserShoppingReport(userId: string, threshold: ThresholdValueDTO): Observable<UserShoppingReportDTO> {
    return this.http.post<UserShoppingReportDTO>(`${environment.apiHost}api/reports/userShopping?userId=${userId}`, threshold);
  }
}
