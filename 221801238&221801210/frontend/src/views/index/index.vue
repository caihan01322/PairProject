<template>
  <div class="full_height" style="width: 100%">
    <!--登录按钮-->
    <el-button class="login_button">登录</el-button>
    <!--主体-->
    <el-row align="middle" justify="center" type="flex" class="full_height middle">
      <p class="title">论文直达</p>
      <el-row class="full_width" align="middle" justify="center" type="flex">
        <el-col class="super_search_contianer" :span="1">
          <div class="super_search_button">
            <i class="el-icon-plus"/>
          </div>
        </el-col>
        <el-col class="search_input_contianer" :span="10">
          <input type="text" class="input_area" placeholder="请输入关键词"/>
          <i class="el-icon-search icon"/>
        </el-col>
      </el-row>
      <div class="batch_input_button">
        <i class="el-icon-upload2 icon" />
        <p class="text">批量导入</p>
      </div>
    </el-row>

  </div>
</template>

<script>
export default {
  name: "index.vue",
  data() {
    return {
      //关键词
      keyword: ''
    }
  }
}
</script>

<style scoped lang="scss">
.login_button {
  width: 172px;
  background-color: rgba(28, 92, 157, 100);
  font-size: 20px;
  color: white;
  letter-spacing: 10px;
  position: fixed;
  top: 23px;
  right: 25px;
}

.middle {
  flex-direction: column;
  font-size: 72px;
  color: rgba(28,92,157,100);

  .title {
    margin-bottom: 37px;
    margin-top: 0;
  }
}

.super_search_contianer {
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

.batch_input_button {

  display: flex;
  align-items: center;
  justify-content: flex-end;
  position: relative;
  left: 20%;


  .text {
    font-size: 20px;
    font-weight: bold;
  }

  .icon {
    font-size: 24px;
    font-weight: bold;
  }
}
</style>