import { Injectable } from '@angular/core';
import { FeedbackDTO, FeedbackReportDTO, FeedbackReportThreshold } from '../../dto/Report';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ProductFeedbackDTO } from '../../dto/Product';
import { UserReportDTO, UserResponseReportDTO } from '../../dto/User';

@Injectable({
  providedIn: 'root'
})
export class ReportService {

  feedbacks: FeedbackDTO[] | undefined;
  productName: string | undefined;



  constructor(private http: HttpClient) { }



  getFeedbackReportsThreshold(threshold:number) : Observable<FeedbackReportThreshold[]> {
    return this.http.get<FeedbackReportThreshold[]>(environment.apiHost + 'api/reports/products-threshold?threshold=' + threshold);
  }

  getFeedbackReportCAProduct(productId: string | undefined) : Observable<FeedbackReportThreshold> {
    return this.http.get<FeedbackReportThreshold>(environment.apiHost + 'api/reports/feedback-product?productId=' + productId);
  }

  getFeedbackReportPerUser(userId: string | undefined) : Observable<UserResponseReportDTO> {
    return this.http.get<UserResponseReportDTO>(environment.apiHost + 'api/reports/feedback-user?userId=' + userId);
  }

  getFeedbackReportWMY(period: string | undefined) : Observable<FeedbackReportDTO[]> {
    return this.http.get<FeedbackReportDTO[]>(environment.apiHost + 'api/reports/feedback?period=' + period);
  }

  getAllProducts() : Observable<ProductFeedbackDTO[]> {
    return this.http.get<ProductFeedbackDTO[]>(environment.apiHost + 'api/products/report');
  }

  getAllUsers() : Observable<UserReportDTO[]> {
    return this.http.get<UserReportDTO[]>(environment.apiHost + 'api/users/report');
  }
}
