import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    // 管道名
    name: 'truncate'
})

export class TruncatePipe implements PipeTransform {

    maxlength = 70;

    transform(value: any, ...args: any[]): any {
        return value.length > this.maxlength ? value.substring(0,this.maxlength)+"..." : value;
    }
}