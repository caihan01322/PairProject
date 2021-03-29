<template>
  <div class="search_option_contianer">
    <el-radio-group v-model="searchOption.searchMethod">
      <el-radio :label="1">在当前收藏夹搜索</el-radio>
      <el-radio :label="2">在所有收藏夹搜索</el-radio>
    </el-radio-group>
    <el-radio-group class="search_order" v-model="searchOption.sortOrder" size="mini">
      <el-radio-button :label="1">出版日期升序</el-radio-button>
      <el-radio-button :label="2">出版日期将序</el-radio-button>
      <el-radio-button :label="3">被引用量</el-radio-button>
      <el-radio-button :label="4">查看量</el-radio-button>
    </el-radio-group>
  </div>
</template>

<script>
export default {
  name: "search_option",
  data() {
    return {
      searchOption: {
        searchMethod: 1,
        selectMode: true,
        sortOrder: 1
      }
    }
  }
}
</script>

<style scoped>
.search_option_contianer {
  width: 910px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-top: 10px;
  margin-left: 80px;

}
</style>