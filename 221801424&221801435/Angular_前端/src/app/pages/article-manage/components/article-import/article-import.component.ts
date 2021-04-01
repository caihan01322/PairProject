import { Component, OnInit } from '@angular/core';
import { NzSelectModule } from 'ng-zorro-antd/select';
@Component({
  selector: 'app-article-import',
  templateUrl: './article-import.component.html',
  styleUrls: ['./article-import.component.css']
})
export class ArticleImportComponent implements OnInit {
  public selectedValue:any = null;

  constructor() { }

  ngOnInit(): void {
  }

}
