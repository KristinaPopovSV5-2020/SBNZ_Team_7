export interface DiscountDTO{
    value:number,
    id:string,
    dateCreated:Date,
    used:boolean
}



export interface DicountUsageReportDTO{
    totalNumber:number;
    totalUsed:number;
    percentageUsed:number
}
