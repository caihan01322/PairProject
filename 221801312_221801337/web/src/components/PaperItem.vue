<template>
<div>
    <div style="display:flex;height:170px;flex-direction:column;font-family:Microsoft YaHei">
        <div><a :href="'http://ieeexplore.ieee.org/'+item.paper.url" 
            target="_blank" 
            class="paper-title"
            :title="item.paper.title"
            >{{item.paper.title}}</a></div>
        <div class="abstract"><span class="item-label" style="float:left">摘&nbsp;&nbsp;要：</span>
             <span style="float:left;width:760px;height:42px;overflow : hidden;text-align:left;">{{item.paper.abstract}}</span>
        </div>
        <div><span style="float:left;margin-bottom:6px;" class="item-label">关键词：</span>
            <div  style="width:620px;overflow : hidden;height:42px;">
                <a :href="'?page=0&searchWord='+keyword.keyword" target="_blank" style="float:left;" class="keyword"  v-for="keyword in item.keywords">{{keyword.keyword}}</a>
            </div>
        </div>
        <div><span style="float:left;" class="item-label">作&nbsp;&nbsp;者：</span>
            <div style="width:500px;overflow : hidden;height:20px;display:inline-block;float:left;">
                <a :href="'?page=0&searchWord='+author.name" target="_blank" style="float:left;" class="keyword" v-for="author in item.authors">{{author.name}}</a>
            </div>
                <span class="item-label" style="float:left;">年份：</span>
                <span style="float:left;">{{ item.paper.publicationYear.substring(0,4) }}</span>
        </div>
    </div>
</div>
</template>

<script>
import {  onMounted,inject  } from "vue";
import axios from 'axios'
var x=new Array();
var list;
for(var i=1;i<=10;i++)x.push("热词"+i);
export default {
  name: "data_page",
  props:['paperPost'],
  data(){
      return{
          item:this.paperPost
      }
  },
  created(){
  },
  setup() {
      
      return{
      }
  },
  components: {},
  mounted() {},
};
</script>

<style>
.paper-title{
    font-size:20px;
    font-weight:520;
    color:#0066CC;
    float:left;
    z-index:inherit;
    left:10px;
    margin-bottom:10px;
    text-overflow: ellipsis;
    overflow: hidden;
    width:660px;
    white-space: nowrap;
    text-align:left;
}
.paper-title:hover{
    text-decoration:underline;
}
.abstract{
    float:left;
    margin-bottom:6px;
}
.item-label{
    font-size:14px;
    color:#7F7F7F;
}
.keyword{
    font-size:14px;
    color:#0066CC;
    margin-right:16px
}
.keyword:hover{
    text-decoration:underline;
}
</style>