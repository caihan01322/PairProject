import { Component, OnInit, ViewChild } from '@angular/core';
import { NzDropDownModule } from 'ng-zorro-antd/dropdown';

@Component({
  selector: 'app-drop-menu',
  templateUrl:'./drop-menu.component.html',
  styles:[
    `
      nz-button-group {
        margin-right: 8px;
      }
    `
  ]
})

export class DropMenuComponent implements OnInit {

  public search_by:string = "标题";
  @ViewChild("menu4") dropmenu:any;

  constructor() { }

  ngOnInit(): void {
  }

  changeSearchBy(newSearchBy:string):void{
    this.search_by = newSearchBy;
  }

}
