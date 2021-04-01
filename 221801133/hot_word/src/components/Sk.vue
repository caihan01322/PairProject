<template>
  <div class="app">
    <div id="nav">
      <img src="../assets/css/1.jpg" />
      <input class="nav-button" type="button" value="论文管理" @click="go1" />
      <input class="nav-button" type="button" value="论文分析" @click="go2" />
      <input class="nav-button" id="loginOut" type="button" value="注 销" @click="go3" />
    </div>
    <div class="main">
    <el-button type="info" plain @click="go2">返回</el-button>
      <el-table :data="tableData" style="width: 1050px" max-height="600">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="摘要">
                <span>{{ props.row.digest }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="序号" prop="id" width="100"></el-table-column>
        <el-table-column label="名称" prop="name" width="320"></el-table-column>
        <el-table-column label="会议" prop="meeting" width="80"></el-table-column>
        <el-table-column label="年份" prop="year" width="80"></el-table-column>
        <el-table-column label="关键词" prop="keyword" width="300"></el-table-column>
        <el-table-column label="链接" width="80">
          <template slot-scope="scope">
            <el-link type="info" @click="See(scope.row.link)">链接</el-link>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div id="footer">
      <input class="footer-button" id="indexButton" type="button" value="首页官网" onclick />
      <input class="footer-button" type="button" value="关于我们" onclick />
      <input class="footer-button" type="button" value="联系我们" onclick />
      <span id="copyright">Copyright © 2020. All rights reserved.</span>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      tableData: []
    };
  },
  created() {
    let key = this.$route.query.key;
    console.log(key);
    this.getKey(key);
  },
  mounted() {},
  methods: {
    getKey(k) {
      axios
        .get(`http://120.77.179.111:3300/data/anal?keyword=${k}`)
        .then(response => {
          this.tableData = response.data.data;
          console.log(response);
        })
        .catch(error => {
          console.log(error);
        });
    },
    go1() {
      this.$router.replace("/manage");
    },
    go2() {
      this.$router.replace("/analy");
    },
    go3() {
      this.$router.replace("/tour");
    }
  }
};
</script>
<style scoped>
.el-button{
    top: 85px;
    left: 40px;
    width: 100px;
}
.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 1100px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
}

.el-table {
  width: 1000px;
  top: 100px;
  left: 300px;
}

.main {
  width: 100%;
  height: 800px;
}
#wrap {
  background-color: #101010;
  color: #eae9e9;
  height: 1000px;
}
#nav,
#footer {
  height: 61px;
  background-color: #353d48;
}

input.nav-button,
input.footer-button {
  background-color: #353d48;
  border-color: #353d48;
  border-bottom-style: none;
  border-right-style: none;

  color: #eae9e9;
  font-family: 思源黑体;
  font-size: 16px;
  vertical-align: top;
  line-height: 24px;
  padding-top: 24px;
  padding-left: 40px;
}
input#findButton {
  padding-left: 73px;
}
input#loginOut {
  float: right;
  padding-right: 126px;
}
#footer {
  margin-top: -61px;

  border-top: 1px solid;
  border-color: #eae9e9;
}
input.footer-button {
  font-size: 14px;
  padding-left: 76px;
}
input.footer-button#indexButton {
  padding-left: 225px;
}
#copyright {
  padding-top: 24px;
  float: right;
  padding-right: 280px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  font-size: 14px;
}
</style>