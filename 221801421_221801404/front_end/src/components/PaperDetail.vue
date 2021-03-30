<template>
  <div>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span class="title">123</span>
        <el-popconfirm
          title="确定删除吗？"
          @confirm="deletePaper()"
        >
          <el-button style="float: right; padding: 3px 0; font-size: 20px;" type="text" slot="reference">删除</el-button>
        </el-popconfirm>
      </div>
        <div class="item">
          <span class="header-text">所属会议 </span><span class="content">123</span>
          <el-divider></el-divider>
        </div>
        <div class="item">
          <span class="header-text">关键词 </span><span class="content">123</span>
          <el-divider></el-divider>
        </div>
        <div class="item">
          <span class="header-text">原文链接 </span><a class="content">123</a>
          <el-divider></el-divider>
        </div>
        <div class="item">
          <span class="header-text">摘要 </span><span class="content">123<el-divider></el-divider></span>
        </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data () {
    return {

    }
  },
}

</script>
//method要实现deletePaper()

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
