import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import * as echarts from 'echarts';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { IconsProviderModule } from './icons-provider.module';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzMenuModule } from 'ng-zorro-antd/menu';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzCarouselModule } from 'ng-zorro-antd/carousel';
import { PageTrendModule } from './pages/page-trend/page-trend.module';
import { ArticleManageModule } from './pages/article-manage/article-manage.module';
import { NzStepsModule } from 'ng-zorro-antd/steps';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { NzListModule } from 'ng-zorro-antd/list';
import {UserManageModule}from './pages/user-manage/user-manage.module';
import { NzTabChangeEvent, NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import {WelcomeModule} from './pages/pageSearch/welcome.module';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { HttpClient} from '@angular/common/http';
import {HttpClientJsonpModule }from '@angular/common/http'
import {RequestService} from './services/request.service'
import { StorageService } from './services/storage.service';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
registerLocaleData(zh);

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    NzTabsModule,
    NzIconModule,
    NzCheckboxModule,
    WelcomeModule,
    NzStepsModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    IconsProviderModule,
    NzLayoutModule,
    NzMenuModule,
    NzSelectModule,
    NzButtonModule,
    NzDropDownModule,
    NzCarouselModule,
    NzLayoutModule,
    ArticleManageModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    IconsProviderModule,
    NzLayoutModule,
    NzMenuModule,
    NzSelectModule,
    NzButtonModule,
    NzDropDownModule,
    ArticleManageModule,
    UserManageModule,
    PageTrendModule,
    NzUploadModule,
    NzPaginationModule,
    NzListModule,
    NzModalModule,
    HttpClientJsonpModule,
    NzPopoverModule
    

  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN },NzMessageService,HttpClient,RequestService,StorageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
