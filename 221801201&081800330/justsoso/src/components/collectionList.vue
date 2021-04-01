<template>
  <div id="main_frame">
    <div class="collection_frame">
      <el-button id="manage_collection" round @click="manageCollection">删除已爬取文章</el-button>
      <el-checkbox id="select_all" :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange"
                   size="medium"></el-checkbox>
      <el-checkbox-group v-model="checkedPapers" @change="handleCheckedPapersChange">
        <Essay v-for="paper in searchCollectionPapers"
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
  name:'CollectionList',
  components:{Essay},
  props:{
    searchInput:{},
  },
  data(){
    return {
      checkAll:false,
      checkedPapers:[],
      isIndeterminate:false,
      isEmpty:true,
      papers:[],
      paperIds:[],
      totalPaperNum:0,
    }
  },
  computed:{
    searchCollectionPapers(){
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

    manageCollection(){
      console.log('http://121.5.100.116:8080/api/deleteCollections?Account='+this.$store.state.account.toString()+'&Nums='+this.checkedPapers.toString())
      if(this.checkedPapers.length>0){
        axios
            .post('http://121.5.100.116:8080/api/deleteCollections?Account='+this.$store.state.account.toString()+'&Nums='+this.checkedPapers.toString())
            .then(response=>{
              if(response.data.code===200){
                this.$message.success('取消收藏成功，共'+response.data.data+'篇')
                this.getCollection()
              }else{
                this.$message.error('取消收藏失败')
              }
            })
      }else{
        this.$message.warning('请先勾选文章')
      }
    },

    getCollection(){
      const loading = this.$loading({
        lock: true,//lock的修改符--默认是false
        text: '加载中...',//显示在加载图标下方的加载文案
        spinner: 'el-icon-loading',//自定义加载图标类名
        background: 'rgba(226,226,226,0.7)',//遮罩层颜色
        target: document.querySelector('.collection_frame')//loading覆盖的dom元素节点
      });
      if(this.$store.state.username!==undefined){
        axios
            .get('http://121.5.100.116:8080/api/getCollections?Account='+this.$store.state.account.toString())
            .then(response=>{
              if(response.data.code===200){
                this.papers=response.data.data.list;
                for(var paper of response.data.data.list){
                  this.paperIds.push(paper.artical.academicNum)
                }
                this.totalPaperNum=response.data.data.totalPaperNum;
                if(this.totalPageNum!==0){
                  this.isEmpty=false
                  this.$message.success("共有收藏文章"+this.totalPaperNum+"篇")
                }else{
                  this.$message({
                    message:'暂无收藏文章',
                    type:'warning'
                  });
                  this.isEmpty=true
                }
              }else{
                this.$message({
                  message:'请求错误',
                  type:'error'
                });
              }
              loading.close()
            })
      }

    }
  },
  created(){
    this.getCollection()
  },
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

#manage_collection {
  background-color: #9e4d4d;
  display: flex;
  height: 30px;
  margin-left: 30px;
  line-height: 5px;
  margin-bottom: 20px;
  font-size: 13px;
  color: white;
  font-weight: bold;
  border: none;
}

a, a:link, a:visited, a:focus {
  text-decoration: none;
  color: #ffffff;
}

</style>
