import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Subject } from 'rxjs';
import { Auth } from 'src/app/model/auth.model';
import { Item } from 'src/app/model/item.model';
import { Keyword } from 'src/app/model/keyword.model';

@Injectable({
    providedIn: 'root'
})
export class CoreService {


    constructor(private http : HttpClient,@Inject('BASE_URL')private  BASE_URL : string,private mesasge : NzMessageService){}

    search_api = "data/search/";

    getColl_api = "data/coll/";

    delColl_api = "data/del/";

    addColl_api = "data/add/";

    getUserHis_api = "user/history/"

    private header : HttpHeaders = new HttpHeaders({'Content-Type':'application/json'});


    public keywordRank : Keyword[] = [];

    public serviceItemList : Item[] = [];

    public stopLoadSubject : Subject<boolean> = new Subject();

    /**
     * 
     * 执行用户搜索
     * @param queryData 
     * @returns 
     */
    search(queryData : string[],isUpload : boolean) : Promise<Item[]>{
        return this.http
        .post(this.BASE_URL + this.search_api+`/${isUpload}`,queryData,{headers : this.header})
        .toPromise()
        .then( (res:any) => {
            this.keywordRank = res.count as Keyword[];
            return res.data as Item[]
        })
        .catch(err => {
            this.mesasge.error("网路错误请重试！");
            this.sendStop();
            return Promise.reject(err);
        })
    }

    getUserCollections(userId : string | null,header : any) : Promise<Item[]>{
        return this.http
        .get(this.BASE_URL+this.getColl_api+userId,{headers : header})
        .toPromise()
        .then( (res:any) => res.data as Item[])
        .catch(err => {
            this.mesasge.error("网路错误请重试！");
            return Promise.reject(err);
        });
    }


    // 封装前端页数处理
    getFirstPageData( totalNum : number , pageSize : number , totalData : any){
        let result = totalNum / pageSize;
        // 计算共有多少页
        let totalPage = result > Math.floor(result) ? Math.floor(result)+1 : Math.floor(result); 
        //第一页显示条数
        let firstPageSize:number = totalPage == 1 ? totalNum : pageSize;
        // 第一页显示的数据
        return Object.assign([],totalData.slice(0,firstPageSize));
    }


    getPageChangedData(totalNum : number , pageSize : number , totalData : any,pageIndex : number){
    let result = totalNum / pageSize;
    let totalPage = result > Math.floor(result) ? Math.floor(result)+1 : Math.floor(result);
        // 当前是最后一页
    if (pageIndex == totalPage){
        let lastTotal = (totalPage - 1) * pageSize;
        console.log(lastTotal)
        return totalData.slice(lastTotal);
    }
    // 当前不是最后一页
    else{
      let start = (pageIndex-1) * pageSize;
      let end = start + pageSize;
      return totalData.slice(start , end);
    }
    }
    // 发出停止加载
    sendStop(){
        this.stopLoadSubject.next(true);
    }

    // 删除收藏
    delCollection(id :string,header : any){
        return this.http.delete(this.BASE_URL+this.delColl_api+id,{headers : header})
        .toPromise()
        .then((res:any) => res.code)
        .catch(err => {
            this.mesasge.error("网络错误!");
            Promise.reject(err);
        })
    }

    addToCollection(id :string){
        if (localStorage.getItem("token") == null){
            return Promise.resolve(202);
        }
        let token:any = localStorage.getItem("token");
        let header = new HttpHeaders({'Content-Type':'application/json','Authorization':token});
        return this.http.get(this.BASE_URL+this.addColl_api+id,{headers : header})
        .toPromise()
        .then((res:any) => res.code)
        .catch(err => {
            this.mesasge.error("网络错误!");
            Promise.reject(err);
        })
    }


    /**
     * 获取用户的搜索记录
     */
    getUserHistory(username : string){
        return this.http.get(this.BASE_URL+this.getUserHis_api+username)
        .toPromise()
        .then( res => res)
        .catch(err => {
            this.mesasge.error("网络错误!");
            Promise.reject(err);
        });
    }
}