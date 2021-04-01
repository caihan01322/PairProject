<template>
  <div class="search">
    <div id="srh">
      <el-select id="sec" v-model="value" placeholder="请选择">
        <el-option v-for="item in types" :key="item.value" :value="item.value">
          <div style="color: #000; font-size: 18px">{{ item.value }}</div>
        </el-option>
      </el-select>
      <el-input v-model="content"></el-input>
      <el-button type="primary" @click.native="searchArticle()">搜索</el-button>
    </div>

    <ArticleItemView
      v-for="item in showArray"
      :key="item.id"
      :itemObj="item"
    />

    <div>
      <button class="prior" @click="formerPage()">&lt;上一页</button>
      <button class="next" @click="nextPage()">&gt;下一页</button>
    </div>
  </div>
</template>

<script>
import ArticleItemView from "@/components/ArticleItemView";
import bus from '@/assets/eventBus';
import axios from "axios";

export default {
  components: {ArticleItemView},
  data() {
    return {
      //从数据库取出来的数组
      listArray: [
        {
          title: "",
          keywordsArray: [],
          abstracted: "",
          link: "",
          paper_id: "",
          publication_year: ""
        }
      ],
      //展示在页面上的数组
      showArray: [],
      //showArray从这个数组取值
      searchArray: [],
      pageNum: 0,
      pageSize: 10,

      types: [
        {
          value: "论文名",
        },
        {
          value: "关键词",
        },
        {
          value: "发布时间",
        },
      ],
      value: "论文名",
      content: "",
    };
  },
  methods: {
    query() {
      this.$axios
        .get('http://localhost:8081/getPapers', {})
        .then(res => {

          this.listArray = res.data
          this.searchArray = this.listArray
          this.showArray = this.listArray
          this.divideList()
        })
        .catch(failResponse => {
        })
    },
    getList() {
      if (this.content !== "") {
        if (this.value === "论文名") {
          this.searchArray = this.listArray.filter((item, index) =>
            item.title.includes(this.content));
        } else if (this.value === "关键词") {
          this.searchArray = this.listArray.filter((item, index) =>
            item.keywordsArray.indexOf(this.content) > -1);

        } else if (this.value === "发布时间") {
          this.searchArray = this.listArray.filter((item, index) =>
            item.publication_year === this.content);

        } else {
          this.searchArray = this.listArray
        }
      }
    },
    divideList() {
      this.showArray = this.searchArray.filter(
        (item, index) =>
          index < (this.pageNum + 1) * this.pageSize &&
          index >= this.pageSize * this.pageNum
      );
    },
    searchArticle() {
      this.pageNum = 0;
      this.getList();
      this.divideList();
    },
    nextPage() {
      if ((this.pageNum + 1) * this.pageSize > this.searchArray.length) {
        alert("已经到最后一页了!");
      } else {
        this.pageNum++;
        this.divideList();
      }
    },
    formerPage() {
      if (this.pageNum > 0) {
        this.pageNum--;
        this.divideList();
      } else {
        alert("已经在第一页了!");
      }
    }
  },
  created() {
    this.query();
  },
  mounted() {
    this.getList()
    bus.$on("delete", id => {
      for (let i = 0; i < this.showArray.length; i++) {
        if (this.showArray[i].paper_id === id) {
          this.showArray.splice(i, 1)
        }
      }
    });
  }
};
</script>

<style lang="less" scoped>
.search {
  height: 100%;
  background-color: rgb(214, 234, 234);
  min-height: 1200px;
}

#srh {
  height: 180px;

  /deep/ .el-select {
    display: inline-block;
    font-size: 20px;
    height: 55px;
    width: 140px;
    margin-top: 55px;
    margin-left: 680px;
  }

  /deep/ .el-input {
    width: 400px;
    height: 55px;

    /deep/ .el-input__inner {
      background-color: #fff;
      background-image: none;
      border-radius: 4px;
      border: 1px solid #dcdfe6;
      box-sizing: border-box;
      color: #131314;
      display: inline-block;
      font-size: 20px;
      height: 55px !important;
      line-height: 55px !important;
      width: 100%;
    }
  }

  /deep/ .el-button--primary {
    color: #fff;
    background-color: #3e4042;
    border-color: #212122;
    width: 140px;
    height: 55px;
    font-size: 17pt;
  }
}

.next {
  height: 40px;
  width: 80px;
}

.prior {
  margin-left: 900px;
  height: 40px;
  width: 80px;
}
</style>
