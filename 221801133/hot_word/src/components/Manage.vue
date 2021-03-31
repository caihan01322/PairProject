<template>
  <div id="wrap">
    <div id="nav">
      <img src="../assets/css/1.jpg" />
      <input class="nav-button" type="button" value="论文管理" @click="go1" />
      <input class="nav-button" type="button" value="论文分析" @click="go2" />
      <input class="nav-button" id="loginOut" type="button" value="注 销" @click="go3" />
    </div>

    <div id="main">
      <div id="userInfo">
        <div id="face"></div>
        <div id="personInf">
          <div id="name">
            <h2>昵称</h2>
            <p>id:XXXXXX</p>
            <p>
              我的收藏：
              <span id="myCol">10</span>
            </p>
            <p>
              我的分享：
              <span id="myShare">13</span>
            </p>
          </div>
          <div id="history">
            <h4 id="historyTit">历史记录</h4>
            <ul>
              <li>ICCV 论文题目（一）</li>
              <li>ICCV 论文题目（二）</li>
              <li>ICCV 论文题目（三）</li>
              <li>ICCV 论文题目（四）</li>
              <li>ICCV 论文题目（五）</li>
              <li>ICCV 论文题目（六）</li>
            </ul>
          </div>
        </div>
      </div>
      <div id="modular">
        <div id="search">
          <p>检索</p>
          <el-input placeholder="请输入论文名称或关键词进行检索" v-model="content"></el-input>
          <el-button icon="el-icon-search" circle @click="getSearch"></el-button>
        </div>
        <div id="table">
          <el-table :data="allData" style="width: 1150px" max-height="600">
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
        <el-table-column label="名称" prop="name" width="350"></el-table-column>
        <el-table-column label="会议" prop="meeting" width="80"></el-table-column>
        <el-table-column label="年份" prop="year" width="80"></el-table-column>
        <el-table-column label="关键词" prop="keyword" width="300"></el-table-column>
        <el-table-column label="链接" width="80">
          <template slot-scope="scope">
            <el-link type="info" @click="See(scope.row.link)">链接</el-link>
          </template>
        </el-table-column>
        <el-table-column label="操作">
              <template slot-scope="scope">
                <button class="delet" @click="Delet(scope.row)">删除</button>
              </template>
            </el-table-column>
      </el-table>
          <div id="pagepos">
            <el-pagination
              layout="prev, pager,next"
              :current-page="pages.page"
              @current-change="currentPage"
              :total="80"
            ></el-pagination>
          </div>
        </div>
      </div>
    </div>

    <div id="footer">
      <input class="footer-button" id="indexButton" type="button" value="首页官网" onclick />
      <input class="footer-button" type="button" value="关于我们" onclick />
      <input class="footer-button" type="button" value="联系我们" onclick />
      <span id="copyright">Copyright © 2020. All rights reserved.</span>
    </div>
  </div>
</template>
<style scoped>
body {
  margin: 0;
  background-color: #101010;
  height: 1043px;
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


button{
  border: none;
  background-color:white;
  color:#9e9399;
  width: 50px;
  height: 40px;

}

div {
  font-family: 微软雅黑;
  vertical-align: top;
}
#main {
  height: 100%;
  width: 100%;
}
#wrap {
  background-color: #101010;
  color: #eae9e9;
  height: 1000px;
}
#main {
  height: 100%;
}
#userInfo,
#modular {
  display: inline-block;
}

#search {
  display: inline-block;
  width: 100%;
  height: 25%;
  vertical-align: top;
}

#search p {
  font-size: 40px;
  width: 100px;
  padding-left: 200px;
  padding-top: 80px;
}

.el-input {
  width: 400px;
  height: 50px;
  position: absolute;
  left: 340px;
  top: 127px;
}

#table {
  display: inline-block;
  width: 100%;
  height: 75%;
  vertical-align: top;
}
#userInfo {
  width: 22%;
  height: 607px;
  margin-top: 39px;

  position: relative;
}
#modular {
  width: 77%;
  height: 89%;

  position: relative;
}
#face {
  background-color: #bbbbbb;
  border: 1px solid #bbbbbb;
  border-radius: 50%;

  width: 121px;
  height: 122px;

  position: absolute;
  left: 90px;
  top: 22px;
  z-index: 3;
}
#face img {
  vertical-align: middle;
  margin: 0 auto;
  padding: 40% 30%;
}
#personInf {
  position: absolute;
  left: 40px;
  top: 70px;

  background-color: #292f37;
  width: 220px;
  height: 493px;
  z-index: 1;
}
#name {
  margin: 100px auto 0px auto;
  text-align: center;
}
#history {
  text-align: center;
}
#history ul li {
  list-style: none;
  margin-top: 3px;
  text-align: center;
}
#historyTit {
  text-align: left;
  margin-left: 25px;
}
#name p {
  margin: 12px;
  padding: 0;
}
#nav,
#footer {
  height: 61px;
  background-color: #353d48;
}
#sign {
  width: 41px;
  height: 41px;
  padding-left: 90px;
  padding-top: 11px;
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

#nav img {
  padding-left: 80px;
  width: 70px;
  height: 60px;
}

#pagepos {
  float: right;
  text-align: center;
  margin-top: 10px;
}

.el-button {
  position: absolute;
  left: 760px;
  top: 127px;
  border: none;
  /*background-color: #353d48;*/
}

#pagepos {
  position: absolute;
  right: 125px;
}
</style>

<script>
/* axios链接 */
import axios from "axios";
export default {
  data() {
    return {
      content: "",
      allData: [],
      flag: true,
      //jud="",
      pages: {
        package: "papers",
        page: "1"
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    go1() {
      this.$router.replace("/manage");
    },
    go2() {
      this.$router.replace("/analy");
    },
    go3() {
      this.$router.replace("/tour");
    },
    Digest(r) {
      window.confirm(r.digest,"摘要");
    },
    Delet(r) {
      if (confirm("确认是否要删除这篇文章？", "提示")) {
        axios
          .get(
            `http://120.77.179.111:3300/data/manager?package=${this.pages.package}&&delid=${r.id}&&page=${this.pages.page}`
          )
          .then(response => {
            this.flag = true;
            this.getList();
            //this.allData = res.data.data;
          })
          .catch(error => {
            console.log(error.response.data);
          });
      }
    },
    getList() {
      axios
        .post("http://120.77.179.111:3300/data/manager", this.pages, function(
          data,
          status
        ) {})
        .then(res => {
          this.flag = true;
          this.allData = res.data.data;
          console.log(this.allData);
        });
    },
    currentPage(val) {
      this.pages.page = val;
      this.getList();
      if (this.flag == true) {
        console.log("456");
        this.getList();
      } else {
        console.log("123");
        this.getSearch();
      }
    },
    getSearch() {
      axios
        .get(
          `http://120.77.179.111:3300/data/manager?input=${this.content}&&package=${this.pages.package}&&page=${this.pages.page}`
        )
        .then(response => {
          //console.log(response.data.data);
          this.allData = response.data.data;
          this.flag = false;
        })
        .catch(error => {
          console.log(error);
        });
    },
    See(e) {
      // 去除双引号
      e = e.replace(/"/g, "");
      console.log(e);
      window.open(e);
    },
    open(s) {
      var a = this.data;
      this.$alert(s, "摘要", {
        confirmButtonText: "确定",
        callback: action => {
          this.$message({
            type: "info",
            message: `action: ${action}`
          });
        }
      });
    }
  }
};
</script>