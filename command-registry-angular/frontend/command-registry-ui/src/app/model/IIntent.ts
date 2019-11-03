export interface IIntent {
    uniqueId: string;
    ticketName: string; // query
    ticketId: string;
    intent: string;
    createdOn: Date;
    updatedOn: Date;
    userId: string;
    entity: string;
    reportType: string;
    commandName:string;
}
