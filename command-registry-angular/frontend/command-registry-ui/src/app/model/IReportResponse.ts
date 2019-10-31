import { IIntent } from './IIntent';
export interface IReportResponse {
            typeOfReport: string;
            entity: string;
            intent: string;
            intentList: IIntent[];
        }

// {
//         "NoCommand": {
//             "representative": {
//                 "ok connecting to representative": [
//                     {
//                         "uniqueId": "6bd36223-495c-4421-b730-3f8656baaf1f",
//                         "ticketName": "connect to someone",
//                         "ticketId": "2",
//                         "intent": "ok connecting to representative",
//                         "createdOn": "21/01/2019",
//                         "updatedOn": "22/01/2019",
//                         "userId": "1234567890",
//                         "entity": "representative",
//                         "reportType": "NoCommand"
//                     }
//                 ]
