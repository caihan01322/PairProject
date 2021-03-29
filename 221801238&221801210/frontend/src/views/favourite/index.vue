<template>
  <div class="favorite_contianer">
    <div class="full_height">
      <div class="favorite_list full_height">
        <div class="favorite_list_title">
          <p>我的收藏夹</p>
          <el-button type="primary" plain>新建收藏夹</el-button>
        </div>
        <div class="favorite_list_item" v-for="(itemList,index) in favoriteList"
             @click="selectFolder(index, itemList.id)"
             :id="'favorite_list_item' + index">
          <p class="favorite_title">{{ itemList.name }}</p>
          <p class="favorite_content">{{ itemList.description }}</p>
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
      favoriteList: [
        {
          name: '收藏夹',
          description: '描述xxxxxxx'
        },
        {
          name: '收藏夹1',
          description: '描述xxxxxxx'
        },
        {
          name: '收藏夹2',
          description: '描述xxxxxxx'
        },
        {
          name: '收藏夹3',
          description: '描述xxxxxxx'
        }
      ]
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
    background-color: #65BD77;
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
  overflow-y: scroll;

}

.favorite_paper_contianer::-webkit-scrollbar {
  display: none;
}

.search_top_bar {
  position: fixed;
  margin-left: 50px;
  background-color: white;
  padding-top: 20px;
  z-index: 1;
}

.search_result_list {
  padding-top: 120px;
}
</style>