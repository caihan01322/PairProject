<template>

  <div class="essay_frame" >
    <div class="check" style="background-color: #42b983" >
      <el-checkbox class="checkbox" :label="this.id" :key="this.id" v-model="this.id"
                   size="medium" ></el-checkbox>
    </div>
    <div class="title" @click="openLink(link)">{{ title }}</div>
    <div class="authors">
      <span class="author" v-for="author in authors" :key="author">{{ author }}</span>
    </div>
    <div class="abstract" @click="openLink(link)">{{ abstract }}</div>
    <div class="keywords">
        <div class="keyword" v-for="keyword in keywords" :key="keyword">{{ keyword }}</div>
    </div>
  </div>

</template>

<script>
export default {
  name:"Essay",
  props: {
    title: {
      type: String,
      default: ''
    },
    authors: {
      type: Array,
      default: () => []
    },
    abstract: {
      type: String,
      default: ''
    },
    keywords: {
      type: Array,
      default: () => []
    },
    id: {
      type: Number,
      default: null
    },
    link: {
      type: String,
      default: ''
    }
  },
  methods:{
    openLink(link){
      window.open(link, '论文详情页');
    }
  },
  data(){
    return{
      // checked: false
    }
  }

}
</script>

<style lang="less" scoped>

.checkbox{
  float: right;
  //margin-right: 10px;
}

.essay_frame {
  font-family: "微软雅黑", sans-serif;
  background-color: white;
  border-radius: 15px;
  width: 96%;
  margin-left: 2%;
  height: auto;
  padding-top: 30px;
  padding-bottom: 20px;
  margin-bottom: 30px;
}
.title:hover{
  color: #4084d0;
  cursor: pointer;
}

.title {
  margin-left: 25px;
  margin-right: 25px;
  font-size: 20px;
  font-weight: bolder;
  color: black;
  text-align: left;
}

.authors {
  margin-top: 10px;
  margin-left: 25px;
  margin-right: 25px;
  alignment: left;
  text-align: left;
}

.author{
  font-size: 15px;
  color: #333333;
  font-weight: bolder;
  margin-right: 20px;
  text-align: justify-all;
}

.abstract {
  margin-top: 20px;
  margin-left: 25px;
  margin-right: 25px;
  font-size: 15px;
  color: black;
  text-align: left;
}

.keywords {
  margin-top: 10px;
  margin-left: 15px;
  margin-right: 15px;
  alignment: left;
  text-align: left;
  line-height: 30px;
  display: flex;
  flex-wrap: wrap;
}

.keyword{
  margin: 10px;
  border-radius: 10px;
  background-color: #efefef;
  font-size: 10px;
  color: #333333;
  text-align: left;
  padding-left: 10px;
  padding-right: 10px;
  height: 30px;
  user-select: none;
}

.keyword:hover{
  color: #4084d0;
  cursor: pointer;
}

.checkbox /deep/.el-checkbox__inner{
  ::after{
    left: 7px;
    top: 3px;
  }
  width: 20px;
  height: 20px;
}

.checkbox /deep/ .el-checkbox__label{
  visibility: hidden;
}

</style>
