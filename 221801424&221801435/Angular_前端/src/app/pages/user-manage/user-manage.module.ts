import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserStarComponent } from './components/user-star/user-star.component';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzAvatarModule } from 'ng-zorro-antd/avatar';
import { NzRadioModule } from 'ng-zorro-antd/radio';
import { LoginComponent } from './components/login/login.component'
import { RegisterComponent } from './components/register/register.component';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';

@NgModule({
  declarations: [UserStarComponent, UserInfoComponent,LoginComponent,RegisterComponent],
  imports: [
    CommonModule,
    NzTabsModule,
    NzIconModule,
    NzButtonModule,
    NzCheckboxModule,
    FormsModule,
    NzSelectModule,
    NzInputModule,
    NzDropDownModule,
    NzUploadModule,
    NzPaginationModule,
    NzListModule,
    NzModalModule,
    NzAvatarModule,
    NzRadioModule,ReactiveFormsModule,
    FormsModule,
    NzDrawerModule
  ],
  exports:[
    LoginComponent
  ]
})
export class UserManageModule { }
