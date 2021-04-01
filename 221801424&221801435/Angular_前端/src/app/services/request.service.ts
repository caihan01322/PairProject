import { Injectable } from '@angular/core';
import { promise } from 'selenium-webdriver';
import { HttpClient,HttpHeaders} from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class RequestService {

  private headers:any=new HttpHeaders({'Content-Type':'application/json'})
  constructor(public http:HttpClient) { }

  getData(api:any,body:any){
    return new Promise((solve)=>{
      this.http.post(api,body,this.headers).subscribe((data)=>{
          solve(data);
      });
    });
  }
}
