<template>
  <div class="main">
    <div id="search">
      <input type="text" name="infoInput" id="info" v-model="keyword" placeholder="请输入要查询的论文题目、ID或关键词"/>
      <el-button type="primary" id="send" @click="getSearchInfo">查询</el-button>
    </div>
    <el-main>
      <el-table
        :data="tableData"
        @row-click="showDetail"
        :row-style="{height: '60px'}"
        >
        <el-table-column prop="title" label="标题" width="160">
        </el-table-column>
        <el-table-column prop="meeting" label="所属会议" width="100">
        </el-table-column>
        <el-table-column prop="postDate" label="发布时间" width="100">
        </el-table-column>
        <el-table-column prop="keyword" label="关键字" width="230" :show-overflow-tooltip='true'>
        </el-table-column>
        <el-table-column prop="abstractContext" label="摘要" :show-overflow-tooltip='true'>
        </el-table-column>
      </el-table>
    </el-main>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'ShowList',
  data () {
    return {
     tableData: '',
     typeandyear:'',
     keyword:''
    }
  },
  methods: {
    getSearchInfo () {
      axios.post('http://localhost:5000/search',
      {keyword: this.keyword}).then((res) => {
        console.log(this.keyword)
        this.tableData = res.data
        //console.log(this.tableData)
        this.splitTypeAndYear()
      })
    },

    splitTypeAndYear(){
      for (var i = 0; i < this.tableData.length; i++) {
        this.typeandyear=this.tableData[i].typeandyear
        this.$set(this.tableData[i],'meeting',this.meeting)
        this.$set(this.tableData[i],'postDate',this.year)
      }
    },

    showDetail(row, column, event) {
      this.selectedObj=row
      let str=JSON.stringify(this.selectedObj)
      this.$router.push({path:'PaperDetail',query:{selectedObj:str}})
    }
  },

  computed:{
    meeting:function(){
      return this.typeandyear.match("[a-zA-z]+")[0];
    },
    year:function(){
      return this.typeandyear.match("[0-9]+")[0];
    }
  },

  created() {
    this.keyword=JSON.parse(this.$route.query.keyword);
    console.log(this.keyword)
    this.getSearchInfo()
  }
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
