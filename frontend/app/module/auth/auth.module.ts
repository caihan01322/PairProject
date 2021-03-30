import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule} from '@angular/forms';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { LoginRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { AuthComponent } from './auth.component';
import { NzCardModule } from 'ng-zorro-antd/card';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import {NzButtonModule} from 'ng-zorro-antd/button';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN } from 'ng-zorro-antd/i18n';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import {AuthService} from '../service/auth.service';
import {NzMessageService} from 'ng-zorro-antd/message';
import { SharedModule } from '../share/share.module';
@NgModule({
  declarations: [LoginComponent, RegisterComponent, AuthComponent],
  imports: [
    CommonModule,
    LoginRoutingModule,
    NzCardModule,
    NzIconModule,
    NzDividerModule,
    NzGridModule,
    NzInputModule,
    FormsModule,
    NzButtonModule,
    NzCheckboxModule,
    SharedModule
  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN },AuthService,NzMessageService],
})
export class AuthModule { }
