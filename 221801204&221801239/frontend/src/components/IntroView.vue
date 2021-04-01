<template>
  <div class="out">
    <button class="btn blt" @click="cLeft"></button>
    <button class="btn mid" @click="cMid"></button>
    <button class="btn rit" @click="cRi"></button>
    <img class="pic" v-bind:src="picArr[num]" alt="" />
  </div>
</template>

<script>
export default {
  data() {
    return {
      num: 0,
      picArr: [
        require("../assets/images/first.png"),
        require("../assets/images/second.png"),
        require("../assets/images/third.png"),
      ],
    };
  },
  methods: {
    cLeft() {
      this.num = 0;
    },
    cRi() {
      this.num = 2;
    },
    cMid() {
      this.num = 1;
    },
  },
};
</script>

<style lang="less" scoped>
.out {
  width: 578px;
  height: 555px;
  margin-top: 60px;
  margin-left: 423px;
  border: 0.5px solid #ccc;
  position: relative;
  user-select: none;
  float: left;
  .btn {
    position: absolute;
    width: 15px;
    height: 15px;
    border-radius: 50%;
    color: #fff;
    cursor: pointer;
    transition: background 0.5s;
  }
  .btn:hover {
    background: rgba(0, 0, 0, 1);
  }
  .blt {
    left: 240px;
    top: 540px;
  }
  .rit {
    left: 290px;
    top: 540px;
  }
  .mid {
    left: 265px;
    top: 540px;
  }
  .pic {
    height: 555px;
    width: 578px;
    border: none;
  }
}
</style>