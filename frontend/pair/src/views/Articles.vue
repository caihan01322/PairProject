<template>
 <div class="wrapper">
    <div class="wrapper_content">
       <div class="tab_title">
          <ul>
            <li @click="tab=0" :class="{tab_active:tab==0}">
          <span>  <i class="el-icon-document"></i> 论文列表</span>
            </li>
            <li @click="tab=1" :class="{tab_active:tab==1}">
          <span>  <i class="el-icon-search"></i> 论文爬取</span>
            </li>
            <li @click="tab=2" :class="{tab_active:tab==2}">
          <span>  <i class="el-icon-data-analysis"></i> 热点分析</span>
            </li>
            <li @click="tab=3" :class="{tab_active:tab==3}">
          <span>  <i class="el-icon-guide"></i> 个人中心</span>
            </li>
          </ul>
        </div>
        <div class="content">
          <div class="tab_content" v-if="tab==0">
          <div style="width: 100%">
          <div style="float:left;margin-top:18px;text-align:center;margin-left:20px;font-weight:bold">
          <span>论文查询</span></div>
          <div style="float: left">
          <template>
  <el-select v-model="value" placeholder="请选择" style="margin-top:10px;margin-left:20px">
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value">
    </el-option>
  </el-select>
</template>
</div>
  <div style="float: left"><el-input v-model="input" placeholder="请输入内容" style="margin-top:10px;margin-left:20px">
  </el-input>
  </div>
  <div style="float: left;margin-top:10px;margin-left:30px">
  <el-button icon="el-icon-search" circle>
  </el-button></div>
          </div>
            <div class="tablepanel">
              <template>
    <el-table
      :data="tableData"
      style="width: 100%;height: 70%;margin-top:100px;width:90%;margin-left:40px;">
      <el-table-column
        prop="title"
        label="标题"
        width="130">
      </el-table-column>
      <el-table-column
        prop="articlenumber"
        label="论文编号"
        width="120">
      </el-table-column>
      <el-table-column
        prop="kwds"
        label="关键词"
        width="120">
      </el-table-column>
      <el-table-column
        prop="abstract"
        label="摘要"
        width="350">
      </el-table-column>
      <el-table-column>
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleEdit(scope.$index, scope.row)">查看</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row)">删除</el-button>
      </template>
      </el-table-column>
    </el-table>
  </template>
              <div style="margin-top:20px">
            <template>
            <el-pagination
            background
            layout="prev, pager, next"
            :total="1000">
            </el-pagination>
            </template>
            </div>
            </div>
          </div>
          <div class="tab_content" v-else-if="tab==1">
            内容二
          </div>
          <div class="tab_content" v-else-if="tab==2">
            <!-- 内容三 -->
            <span class="demonstration">默认 Hover 指示器触发</span>
            <el-carousel height="500px">
              <el-carousel-item v-for="item in 4" :key="item">
                <h3 class="small">{{ item }}</h3>
              </el-carousel-item>
            </el-carousel>
          </div>
          <div class="tab_content" v-else-if="tab==3">
            <!-- 内容四 -->
              <div>
                <el-avatar style="margin-top:100px" :size="150" src="https://d1icd6shlvmxi6.cloudfront.net/gsc/4ZWR3V/f2/5f/0b/f25f0b8a8a354b97879b76719e319cf8/images/论文列表/u22.svg?token=97bb5bafada96da5d048a20f601fd183fb436b10ff76c40e10d1c000a2e73a68"></el-avatar>
              </div>
              <el-row>
                <el-col :span="12">
              <div>
              账号: <el-input v-model="input" placeholder="fzu123"></el-input>
              </div>
              </el-col>
              <el-col :span="12">
              <div>
              昵称: <el-input v-model="input" placeholder="zhlzsy"></el-input>
              </div>
              </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
              <div>
                学校:
                  <el-select v-model="value1" placeholder="请选择">
                    <el-option
                      v-for="item in options1"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
              </div>
              </el-col>
              <el-col :span="12">
              <div>
                专业:
                  <el-select v-model="value2" placeholder="请选择">
                    <el-option
                      v-for="item in options2"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
              </div>
              </el-col>
              </el-row>
          </div>
        </div>
      </div>
    </div>
</template>
<script>
export default {
  data () {
    return {
      tab: 0,
      input: '',
      tableData: [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }, {
        date: '2016-05-03',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1516 弄'
      }],
      options: [{
        value: '选项1',
        label: '论文标题'
      }, {
        value: '选项2',
        label: '论文编号'
      }, {
        value: '选项3',
        label: '关键词'
      }],
      options1: [{
        value: '选项1',
        label: '福州大学'
      }, {
        value: '选项2',
        label: '厦门大学'
      }, {
        value: '选项3',
        label: '福建师范大学'
      }, {
        value: '选项4',
        label: '福建农林大学'
      }, {
        value: '选项5',
        label: '其他'
      }],
      options2: [{
        value: '选项1',
        label: '软件工程'
      }, {
        value: '选项2',
        label: '计算机'
      }, {
        value: '选项3',
        label: '数学'
      }, {
        value: '选项4',
        label: '土木工程'
      }, {
        value: '选项5',
        label: '其他'
      }],
      value1: '选项1',
      value2: '选项1'
    }
  }
}
</script>
<style scoped>
.wrapper {
  width: 100%;
  height: 600px;
  background-color: white;
  background-position: center;
  display: flex;
  justify-content: center;
}
.wrapper_content{
 width: 1236px;
 height: 100%;
 display: flex;
 justify-content: flex-start;
}
.tab_title{
  width: 200px;
  height: 100%;
  background-color: #333744;
    border: 1px solid #333744;
}
.tablepanel{
  width: 100%;
  height: 100%;
}
.tab_title>ul{
  margin: 0;
  padding: 0;
  list-style: none;
  width: 100%;
  height: 120px;
  margin-top: 0px;
  display: flex;
  flex-direction: column;
}
.tab_title>ul>li{
  flex-grow: 1;
  width: 100%;
  text-align: center;
  height: 70px;
  line-height: 80px;
  display: flex;
  color: #fff;
  justify-content: center;
}
.tab_title>ul>li:hover{
  cursor: pointer;
}
.tab_title .tab_active{
  cursor: pointer;
  background: white;
  color: #333744;
  border: 0;
}
.content{
  width: 976px;
  height: 100%;
  border: 1px solid #333744;
}
.tab_content{
  width: 100%;
  height: 100%;
}
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .el-input {
    width: 221.4px;
  }
  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
  }
</style>
