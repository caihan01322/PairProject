<template>
  <div id="main_frame">
    <div class="search_frame">
      <el-button id="crawl_data" round>爬取论文</el-button>
      <el-checkbox id="select_all" :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange"
                   size="medium"></el-checkbox>
      <el-checkbox-group v-model="checkedPapers" @change="handleCheckedPapersChange">
        <essay v-for="paper in papers"
               v-bind:id="paper.artical.academicNum"
               v-bind:link="paper.artical.link"
               v-bind:title="paper.artical.title"
               v-bind:authors="paper.authors"
               v-bind:abstract="paper.artical.abstract"
               v-bind:keywords="paper.keywords"
               v-bind:key="paper.title">
        </essay>
      </el-checkbox-group>
      <img id="empty_img" v-if="isEmpty" src="../assets/empty-folder.png" alt="">
    </div>

  </div>
</template>
<script>
import Essay from "@/components/Essay"

export default {
  name:'SearchList',
  components:{Essay},
  props:{
    checkAll:{},
    checkedPapers:{},
    handleCheckAllChange:{},
    handleCheckedPapersChange:{},
    isEmpty:{},
    isIndeterminate:{},
    papers:{}
  }
}
</script>
<style lang="less">

#select_all {
  position: absolute;
  right: 2%;
  margin-right: 42px;
  top: 20px;
}


#main_frame {
  width: 100%;
  position: relative;
  margin-top: 50px;
  margin-bottom: 50px;
}

.search_frame {
  background-color: #f7f7f7;
  border-radius: 15px;
  box-shadow: 5px 5px 20px rgba(25, 25, 25, .75);
  width: 90%;
  margin-left: 5%;
  height: auto;
  margin-bottom: 50px;
  padding-top: 20px;
  padding-bottom: 50px;
  position: relative;
}

#empty_img {
  width: 400px;
  height: auto;
}

#crawl_data {
  background-color: white;
  width: 100px;
  display: flex;
  height: 30px;
  margin-left: 30px;
  line-height: 5px;
  margin-bottom: 20px;
  font-size: 13px;
  font-weight: bold;
  border: none;
}

a, a:link, a:visited, a:focus {
  text-decoration: none;
  color: #ffffff;
}

</style>
