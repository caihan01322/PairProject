import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzButtonModule } from 'ng-zorro-antd/button';
import {ArticleListComponent} from './components/article-list/article-list.component';
import {ArticleImportComponent}from './components/article-import/article-import.component';
import { FormsModule } from '@angular/forms';
import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzMessageModule } from 'ng-zorro-antd/message';
@NgModule({
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
    NzMessageModule
  ],
  declarations: [ArticleListComponent,ArticleImportComponent]
  
  
})

export class ArticleManageModule { }
