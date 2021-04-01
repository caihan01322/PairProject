|这个作业属于哪个课程|[2021春软件工程实践\|W班(福州大学)](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice)|
| -- | -- |
|这个作业要求在哪里|[作业要求](https://edu.cnblogs.com/campus/fzu/2021SpringSoftwareEngineeringPractice/homework/11890)|
|结对学号|221801433|
||221801407|
|这个作业的目标| 用web技术来实现原型中的功能 |
|其他参考文献| 无 |
[toc]
### 项目介绍
#### 代码实现
 1. 框架使用:使用的是thinkphp5框架来实现构建，
    2. 前端：采用HTML、CSS、JS的形式来编写代码，并且用Vue渲染Element，也用Ajax来进行POST对url发送请求
    3. 后端：采用think php5框架，通过PHP语言来进行数据库操作。
    4. 数据库用的是phpyadmin
#### 项目主要代码及说明
1. 首页搜索栏
搜索拦HTML部分，可以通过el-select下拉框来选择所要搜索的部分来z
```
<div class="search">
            <el-row>
              <el-col :span="20">
                <el-input placeholder="请输入小写字母" icon="search" v-model="devfilter" class="search-input" clearable>
                </el-input>
              </el-col>
              <el-col :span="4">
                <el-select v-model="value" placeholder="请选择">
                  <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value">
                  </el-option>
                </el-select>
              </el-col>
            </el-row>

</div>
```
搜索栏Vue部分，根据搜索栏的值来判断对哪一部分进行搜索来缩小论文的范围，更容易的找到需要的论文
```
watch: {
      devfilter: function (val, oldVal) {
        console.log(this.value);
        if (this.value == 'title') {
          this.tableData = this.sourceData.filter(dataNews => {
            return Object.keys(dataNews).some(key => {
              return String(dataNews['title']).toLowerCase().indexOf(val) > -1
            })
          });
        } else if (this.value == 'abstract') {
          this.tableData = this.sourceData.filter(dataNews => {
            return Object.keys(dataNews).some(key => {
              return String(dataNews['abstract']).toLowerCase().indexOf(val) > -1
            })
          });
        } else if (this.value == 'keyword') {
          this.tableData = this.sourceData.filter(dataNews => {
            return Object.keys(dataNews).some(key => {
              return String(dataNews['keyword']).toLowerCase().indexOf(val) > -1
            })
          });
        }

      }
```
列表部分提供了标题、摘要、关键字、发布日期四项属性来显示论文信息
```
<el-table :data="tableData.slice((currpage-1)*4,currpage*4)" stripe style="width: 100%"
            @cell-click="handleMainCellClick">
            <el-table-column prop="title" label="标题·" width="180">
            </el-table-column>
            <el-table-column prop="abstract" label="摘要">
            </el-table-column>
            <el-table-column prop="keyword" label="关键字" width="180">
            </el-table-column>
            <el-table-column prop="releasetime" label="发布日期" width="180">
            </el-table-column>
</el-table>
```
2. 概况页面
HTML部分
通过动图形式来呈现Top10和
```
el-tab-pane label="概况" name="third">
        <div id="top" style="width: 280px;height: 800px; float:right;">
          <el-table :data="wordTableData" stripe @cell-click="handleCellClick" style="width: 100%">
            <el-table-column prop="hot" label="TOP 10" align="center">
            </el-table-column>
          </el-table>
        </div>
        <div id="CVPR" style="height: 400px;width: 900px;"></div>
        <div id="ECCV" style="height: 400px;width: 900px;"></div>
        <div id="ICCV" style="height: 400px;width: 900px;"></div>

      </el-tab-pane>
```
3. 编辑页面
编辑页面论文列表部分，列表显示论文题目、摘要、关键词可以通过按钮来对论文进行编辑和删除，通过分页器来让列表更加直观
```
<div id = "table" style = "margin-top:20px">`
        <template>
            <el-table :data="tableData.slice((currPage-1)*limitCount,currPage*limitCount)" border style = "width:100%;" >
                <el-table-column fixed prop = "title" label="论文题目" align="center" min-width="20%"></el-table-column>
                <el-table-column fixed prop = "abstract" label="论文摘要"  align="center" min-width="30%">
                    <template slot-scope="scope" >
                        <span>{{scope.row.abstract|ellipsis}}</span>
                    </template>
                </el-table-column>
                <el-table-column fixed prop = "keyword" label="论文关键词" align="center" min-width="30%">
                    <template slot-scope="scope" >
                        <span>{{scope.row.keyword|ellipsis}}</span>
                    </template>
                </el-table-column>
                <el-table-column fixed prop = "url" label="论文链接" align="center" min-width="10%">
                    <template slot-scope="scope">
                        <span class="span-text" v-if="scope.row.link!== undefined && scope.row.link.length >=1">
                            <a link :href="scope.row.link" target="_blank">原文链接</a>
                        </span>
                    </template>
                </el-table-column>
                <el-table-column fixed prop = "operation" label="操作" align="center" min-width="15%">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="primary" size="small">编辑</el-button>
                        <el-button @click="handleDelete(scope.row)" type="danger" size="small">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </template>
    </div>

    <div id = "page" style = "margin-top: 20px;">
        <el-pagination
            @current-change="currentChange"
            :current-page= "currPage"
            layout = "prev, pager, next"
            :total="{$count}"
            :page-size = "limitCount"
            style="text-align: center"
        >
        </el-pagination>
    </div>
```
script部分，列表部分添加过滤器，当题目摘要等文字长度过长的时候进行省略。当点击删除，通过ajax发送POST请求给PHP文件来进行数据库操作，当点击操作按钮，通过cookie来传递所需要的值，跳转到编辑表单部分，解析cookie给表单赋值。
```
<script type = "text/javascript">
        var aa = {$tableData|raw};
        var Main = {
            methods:{
                handleClick:function(row){
                    var list = {
                        "abstract":row.abstract,
                        "title":row.title,
                        "link":row.link,
                        "keyword":row.keyword
                    }
                    $.cookie("list",JSON.stringify(list));
                    $.cookie("page",this.currPage);
                    // document.getElementById("app").innerHTML = 
                    window.location.href = "editdetail";
                },
                currentChange:function(val){
                    console.log(val);
                    this.currPage = val;
                },
                handleDelete:function(row){
                    var list = {
                        "abstract":row.abstract,
                        "title":row.title,
                        "link":row.link,
                        "keyword":row.keyword
                    }
                    $.cookie("deleteList",JSON.stringify(list));
                    $.cookie("page",this.currPage);

                    $.ajax({
                        type:"POST",
                        contentType: "application/x-www-form-urlencoded",
                        url:'editdetail/deletePaper',
                        data:{},
                        success:function(result){
                        location.href = "edit" 
                            console.log("修改成功");
                            alert("删除成功")
                        },
                        error:function(msg){
                            console.log("传输失败");
                            alert("删除失败")
                        }
                    });
                    
                }
            },
            data(){
                return {
                    tableData:aa,
                    limitCount:4,
                    currPage : parseInt($.cookie("page")??1),
                    input:'',
                }
            },
            filters: {
                ellipsis(value) {
                if (!value) return "";
                if (value.length > 150) {
                    return value.slice(0, 150) + "...";
                }
            return value;
            }
        },
        }  
        var Ctor = Vue.extend(Main)
        new Ctor().$mount('#app') 
    </script>
```
4. 编辑详情页面
表单部分HTML 表单输入框可以通过内容只适应高度
```
<el-form ref="form" label-width="100px">
            <el-form-item label="论文名称">
                <el-input v-model="form.title" type="textarea" :autosize="{minRows:1,maxRows:3}">
                </el-input>
            </el-form-item>
            <el-form-item label="论文链接">
                <el-input v-model="form.link" type="textarea" :autosize="{minRows:1,maxRows:3}">
                </el-input>
            </el-form-item>
            <el-form-item label="论文关键词">
                <el-input v-model="form.keyword" type="textarea" :autosize="{minRows:2,maxRows:10}">
                </el-input>
            </el-form-item>
            <el-form-item label="论文摘要">
                <el-input v-model="form.abstract" type="textarea" :autosize="{minRows:2,maxRows:10}">
                </el-input>
            </el-form-item>
            <el-form-item type= "width:100%" align="center">
                <el-button  @click="commit()" type="success" size="small">完成修改</el-button>
                <el-button  @click="recover()" type="primary" size="small">重置</el-button>
            </el-form-item>
        </el-form>
```
JS部分 当需要重置的时候调用未修改的数据来对表单进行复制，需要进行修改时通过ajax发送请求给php文件，成功跳转回原页面，失败提示修改失败。
```
var formJSON = $.cookie("list");
        $.cookie("list",null);
        var editPaper = JSON.parse(formJSON);
        
        console.log(editPaper);
        var oldPaperString = JSON.stringify(editPaper);
        var oldPaper = JSON.parse(oldPaperString);
        var Main = {
            methods: {
                commit:function(){
                    var list = {
                        "oldTitle":oldPaper.title,
                        "title":editPaper.title,
                        "oldLink":oldPaper.link,
                        "link":editPaper.link,
                        "oldAbstract":oldPaper.abstract,
                        "abstract":editPaper.abstract,
                        "oldKeyword":oldPaper.keyword,
                        "keyword":editPaper.keyword
                    }
                    $.cookie("updateList",JSON.stringify(list));
                    
                    $.ajax({
                        type:"POST",
                        contentType: "application/x-www-form-urlencoded",
                        url:'editdetail/updatePaper',
                        data:{},
                        success:function(result){
                            window.location.href = "edit";
                            console.log("修改成功");
                        },
                        error:function(msg){
                            console.log("传输失败");
                        }
                    });
                },
                recover:function(){
                    this.form=JSON.parse(oldPaperString);
                    console.log(oldPaperString);
                }
            },
            data() {
                return {
                    form: editPaper
                }
            },
        }

        var Ctor = Vue.extend(Main)
        new Ctor().$mount('#app')
    </script>
```
5. PHP部分
php获取数据库中数据项的个数和数据，将数据项转化成json数据给页面赋值
```
<?php
namespace app\index\controller;

use think\Controller;
use think\Db;
use think\Request;

class Edit extends Controller
{
    protected $request;
    public function index()
    {
        $count = Db::table('paper')->count();
        $data = Db::table('paper')->select();
        $this->assign('tableData',json_encode($data));
        $this->assign('count',$count);
        return $this->fetch();
    }
    
}

```
对所要进行修改的论文数据进行数据库操作，修改和删除。
```
<?php
namespace app\index\controller;
use think\Controller;
use think\Db;

class Editdetail extends Controller{
    
    public function index(){
        return $this->fetch();
    }
    public function updatePaper(){
        $listString = $_COOKIE["updateList"];
        
        $list = json_decode($listString,true);

        
        $oldTitle =  $list["oldTitle"];
        $title = $list["title"];
        $oldLink = $list["oldLink"];
        $link = $list["link"];
        $oldAbstract = $list["oldAbstract"];
        $abstract = $list["abstract"];
        $oldKeyword = $list["oldKeyword"];
        $keyword = $list["keyword"];
        // $form = json_encode($_POST);
        $result = Db::table('paper')
            ->where("title",$oldTitle)
            ->where("link",$oldLink)
            ->where("abstract",$oldAbstract)
            ->where("keyword",$oldKeyword)
            ->update([
                "title"=>$title,
                "link"=>$link,
                "abstract"=>$abstract,
                "keyword"=>$keyword
        ]);
        if($result!==false){
            return $this->success("编辑成功");
        }
        else {
            return $this->error("编辑失败");
        }
    }
    public function deletePaper(){
        $listString = $_COOKIE["deleteList"];
        $list = json_decode($listString,true);
        
        $title = $list["title"];
      
        $link = $list["link"];
        
        $abstract = $list["abstract"];
        
        $keyword = $list["keyword"];

        $result = Db::table('paper')
            ->where("title",$title)
            ->where("link",$link)
            ->where("abstract",$abstract)
            ->where("keyword",$keyword)
            ->delete();
        if($result!==false){
            return $this->success("删除成功");
        }
        else {
            return $this->error("删除失败");
        }
    }
}
```