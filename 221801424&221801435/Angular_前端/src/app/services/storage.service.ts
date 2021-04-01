import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  set(key:any,value:any){
    localStorage.setItem(key,JSON.stringify(value))
  }

  get(key:any){
    let temp:any=localStorage.getItem(key)

    return JSON.parse(temp)
  }
  remove(key:any){
    localStorage.removeItem(key)
  }
}
