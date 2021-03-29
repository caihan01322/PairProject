<template>
  <div class="side-nav-bar">
    <div>
      <div class="avatar"/>
      <p class="username">用户XXXX</p>
      <div class="menu">
        <p class="title">菜单 Menu</p>
        <div v-for="menuItem in menuList" :key="menuItem.pageName" class="button">
          <i :class="'page_icon ' + menuItem.pageIcon" />
          <span class="page_name">{{ menuItem.pageName }}</span>
        </div>
      </div>
    </div>
    <p class="copy_right">
      Copyright © 2021 221801238 221801210
      All rights reserved.
    </p>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      menuList: [
        {
          pageIcon: 'el-icon-s-home',
          pageName: '主页'
        },
        {
          pageIcon: 'el-icon-user-solid',
          pageName: '个人中心'
        },
        {
          pageIcon: 'el-icon-folder',
          pageName: '收藏夹'
        },
        {
          pageIcon: 'el-icon-s-data',
          pageName: '数据分析'
        },
        {
          pageIcon: 'el-icon-switch-button',
          pageName: '登出'
        }
      ]
    }
  }
}
</script>

<style lang="scss" scoped>
.side-nav-bar {
  background-color: rgba(0, 35, 45, 100);
  height: 100%;
  width: 314px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.avatar {
  width: 135px;
  height: 135px;
  border-radius: 100%;
  border: 4px solid rgba(223, 223, 223, 100);
  margin-top: 27px;
  margin-left: 90px;
}

.username {
  margin-left: 122px;
  margin-top: 11px;
  color: rgba(255, 255, 255, 100);
  font-size: 16px;
}

.menu {
  .title {
    margin-left: 35px;
    font-size: 13px;
    color: rgba(162, 162, 162, 100);
    padding-bottom: 13px;
    border-bottom: 1px solid rgba(187, 187, 187, 100);
    width: 150px;
  }

  .button {
    margin-bottom: 24px;
    margin-left: 36px;
    display: flex;
    align-items: center;

    .page_icon {
      font-size: 30px;
      color: rgba(255, 255, 255, 100);
      margin-right: 30px;
    }

    .page_name {
      color: rgba(255, 255, 255, 100);
      font-size: 18px;
    }
  }
}

.copy_right {
  color: rgba(176, 176, 176, 100);
  font-size: 14px;
}
</style>