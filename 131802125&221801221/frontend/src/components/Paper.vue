<template>
  <div
    v-infinite-scroll="handleInfiniteOnLoad"
    class="demo-infinite-container"
    :infinite-scroll-disabled="busy"
    :infinite-scroll-distance="10"
  >
    <a-list :data-source="data" size=large itemLayout="vertical" >
      <a-list-item slot="renderItem" slot-scope="item">
        <a-list-item-meta :description="item.email">
          <a slot="title" :href="item.link">{{ item.title }}</a>
        </a-list-item-meta>
            <p>{{item.magazine}} in {{item.publication_year}}</p>
        <div>{{item.abstract}}</div>
      </a-list-item>
      <div v-if="loading && !busy" class="demo-loading-container">
        <a-spin />
      </div>
    </a-list>
  </div>
</template>
<script>
import reqwest from 'reqwest';
import infiniteScroll from 'vue-infinite-scroll';
var pageNum = 1;
var key = localStorage.getItem("key");
var maxPage = localStorage.getItem("maxPage");
var token = localStorage.getItem("token")
var dataUrl = 'http://47.98.152.179:5000/xjbs/api/v1/paper/search?page=' + pageNum +'&key=' + key + '&token=' + token;
export default {
  directives: { infiniteScroll },
  data() {
    return {
      data: [],
      loading: false,
      busy: false,
    };
  },
  beforeMount() {
    this.fetchData(res => {
      this.data = res;
    });
  },
  methods: {
    fetchData(callback) {
        console.log(dataUrl)
      reqwest({
        url: dataUrl,
        type: 'json',
        method: 'get',
        contentType: 'application/json',
        success: res => {
          pageNum++;
          dataUrl = 'http://47.98.152.179:5000/xjbs/api/v1/paper/search?page=' + pageNum +'&key=' + key + '&token=' + token;
          callback(res.data);
        },
      });
    },
    handleInfiniteOnLoad() {
      const data = this.data;
      this.loading = true;
      if (pageNum > maxPage) {
        this.$message.warning('没有更多结果了...');
        this.busy = true;
        this.loading = false;
        return;
      }
      this.fetchData(res => {
        this.data = this.data.concat(res);
        this.loading = false;
      });
    },
  },
};
</script>
<style>
.demo-infinite-container {
  border: 1px solid #e8e8e8;
  border-radius: 4px;
  overflow: auto;
  padding: 8px 24px;
  height: 300px;
}
.demo-loading-container {
  position: absolute;
  bottom: 40px;
  width: 100%;
  text-align: center;
}
</style>