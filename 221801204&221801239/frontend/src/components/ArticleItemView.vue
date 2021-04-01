<template>
  <div class="article">
    <div id="left">
      <h1 id="title">{{ itemObj.title }}</h1>
      <div id="cen">
        关键词:
        <div
          class="spr"
          style="display: inline"
          v-for="item in itemObj.keywordsArray"
          :key="item.index"
        >
          {{ item }}
        </div>
      </div>
      <p class="cont">{{ itemObj.abstracted }}...</p>
      <div id="alink">
        原文链接:<a href="itemObj.link">{{ itemObj.link }}</a>
      </div>
    </div>
    <div id="right">
      <div class="number">编号:{{ itemObj.paper_id }}</div>
      <div id="ope">
        <button class="look" @click="viewArt()">查看</button>
        <button class="del" @click="delArt()">删除</button>
      </div>
      <div id="time">{{ itemObj.publication_year }}</div>
    </div>


  </div>
</template>

<script>
import bus from '@/assets/eventBus';

export default {
  props: {
    itemObj: {
      type: Object,
      default: function () {
        return {};
      },
    },
  },

  methods: {
    viewArt() {
      this.$router.replace("/ViewArticle");
      this.$nextTick(() => {
        bus.$emit('sendData', this.itemObj);
      });
    },
    delArt() {
      this.$nextTick(() => {
        bus.$emit('delete', this.itemObj.paper_id);
      });
    }

  }, created() {
  }
};
</script>

<style lang="less" scoped>
.article {
  display: flex;
  justify-content: space-between;
  align-content: center;
  width: 1600px;
  height: 20%;
  min-height: 10%;
  background-color: white;
  margin-top: 3px;
  margin-left: 155px;

  #left {
    width: 1400px;
    height: 100%;

    h1 {
      margin-left: 0.8%;

    }

    #cen {
      margin-left: 0.8%;

      .spr {
        margin-left: 0.8%;
      }
    }

    .cont {
      margin-left: 0.8%;
      display: inline-block;
      width: 1400px;
      word-break: break-all;
      text-indent: 2em;
    }

    #alink {
      display: inline-block;
      height: 15px;
      margin-left: 0.8%;
      font-size: 11pt;
    }
  }

  #right {
    display: block;
    padding: 0 15px;

    .number {
      margin-top: 5px;
      width: 100%;
      font-size: 15pt;
    }

    #ope {
      display: flex;
      margin: 50% 0;

      .look {
        height: 40px;
        width: 60px;
        background-color: white;

      }

      .del {
        height: 40px;
        width: 60px;
        background-color: white;

      }
    }

    #time {
      margin: 10px 0;
      font-size: 11pt;
    }
  }

}
</style>
