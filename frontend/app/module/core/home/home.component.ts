import { animate, state, style, transition,trigger } from '@angular/animations';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { NzUploadChangeParam } from 'ng-zorro-antd/upload';
import { Item } from 'src/app/model/item.model';
import { CoreService } from '../../service/core.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations:[
    trigger("clearTrigger",[
      state("show",style({
          transform:'scale(1.0)'
      })),
      state('hide',style({
          transform:'scale(0)'
      })),
      transition("show => hide",animate(".5s ease-out")),
      transition("hide => show",animate(".5s ease-in"))
    ])
  ]
})
export class HomeComponent implements OnInit {
  filteredOptions: any[] = []
  
  // 对话框是否加载中
  isOkLoading = false;
  // 对话框是否可视
  isVisible = false;
  // 某页显示的内容
  uploadContent = [];
  // 上传按钮是否加载
  uploadBtnLoading = false;
  // 当前页码以及每页显示项数
  currentPage = 1;
  pageSize = 3;
  // 总数据
  totalData = [];
  // 总页数
  totalPage = 0;
  // 搜索输入框内容
  searchValue :string = "";

  // 搜索按钮加载中
  searchLoading = false;

  // 
  hasCurrentUser = false;

  currentUserName:any = "";
  
  currentUserId:any = "";

  // 清空记录动画状态
  clearState="hide";

  options:any[] = [];


  constructor(private msg : NzMessageService,
    private coreService : CoreService,
    private router : Router,
    private title : Title,
    private modalService: NzModalService,
    private http:HttpClient,
    @Inject("BASE_URL")private BASE_URL : string)
    {}
  
  ngOnInit(): void {
    this.coreService.stopLoadSubject.subscribe(b => this.searchLoading = false);
    this.title.setTitle("首页");
    if (localStorage.getItem("token") != null){
      this.hasCurrentUser = true;
      this.currentUserName = localStorage.getItem("username");
      this.currentUserId = localStorage.getItem("userId");
      // 请求用户的搜索历史
      this.coreService.getUserHistory(this.currentUserName)
      .then((res:any) => {
        this.options = res;
        this.filteredOptions = this.options
      });
    }
  
  }

  // 用户点击搜索
  onSearchClick(){
    if (this.searchValue.trim().length < 3){
      this.msg.error("搜索内容必须大于三个字符!")
    }else{
      this.searchForItems([this.searchValue],false);
    }
  }


  /**
   * 论文题目批量导入
   * @param info 
   */
  handleChange(info: NzUploadChangeParam): void {
    if (info.file.status === 'uploading'){
        // 按钮进入加载
        this.toggleBtnLoading(true);
    }
    if (info.file.status === 'done') {
      // 先判断数据格式是否符合要求   
      let responseData = info.file.response.result;
      if (responseData.code !== 200){
        this.msg.error(responseData.msg);
        this.toggleBtnLoading(false);
      }
      else{
      // 获得返回的总数据，['title1','title2',..]
      this.totalData = Object.assign([],this.totalData,responseData.data)    
      // 上传成功后显示对话框
      this.toggleVisible(true)
      // 上传按钮加载中
      this.toggleBtnLoading(true);
        // 第一页显示的数据
        this.uploadContent =Object.assign([],
          this.coreService.getFirstPageData(this.totalData.length,this.pageSize,this.totalData));   
      }
    } else if (info.file.status === 'error') {
      this.msg.error('上传失败，请重试!');
      this.toggleBtnLoading(false);
    }
  }

  toggleBtnLoading(state : boolean){
      this.uploadBtnLoading = state;
  }

  toggleVisible(state : boolean){
    this.isVisible = state;
  }

  // 对话框关闭
  handleCancel(){
    this.msg.info("取消导入");
    // 清空数据
    this.uploadContent = [];
    this.totalData = [];
    this.toggleVisible(false)
    this.toggleBtnLoading(false)
  }
  // 对话框确认
  handleOk(){
    // 执行搜索
    this.searchForItems(this.totalData,true);
  }

  handlePageIndexChange(pageIndex : any){

    this.uploadContent = Object.assign([],
      this.coreService
      .getPageChangedData(this.totalData.length,this.pageSize,this.totalData,pageIndex));
    }


    /**
     * 点击搜索
     * @param queryData 
     * @param isUpload 
     */
  searchForItems(queryData : string[],isUpload : boolean){
    if (isUpload){
      this.isOkLoading = true;
    }
    this.searchLoading = true;
    // 向服务器发起请求，获取爬取的数据
    this.coreService.search(queryData,isUpload)
    .then((itemArr:Item[]) => {
      // 获得每个查询项组成的对象列表
      this.coreService.serviceItemList = itemArr;
      // itemArr将要传给其他组件。。。。
      // 考虑将数据保存到Service中，在列表页面中向服务获取数据
      if (isUpload){
        // 关闭对话框，准备跳转
        this.isOkLoading = false;
        this.toggleVisible(false)
      }
      this.router.navigate(['/core/list']);
    })
  }


  logout(){
    this.modalService.confirm({
      nzTitle: 'Confirm',
      nzContent: '您确定要登出吗？',
      nzOkText: '确定',
      nzCancelText: '取消',
      nzOnOk: () => {
        this.http.delete(this.BASE_URL+"auth/logout")
        .toPromise()
        .then(res => {
            localStorage.removeItem("userId");
            localStorage.removeItem("username");
            localStorage.removeItem("token");
            this.msg.success("已登出");
            window.location.reload();
        })
      }
    });
  }

  onChange(value: string): void {
    this.filteredOptions = this.options.filter(option => option.toLowerCase().indexOf(value.toLowerCase()) !== -1);
  }

  // 输入框聚焦，显示按钮
  triggerState(state : boolean){
    if (this.options.length == 0){
      this.clearState = "hide"
    }else{
      this.clearState = state ? "show" : "hide"
    }
  }

  // 清空搜索历史
  clearHistory(){
      let token:any = localStorage.getItem("token")
      this.http.get(this.BASE_URL+"user/clear",{
        headers:{
          'Content-Type':'application/json',
          'Authorization':token
        }
      })
      .toPromise()
      .then(res => {
        this.options = [];
        this.filteredOptions = [];
        this.msg.success("成功清空搜索记录!");
      })
  }
}

