<template>
  <div class="app">
    <el-button type="primary" plain @click="postPackage2">关键词</el-button>
    <el-button type="primary" plain @click="getKey">单机关键词</el-button>
    
  </div>
</template>
<script>
import axios from "axios";
export default {
  data() {
    return {
      tableData: [
      ],
      Form: {
        package: "papers",
        page: "1"
      },
      package: "papers",
      page: "1",
      input: "Image Plane",
      allData: {},
      search: {},
      allKey: {},
      seachKey: {},
      Form2: {
        metting: "ECCV",
        year: "2000"
      }
    };
  },
  methods: {
    postPackage() {
      axios
        .post("http://120.77.179.111:3300/data/manager", this.Form, function(
          data,
          status
        ) {})
        .then(res => {
          this.allData = res.data;
          console.log(this.allData);
        });
    },
    getSearch() {
      axios
        .get(
          `http://localhost:3000/data/manager?input=&&package=papers&page=1`
        )
        .then(response => {
          this.tableData = response.data;
          console.log(res);
        })
        .catch(error => {
          console.log(error);
        });
    },
    //关键词统计+热词top10
    postPackage2() {
      axios
        .post("http://120.77.179.111:3300/data/anal", this.Form2, function(
          data,
          status
        ) {})
        .then(res => {
          this.allKey = res.data;
          console.log(this.allKey);
        });
    },
    getKey() {
      axios
        .get(`http://localhost:3000/data/anal?keyword=Image`)
        .then(response => {
          this.tableData = response.data.data;
          console.log(tableData);
        })
        .catch(error => {
          console.log(error);
        });
    }
  }
};
</script>