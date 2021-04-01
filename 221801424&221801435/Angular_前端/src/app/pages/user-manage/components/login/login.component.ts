import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RequestService } from '../../../../services/request.service';
import { Router } from '@angular/router';
import { StorageService } from 'src/app/services/storage.service';
import { NzMessageService } from 'ng-zorro-antd/message';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public userInfo:any = {
      'name':'',
      'account':'',
      'password':'',
      'id':'',

  };
  constructor(public http:HttpClient,public request:RequestService ,public storage:StorageService ,private router:Router,public message:NzMessageService) { }

  ngOnInit(): void {
  }

  login():void{
    if(this.userInfo.name==''||this.userInfo.account==''||this.userInfo.password==''){
      this.message.warning("缺少字段");
    }
    else{
      let  promiseData = this.request.getData('http://47.96.231.121/auth/login',{'account':this.userInfo.account,'password':this.userInfo.password});
    promiseData.then((data:any)=>{
      console.log(data)
      if(data.status=='200'){
        this.storage.set("userInfo",{userInfo:data})
        // console.log((data));
        console.log(this.storage.get('userInfo'))
        this.message.success("登录成功")
        this.router.navigate(['/article-manage/article-list'])
      }
      else{
        this.message.success("登录失败")
        this.userInfo.account=""
        this.userInfo.password=""
        
      }
        
    });
    }
  }

  register():void{
    if(this.userInfo.name==''||this.userInfo.account==''||this.userInfo.password==''){
      this.message.warning("注册缺少字段")
    }
  }


  
}
