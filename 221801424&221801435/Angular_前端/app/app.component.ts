import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { StorageService } from './services/storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  isCollapsed = false;
  public user_name:any;

  constructor(public storage:StorageService,public router:Router,public message:NzMessageService){

  }

  judgeIsLogin(){
    if(this.storage.get("userInfo")!=null){
      this.user_name=this.storage.get("userInfo")['userInfo']['data']['name']
      return true;
    }
    else{
      return false;
    }
  }

  logout():void{
    this.storage.remove("userInfo")
    this.message.success("你已成功退出")
    this.router.navigate(['/user/login'])
  }

  login():void{
    this.router.navigate(['/user/login']);
  }
}

