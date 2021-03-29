<template>
  <div id="app">
    <el-container v-if="$route.meta.keepalive">
      <el-header class="header">
        <keep-alive>
          <v_Header></v_Header>
        </keep-alive>
      </el-header>
    <el-container>
    <el-aside style="width:200px; height:600px">
      <keep-alive>
        <v-leftmenu></v-leftmenu>
      </keep-alive>
    </el-aside>
    <el-container>
      <el-main>
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </el-main>
      <el-footer style=" padding-left: 0px;padding-right: 0px;">
        <v_footer></v_footer>
      </el-footer>
    </el-container>
    </el-container>
    </el-container>
    <router-view v-if="!$route.meta.keepalive"></router-view>
    <!-- <Test></Test>
    <img src="./assets/logo.png">
    </router-view>  -->
  </div>
</template>

<script>
import LeftMenu from './components/LeftMenu'
import Header from './components/Header'
import Footer from './components/Footer'
// import Test from './components/Test.vue'
export default {
  // components: { Test },
  name: 'App',
  components: {
    'v_Header': Header,
    'v_leftmenu': LeftMenu,
    'v_footer': Footer
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /* margin-top: 60px; */
}

  .header{
    line-height: 60px;
    background-color: #545c64;
    /* text-color: white; */
  }
</style>
