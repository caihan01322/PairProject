<template>
  <div class="search_page_contianer full_height">
    <div class="search_result_contianer" >
      <div>
        <searchInput />
        <searchOption />
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
  }
}
</script>

<style lang="scss" scoped>
.search_page_contianer {
  display: flex;
  overflow: hidden;

  .result_list {
    height: 100%;
    overflow-y: scroll;
  }

  .result_list::-webkit-scrollbar {
    display: none;
  }




}
</style>