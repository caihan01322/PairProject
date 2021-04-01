import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { RequestService } from 'src/app/services/request.service';
import { StorageService } from 'src/app/services/storage.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public userInfo:any = {
      'name':'',
      'account':'',
      'password':'',
      'repeatPwd':''
  };
  constructor(public request:RequestService ,public storage:StorageService,private router: Router,public message:NzMessageService ) { }

  ngOnInit(): void {
  }

  register():void{
    if(this.userInfo.name==''||this.userInfo.account==''||this.userInfo.password==''||this.userInfo.repeatPwd==''){
      this.message.warning("注册缺少字段");
    }
    else if(this.userInfo.password!=this.userInfo.repeatPwd){
      this.message.warning("两次密码不一致");
      this.userInfo.repeatpwd="";
      this.userInfo.password="";
    }

    else{
      let Data=this.request.getData("http://47.96.231.121/auth/register",{'user_name':this.userInfo.name,'user_password':this.userInfo.password,'user_account':this.userInfo.account,'user_repassword':this.userInfo.repeatpwd});
      Data.then((data:any)=>{
        console.log(data)
        if(data.status=="200"){
          alert("注册成功");
          this.router.navigate(['/user/login'])
        }
        if(data.status=='1012'){
          alert("账号已存在");
        }
        if(data.status=='1013'){
          alert("用户名已存在");
        }
      })
    }
  }
}
