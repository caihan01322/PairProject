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
          <Upload action="//jsonplaceholder.typicode.com/posts/">
            <Button class="bcColor2" icon="ios-cloud-upload-outline">批量上传</Button>
          </Upload>
        </div>
      </div>
    </div>
    <div id="essayResult">
      <div class="essayTitle">
        <div>检索结果</div>
      </div>
      <div id="Result">
        <div class="resultItem" v-for="(item,index) in searchResult" :key="index">
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
        <div class="resultItem">
          <div class="resultTitle">Pictorial structures revisited: People detection and articulated pose estimation</div>
          <div class="resultContent"><span>摘要内容：</span>Non-rigid object detection and articulated pose estimation are two related and
            challenging problems in computer vision. Numerous models have been proposed over the years and often
            address different special cases, such as pedestrian detection or upper body pose estimation in TV footage.
            This paper shows that such specialization may not be necessary, and proposes a generic approach based on
            the pictorial structures framework. We show that the right selection of components for both appearance and
            spatial modeling is crucial for general applicability and overall performance of the model. The appearance
            of body parts is modeled using densely sampled shape context descriptors and discriminatively trained AdaBoost
            classifiers. Furthermore, we interpret the normalized margin of each classifier as likelihood in a generative
            model. Non-Gaussian relationships between parts are represented as Gaussians in the coordinate system of the joint between parts.
            The marginal posterior of each part is inferred using belief propagation. </div>
          <div class="resultInfo">
            <div class="resultLink"><div>原文地址</div></div>
            <div class="resultTime">2009年X月X日</div>
          </div>
          <div class="resultOpt">
            <Button class="btn1">阅读全文</Button>
            <Button class="btn2">收藏此文</Button>
          </div>
        </div>
      </div>
      <div id="myPage">
        <Page :total="totle" :page-size="10" />
      </div>
      <div class="resultTips" v-show="isSearch === 0">
        <div>无检索内容</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'search',
  data () {
    return {
      text: '',
      isSearch: 1,
      totle: 100,
      searchResult: []
    }
  },
  methods: {
    search () {
      console.log('search' + this.text)
      this.$axios.post('http://localhost:80801/PaperOperationController/fuzzyQuery', {
        fuzzyTitle: this.text
      })
        .then(res => {
          this.searchResult = res.data.result
          this.totle = res.data.item_num
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
        year: item.link,
        keywork: item.keyword
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
