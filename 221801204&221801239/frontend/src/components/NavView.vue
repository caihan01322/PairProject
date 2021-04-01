<template>
  <div id="nav">
    <div id="image">
      <img class="pic" src="../assets/images/logo.png" alt=""/>
    </div>
    <div id="menu">
      <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
      >
        <el-menu-item index="1" @click="jumpToFirst">首页</el-menu-item>
        <el-menu-item index="2" @click="jumpToSearch">论文列表</el-menu-item>
        <el-menu-item index="3" @click="jumpToCloud">关键词图谱</el-menu-item>
        <el-menu-item index="4" @click="jumpToChart">热度走势图</el-menu-item>
      </el-menu>
    </div>
    <div>
      <button @click="Logout" id="out" v-if="this.state.index!==0">退出登录</button>
    </div>
  </div>
</template>

<script>
import bus from "@/assets/eventBus";

export default {
  data() {
    return {
      activeIndex: "1",
      state: {
        index: 0,
        status: 0
      }

    };
  },
  methods: {
    handleSelect(key, keyPath) {
    },
    jumpToFirst() {
      this.state.index = 0;
      this.$router.push("/Login");

    },
    jumpToSearch() {

      if (this.state.status === 1) {
        this.state.index = 1;
        this.$router.push("/Search");
      } else {
        this.$message.error("请先登录!")
      }

    },
    jumpToChart() {
      if (this.state.status === 1) {
        this.state.index = 1;
        this.$router.push("/ChartView");
      } else {
        this.$message.error("请先登录!")
      }
    },
    jumpToCloud() {
      if (this.state.status === 1) {
        this.state.index = 1;
        this.$router.push("/WordCloudView");
      } else {
        this.$message.error("请先登录!")
      }
    },
    Logout() {
      this.state.index = 0;
      this.state.status = 0;
      this.$router.push("/Login");
    }
  },
  mounted() {
    bus.$on("sendStatus", state => {
      this.state = state;
    });
  },
};
</script>

<style lang="less" scoped>
#nav {
  height: 90px;
  width: auto;
  background-color: rgb(0, 0, 0);

  #image {
    width: 337px;
    height: 90px;
    float: left;
    margin-left: 236px;
  }

  #menu {
    width: 1100px;
    float: left;
  }

  .pic {
    width: 337px;
    height: 90px;
    z-index: 2000;
  }

  ::v-deep.el-menu.el-menu--horizontal {
    border-bottom: solid 1px #e6e6e6;
    background-color: black;
  }

  ::v-deep.el-menu-item {
    font-size: 26px;
    height: 90px;
    width: 160px;
    line-height: 90px;
    color: #f1f3f5;
    align-content: center;
    text-align: center;
    cursor: pointer;
    transition: border-color 0.3s, background-color 0.3s, color 0.3s;

    margin-left: 40px;
  }

  ::v-deep.el-menu-item:hover {
    background-color: gray;
  }

  #out {
    background-color: black;
    color: white;
    height: 50%;
    margin-top: 50px;
  }
}
</style>
