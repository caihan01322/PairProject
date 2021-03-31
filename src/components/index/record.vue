<template>
  <div id="indexRecord">
    <div id="recordSearch">
      <div id="recordRadio">
        <RadioGroup v-model="searchType">
          <Radio label="标题"></Radio>
          <Radio label="关键字"></Radio>
        </RadioGroup>
      </div>
      <div>
        <Input v-model="searchContent" placeholder="输入搜索内容" style="width: 500px; margin: 10px 10px" />
        <Button type="primary" ghost style=" margin: 0 10px" @click="searchBtn()">搜索</Button>
<!--        <Button type="primary" ghost style=" margin: 0 10px">添加</Button>-->
      </div>

    </div>
    <div id="recordBody">
      <div class="recordItem" v-for="(item,index) in searchResult" :key="index">
        <div class="recordTitle">{{item.title}}</div>
        <div class="recordCode"><span>论文编号：</span>{{item.number}}</div>
        <div class="recordTag">
          <span v-for="(item1,index1) in item.keyword.slice(0,3)" :key="index1">{{item1}}</span>
        </div>
        <div class="recordContent"><span>摘要内容：</span>{{item.abstract}}</div>
        <div class="recordAddress">
          <div @click="copy(item.link)">复制原文地址</div>
        </div>
        <div class="opeBtn">
<!--          <Button shape="circle" icon="ios-create-outline"></Button>-->
          <Button shape="circle" icon="ios-trash-outline" @click="myDelete(item)"></Button>
        </div>
      </div>
      <div class="recordItem">
        <div class="recordTitle">Pictorial structures revisited: People detection and articulated pose estimation.</div>
        <div class="recordCode"><span>论文编号：</span>0000000001</div>
        <div class="recordTag">
          <span>ieee</span>
          <span>computer</span>
          <span>society</span>
        </div>
        <div class="recordContent"><span>摘要内容：</span>Non-rigid object detection and articulated pose estimation are two related and
          challenging problems in computer vision. Numerous models have been proposed over the years and often
          address different special cases, such as pedestrian detection or upper body pose estimation in TV footage.
          This paper shows that such specialization may not be necessary, and proposes a generic approach based on
          the pictorial structures framework. We show that the right selection of components for both appearance and
          spatial modeling is crucial for general applicability and overall performance of the model. The appearance
          of body parts is modeled using densely sampled shape context descriptors and discriminatively trained AdaBoost
          classifiers. Furthermore, we interpret the normalized margin of each classifier as likelihood in a generative
          model. Non-Gaussian relationships between parts are represented as Gaussians in the coordinate system of the joint between parts.
          The marginal posterior of each part is inferred using belief propagation.</div>
        <div class="recordAddress">
          <a href="#">原文地址</a>
        </div>
        <div class="opeBtn">
          <!--          <Button shape="circle" icon="ios-create-outline"></Button>-->
          <Button shape="circle" icon="ios-trash-outline"></Button>
        </div>
      </div>

    </div>
    <div id="myPage">
      <Page :total="totle" :page-size="10" />
    </div>
    <div id="recordCover" v-show="showCover">
      <myadd v-show="coverType === 1"></myadd>
      <mydelete v-show="coverType === 2"></mydelete>
      <myedit v-show="coverType === 3"></myedit>
    </div>
  </div>
</template>

<script>
import mydelete from './record/delete.vue'
import myadd from './record/add'
import myedit from './record/edit'

export default {
  name: 'record',
  data () {
    return {
      searchType: '标题',
      searchContent: '',
      totle: 100,
      showCover: false,
      coverType: 1,
      searchResult: []
    }
  },
  components: {
    myadd,
    mydelete,
    myedit
  },
  methods: {
    searchBtn () {
      if (this.searchType === '标题') {
        this.search1()
      } else if (this.searchType === '关键字') {
        this.search2()
      }
    },
    search1 () {
      this.$axios.post('http://localhost:8081/PaperOperationController/fuzzyQuery', {
        fuzzyTitle: this.searchContent
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
    search2 () {
      this.$axios.post('http://localhost:8081/PaperOperationController/keywordQuery', {
        keyword: this.searchContent
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
    myDelete (item) {
      this.$axios.post('http://localhost:8081/PaperOperationController/deleteCollection', {
        number: item.number
      })
        .then(res => {
          this.$Message.info('删除成功')
        })
        .catch(err => {
          console.log(err)
        })
        .finally({
        })
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
#indexRecord {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}
  #recordSearch {
    width: 90%;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    margin: 10px 0 20px 0;
    #recordRadio {
      width: 100%;
      display: flex;
      flex-direction: row;
      justify-content: center;
      align-items: center;
    }
  }
  #recordBody {
    width: 90%;
    min-height: 300px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
    /*border: 1px solid gray;*/
    .recordItem {
      width: 100%;
      display: flex;
      flex-direction: column;
      justify-content: flex-start;
      align-items: center;
      border-bottom: 1px solid rgba(0,0,0,0.1);
      padding: 0 0 10px 0;
      margin-bottom: 5px;
      >div{
        width: 98%;
      }
      .recordAddress {
        >div{
          color: #4dcfcf;
          cursor: pointer;
        }
        >div:hover {
          color: rgba(15, 190, 152, 0.97);
        }
      }
      .recordTitle {
        width: 100%;
        font-size: 16px;
        font-weight: 700;
        margin: 5px 0;
      }
      .recordCode {
        color: #000080;
      }
      .recordTag {
        display: flex;
        flex-direction: row;
        justify-content: flex-start;
        align-items: flex-start;
        >span {
          font-size: 12px;
          padding: 1px 3px;
          margin: 0 5px;
          background-color: #F7F7F7;
          border: 1px solid #AAAAAA;
          border-radius: 4px;
        }
      }
      .recordContent{
        margin: 5px 0px;
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
      .opeBtn {
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        align-items: flex-start;
        Button {
          margin: 0 10px;
        }
      }
    }
  }
  #recordCover {
    position: absolute;
    top: -0px;
    width: 100%;
    height: 100%;
    background-color: rgba(0,1,1,0.2);
  }
</style>
