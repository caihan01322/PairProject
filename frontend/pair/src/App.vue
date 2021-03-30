<template>
  <div id="app">
    <!-- <login></login> -->
    <!-- <register></register> -->
    <!-- <mycenter></mycenter> -->
    <articles></articles>
    <router-view></router-view>
  </div>
</template>

<script>
import Articles from './views/Articles.vue'
// import Mycenter from './views/mycenter.vue'
// import Register from './views/register.vue'
// import Login from './views/login'
export default {
  components: { Articles },
  name: 'App'
}
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    /* margin-top: 60px; */
  }

  .header{
    line-height: 60px;
    background-color: #545c64;
    /* text-color: white; */
  }
</style>
