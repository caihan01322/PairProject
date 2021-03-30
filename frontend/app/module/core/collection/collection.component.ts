import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CoreService } from '../../service/core.service';
import { Item } from 'src/app/model/item.model';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { NzModalService } from 'ng-zorro-antd/modal';
import { HttpHeaders } from '@angular/common/http';
import { Title } from '@angular/platform-browser';
@Component({
  selector: 'app-collection',
  templateUrl: './collection.component.html',
  styleUrls: ['./collection.component.css']
})
export class CollectionComponent implements OnInit {


  searchValue = '';
  visible = false;
  listOfData: Item[] = []
  listOfDisplayData: Item[] = [];
  isVisible = false;
  detailItem : Item = new Item();
  private AuthorizationHeader : any;
  
  constructor(private coreService: CoreService, 
    private route: ActivatedRoute, 
    private router: Router, 
    private message: NzMessageService,
    private modal: NzModalService,
    private title : Title
  
    ) { }


  ngOnInit(): void {
    this.title.setTitle(localStorage.getItem("username")+"的收藏页面")
    // 测试用
      this.route.params.forEach(param => {
        let requestUserId = param["userid"];
        if (localStorage!.getItem("userId") != requestUserId) {
          this.message.error("请先登录!")
          this.router.navigate(['/auth/login']);
        } else {
          let token:any = localStorage.getItem("token");
          this.AuthorizationHeader = new HttpHeaders({'Content-Type':'application/json','Authorization':token});
          this.fetchData();
        }
    });
  }


  fetchData() {
    // 获取数据
    this.coreService.getUserCollections(localStorage.getItem("userId"),this.AuthorizationHeader)
      .then(items => {
        Object.assign(this.listOfData, items);
        this.listOfDisplayData = [...items];
      })
  }

  reset(): void {
    this.searchValue = '';
    this.search();
  }

  search(): void {
    this.visible = false;
    this.listOfDisplayData = this.listOfData.filter(item => item.title.toLowerCase().indexOf(this.searchValue.toUpperCase()) !== -1);
  }

  toDetail(data: Item) {
      this.isVisible = true;
      this.detailItem = data;
  }

  handleOk(){
    this.isVisible = false;
  }

  delData(id: string) {
    this.modal.confirm({
      nzTitle: '删除',
      nzContent: '<b style="color: red;">确定要删除该收藏吗？</b>',
      nzOkText: '删除',
      nzOkType: 'primary',
      nzOkDanger: true,
      nzOnOk: () => {
        // 删除收藏。。。。。。
          this.coreService.delCollection(id,this.AuthorizationHeader)
          .then(code => {
            if (code == 200){
              this.message.success("删除成功");
              this.fetchData();
            }else{
              this.message.error("删除失败!");
            }
          })
      },
      nzCancelText: '取消',
      nzOnCancel: () => {}
    });

  }

  handleClose(){
    this.isVisible = false;
  }


}
