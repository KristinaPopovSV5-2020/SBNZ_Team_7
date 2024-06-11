export interface FeedbackReportThreshold{
    productId: string;
    name: string;
    numberOfProducts: number;
    average: number;
}


export interface FeedbackDTO{
    username: string;
    date: Date;
    rating: number;
}

export interface FeedbackReportDTO{
    productName: string;
    feedbacks: FeedbackDTO[];
    averageRating: number;
}