<template>
  <div id="index">
    <Sider :style="{background: '#fff', position: 'fixed', height: '100vh', left: 0, overflow: 'auto'}">
      <div id="totleTitle">
        <div>杰宝论文</div>
      </div>
      <div id="avtarShow">
        <img src="../assets/images/jbavtar.jpg">
      </div>
      <Menu active-name="1" theme="light" width="auto" accordion>
        <MenuItem name="1" to="search">
            <Icon type="ios-navigate"></Icon>
            论文检索
        </MenuItem>
        <MenuItem name="2" to="record">
            <Icon type="ios-keypad"></Icon>
            收藏记录
        </MenuItem>
        <Submenu name="3">
          <template slot="title">
            <Icon type="ios-analytics"></Icon>
            热词分析
          </template>
          <MenuItem name="3-1" to="wordcloud">热词图谱</MenuItem>
          <MenuItem name="3-2" to="wordtrend">热度走势</MenuItem>
          <MenuItem name="3-3" to="wordcontrast">热度对比</MenuItem>
        </Submenu>
      </Menu>
    </Sider>
    <Layout :style="{marginLeft: '200px'}">
      <Header id="Header" :style="{background: '#fff', boxShadow: '0 2px 3px 2px rgba(0,0,0,.1)'}">
        <div class="avtarShow">
          <img src="../assets/images/jbavtar.jpg">
        </div>
        <div class="username">1227-1208</div>
      </Header>
      <Content :style="{margin: '20px', background: '#fff', minHeight: '220px'}">

        <Card>
          <div id="indexContent">
            <router-view></router-view>
          </div>
        </Card>
      </Content>
    </Layout>
  </div>
</template>

<script>
export default {
  name: 'main',
  data () {
    return {
      // name: ''
    }
  },
  computed: {
  },
  methods: {
  }
}
</script>

<style scoped lang="less">
  #index {
    width: 100%;
    height: 100%;
  }
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    border-radius: 4px;
    overflow: hidden;
  }
  .layout-header-bar{
    background: #fff;
    box-shadow: 0 1px 1px rgba(0,0,0,.1);
  }
  #totleTitle {
    width: 100%;
    height: 64px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    /*border-bottom: solid 1px #D7D7D7;*/
    >div {
      font-size: 26px;
      font-weight: 700;
      color: #0096FA;
    }
  }
  #avtarShow {
    width: 100%;
    height: 200px;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    img {
      width: 150px;
      height: 150px;
      border-radius: 50%;
    }
  }
  #Header {
    display: flex;
    flex-direction: row;
    justify-content: flex-end;
    align-items: center;
    >div{
      margin: 0 10px;
    }
    .avtarShow {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      width: 20px;
      /*height: 20px;*/
      img {
        width: 35px;
        height: 35px;
        border-radius: 50%;
      }
    }
  }
  #indexContent {
    min-height: 800px;
  }

</style>
