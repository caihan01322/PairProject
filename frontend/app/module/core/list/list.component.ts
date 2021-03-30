import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzNotificationService } from 'ng-zorro-antd/notification';
import { fromEvent, Subscription } from 'rxjs';
import { debounceTime, distinctUntilChanged, pluck } from 'rxjs/operators';
import { Item } from 'src/app/model/item.model';
import { Keyword } from 'src/app/model/keyword.model';
import { CoreService } from '../../service/core.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  constructor(private coreService : CoreService,private message : NzMessageService,private notification: NzNotificationService,private title : Title) { 
    coreService.serviceItemList.forEach(item => {
      this.titleoptions.push(item.title);
      item.keyword.forEach(k => {
        if (!this.kwdoptions.includes(k)){
          this.kwdoptions.push(k);
        }
      })
    });
    this.filteredOptions = this.titleoptions;
  }

  itemsList : Item[] = [];
  localKwdRank : Keyword[] = [];
  currentPageList : Item[] = [];
  searchPH = ""
  resultNum = 0;
  currentPage = 1;
  pageSize = 10;
  keyword = "";
  hasKeyword = false;
  currentStart = 0;
  currentEnd = 0;
  totalPage = 0;
  searchType = "title";
  searchValue = "";
  searchTip = "标题";
  filteredOptions: string[] = [];
  titleoptions:string[] = [];
  kwdoptions : string[] = [];
  @ViewChild("inputRef") searchInput : ElementRef | undefined;
  inputSubscription : Subscription = new Subscription();


  ngOnInit(): void {
    this.title.setTitle("搜索结果")
    this.fetchListData(); 
  }

  
  ngAfterViewInit():void{
    this.inputSubscription = fromEvent(this.searchInput?.nativeElement,'input')
    .pipe(
      debounceTime(200),
      distinctUntilChanged(),
      pluck('target','value')
    ).subscribe((value:any) => {
      
        this.changeOps(value);
    })
  }

  changeOps(value : string){
    if (this.searchType == 'title'){

      this.filteredOptions = this.titleoptions.filter(option => {
        return option.toLowerCase().startsWith(value.toLowerCase());
      });
    }
    else{
    this.filteredOptions = this.kwdoptions.filter(option => option.toLowerCase().startsWith(value.toLowerCase()));
    }
  }

  ngOnDestory(){
    if (this.inputSubscription != null){
      this.inputSubscription.unsubscribe();
    }
  }

  // 初始化数据
  setInitData(){
    this.searchPH = `在${this.resultNum}条结果中搜索`;
    // 从服务中获得关键词排行
    this.localKwdRank = this.coreService.keywordRank;
    this.currentPageList =Object.assign([],
    this.coreService.getFirstPageData(this.resultNum,this.pageSize,this.itemsList));
    if (this.resultNum > this.pageSize){
        this.currentStart = 1;
        this.currentEnd = this.pageSize;
    }
  }

  fetchListData(){
      this.resultNum = this.coreService.serviceItemList.length;
      this.itemsList = this.coreService.serviceItemList;
      this.totalPage = Math.round(this.resultNum / this.pageSize);
      this.setInitData();
    };

    

    copyLink(link:any){
        var hidddenInput:any = document.getElementById("copyInput");
        hidddenInput.value = link;
        hidddenInput.select();
        document.execCommand('copy');
        this.message.success("链接已复制");
    }


    handlePageIndexChange(pageIndex : any){
          this.currentPageList = Object.assign(
            [],
            this.coreService.getPageChangedData(this.resultNum,this.pageSize,this.itemsList,pageIndex)
      )
      if(this.currentPage != this.totalPage){
          this.currentStart = this.pageSize * (this.currentPage-1) + 1;
          this.currentEnd = this.currentStart + this.pageSize - 1;
      }else{
          this.currentStart = (this.totalPage-1)*this.pageSize + 1;
          this.currentEnd = this.resultNum;
      }
      window.scrollTo(0,0);
    }


    toSearch(param : string){
      this.itemsList = []
      // 搜索关键词
      if (this.searchType == "keyword"){
        this.searchTip = "关键词";
        this.searchValue = param;
        this.coreService.serviceItemList.forEach(item => {
          for(let k of item.keyword){
            if (k.includes(param)){
              this.itemsList.push(item);
              break;
            }
          }
        })
      }else{
        // 搜索标题
        this.searchTip = "标题";
        this.coreService.serviceItemList.forEach(item => {
          if (item.title.includes(param)){
            this.itemsList.push(item);
          }
        })
      }
      this.resultNum = this.itemsList.length;
      this.currentPage=1;
      this.setInitData();
      this.hasKeyword = true;
      this.keyword = param;
    }
    
    toKwdSearch(kwd : string){
        this.searchType = "keyword";
        this.changeOps(kwd);
        this.toSearch(kwd)
    }

    // 回到所有结果
    backAll(){
      this.itemsList = this.coreService.serviceItemList;
      this.resultNum = this.itemsList.length;
      this.hasKeyword = false;
      this.searchValue = "";
      this.searchType = "title";
      this.currentPage=1;
      this.setInitData();
    }


    // 在结果中搜索，按关键词或标题搜索
    searchResult(){
      this.toSearch(this.searchValue.trim());
    }

    // 选择框改变时
    selectChange(value : any){
      this.searchValue = "";
      if (value == 'title'){
          this.filteredOptions = Object.assign([],this.titleoptions);
      }else{
          this.filteredOptions = Object.assign([],this.kwdoptions);
      }
    }

    toCollect(id : string){
        this.coreService.addToCollection(id)
        .then(code => {
          // 收藏成功
          if (code == 200){
            this.notification.blank(
              '收藏成功',
              `已成功将论文信息项存入收藏夹，<a href="/#/core/collections/${localStorage.getItem("userId")}" target="_blank">点击查看</a>`
            )
          }
          // 已收藏过
          else if(code == 201){
            this.notification.blank(
              '已收藏',
              `您已经收藏过该论文，<a href="/#/core/collections/${localStorage.getItem("userId")}" target="_blank">点击查看</a>`
            )
          }
          // 用户未登录
          else if(code == 202){
            this.notification.blank(
              '您还未登录！',
              `只有成为我们的用户才能享受收藏功能！<a href="/#/auth/login">点击登录</a>`
            )
          }else{
            this.message.error("收藏失败!");
          }
        })
    }
}
