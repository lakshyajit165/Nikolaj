export interface IReport {
    type: string;
    intent: [
        {
            name: string;
            queries: [
                {
                    queryName: string;
                    date: Date;
                }
            ];
        }
    ];

}
