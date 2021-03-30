import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    // 管道名
    name: 'truncateAuthor'
})

export class TruncateAuthorPipe implements PipeTransform {

    maxlength = 10;

    transform(value: string[], ...args: any[]): any {
        if (value.length > 2){
            return `${value[0]}，${value[1]}等`;
        }else if(value.length == 2){
            return `${value[0]}，${value[1]}`;
        }
        return value[0];
    }
}