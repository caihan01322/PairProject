import { NumberFormatStyle } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';

@Injectable({providedIn: 'root'})
export class DataService {
    constructor(private http : HttpClient,@Inject('BASE_URL')private  BASE_URL : string,private mesasge : NzMessageService) {

     }

     private api = "data/statistics";
    getData(origin:any,target:any){
        origin?.forEach((element:any) => {
            target.push({
                name:`关键词：<b>${element.key}</b>`,
                y:element.value
            })
        });
    }

    getYears(origin:any,target:any){
        origin?.forEach((element:any) => {
            target.push(element + "");
        });
    }

     getStatisticsData(){
         return this.http.get(this.BASE_URL+this.api)
         .toPromise()
         .then((res:any) => {
             return res;
         })
         .catch(err => {
             this.mesasge.error("网络错误！");
             return Promise.reject(err);
         })
     }
    
}