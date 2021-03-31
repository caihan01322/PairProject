import { Component, OnInit } from '@angular/core';
import { SearchInputComponent } from '../search-input/search-input.component';
import { CarouselComponent } from '../carousel/carousel.component';
import {} from '../drop-menu/drop-menu.component';
import { NzCarouselModule } from 'ng-zorro-antd/carousel';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { HistoryComponent } from '../history/history.component';

@Component({
  selector: 'app-search-component',
  templateUrl: './search-component.component.html',
  styleUrls: ['./search-component.component.css']
})

export class SearchComponentComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
