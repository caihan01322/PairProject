<template>
    <el-container style="text-aligh: right; font-size: 12px">
  <el-header class="header">
      <span>LOGO</span>
      <el-avatar float = right icon ="https://d1icd6shlvmxi6.cloudfront.net/gsc/4ZWR3V/f2/5f/0b/f25f0b8a8a354b97879b76719e319cf8/images/个人信息/u130.svg?token=7355a9e49eedf9f59c9b3abde069ebd945499a9ba5f19b0423db15e585fef8e3"></el-avatar>
  </el-header>
  <el-container>
    <el-aside width="400px">
        <el-row class="tac">
  <el-col :span="12">
    <el-menu
    :default-active="this.$route.path"
    :default-openeds="['1']"
    :router = true
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
    <el-main>

    </el-main>
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
