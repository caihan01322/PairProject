<template>
    <div id="consumer">
      <div id="consumerBody">
        <div id="consumerTitle">knowledge</div>
        <div id="consumerForm">
          <router-view></router-view>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: 'consumer'
}
</script>

<style scoped lang="less">
#consumer {
  width: 100%;
  height: 100%;
}
#consumerBody {
  width: 85%;
  height: 75%;
  max-height: 550px;
  max-width: 1000px;
  position: relative;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -45%);
  background-color: #F2F2F2;
  border-radius: 36px;
}
#consumerTitle{
  width: fit-content;
  position: relative;
  left: 50%;
  transform: translate(-50%, -60%);
  color: #015478;
  font-size: 72px;
  font-family: "Comic Sans MS";
}
#consumerForm{
  /*background-color: #42b983;*/
  /*border:black solid 1px;*/
  width: 600px;
  height: 350px;
  position: relative;
  left: 50%;
  transform: translate(-50%, 0);
}
</style>
