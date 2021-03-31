<template>
  <div class="favorite_contianer">
    <div class="full_height">
      <div class="favorite_list full_height">
        <div class="favorite_list_title">
          <p>我的收藏夹</p>
          <el-button type="primary" plain @click="newFavorite()">新建收藏夹</el-button>
        </div>
        <div class="favorite_list_item" v-for="(itemList,index) in favoriteList"
             @click="selectFolder(index, itemList.favorite_id)"
             :id="'favorite_list_item' + index">
          <p class="favorite_title">{{ itemList.name }}</p>
        </div>
      </div>
    </div>
    <div class="favorite_paper_contianer">
      <div class="search_top_bar">
        <searchInput />
        <searchOption />
      </div>
      <searhResultList mode="favorite" class="search_result_list"/>
    </div>
  </div>
</template>

<script>
import searhResultList from '@/components/search-result-list'
import searchInput from '@/components/search-input'
import searchOption from './components/search-option'

export default {
  name: "index",
  data() {
    return {
      favoriteList: []
    }
  },
  created() {
    this.initFavorite()
  },
  methods: {
    selectFolder(index, id) {

      let elements = document.getElementsByClassName('favorite_list_item')
      for (let i = 0 ; i < this.favoriteList.length ; i ++){
        elements[i].className = 'favorite_list_item'
        if (i == index){
          elements[i].className += ' favorite_item_active'
        }
      }

      this.$api.Favorites.getPaperList(id).then(res => {
        this.$store.commit('setPaperList', res.data.data.paperlist )
      })


    },
    newFavorite() {
      this.$prompt('请输入收藏夹名字', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      }).then(({ value }) => {
        this.$api.Favorites.create(value).then(res => {
          this.$message.success('创建收藏夹成功')
          this.initFavorite()
        })
      })
    },
    initFavorite() {
      this.$api.Favorites.getList().then(res => {
        this.favoriteList = res.data.data.favorites
      })
    }
  },
  components: {
    searchInput,
    searhResultList,
    searchOption
  }
}
</script>

<style lang="scss" scoped>
.favorite_contianer {
  display: flex;
}

.favorite_list {
  display: flex;
  flex-direction: column;
  color: rgba(16, 16, 16, 100);
  font-size: 28px;
  width: 300px;
  align-items: center;
  border-right: 0.5px solid #D0D0D0;

  .favorite_list_title {
    display: flex;
    align-items: center;
    justify-content: space-around;
    width: 100%;
  }


  .favorite_list_item {
    width: 90%;
    background-color: white;
    border: 1px solid #E8E8E8;
    border-radius: 10px;
    margin-top: 10px;
    margin-bottom: 10px;
    cursor: pointer;
  }

  .favorite_list_item:hover {
    background-color: #65BD77;
    color: white;
  }

  .favorite_list_item:hover .favorite_content {
    color: white;
  }

  .favorite_item_active {
    background-color: #409EFF;
    color: white;
    width: 90%;
    border: 1px solid #E8E8E8;
    border-radius: 10px;
    margin-top: 10px;
    margin-bottom: 10px;
    cursor: pointer;
  }

  .favorite_item_active p{
    margin: 10px;
  }

  .favorite_item_active .meeting_time {
    color: white;
  }

  .favorite_item_active .icon {
    color: white;
  }

  .favorite_item_active .favorite_content {
    color: white;
  }


  .list_title {
    color: #717179;
    font-size: 16px;
  }

  .favorite_title {
    font-weight: 700;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }


  .favorite_list_item p {
    margin: 10px;
  }


  .favorite_content {
    font-size: 13px;
    color: grey
  }

}

.favorite_paper_contianer {
  overflow: hidden;

}

.favorite_paper_contianer::-webkit-scrollbar {
  display: none;
}

.search_top_bar {
  background-color: white;
}

.search_result_list {
  overflow-y: scroll;
  height: 80%;
}

.search_result_list::-webkit-scrollbar {
  display: none;
}

</style>