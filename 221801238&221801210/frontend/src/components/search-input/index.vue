<template>
  <el-row class="full_width" align="middle" justify="center" type="flex">
    <div class="super_search_contianer" >
      <div class="super_search_button">
        <i class="el-icon-plus"/>
      </div>
    </div>
    <div class="search_input_contianer" >
      <input type="text" class="input_area" placeholder="请输入关键词"/>
      <i class="el-icon-search icon"/>
    </div>
  </el-row>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      keyword: ''
    }
  }
}
</script>

<style lang="scss" scoped>

.super_search_contianer {
  margin-right: 17px;

  .super_search_button {
    width: 50px;
    height: 50px;
    background-color: rgba(255, 255, 255, 100);
    box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.4);
    border: 1px solid rgba(78, 110, 242, 100);
    border-radius: 100%;
    font-size: 28px;
    display: flex;
    justify-content: center;
    align-items: center;
    color: rgba(78, 110, 242, 100);
  }
}

.search_input_contianer {
  height: 78px;
  border-radius: 20px;
  width: 908px;
  background-color: rgba(255, 255, 255, 100);
  color: rgba(16, 16, 16, 100);
  font-size: 14px;
  text-align: center;
  font-family: Roboto, serif;
  border: 3px solid rgba(35, 132, 230, 73);
  display: flex;
  justify-content: center;
  align-items: center;

  .input_area {
    width: 90%;
    height: 80%;
    border: 0;
    font-size: 28px;
    background-color: transparent;
    outline: none
  }

  .icon {
    font-size: 28px;
    color: rgba(35, 132, 230, 100);
    font-weight: bold;
  }
}
</style>