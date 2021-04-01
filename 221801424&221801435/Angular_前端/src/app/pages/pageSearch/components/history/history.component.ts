import { Component, OnInit } from '@angular/core';
import { NzDividerModule } from 'ng-zorro-antd/divider';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {
  public records:any[] = ['LSTM长短期记忆网络','RNN循环神经网络','Attention机制'];
  constructor() { }
  ngOnInit(): void {
  }

}
