<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="title">{{selectedObj.title}}</span>
        <el-popconfirm
          title="确定删除吗？"
          @confirm="deletePaper()"
        >
        <el-button style="float: right; padding: 3px 0; font-size: 20px;" type="text" slot="reference">删除</el-button>
        </el-popconfirm>
      </div>
        <div class="item">
          <span class="header-text">所属会议 </span><span class="content">{{selectedObj.meeting + '   ' + selectedObj.postDate}}</span>
          <el-divider></el-divider>
        </div>
        <div class="item">
          <span class="header-text">关键词 </span><span class="content">{{selectedObj.keyword}}</span>
          <el-divider></el-divider>
        </div>
        <div class="item">
          <span class="header-text">原文链接 </span><a class="content" :href="selectedObj.link">{{selectedObj.link}}</a>
          <el-divider></el-divider>
        </div>
        <div class="item">
          <span class="header-text">摘要 </span><span class="content">{{selectedObj.abstractContext}}<el-divider></el-divider></span>
        </div>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data () {
    return {
     selectedObj:'',
     res:true
    }
  },
  methods: {
    getObj:function(){
      this.selectedObj=JSON.parse(this.$route.query.selectedObj);
    },
    goBack:function(){
      this.$router.go(-1)
    },
    deletePaper:function(){
      axios.post('http://localhost:5000/delete',
      {paperID: this.selectedObj.id+''}).then((res) => {          
        if(res.data[0].state){
          //回到上一页
          this.$router.go(-1)
          //提示删除成功
          this.$notify({
            title: '成功',
            message: '这是一条成功的提示消息',
            type: 'success'
          })
        }
      })
    }
  },
  created() {
    this.getObj()
  }
}

</script>

<style>
.title{
  font-size: 22px;
}
.header-text{
  position: relative;
  top:-20px;
  left: -20px;
  font-size: 10px;
  color: #607D8B;
  margin: 10px;

}
.text {
  font-size: 16px;
}
.content{
  display:block;
  float: right;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 100%;
  height: 800px;
}
</style>
