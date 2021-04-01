<template>
  <div>
    <Header/>
    <el-affix :offset="0">
      <div style="font-size:18px;color:black;">
        <router-link to="/HotWords"><i class="el-icon-sunny"></i><span>近年热词</span></router-link> |
      <router-link to="/Top10"><i class="el-icon-s-data"></i><span>Top10</span></router-link>
    </div>
    </el-affix>

    <router-view/>
    <Footer style="position:fixed"/>
  </div>
</template>

<script>
import Footer from '@/components/Footer.vue'
import Header from '@/components/Header.vue'
import Top10 from '@/components/Top10.vue'
import HotWords from '@/components/HotWords.vue'

export default {
  name: 'HotAreas',
  components: {
    //HelloWorld,
    Footer,
    Header,
    Top10,
    HotWords,
  }
}
</script>

<style>

</style>