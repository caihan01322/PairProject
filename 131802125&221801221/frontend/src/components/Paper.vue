<template>
  <div
    v-infinite-scroll="handleInfiniteOnLoad"
    class="demo-infinite-container"
    :infinite-scroll-disabled="busy"
    :infinite-scroll-distance="10"
  >
    <a-list :data-source="data" size=large itemLayout="vertical" >
      <a-list-item slot="renderItem" slot-scope="item">
            <div style="display:flex;">
              <div>
              <a-list-item-meta :description="item.magazine">
                <a slot="title" :href="item.link">{{ item.title }}</a>
              </a-list-item-meta>
              <p>{{item.publication_year}}</p>
              <div>{{item.abstract}}</div>
            </div>
              <div >
                <a-button  type="danger" @click="deletePaper(item.paper_id)">
                    删除
                </a-button>
              </div>
            </div>
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
      paper_id:"",
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
      console.log(key);
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
    deletePaper (value){
      reqwest({
        url: dataUrl,
        type: 'json',
        method: 'post',
        data: {
          "token": token,
          "paper_id": value
        },
        contentType: 'application/json',
        success: res => {
          pageNum++;
          dataUrl = 'http://47.98.152.179:5000/xjbs/api/v1/paper/delete';
          callback(res.data);
          alert(删除成功);
        },
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