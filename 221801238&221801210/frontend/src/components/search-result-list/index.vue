<template>
  <div class="paper_contianer">
    <div v-for="paper in paperList" class="paper_info">
      <div class="paper_detail">
        <p class="paper_title">{{ paper.title  }}</p>
        <el-divider />
        <div class="info_contianer">
          <el-tag>作者</el-tag>
          <span class="text">{{ paper.author || '暂无数据' }}</span>
        </div>
        <div class="info_contianer">
          <el-tag>摘要</el-tag>
          <span class="text">{{  paper.abstract.slice(0,200) + '...' }}</span>
        </div>
        <div class="info_contianer">
          <el-tag>原文链接</el-tag>
          <span class="text">
            <a :href=" paper.url " >{{ paper.url }}</a>
          </span>
        </div>
        <div>
          <el-tag v-for="(word,index) in paper.keywords" class="tag" effect="dark"
          :type="getTagsColor(index)"
          >{{ word }}</el-tag>
        </div>
        <el-divider />
        <div class="button_group">
          <el-checkbox class="collect_button" v-model="collection" :label="paper.id" border v-if=" mode === 'search' ">添加至收藏夹</el-checkbox>
          <el-button class="collect_button"  v-if=" mode === 'favorite' " type="danger">删除</el-button>
        </div>
      </div>
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
      collection: [],
      colorType: ['','success','info','danger','warning']
    }
  },
  created() {
    console.log(this.$store.state.paperList)
  },
  methods: {
    getTagsColor(index) {
      return this.colorType[index % 5]
    }
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
  margin-top: 10px;
  margin-left: 80px;
}

.paper_info {
  display: flex;
  align-items: center;
  position: relative;
  width: 850px;

  .paper_detail {
    margin-left: 25px;

    .paper_title {
      font-size: 20px;
      font-weight: bold;
    }

    .button_group {
      display: flex;
      justify-content: flex-end;
    }

    p {
      color: rgba(16, 16, 16, 100);
      font-size: 20px;
    }
  }



}

.tag {
  margin: 5px
}

.info_contianer {
  margin-top: 10px;
  margin-bottom: 5px;
  .text {
    margin-left: 5px;
  }
}





</style>