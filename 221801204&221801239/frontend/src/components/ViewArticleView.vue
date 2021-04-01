<template>
  <div class="detail">
    <div id="view">
      <div id="top">
        <h1>标题:{{ itemObj.title }}</h1>
        <div id="number">编号:{{ itemObj.paper_id }}</div>
      </div>
      <div id="center">
        关键词:
        <div
          class="spr"
          style="display: inline"
          v-for="item in this.itemObj.keywordsArray"
          :key="item"
        >
          {{ item }}
        </div>
        <p id="cont">{{ itemObj.abstracted }}</p>
      </div>

      <div id="bottom">
        <div id="time">发表时间:{{ itemObj.publication_year }}</div>
        <div id="alink">
          原文链接:<a href="itemObj.link">{{ itemObj.link }}</a>
        </div>
        <button id="back" @click="backToView">返回</button>
      </div>
    </div>

  </div>
</template>

<script>
import bus from "@/assets/eventBus";

export default {
  data() {
    return {
      itemObj: {
        type: Object,
        default: function () {
          return {};
        },
      },

    };
  },
  mounted() {
    bus.$on("sendData", item => {
      this.itemObj = item;
    });

  },
  methods: {
    backToView() {
      this.$router.replace("/search");
    }
  }

};
</script>

<style lang="less" scoped>


.detail {
  background-color: white;
  width: 100%;
  height: 100%;

  #view {
    height: 100%;
    background-color: #D6EAEA;
    margin-top: 2%;
    margin-left: 25%;
    width: 50%;
    border-radius: 10px;
    box-shadow: 0 5px 20px rgba(25, 25, 25, .75);
    padding: 40px;

    h1 {
      text-align: center;
    }

    #number {
      text-align: center;
      font-size: 15pt;
    }

    #center {
      margin: 2% 0;
      font-size: 1.5em;

      #cont {
        word-break: break-all;
        text-indent: 2em;
      }
    }

    #bottom {
      font-size: 1.3em;
      padding: 20px 0px;

      #time {
        margin: 10px 0;
      }

      #back {
        width: 150px;
        height: 70px;
        margin: 5% 40%;
        color: white;
        font-size: 1.3em;
        background-color: #02a7f0;
      }
    }
  }

}


</style>
