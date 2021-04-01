import { Component, NgModule, OnInit } from '@angular/core';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls:['./carousel.component.css']
})
export class CarouselComponent implements OnInit {
  public array:any = [1, 2, 3, 4];
  public effect:any = 'scrollx';
  constructor() { }

  ngOnInit(): void {
  }
}

