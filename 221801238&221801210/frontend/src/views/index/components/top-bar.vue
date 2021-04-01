<template>
  <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect" router>
    <el-menu-item index="/center">个人中心</el-menu-item>
    <el-menu-item index="/favourite">收藏夹</el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "top-bar",
  data() {
    return {
      activeIndex: ''
    }
  },
  methods: {
    handleSelect(){
      console.log(arguments)
    }
  }
}
</script>

<style scoped>
.el-menu.el-menu--horizontal {
  border-bottom: 0 !important;
}
</style>