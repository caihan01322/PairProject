<template>
  <div id="app">
    <sideNavBar v-if=" $route.path !== '/' "/>
    <router-view class="fullHeight"/>
  </div>
</template>

<script>
import sideNavBar from '@/components/side-nav-bar'
export default {
  name: 'app',
  components: {
    sideNavBar
  }
}
</script>

<style lang="scss">
html {
  height: 100%;
}

body {
  margin: 0;
  height: 100%;
}

#app {
  height: 100%;
}

.full_height {
  height: 100%;
}

.full_width {
  width: 100%;
}

#app {
  display: flex;
}
</style>
