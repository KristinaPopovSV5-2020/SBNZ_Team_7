import { Injectable } from '@angular/core';
import { FeedbackReportThreshold } from '../../dto/FeedbackReportThreshold';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ProductFeedbackDTO } from '../../dto/Product';

@Injectable({
  providedIn: 'root'
})
export class ReportService {



  constructor(private http: HttpClient) { }



  getFeedbackReportsThreshold(threshold:number) : Observable<FeedbackReportThreshold[]> {
    return this.http.get<FeedbackReportThreshold[]>(environment.apiHost + 'api/reports/products-threshold?threshold=' + threshold);
  }

  getFeedbackReportCAProduct(productId: string | undefined) : Observable<FeedbackReportThreshold> {
    return this.http.get<FeedbackReportThreshold>(environment.apiHost + 'api/reports/feedback-product?productId=' + productId);
  }

  getAllProducts() : Observable<ProductFeedbackDTO[]> {
    return this.http.get<ProductFeedbackDTO[]>(environment.apiHost + 'api/products/report');
  }
}
