<template>
  <div class="paper_contianer">
    <div v-for="paper in paperList" class="paper_info">
      <div class="cover"/>
      <div class="paper_detail">
        <p>{{ paper.title  }}</p>
        <p>{{ '作者：' + paper.author }}</p>
        <p>{{ '关键词：' + paper.keywords }}</p>
        <p>{{ '摘要：' + paper.abstract }}</p>
        <p>{{ '原文链接：' + paper.url }}</p>
      </div>
      <el-checkbox class="collect_button" v-model="collection" :label="paper.id" border>添加至收藏夹</el-checkbox>
    </div>
  </div>
</template>

<script>
export default {
  name: "search-result-list",
  data() {
    return {
      paperList: [],
      collection: []
    }
  },
  mounted() {
    for (let i = 0; i < 5; i++) {
      this.paperList.push({
        id: i,
        title: '论文标题xxxxxxxxxxxxxxxxx',
        author: 'xxxxxxx',
        keywords: 'xxxxxxx',
        abstract: 'xxxxxxx',
        url: 'xxxxxxx'
      })
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


  .cover {
    width: 171px;
    height: 222px;
    border: 1px solid black;
  }

  .paper_detail {
    margin-left: 25px;

    p {
      color: rgba(16, 16, 16, 100);
      font-size: 20px;
    }
  }

  .collect_button {
    position: absolute;
    bottom: 29px;
    right: 0;
  }

}
</style>