<template>
  <div class="paper_contianer">
    <div v-for="paper in paperList" class="paper_info">
      <div class="paper_detail">
        <p class="paper_title">{{ paper.title  }}</p>
        <p>{{ '作者：' + paper.author }}</p>
        <div>
          <p>关键词：</p>
          <el-tag v-for="word in paper.keywords">{{ word }}</el-tag>
        </div>

        <p>{{ '摘要：' + paper.abstract }}</p>
        <p>{{ '原文链接：' + paper.url }}</p>
      </div>
      <el-checkbox class="collect_button" v-model="collection" :label="paper.id" border v-if=" mode === 'search' ">添加至收藏夹</el-checkbox>
      <el-button class="collect_button"  v-if=" mode === 'favorite' " type="danger">删除</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "search-result-list",
  props: {
    mode: {
      type: String
    }
  },
  data() {
    return {
      collection: []
    }
  },
  created() {
    console.log(this.$store.state.paperList)
  },
  computed: {
    paperList() {
      return this.$store.state.paperList
    }
  }
}
</script>

<style lang="scss" scoped>

.paper_contianer {
  margin-left: 150px;
  margin-top: 10px;
}

.paper_info {
  border-bottom: 1px solid rgba(187, 187, 187, 100);
  display: flex;
  align-items: center;
  position: relative;
  width: 850px;

  .paper_detail {
    margin-left: 25px;

    .paper_title {
      font-size: 26px;
    }

    p {
      color: rgba(16, 16, 16, 100);
      font-size: 20px;
    }
  }

  .collect_button {
    position: absolute;
    bottom: 29px;
    right: 0;
    z-index: -1;
  }

}


</style>