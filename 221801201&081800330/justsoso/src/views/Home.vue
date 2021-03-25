<template>
  <div class="home">

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
            <el-button id="search_button_home" slot="suffix" icon="el-icon-search" circle/>
          </el-input>
        </div>
      </el-col>
      <el-col :span="4">
        <div style="display: flex;margin-left: 50px ">
          <img id="avatar_home" src="../assets/avatar.png" alt="avatar"
               @click="loginVisible=!loginVisible">
          <div id="nickname">请登录</div>
        </div>
      </el-col>
    </el-row>

    <div style="display: flex ;margin-top: 30px;margin-left: 50px">
      <a :class="['tab', currentTab===1 ? 'tab_underline' : 'left-to-right' ]" id="search_tab" @click="currentTab=1">论文列表</a>
      <a :class="['tab', currentTab===2 ? 'tab_underline' : 'left-to-right' ]" id="analyze_tab" @click="currentTab=2">论文结果统计</a>
      <a :class="['tab', currentTab===3 ? 'tab_underline' : 'left-to-right' ]" id="manage_tab" @click="currentTab=3">爬取记录</a>
    </div>


    <SearchList v-if="currentTab===1"
                :check-all="checkAll" :checked-papers="checkedPapers" :handle-check-all-change="handleCheckAllChange"
                :handle-checked-papers-change="handleCheckedPapersChange" :is-empty="isEmpty"
                :is-indeterminate="isIndeterminate" :papers="papers"/>

    <div id="background_home"/>
  </div>
</template>

<script>

import axios from "axios";
import SearchList from "@/views/SearchList";

export default {
  name:'Home',
  components:{SearchList},
  data(){
    return {
      searchInput:'',

      papers:[],
      message:'testtest',
      searchWord:'',

      checkAll:false,
      checkedPapers:[],
      paperIds:[],
      isIndeterminate:false,

      isEmpty:true,
      isReadyLoad:false,
      pageNum:0,
      totalPageNum:0,
      totalPaperNum:0,

      currentTab: 1
    }
  },
  methods:{
    routeToSearch:function(){
      this.$router.push("/")
    },
    searchSuccess(totalNum){
      this.$message({
        message:'搜索成功,共'+totalNum+'页',
        type:'success'
      });
      this.isReadyLoad=true
    },

    handleCheckAllChange(val){
      this.checkedPapers=val?this.paperIds:[];
      this.isIndeterminate=false;
    },

    handleCheckedPapersChange(value){
      let checkedCount=value.length;
      this.checkAll=checkedCount===this.paperIds.length;
      this.isIndeterminate=checkedCount>0&&checkedCount<this.paperIds.length;
    },

    //触底触发函数
    listenBottomOut(){
      let scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
      let clientHeight=document.documentElement.clientHeight;
      let scrollHeight=document.documentElement.scrollHeight;
      if(scrollTop+clientHeight>=scrollHeight-1&&this.isReadyLoad===true){
        this.isReadyLoad=false
        this.search()
      }
    },

    search(){
      if((this.pageNum<this.totalPageNum||this.totalPageNum===0)&&this.searchWord!==undefined){
        axios
            .get('http://121.5.100.116:8080/api/search?keyword='+this.searchWord+'&pageNum='+this.pageNum)
            .then(response=>{
              this.papers=this.papers.concat(response.data.data.list);
              for(var paper of response.data.data.list){
                this.paperIds.push(paper.artical.academicNum)
              }
              this.totalPageNum=response.data.data.totalNum;
              if(this.pageNum===0){
                if(this.totalPageNum!==0){
                  this.isEmpty=false
                  this.searchSuccess(this.totalPageNum)
                }else{
                  this.$message({
                    message:'未搜索到文章',
                    type:'warning'
                  });
                  this.isEmpty=true
                }
              }else{
                this.$message({
                  message:'请求第'+(this.pageNum+1)+'页',
                  type:'success'
                });
              }
            })
            .finally(()=>{
              this.pageNum++;
              this.isReadyLoad=true
            })
      }else{
        this.$message({
          message:'没有更多了',
          type:'warning'
        });
      }
    }

  },
  created(){
    this.searchWord=this.$route.query.searchWord;
    this.search()
  },
  mounted(){
    //事件监听
    window.addEventListener('scroll',this.listenBottomOut)
  },
  //vue 的生命周期钩子函数
  destroyed(){
    //离开页面取消监听
    window.removeEventListener('scroll',this.listenBottomOut,false)
  }

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
  margin-top: 10px;
}

#search_input_home {
  border-radius: 100px;
}

#search_button_home {
  border: none;
  background-color: rgba(0, 0, 0, 0);
}

#search_button_home:hover {
  background-color: rgba(0, 0, 0, 0);
}

#avatar_home {
  width: 40px;
  height: 40px;
  margin-top: 10px;
  margin-left: 30px;
  transition: transform .3s ease-in-out, box-shadow .3s cubic-bezier(.47, 0, .745, .715), border .3s linear .1s;
}

#nickname {
  width: auto;
  margin-top: 18px;
  margin-left: 20px;
  font-weight: bold;
  letter-spacing: 3px;
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
