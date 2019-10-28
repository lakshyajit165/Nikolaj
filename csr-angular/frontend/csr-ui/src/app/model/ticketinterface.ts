
export interface Ticket {
      uuid: string;
      query: string;
      intent: string[];
      raisedBy: string;
      createdOn: Date;
      assignedTime: Date;
      updatedOn: Date;
      assignedTo: string;
      rating: number;
      entity: string;
      type: string;
      status: number;
      responseType: number;
      resolvedBy: string;
      reopenTime: Date;

}
