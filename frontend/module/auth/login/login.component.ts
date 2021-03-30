import { animate, state, style, transition, trigger } from '@angular/animations';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [

    // 按钮动画
    trigger('btnTrigger', [
      state('in', style({
        transform: 'scale(1.05)'
      })),
      state('out', style({
        transform: 'scale(1.0)'
      })),
      transition('in => out', animate('100ms ease-out')),
      transition('out => in', animate('100ms ease-in'))
    ]),

    // 输入框动画
    trigger('inputTrigger', [
      state('in', style({
        opacity: 1, transform: 'translateX(0) scale(1)'
      })),
      state('inputEnter', style({
        'border-radius': '0px'
      })),
      state('inputLeave', style({
        'border-radius': '10px'

      })),
      transition("* => in", [
        style({ opacity: 0, transform: 'translateX(-100%) scale(0)' }),
        animate(500)
      ]),
      transition("inputEnter => inputLeave", animate('.5s ease-out')),
      transition("inputLeave => inputEnter", animate('.5s ease-in'))
    ])
  ]
})
export class LoginComponent implements OnInit {

  constructor(
    private message: NzMessageService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private title : Title) { }

  userForm = {
    username: "",
    password: "",
  };

  loginBtnState = 'out';
  inputState = 'in';

  ngOnInit(): void {
    this.title.setTitle("登录");
    // 判断当前url查询字符串中是否有targetUser
    this.route.queryParams.forEach(params => {
      // 如果有查询字符串，则表示用户刚注册完
      if (params?.targetUser !== null) this.userForm.username = params.targetUser
    })
  }

  // 登录按钮动画状态改变
  toggleState(state: boolean) {
    this.loginBtnState = state ? 'in' : 'out';
  }
  // 输入框状态改变
  toggleInputState(state: boolean) {
    this.inputState = state ? 'inputEnter' : 'inputLeave'
  }
  //用户登录触发
  userLogin() {
    // 暂时先在这里判断用户输入是否为空，服务器端仍要判断。
    if (this.userForm.username == undefined || this.userForm.password == undefined ||
      this.userForm.username!.trim() === ""
      ||
      this.userForm.password!.trim() === ""
    ) {
      this.message.error("用户名或密码不得为空!")
    } else {
      // 执行登录功能
      this.authService.login(this.userForm)
        .then(auth => {
          switch (auth.statusCode) {
            // 登录成功!
            case 200:
              //保存用户信息
              this.authService.setLocalStorage(auth);
              // 导航到主页面
              this.router.navigate(['/core/home']);
              break;
            // 用户名不存在
            case 201:
              this.message.error(auth.errMsg);
              this.userForm.password = "";
              break;
            default:
              break;
          }
        })
    }
  }
}
