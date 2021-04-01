<template>
<div class="SearchView">

    <div style="height:54px;">
         <el-autocomplete
            class="inline-input"  
             v-model="inputValue"
             :fetch-suggestions="querySearch"
              placeholder="支持关键字、论文编号检索"
             :trigger-on-focus="false"
            @select="handleSelect"
          style="width:500px;height:100%" id="searchBox"></el-autocomplete>
        <el-button type="primary" id="searchButton" @click="passInput()"><span style="position:relative;top:-2px;" @click="clickTest">论文检索</span></el-button>
    </div>
</div>
</template>
<style>
.logo{
    width: 520px;
    height: 70px;
    margin-top:80px;
    margin-bottom:30px;
}
#searchBox{
    border-top-left-radius: 10px;
    border-top-right-radius: 0px;
    border-bottom-left-radius: 10px;
    border-bottom-right-radius: 0px;
    border-right: none;
    font-size: 16px;
}
#searchButton{
    border-top-left-radius: 0px;
    border-top-right-radius: 10px;
    border-bottom-left-radius: 0px;
    border-bottom-right-radius: 10px;
    height:36px;
    color:#ffffff;
    font-weight: bold;
    font-size: 20px;
    text-align:center;
    vertical-align:middle ;
    position: relative;
    top:-1.5px;
}
</style>

<script>
import { defineComponent, ref, onMounted } from 'vue'
import axios from 'axios'
import QS from 'qs'

export default defineComponent({
  data(){
      return{
      }
  },
  methods:{
    
  },
  setup(context,{emit}) {
    let keywordList;
    var inputValue=ref("");
    const restaurants = ref([]);
    const querySearch = (queryString, cb) => {
      var data={
          data:{searchWord:queryString,limit:7}
        };
        //console.log(data.data.searchWord);
        cb([]);
        var list= postHeader(data,true);
        list.then(
           function(value){
            keywordList=value.data.searchWord;
            for(var i=0;i<keywordList.length;i++){
              keywordList[i].value=keywordList[i].keyword;
            }
              cb(keywordList);
           //restaurants.value=keywordList;
        })
        
    };
  
    const handleSelect = (item) => {
      //console.log(item);
    };
    onMounted(() => {
    //   restaurants.value = loadAll();
         var searchWord =window.location.search;
         if(searchWord!="")
          inputValue.value=decodeURIComponent(searchWord.match('((?<=searchWord=).*?(?=&|$))')[0]);
         window.addEventListener("keydown", keyDown);
    });
    function keyDown(e){
      if(e.keyCode===13)
        passInput();
    }
    /**
 * post方法，对应post请求
 * @param {String} url [请求的url地址]
 * @param {Object} params [请求时携带的参数]
 * @param {Boolean} json [true：json格式请求头；false：FormData格式请求头]
 */
   function post(url, params = {}, json = false) {
  // json格式请求头
  const headerJSON = {
    "Content-Type": "application/json"
  };
  // FormData格式请求头
  const headerFormData = {
    "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8"
  };
  return new Promise((resolve, reject) => {
    axios
      .post(url, json ? JSON.stringify(params) : QS.stringify(params), {
        headers: json ? headerJSON : headerFormData
      })
      .then(res => {
        resolve(res.data);
      })
      .catch(err => {
        reject(err.data);
      });
  });
}
// post接口封装：
// json或FormData格式请求头测试接口
   function  postHeader(params, isJson) {
    return  post('http://106.15.74.153:8080/api/search_word', params, isJson);
    }

    function passInput(){
      var searchWord =window.location.search;
      if(searchWord!="")
        emit('inputValue',inputValue.value);
      else{
        window.location.href="/SearchDetail?page=0&searchWord="+inputValue.value;
      }
    }
    return {
      restaurants,
      state1: ref(''),
      state2: ref(''),
      querySearch,
      handleSelect,
      postHeader,
      post,
      passInput,
      inputValue,
      keyDown,
    };
  },
});

</script>
