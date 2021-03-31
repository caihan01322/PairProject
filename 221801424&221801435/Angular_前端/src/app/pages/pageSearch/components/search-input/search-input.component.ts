import { Component, Input, OnInit } from '@angular/core';
import { NzInputModule } from 'ng-zorro-antd/input';

@Component({
  selector: 'app-search-input',
  templateUrl:'./search-input.component.html',
  styleUrls: ['./search-input.component.css']
})
export class SearchInputComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  search():void{
    alert('搜索功能测试');
  }

}
