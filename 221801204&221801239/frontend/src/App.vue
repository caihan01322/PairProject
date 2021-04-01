<template>
  <div id="app">
    <NavView/>
    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import NavView from "@/components/NavView";

export default {
  components: {
    NavView,
  },
};
</script>

<style lang="less" scoped>
* {
  padding: 0;
  margin: 0;
  font-family: "微软雅黑", serif;
}

.main {
  height: 100%;
  width: 100%;
  background: rgba(53, 64, 68, 1);

}
</style>
