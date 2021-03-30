<template>
    <el-container style="text-aligh: right; font-size: 12px">
  <el-header class="header">
      <span>LOGO</span>
  </el-header>
  <el-container>
    <el-aside width="400px">
        <el-row class="tac">
  <el-col :span="12">
    <el-menu
    router :default-active="$route.path"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose">
      <el-menu-item index="/views/login">
        <i class="el-icon-reading"></i>
        <span slot="title">论文列表</span>
      </el-menu-item>
      <el-menu-item index="2">
        <i class="el-icon-search"></i>
        <span slot="title">论文爬取</span>
      </el-menu-item>
      <el-menu-item index="3">
        <i class="el-icon-data-analysis"></i>
        <span slot="title">热点分析</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-user"></i>
        <span slot="title">个人中心</span>
      </el-menu-item>
    </el-menu>
  </el-col>
        </el-row>
    </el-aside>
    <el-main>Main</el-main>
  </el-container>
</el-container>
</template>

<script>
export default {
    methods: {
      handleOpen (key, keyPath) {
        console.log(key, keyPath)
      },
      handleClose (key, keyPath) {
        console.log(key, keyPath)
      }
    }
}
</script>

<style>
    .el-header {
        background-color: #555555;
        color: #333;
        width: auto;
        height: auto;
    }
    header span {
        color: #FFFFFF;
        font: 100px;
        float: left;
        padding-top:1px;
    }
</style>
