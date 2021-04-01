import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TendencyComponent } from './components/tendency/tendency.component';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { Routes } from '@angular/router';
import { TopTrendComponent } from './components/top-trend/top-trend.component';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzStepsModule } from 'ng-zorro-antd/steps';

export const routes: Routes = [
    {
      path:'page-trend/tendency',component:TendencyComponent
    },  
]

@NgModule({
  declarations: [TendencyComponent,TopTrendComponent],
  imports: [
    CommonModule,NzLayoutModule,NzButtonModule,NzStepsModule
  ],
})
export class PageTrendModule { }
