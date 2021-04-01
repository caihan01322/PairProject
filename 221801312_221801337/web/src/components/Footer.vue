<template>
<div class="footer">
  <el-row style="background-color:#ffffff;height:40px">
  <el-col :span="4"><div ></div></el-col>
  <el-col :span="2"><div>
          <a href="" class="footer-font">关于搜索</a>
        </div></el-col>
  <el-col :span="2"><div >
        <el-dropdown :hide-on-click="false">
        <span class="el-dropdown-link" style="margin-top:10px">
            关于我们<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <template #dropdown>
            <el-dropdown-menu>
            <el-dropdown-item @click="openGithub('https://github.com/RoseSavona')">肖恩嘻嘻</el-dropdown-item>
            <el-dropdown-item @click="openGithub('https://github.com/yrc123')">羊肉串</el-dropdown-item>
            </el-dropdown-menu>
        </template>
        </el-dropdown>
        </div></el-col>
  <el-col :span="8"><div>
          <a href="" class="footer-font">Copyright ©2021 羊肉串&肖恩嘻嘻, All Rights Reserved.</a>
        </div></el-col>
  <el-col :span="3"><div>
          <a href="" class="footer-font">条款</a>
        </div></el-col>
  <el-col :span="5"></el-col>
</el-row>
</div>
</template>

<style>
  .el-row {
    padding-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .footer{
    width: 100%;   
    bottom: 0;
    margin-top: 10px;
  }
  .footer-font {
      color: #AAAAAA;
      text-decoration:none;
  }
  .footer-font:hover{
      color: #000000;
  }
  .el-dropdown-link {
    cursor: pointer;
    color: #AAAAAA;
  }
</style>


<script>
import {defineComponent} from 'vue';
    export default defineComponent ({
        el:"#footer", 
        setup(){
            function openGithub(path){
                var tempwindow=window.open('_blank');
                tempwindow.location=path;
            }
            return{
                openGithub,
            }
        }
    })
</script>
