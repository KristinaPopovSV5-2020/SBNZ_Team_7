export interface FeedbackReportThreshold{
    productId: string;
    name: string;
    numberOfProducts: number;
    average: number;
}



export interface ThresholdValueDTO{
    checkDate:boolean;
    value:number
}


export interface UserShoppingReportDTO{
    userId:string;
    userEmail:string;
    totalValue:number;
    totalCount:number;
    average:number;
}

