<template>
  <div class="home">
    <transition name="fade">
      <!--    登录弹窗-->
      <Login v-model="loginVisible" id="login" v-if="loginVisible"/>

      <ManageAccountPop v-model="manageAccountVisible" id="manage_account" v-if="manageAccountVisible"/>
    </transition>

    <el-row align="middle" type="flex">
      <el-col :span="4">
        <img id="logo_home" src="../assets/logo.png" alt="logo" @click="routeToSearch"/>
      </el-col>
      <el-col :span="16">
        <div id="search_div_home">
          <el-input id="search_input_home"
                    placeholder="搜索论文题目 / 摘要 / 关键字 / 作者"
                    v-model="searchInput"
                    clearable="clearable">
          </el-input>
        </div>
      </el-col>
      <el-col :span="4">
        <div style="display: flex;margin-left: 50px ">
          <img id="avatar_home"
               alt="avatar"
               :src="avatarUrl!=='' ? avatarUrl : defaultAvatar"
               @click="clickAvatar">
          <div id="nickname">{{ this.$store.state.username }}</div>
        </div>
      </el-col>
    </el-row>

    <div style="display: flex ;margin-top: 30px;margin-left: 50px">
      <a :class="['tab', currentTab===1 ? 'tab_underline' : 'left-to-right' ]" id="search_tab" @click="currentTab=1">论文列表</a>
      <a :class="['tab', currentTab===2 ? 'tab_underline' : 'left-to-right' ]" id="analyze_tab" @click="currentTab=2">论文结果统计</a>
      <a :class="['tab', currentTab===3 ? 'tab_underline' : 'left-to-right' ]" id="manage_tab" @click="showCollection">爬取记录</a>
    </div>


    <SearchList v-if="currentTab===1"
                 :searchWord="searchWord" :searchInput="searchInput"/>
    <statisticList v-if="currentTab===2"
                   :searchInput="searchInput"/>
    <CollectionList v-if="currentTab===3"
                 :searchInput="searchInput"/>

    <div id="background_home"/>
  </div>
</template>

<script>
import SearchList from "@/components/SearchList";
import CollectionList from "@/components/collectionList";
import StatisticList from "@/components/statisticList";
import Login from "@/components/LoginPop";
import ManageAccountPop from "@/components/ManageAccountPop";

export default {
  name:'Home',
  components:{
    StatisticList,CollectionList,SearchList,
    Login,ManageAccountPop
  },
  watch:{
    loginVisible(){
      this.avatarUrl= this.$store.state.avatarUrl
    },
    manageAccountVisible(){
      this.avatarUrl= this.$store.state.avatarUrl
    }
  },
  data(){
    return {
      searchInput:'',
      message:'testtest',
      searchWord:'',
      loginVisible:false,
      manageAccountVisible:false,
      avatarUrl:this.$store.state.avatarUrl,
      defaultAvatar:require('../assets/avatar.png'),
      currentTab: 1
    }
  },
  methods:{
    routeToSearch:function(){
      this.$router.push("/")
    },

    showCollection(){
      if(this.$store.state.account!==''){
        this.currentTab=3
      }else{
        this.$message.warning("请先登录")
      }
    },

    clickAvatar(){
      if(this.$store.state.account===''){
        this.loginVisible=!this.loginVisible
      }else{
        this.manageAccountVisible=!this.manageAccountVisible
      }
    }

  },

}
</script>

<style lang="less">


.el-checkbox__inner {
    width: 20px;
    height: 20px;
    border-color: #333333;
}

.el-checkbox__inner::after{
    left: 7px;
    top: 3px;
}

.el-checkbox__input.is-indeterminate .el-checkbox__inner::before{
  top: 7px;
}

a, a:link, a:visited, a:focus {
  text-decoration: none;
  color: #ffffff;
}

/*设置a的定位，给我们自己编写的下划线一个定位参考*/
.left-to-right {
  position: relative;
}

/*使用伪类给a下面添加下划线*/
/*css3为了区别伪类选择器把::改为:,使用:也会自动转为::*/
.left-to-right::after {
  content: '';
  display: block;
  /*开始时候下划线的宽度为0*/
  width: 0;
  height: 2px;
  position: absolute;
  left: 0;
  bottom: -10px;
  background: #ffffff;
  /*这里我们设定所有改变都有动画效果，可以自己指定样式才有动画效果*/
  transition: all 0.3s ease-in-out;
}

.left-to-right:hover::after {
  width: 100%;
}

.tab_underline {
  position: relative;
}

.tab_underline::after{
  content: '';
  display: block;
  /*开始时候下划线的宽度为0*/
  width: 100%;
  height: 2px;
  position: absolute;
  left: 0;
  bottom: -10px;
  background: #ffffff;
  /*这里我们设定所有改变都有动画效果，可以自己指定样式才有动画效果*/
  transition: all 0.3s ease-in-out;
}

.tab {
  color: white;
  margin-left: 50px;
  font-size: 18px;
  filter: drop-shadow(2px 2px 10px #333333);
}

.tab:hover {
  color: white;
  text-underline-color: white;
  cursor: pointer;
}

#logo_home {
  width: auto;
  height: 90px;
  transition: transform .3s ease-in-out, box-shadow .3s cubic-bezier(.47, 0, .745, .715), border .3s linear .1s;
}

#logo_home:hover {
  filter: drop-shadow(5px 5px 5px #000000);
  -webkit-transform: translateY(-5px);
  -moz-transform: translateY(-5px);
  transform: translateY(-5px)
}

#search_div_home {
  //margin-top: 10px;
  position: fixed;
  top: 40px;
  z-index: 100;
  width: 65%;
  filter:drop-shadow( 5px 5px 5px rgba(25, 25, 25, .25));
}

#search_input_home {
  border-radius: 100px;
}

#avatar_home {
  width: 40px;
  height: 40px;
  margin-top: 10px;
  border-radius: 50%;
  transition: transform .3s ease-in-out, box-shadow .3s cubic-bezier(.47, 0, .745, .715), border .3s linear .1s;
}

#nickname {
  width: auto;
  margin-top: 18px;
  margin-left: 20px;
  font-weight: bold;
  color: white;
  transition: transform .3s ease-in-out, box-shadow .3s cubic-bezier(.47, 0, .745, .715), border .3s linear .1s;
}

#avatar_home:hover {
  filter: drop-shadow(5px 5px 5px #000000);
  -webkit-transform: translateY(-5px);
  -moz-transform: translateY(-5px);
  transform: translateY(-5px)
}

#background_home {
  z-index: -2;
  background-image: url("../assets/background.png");
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background-color: transparent;
  background-size: cover;
  background-position: center center;
  background-repeat: no-repeat
}

/*#background_home {*/
/*  z-index: -2;*/
/*  background-image: url("../assets/background_search.jpg");*/
/*  position: absolute;*/
/*  top: 0;*/
/*  left: 0;*/
/*  right: 0;*/
/*  bottom: 0;*/
/*  background-color: transparent;*/
/*  background-size: cover;*/
/*  background-position: center center;*/
/*  background-repeat: no-repeat*/
/*}*/
</style>
