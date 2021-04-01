import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Observable } from 'rxjs';
import { Auth } from 'src/app/model/auth.model';

@Injectable({providedIn: 'root'})
export class AuthService {
    constructor( private message: NzMessageService,
        private http : HttpClient,
        @Inject('BASE_URL') private BASE_URL : string) {}


    // 登录请求api
    login_api = "auth/login";

    // 注册api
    register_api = "auth/register";

    logout_api = "auth/logout";

    headers : HttpHeaders = new HttpHeaders({'Content-Type':'application/json'})

    /* 
        登录服务登录功能
        返回数据格式
        {
            "userId" : 1,
            "username":"yzx",
            "statusCode" : 200,
            "errMsg":"success"
        }
    */
    login(userForm : any) : Promise<Auth>{
        return this.http
        .post(this.BASE_URL + this.login_api,userForm,{headers : this.headers})
        .toPromise()
        .then( (res:any) => res.data as Auth)
        .catch(err => {
            this.message.error("网络错误，请重试");
            return Promise.reject(err);
        });

    }

    
    register(userForm :any) : Promise<Auth>{
        return this.http
        .post(this.BASE_URL + this.register_api,
            userForm,
            {headers:this.headers})
        .toPromise()
        .then( (res:any) => res.data as Auth)
        .catch(err => {
            this.message.error("网络错误，请重试!");
            return Promise.reject(err);
        })
    }

    
    // 设置
    setLocalStorage(auth : Auth){
        localStorage.setItem("userId", auth.userId + "");
        localStorage.setItem("username", auth.username);
        localStorage.setItem("token",auth.token);
    }


    logout(){
        return this.http.get(this.BASE_URL+"auth/logout")
        .toPromise()
        .then(res => {
            localStorage.removeItem("userId");
            localStorage.removeItem("username");
            localStorage.removeItem("token");
        })
        .catch(err => {
            this.message.error("网络错误，请重试!");
            return Promise.reject(err);
        })
    }
}