import { ICommand } from './ICommand';

export interface ICommandResponse {
    result: Array<ICommand>;
    message: string;
    error: string;
}
