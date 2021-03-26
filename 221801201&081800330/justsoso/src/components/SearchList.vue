<template>
  <div id="main_frame">
    <div class="collection_frame">
      <el-button id="crawl_paper" round @click="crawl">爬取论文</el-button>
      <el-checkbox id="select_all" :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange"
                   size="medium"></el-checkbox>
      <el-checkbox-group v-model="checkedPapers" @change="handleCheckedPapersChange">
        <Essay v-for="paper in searchPapers"
               v-model="searchWord"
               v-bind:id="paper.artical.academicNum"
               v-bind:link="paper.artical.link"
               v-bind:title="paper.artical.title"
               v-bind:authors="paper.authors"
               v-bind:abstract="paper.artical.abstract"
               v-bind:keywords="paper.keywords"
               v-bind:key="paper.title">
        </Essay>
      </el-checkbox-group>
      <img id="empty_img" v-if="isEmpty" src="../assets/empty-folder.png" alt="">
    </div>

  </div>
</template>
<script>
import Essay from "@/components/Essay"
import axios from "axios";

export default {
  name:'SearchList',
  components:{Essay},
  model:{
    prop:"searchWord",
    event:"search-event"
  },
  props:{
    searchInput:{},
  },
  data(){
    return{
      checkAll:false,
      checkedPapers:[],
      isIndeterminate:false,
      isEmpty:true,
      papers:[],
      paperIds:[],
      isReadyLoad:false,
      pageNum:0,
      totalPageNum:0,
      totalPaperNum:0,
    }
  },
  computed:{
    searchPapers(){
      return this.papers
          .filter(paper=>{
            if(this.searchInput.length===0){
              return paper
            }else{
              for(var author of paper.authors){
                if(author.toLowerCase().indexOf(this.searchInput.toLowerCase())>=0){
                  return paper
                }
              }
              for(var keyword of paper.keywords){
                if(keyword.toLowerCase().indexOf(this.searchInput.toLowerCase())>=0){
                  console.log(keyword.toLowerCase())

                  return paper
                }
              }
              if(paper.artical.title.toLowerCase().indexOf(this.searchInput.toLowerCase())>=0
                  ||paper.artical.abstract.toLowerCase().indexOf(this.searchInput.toLowerCase())>=0){
                return paper
              }
            }
          })
    }
  },
  methods:{
    handleCheckAllChange(val){
      this.checkedPapers=val?this.paperIds:[];
      this.isIndeterminate=false;
      console.log(this.isIndeterminate)
    },

    handleCheckedPapersChange(value){
      let checkedCount=value.length;
      this.checkAll=checkedCount===this.paperIds.length;
      this.isIndeterminate=checkedCount>0&&checkedCount<this.paperIds.length;
    },

    crawl(){
      console.log('http://121.5.100.116:8080/api/Collect?Account='+this.$store.state.account.toString()+'&Nums='+this.checkedPapers.toString())
      if(this.checkedPapers.length>0){
        axios
            .get('http://121.5.100.116:8080/api/Collect?Account='+this.$store.state.account.toString()+'&Nums='+this.checkedPapers.toString())
            .then(response=>{
              if(response.data.code===200){
                this.$message.success('收藏成功，共'+response.data.data+'篇')
              }else{
                this.$message.error('收藏失败')
              }
            })
      }else{
        this.$message.warning('请先勾选文章')
      }
    },

    searchSuccess(totalNum){
      this.$message({
        message:'搜索成功,共'+totalNum+'页',
        type:'success'
      });
      this.isReadyLoad=true
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

#select_all {
  position: absolute;
  right: 2%;
  margin-right: 20px;
  top: 20px;
}


#main_frame {
  width: 100%;
  position: relative;
  margin-top: 50px;
  margin-bottom: 50px;
}

.collection_frame {
  background-color: #f7f7f7;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  width: 90%;
  margin-left: 5%;
  height: auto;
  margin-bottom: 50px;
  padding-top: 20px;
  padding-bottom: 50px;
  position: relative;
}

#empty_img {
  width: 400px;
  height: auto;
}

#crawl_paper {
  background-color: white;
  width: 100px;
  display: flex;
  height: 30px;
  margin-left: 30px;
  line-height: 5px;
  margin-bottom: 20px;
  font-size: 13px;
  font-weight: bold;
  border: none;
}

a, a:link, a:visited, a:focus {
  text-decoration: none;
  color: #ffffff;
}

</style>
