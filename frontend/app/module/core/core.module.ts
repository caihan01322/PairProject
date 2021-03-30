import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CoreRoutingModule } from './core-routing.module';
import { CoreComponent } from './core.component';
import { HomeComponent } from './home/home.component';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { zh_CN } from 'ng-zorro-antd/i18n';
import {NzMessageService} from 'ng-zorro-antd/message';
import { NzIconModule } from 'ng-zorro-antd/icon';
import {NzButtonModule} from 'ng-zorro-antd/button';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { NzTypographyModule } from 'ng-zorro-antd/typography';
import { NzPopoverModule } from 'ng-zorro-antd/popover';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzModalModule, NzModalService } from 'ng-zorro-antd/modal';
import { CoreService } from '../service/core.service';
import {FormsModule} from '@angular/forms';
import { ListComponent } from './list/list.component';
import { TruncatePipe } from 'src/app/pipe/truncate.pipe';
import { TruncateAuthorPipe } from 'src/app/pipe/truncate.author.pipe';
import { NzListModule } from 'ng-zorro-antd/list';
import { NzToolTipModule } from 'ng-zorro-antd/tooltip';
import { NzPaginationModule } from 'ng-zorro-antd/pagination';
import { SharedModule } from '../share/share.module';
import { NzBackTopModule } from 'ng-zorro-antd/back-top';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzEmptyModule } from 'ng-zorro-antd/empty';
import { CollectionComponent } from './collection/collection.component';
import { NzAutocompleteModule } from 'ng-zorro-antd/auto-complete';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { NzNotificationModule } from 'ng-zorro-antd/notification';
import { DataComponent } from './data/data.component';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { NzAlertModule } from 'ng-zorro-antd/alert';
import { HighchartsChartModule } from 'highcharts-angular';
import { DataService } from '../service/data.service';
import { NzStatisticModule } from 'ng-zorro-antd/statistic';
@NgModule({
  declarations: [CoreComponent, HomeComponent, ListComponent,TruncatePipe,TruncateAuthorPipe, CollectionComponent, DataComponent],
  imports: [
    CommonModule,
    CoreRoutingModule,
    NzIconModule,
    NzButtonModule,
    NzGridModule,
    NzInputModule,
    NzDividerModule,
    NzUploadModule,
    NzTypographyModule,
    NzPopoverModule,
    NzTabsModule,
    NzModalModule,
    NzTableModule,
    FormsModule,
    NzListModule,
    NzToolTipModule,
    SharedModule,
    NzPaginationModule,
    NzBackTopModule,
    NzSelectModule,
    NzEmptyModule,
    NzAutocompleteModule,
    NzDropDownModule,
    NzNotificationModule,
    NzSpinModule,
    NzAlertModule,
    NzStatisticModule,
    HighchartsChartModule,
  ],
  providers: [{ provide: NZ_I18N, useValue: zh_CN },
    NzMessageService,CoreService,NzModalService,NzNotificationService,DataService],
})
export class CoreModule { }
