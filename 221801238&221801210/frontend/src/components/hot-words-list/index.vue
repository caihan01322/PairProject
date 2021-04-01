<template>
  <div class="hot_words_contianer">
    <p class="title">热门领域</p>
    <el-divider />
    <div v-for="(wordData, index) in hotWordsList" class="hot_words">
      <div>
        <span :class="'order_' + index.toString()">{{ (index + 1) }}</span>
        <span class="word">{{ wordData }}</span>
      </div>
<!--      <span class="num">{{ wordData.num + '次' }}</span>-->
    </div>
  </div>
</template>

<script>
export default {
  name: "index.vue",
  data() {
    return {
      hotWordsList: []
    }
  },
  mounted() {
    this.$api.Data.getTopTen().then(res => {
      this.hotWordsList = res.data.data.words_list
    })
  }
}
</script>

<style lang="scss" scoped>

.hot_words_contianer {

  border-left: 1px solid gainsboro;
  padding-left: 30px;
  padding-top: 50px;

  .title {
    margin: 0;
    font-size: 26px;
    font-weight: bold;
  }

}

.hot_words {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-top: 7px;
  width: 450px;


  @for $num from 0 through 9{
    .order_#{$num} {
      color: rgba(254, 45 + ($num * 30), 45, 100);
      font-size: 20px;
      margin-right: 14px;
    }
  }


  .word {
    color: rgba(16, 16, 16, 100);
    font-size: 20px;
  }

  .num {
    color: rgba(170, 170, 170, 100);
    font-size: 14px;
  }


}
</style>