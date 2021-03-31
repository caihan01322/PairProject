
import { Component, OnInit, ViewChild } from '@angular/core';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzUploadChangeParam } from 'ng-zorro-antd/upload';
import { NzUploadFile } from 'ng-zorro-antd/upload';
import { NzMessageService } from 'ng-zorro-antd/message';
import {RequestService} from '../../../../services/request.service';
import {StorageService} from '../../../../services/storage.service';
import { NzButtonSize } from 'ng-zorro-antd/button';

interface ItemData {
  address: string;
  title: string;
  avatar: string;
  abstract: string;
  keyword:string;
  meeting_name: string;
  author:string;
}

@Component({
  selector: 'app-article-list',
  templateUrl:'./article-list.component.html',
  styleUrls: ['./article-list.component.css'],
})

export class ArticleListComponent implements OnInit {
  public fileUrl:any="https://www.mocky.io/v2/5cc8019d300000980a055e76"
  public checked:any = true;
  public info:any = '';
  public search_by:string = "标题";
  public search_byFile:string = "标题";
  public size: NzButtonSize = 'small';
  public pageNumber:any = 5;
  public allPageNumber:any=5;
  public pageSize:any = 1;
  public titleSearch:string = 'http://47.96.231.121/search_title';
  public keySearch:string = 'http://47.96.231.121/search_keyword';
  public authorSearch:string = 'http://47.96.231.121/search_author';
  public Data:any[]=[];
  public IssearchBy:boolean=false;
  public articles_id:any[]=[];

  public Judge:any;
  public data:any[] = [];
  loadData(pi: number): void {
    this.data = [
      
    ]
      
  }
  


  @ViewChild("menu4") dropmenu:any;
  tabs:any = [
    {
      name: '已有数据查询入口',
      icon: 'apple'
    },
    {
      name:'txt文件批量查询',
      icon:'reddit'
    }
  ];

  optionList = [
    { label: '标题', value: '标题'},
    { label: '作者', value: '作者'},
    { label: '关键字', value: '关键字'}
  ];
  selectedValue = { label: '标题', value: '标题'};
  // tslint:disable-next-line:no-any
  compareFn = (o1: any, o2: any) => (o1 && o2 ? o1.value === o2.value : o1 === o2);

  log(value: { label: string; value: string }): void {
    this.search_byFile=value.value;
    console.log(value);
    console.log(this.search_byFile);
  }

  constructor(private msg: NzMessageService,private request:RequestService,private storage:StorageService,private message: NzMessageService) {}

  handleChange({ file, fileList }: NzUploadChangeParam): void {
    const status = file.status;
    if (status !== 'uploading') {
      console.log(file, fileList);
    }
    if (status === 'done') {
      this.msg.success(`${file.name} file uploaded successfully.`);
    } else if (status === 'error') {
      this.msg.error(`${file.name} file upload failed.`);
    }
  }

  ngOnInit(): void {
    this.loadData(1);
  }

  getPageChangedData(){
    let result = this.allPageNumber / this.pageSize;
    let totalPage = result > Math.floor(result) ? Math.floor(result)+1 : Math.floor(result);
        // 当前是最后一页
    if (this.pageSize == totalPage){
        let lastTotal = (totalPage - 1) * this.pageNumber;
        this.pageSize=totalPage;
        this.pageNumber=this.allPageNumber-lastTotal;
        console.log(this.pageSize)

        this.search();
    }
    // 当前不是最后一页
    else{
      console.log(this.pageSize)
      
      this.search();
    }
}




  search():void{
    if(this.info==''){
      this.message.info('搜索框不能为空')
    }
    else if(this.search_by=='标题'){

      this.IssearchBy=true;
      this.doTitleSearch();
    }else if(this.search_by=='作者'){

      this.IssearchBy=true;
      this.doAuthorSearch();
    }else if(this.search_by=='关键字'){

      this.IssearchBy=true;
      this.doKeySearch();
    }else{
      this.message.info('error:索引错误')
      this.pageNumber=5;
      this.search_by=='';
    }
  }

  ready_search(){
    this.pageSize=1;
    console.log(this.pageSize)
    this.storage.remove("articles_id")
    this.search();
  }

  doKeySearch():void{
    var data2 = {
      'keyword':this.info,
      'page_info':{
        'page_size':this.pageSize,
        'page_num':this.pageNumber,
      }
    };
    this.request.getData(this.keySearch,data2).then((d:any)=>{
     this.Data=d.data;
     this.allPageNumber=d.data['allPageNum']
     console.log(this.allPageNumber)
      if(this.allPageNumber==0){
        this.message.info("未查询到论文")
        this.IssearchBy=false;
        this.data=[];
        this.info="";
      }
      else if(d.data['data'].length==0){
        this.message.info("未查询到论文")
        this.IssearchBy=false;
        this.data=[];
        this.info="";
      }

      else{
        console.log(d)
        console.log(d.data['data'])
        this.data=d.data['data'];
        //存储id
        for (let index = 0; index < this.data.length; index++) {
          this.articles_id.push(d.data['data'][index]['id']);
          
        }
        this.storage.set("articles_id",this.articles_id);
      }
      
      
    });
  }

  doAuthorSearch():void{
    var data3 = {
      'author':this.info,
      'page_info':{
        'page_size':this.pageSize,
        'page_num':this.pageNumber,
      }
    };
    this.request.getData(this.authorSearch,data3).then((d:any)=>{
      this.Data=d.data;
     this.allPageNumber=d.data['allPageNum']
     
      if(this.allPageNumber==0){
        this.message.info("未查询到论文");
        this.IssearchBy=false;
        this.data=[]
        this.info="";
      }
      else if(d.data['data'].length==0){
        this.message.info("未查询到论文");
        this.IssearchBy=false;
        this.data=[];
        this.info="";
      }

      else{
        console.log(d)
        this.data=d.data['data'];
        //存储id
        for (let index = 0; index < this.data.length; index++) {
          this.articles_id.push(d.data['data'][index]['id']);
          
        }
        this.storage.set("articles_id",this.articles_id);
      }
      
      
    });

  }

  doTitleSearch():void{
    var data1 = {
      'title':this.info,
      'page_info':{
        'page_size':this.pageSize,
        'page_num':this.pageNumber,
      }
    };
    // console.log(data);
    this.request.getData(this.titleSearch,data1).then((d:any)=>{
      this.Data=d.data;
      console.log(d.data['allPageNum'])
     this.allPageNumber=d.data['allPageNum']
      if(this.allPageNumber==0){
        this.message.info("未查询到论文");
        this.IssearchBy=false;
        this.data=[]
        this.info="";
      }
      else if(d.data['data'].length==0){
        this.message.info("未查询到论文");
        this.IssearchBy=false;
        this.data=[];
        this.info="";
      }
      else{
        
        console.log(d)
        console.log(d.data['data'])
        this.data=d.data['data'];
        //存储id
        for (let index = 0; index < this.data.length; index++) {
          this.articles_id.push(d.data['data'][index]['id']);
          
        }
        this.storage.set("articles_id",this.articles_id);
        
      }
      
      
    });
  }


  star(index:any){
    let article_id=this.storage.get("articles_id").slice((index-this.pageNumber));
    let user_id=this.storage.get('userInfo')['userInfo']['data']['userid']
    console.log(index)
    console.log(article_id[0])
    console.log(user_id)
    let Star=this.request.getData("http://47.96.231.121/auth/star_article",{'user_id':user_id,'article_id':article_id[0]});
    Star.then((data:any)=>{
      console.log(data)
      if(data.status==200){
        this.message.create('success','收藏成功');
      }
      if(data.status==1015){
        this.message.create('error','已收藏');
      }
      if(data.status==1008){
        this.message.create('error','失败');
      }
      
    })
  }
  delete(index:any){
    let article_id=this.storage.get("articles_id").slice((index-this.pageNumber));
    console.log(index)
    console.log(article_id[0])
    let Delete=this.request.getData("http://47.96.231.121/del_article",{'article_id':article_id[0]});
    Delete.then((data:any)=>{
      console.log(data)
      if(data.status==200){
        this.message.create('success','删除论文成功(违背功能，能够删除库里信息)');

        this.ready_search();
      }
      if(data.status==1008){
        this.message.create('error','删除失败');
      }  
    })
  }


  setPageData(response:any){
    // console.log(response);
    console.log(response.data);
    this.Data=response.data;
    console.log(this.Data);
  }

  changeSearchBy(newSearchBy:string):void{
    this.search_by = newSearchBy;
  }
  changeSearchByFile(newSearchByFile:string):void{
    this.search_byFile = newSearchByFile;
  }

  clearData(){
    console.log(this.data)
    this.data=[];
    console.log("112323")
  }

  // 指示变量
uploaded = false;

/*
	上传文件后回调，后端响应数据在info.file.response中
*/
handleFileUpload(info: NzUploadChangeParam): void {
    // 文件上传中
    if (info.file.status !== 'uploading') {
      console.log(info.file, info.fileList);
    }
    // 文件上传后
    if (info.file.status === 'done') {
      // 更新指示变量，表示当前已有文件上传 
      this.uploaded = true;
      this.msg.success(`${info.file.name} file uploaded successfully`);
    } else if (info.file.status === 'error') {
      this.msg.error(`${info.file.name} file upload failed.`);
    }
  }

/*
	文件上传前回调，必须用 => 
*/
onBeforeUpload = (file: NzUploadFile, _fileList: NzUploadFile[]) => {
      // 当前无已上传文件，则允许上传
      if (!this.uploaded){
        return true;
      }else{
        // 否则拒绝上传
        this.msg.error("最多只能上传一个文件!");
        return false;
      }
  }

/*
	文件被删除时回调
*/
onRemove = (file: NzUploadFile) =>{
    // 重置指示变量
    this.uploaded = false;
    return true;
  }
}


