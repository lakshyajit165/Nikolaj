import { IService } from './IService';
import { Icsr } from './Icsr';
import { IReport } from './IReport';

export interface ResponseFormatforService {
    result: IService[];
    errors: string;
    message: string;
}

export interface ResponseFormatforCsr {
    result: Icsr[];
    errors: string;
}

export interface ResponseFormatforReport {
    result: IReport[];
    errors: string;
    message: string;
}

export interface ResponseFormatforSize {
    result: number;
    errors: string;
    message: string;
}
