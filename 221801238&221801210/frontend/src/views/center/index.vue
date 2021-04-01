<template>
  <div class="center_contianer full_height">
    <div class="user_dialog">
      <div class="avatar" >
        <i class="el-icon-user-solid icon" />
      </div>
      <div class="dialog_input_contianer">
        <div class="dialog_input">
          <span>用户名：</span>
          <el-input v-model="user.name" class="input_area" />
        </div>
        <div class="dialog_input">
          <span>邮箱：</span>
          <el-input v-model="user.email" class="input_area"/>
        </div>
      </div>

      <div class="dialog_input_contianer">
        <el-button type="primary" class="dialog_button">保存</el-button>
        <el-button type="danger" class="dialog_button">修改密码</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      user: {
        name: '用户xxxx',
        email: '221801238@qq.com'
      }
    }
  },
  mounted() {
    this.user = this.$store.state.user
  }
}
</script>

<style lang="scss" scoped>
.center_contianer {
  display: flex;
  justify-content: center;
  align-items: center;
  width:  80%;

}

.user_dialog {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 615px;
  height: 593px;
  line-height: 20px;
  border-radius: 5px;
  text-align: center;
  box-shadow: 0 2px 6px 0 rgba(0, 0, 0, 0.4);
  border: 1px solid rgba(187, 187, 187, 100);

  .dialog_input_contianer {
    width: 400px;
    margin-top: 47px;

    .dialog_input {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .dialog_button:nth-child(1) {
      margin-right: 100px;
    }
  }
}

.avatar {
  width: 135px;
  height: 135px;
  border-radius: 100%;
  border: 4px solid rgba(223, 223, 223, 100);
}

.input_area {
  margin-top: 10px;
  width: 300px;
}

.icon {
  font-size: 130px;
  color: rgba(223, 223, 223, 100)
}
</style>