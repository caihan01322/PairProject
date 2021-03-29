<template>
  <div class="full_height" style="width: 100%">
    <!--登录按钮-->
    <el-button class="login_button">登录</el-button>
    <!--主体-->
    <el-row align="middle" justify="center" type="flex" class="full_height middle">
      <p class="title">论文直达</p>
      <el-row class="full_width" align="middle" justify="center" type="flex">
        <searhInput />
      </el-row>
      <div class="batch_input_button">
        <i class="el-icon-upload2 icon" />
        <p class="text">批量导入</p>
      </div>
    </el-row>

  </div>
</template>

<script>
import searhInput from '@/components/search-input'
export default {
  name: "index.vue",
  components: {
    searhInput
  },
  data() {
    return {

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


.batch_input_button {

  display: flex;
  align-items: center;
  justify-content: flex-end;
  position: relative;
  left: 17%;


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