export interface ICommand {
    id: string;
    name: string;
    tags: string[];
    desc: string;
    last_updated_on: string;
    created_on: string;
    created_by: string;
    usage: string;
    status: string;


    parameters: [{
        parameterName: string;
        required: string;
        default_value: string;
        description: string;
    }];
}
