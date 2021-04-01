<template>
<div class="header">
  <div class="header-bar">
      <el-tooltip class="item" effect="dark" content="查看最热门的10个领域" placement="bottom-end" >
        <el-link :underline="false" class="header-font" style="margin-right:60px" href="/HotWords">热门领域</el-link>
      </el-tooltip>
      <el-tooltip class="item" effect="dark" content="可通过关键字、作者、标题搜索" placement="bottom-end" >
        <el-link :underline="false" class="header-font" href="/..">论文搜索</el-link>
      </el-tooltip>
  </div>

</div>
</template>

<script>
export default {
  setup() {
    function openHot(){
      window.location.href="/HotWords";
    }
    return{
      openHot,
    }
  },
}
</script>

<style>
  .header-font{
      line-height: 30px;
  }
  .header{
      margin-top:16px;
      display:flex;
      flex-direction:row-reverse;

  }

  .header-bar{
    height: 40px;
    display: flex;
    width: fit-content;
    flex-direction: unset;
    margin-right: 16%;
  }
</style>  