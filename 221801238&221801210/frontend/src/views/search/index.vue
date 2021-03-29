<template>
  <div class="search_page_contianer full_height">
    <div class="search_result_contianer"  style="overflow: scroll; ">
      <div style="position: absolute; background-color: white; z-index: 1; padding: 29px">
        <searchInput />
        <searchOption />
      </div>
      <searchResultList style="margin-top: 200px; " mode="search"/>
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
  }
}
</script>

<style lang="scss" scoped>
.search_page_contianer {
  display: flex;

  .search_result_contianer {

  }

  .search_result_contianer::-webkit-scrollbar {
    display: none;
  }




}
</style>