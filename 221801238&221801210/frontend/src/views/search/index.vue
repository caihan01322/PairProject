<template>
  <div class="search_page_contianer full_height"  v-loading="loading"
       :element-loading-text="LoadingText"
       element-loading-background="rgba(0, 0, 0, 0.8)">
    <div class="search_result_contianer" >
      <div>
        <searchInput @searchEvent="searchKeyword"/>
        <searchOption :total="total"/>
      </div>
      <searchResultList mode="search" class="result_list"/>
    </div>
    <hotWordsList style="margin-left: 50px"/>
  </div>
</template>

<script>
import sideNavBar from '@/components/side-nav-bar'
import searchInput from '@/components/search-input'
import searchOption from './components/search-option'
import searchResultList from '../../components/search-result-list/index'
import hotWordsList from '@/components/hot-words-list'

export default {
  name: "index.vue",
  components: {
    sideNavBar,
    searchInput,
    searchOption,
    searchResultList,
    hotWordsList
  },
  data() {
    return {
      loading: false,
      LoadingText: '',
      total: 0
    }
  },
  methods: {
    searchKeyword(keyword) {
      this.loading = true
      let checkProcessTask


      this.$api.Paper.getList(keyword).then(res => {
        this.loading = false
        this.total = res.data.data.total
        clearInterval(checkProcessTask)
        this.$store.commit('setPaperList', res.data.data.paperList)
        this.$router.push('/search')
      })

      checkProcessTask = setInterval(() => {
        this.$api.Paper.getProcess().then(res => {
          this.currentFindNums = res.data.data.currentFindNums
          this.LoadingText = `已经为您搜索到${this.currentFindNums}条记录，请稍等`
        })
      }, 500)


    }
  }
}
</script>

<style lang="scss" scoped>
.search_page_contianer {
  display: flex;
  overflow: hidden;

  .result_list {
    height: 80%;
    overflow-y: scroll;
  }

  .result_list::-webkit-scrollbar {
    display: none;
  }




}
</style>