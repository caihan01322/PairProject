import { NgModule } from '@angular/core';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
import { WelcomeRoutingModule } from './welcome-routing.module';
import { FormsModule } from '@angular/forms';
import { CarouselComponent } from './components/carousel/carousel.component';
import { WelcomeComponent } from './welcome.component';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { SearchComponentComponent } from './components/search-component/search-component.component';
import { SearchInputComponent } from './components/search-input/search-input.component';
import { DropMenuComponent } from './components/drop-menu/drop-menu.component';
import { NzCarouselModule } from 'ng-zorro-antd/carousel';
import { NzImageModule } from 'ng-zorro-antd/image';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { HistoryComponent } from './components/history/history.component';
import { from } from 'rxjs';


@NgModule({
  imports: [WelcomeRoutingModule, NzButtonModule,NzInputModule,NzDropDownModule,FormsModule,NzCarouselModule,NzImageModule,NzDividerModule],
  declarations: [WelcomeComponent, SearchComponentComponent, SearchInputComponent, DropMenuComponent,CarouselComponent,HistoryComponent],
  exports: [WelcomeComponent]
})
export class WelcomeModule { }
