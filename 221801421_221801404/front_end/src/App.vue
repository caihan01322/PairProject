<template>
  <div id="app">
    <el-header>
        欢迎来到论文爬取网
    </el-header>
    <div id="wrap">
      <div id="aside">
        <el-menu :default-active="$route.path" router class="el-menu-vertical-demo">
          <el-menu-item index="/">
            <i class="el-icon-document"></i>
            <span slot="title">论文查询</span>
          </el-menu-item>
          <el-menu-item index="/BuzzWord">
            <i class="el-icon-s-flag"></i>
            <span slot="title">热词统计</span>
          </el-menu-item>
        </el-menu>
      </div>
      <router-view></router-view>
    </div>
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
<style>
.el-header {
  background-color: #B3C0D1;
  color: #607D8B;
  line-height: 60px;
  text-align: center;
  font-size: 30px;
  font-weight: bold;
}

#wrap{
  height: 800px;
  border: 1px solid #eee;
  padding-left: 150px;
}

#aside{
  margin-left:-150px;
  float:left;
  width:150px;
}

</style>
