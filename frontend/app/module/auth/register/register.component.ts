import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AuthService } from '../../service/auth.service';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
  animations:[
    // 按钮动画
    trigger('btnTrigger',[
      state('in',style({
        transform:'scale(1.05)'
      })),
      state('out',style({
        transform:'scale(1.0)'
      })),
      transition('in => out',animate('100ms ease-out')),
      transition('out => in',animate('100ms ease-in'))
    ]),
    
    // 输入框动画
    trigger('inputTrigger',[
      state('in',style({
        opacity: 1, transform: 'translateX(0) scale(1)'
      })),
      state('inputEnter',style({
        'border-radius':'0px'
      })),
      state('inputLeave',style({
      'border-radius':'10px'
      })),
      transition("* => in",[
        style({opacity: 0, transform: 'translateX(-100%) scale(0)'}),
        animate(500)
    ]),
      transition("inputEnter => inputLeave",animate('.5s ease-out')),
      transition("inputLeave => inputEnter",animate('.5s ease-in'))
    ])
  ]
})
export class RegisterComponent implements OnInit {

  constructor(private message : NzMessageService,
    private authService : AuthService,
    private router : Router,
    private title : Title) { }

  ngOnInit(): void {
    this.title.setTitle("注册");
  }

  userForm:any = {
      username : "",
      password1 : "",
      password2 : ""
  }

  loginBtnState = 'out';
  inputState='in';

  // 登录按钮动画状态改变
  toggleState(state : boolean){
    this.loginBtnState = state ? 'in' : 'out';
}

toggleInputState(state : boolean){
  this.inputState = state ? 'inputEnter' : 'inputLeave'
}

  // 对用户进行注册
  registerUser(){
      if (this.userForm.username.trim() === ""
      || this.userForm.password1.trim() === ""
      || this.userForm.password2.trim() === "")
      {
          this.message.error("输入不得为空!");
      }
      else if (this.userForm.username.length < 5 || this.userForm.password1.length < 5)
      {
        this.message.error("用户名或密码不得少于5个字符")
      }
      else if (this.userForm.password1 !== this.userForm.password2){
        this.message.error("两次输入密码不一致!")
        this.userForm.password2 = ""
      }else{
        // 执行注册
        this.authService.register(this.userForm)
        .then(auth => {
          // auth.statusCode == 200成功
          switch(auth.statusCode){
          // 201 密码不一致
            case 201:
                this.message.error("两次输入密码不一致!");
                this.userForm.password2 = "";
                break;
          // 202 输入为空
            case 202:
                this.message.error("输入不得为空!");
                break;
          // 203 用户名已存在
            case 203:
              this.message.error("用户名已存在!")
              break;
            case 200:
              this.message.success("注册成功!");
              this.router.navigate(['/auth/login'],{
                // 跳转到登录页面，并携带用户名的查询字符串
                queryParams:{
                  targetUser:auth.username
                }
              });
              break;
          }
        
        })
      }
  }

}
