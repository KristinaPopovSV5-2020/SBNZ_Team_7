export interface GiftNameDTO{
    giftName: string;
}


export interface UserGiftReportDTO{
    username:string;
    giftCount:number;
    giftNames:string[]
}


export interface GiftDTO{
    giftName:string,
    timeGiven:string,
    reason:string
}