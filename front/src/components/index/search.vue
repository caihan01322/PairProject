<template>
  <div id="essaySearch">
    <div id="essayQuestion">
      <div class="essayTitle">
        <div>输入搜索内容</div>
      </div>
      <div class="essayForm">
        <Input v-model="text" placeholder="输入搜索内容"></Input>
        <div id="searchBtn">
          <Button  class="bcColor1" @click="search()">检索</Button>
<!--          <Upload action="//jsonplaceholder.typicode.com/posts/">-->
<!--            <Button class="bcColor2" icon="ios-cloud-upload-outline">批量上传</Button>-->
<!--          </Upload>-->
        </div>
      </div>
    </div>
    <div id="essayResult" v-show="searchResult.length !== 0">
      <div class="essayTitle">
        <div>检索结果</div>
      </div>
      <div id="Result">
        <div class="resultItem" v-for="(item,index) in showResult" :key="index">
          <div class="resultTitle">{{item.title}}</div>
          <div class="resultContent"><span>摘要内容：</span>{{item.abstract}}</div>
          <div class="resultInfo">
            <div class="resultLink"><div @click="copy(item.link)">复制原文地址</div></div>
            <div class="resultTime">{{item.year}}</div>
          </div>
          <div class="resultOpt">
            <Button class="btn1" @click="openLink(item.link)">阅读全文</Button>
            <Button class="btn2" @click="collect(item)">收藏此文</Button>
          </div>
        </div>
      </div>
      <div id="myPage">
        <Page :total="totle" :page-size="10" @on-change="change()" />
      </div>
      <div class="resultTips" v-show="isSearch === 0">
        <div>无检索内容</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from 'vuex'

export default {
  name: 'search',
  props: ['skeyword'],
  data () {
    return {
      text: '',
      isSearch: 1,
      totle: 0,
      searchResult: [],
      showResult: []
    }
  },
  methods: {
    change (page) {
      console.log(page)
      const start = page * 10 - 10
      this.showResult = this.searchResult.slice(start, page * 10)
    },
    search () {
      console.log('search' + this.text)
      this.$axios.post('http://localhost:8081/PaperOperationController/fuzzyQuery', {
        fuzzyTitle: this.text
      })
        .then(res => {
          this.searchResult = res.data.result
          this.totle = res.data.item_num
          this.showResult = this.searchResult.slice(0, 10)
        })
        .catch(err => {
          console.log(err)
        })
        .finally({
        })
    },
    search2 () {
      this.$axios.post('http://localhost:8081/PaperOperationController/keywordQuery', {
        keyword: this.text
      })
        .then(res => {
          this.searchResult = res.data.result
          this.totle = res.data.item_num
          this.showResult = this.searchResult.slice(0, 10)
        })
        .catch(err => {
          console.log(err)
        })
        .finally({
        })
    },
    collect (item) {
      this.$axios.post('http://localhost:8081/PaperOperationController/collectPaper', {
        title: item.title,
        number: item.number,
        abstract: item.abstract,
        link: item.link,
        year: item.year,
        keyword: item.keyword
      })
        .then(res => {
          this.$Message.success('成功收藏')
        })
        .catch(err => {
          console.log(err)
        })
        .finally({
        })
    },
    openLink (href) {
      window.open(href, '_blank')
    },
    copy (text) {
      const transfer = document.createElement('input')
      document.body.appendChild(transfer)
      transfer.value = text // 这里表示想要复制的内容
      transfer.focus()
      transfer.select()
      if (document.execCommand('copy')) {
        document.execCommand('copy')
      }
      transfer.blur()
      this.$Message.info('复制成功')
      document.body.removeChild(transfer)
    }
  },
  computed: mapState(['searchKey', 'isSearchKey']),
  ...mapMutations(['setSerchKey', 'openSearchKey', 'closeSearchKey']),
  mounted () {
    if (this.$store.state.isSearchKey === true) {
      console.log(this.$store.state.searchKey)
      this.text = this.$store.state.searchKey
      this.$store.commit('closeSearchKey')
      this.$store.commit('setSerchKey', '')
      this.search2()
    }
  }
}
</script>

<style scoped lang="less">
  #essaySearch {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
  }
  #essayQuestion {
    width: 80%;
    height: 230px;
    border: solid 1px #326291;
    box-shadow: 5px 5px 10px #D7D7D7;
    padding: 0;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    .essayTitle {
      width: 100%;
      height: 50px;
      background-color: #326291;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      >div{
        color: white;
        font-size: 20px;
        font-weight: 700;
        margin: 0 20px;
      }
    }
    .essayForm {
      width: 80%;
      height: 180px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      >div{
        height: 50px;
      }
      >div#searchBtn{
        height: 50px;
        width: 300px;
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        /*align-items: center;*/
      }
      .bcColor1 {
        background-color: #536570;
        color: white;
      }
      .bcColor1:hover {
        background-color: #637b87;
        color: white;
        border-color: #7DA8C3;
      }
      .bcColor2 {
        background-color: #7DA8C3;
        color: white;
      }
      .bcColor2:hover {
        background-color: #83b3ce;
        color: white;
        border-color: #7DA8C3;
      }
    }
  }

  #essayResult {
    width: 80%;
    min-height: 500px;
    border: solid 1px #326291;
    box-shadow: 5px 5px 10px #D7D7D7;
    padding: 0;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    margin-top: 40px;
    .essayTitle {
      width: 100%;
      height: 50px;
      background-color: #326291;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: row;
      justify-content: flex-start;
      align-items: center;
      >div{
        color: white;
        font-size: 20px;
        font-weight: 700;
        margin: 0 20px;
      }
    }
    .resultTips {
      width: 80%;
      height: 250px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      >div{
        color: rgba(0, 0, 2, 0.25);
        font-size: 50px;
        /*font-family: 仿宋;*/
      }
    }
    #result {
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
    }
    #myPage {
      margin-bottom: 20px;
    }
  }

  .resultItem {
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    padding-bottom: 15px;
    margin-bottom: 10px;
    border-bottom: solid rgba(0, 0, 0, 0.1) 1px;
    .resultTitle {
      font-size: 16px;
      font-weight: 700;
      margin: 10px 10px 5px 10px;
    }
    .resultContent {
      margin: 5px 20px;
      ext-overflow: ellipsis;
      overflow: hidden;
      display: -webkit-box;
      /* autoprefixer: off */
      -webkit-box-orient: vertical;
      -webkit-line-clamp: 3;
      >span {
        /*font-weight: 600;*/
      }
    }
    .resultInfo{
      width: 100%;
      padding: 0px 20px;
      display: flex;
      flex-direction: row;
      justify-content: space-between;
      align-items: center;
    }
    .resultOpt {
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
      .btn1 {
        margin: 0px 10px;
        background-color: #7DA8C3;
        border-color: #7DA8C3;
        color: white;
      }
      .btn1:hover {
        background-color: #86b5d0;
        border-color: #86b5d0;
        color: white;
      }
      .btn2 {
        margin: 0px 10px;
        background-color: rgba(255, 215, 155, 0.91);
        border-color: rgba(255, 215, 155, 0.91);
        color: white;
      }
      .btn2:hover {
        background-color: rgba(255, 211, 159, 0.91);
        border-color: rgba(255, 211, 159, 0.91);
        color: white;
      }
    }
    .resultLink {
      >div{
        color: #4dcfcf;
        cursor: pointer;
      }
      >div:hover {
        color: rgba(15, 190, 152, 0.97);
      }
    }
  }

</style>
