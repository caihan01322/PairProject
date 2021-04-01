<template>
  <div class="data_contianer">
    <div class="graphs_contianer">
<!--      <div id="keywordsTrend" />-->
      <div class="graph_contianer">
        <div class="options_panel">
          <h2 class="graph_title">论文来源会议</h2>
          <div class="select_contianer">
            <span>开始年份：</span>
            <el-select v-model="paperSourceSetting.beg" class="selecter">
              <el-option v-for="item in yearOption" :key="item" :label="item" :value="item" />
            </el-select>
          </div>
          <div class="select_contianer">
            <span>结束年份：</span>
            <el-select v-model="paperSourceSetting.end" class="selecter">
              <el-option v-for="item in yearOption" :key="item" :label="item" :value="item" />
            </el-select>
          </div>
        </div>
        <div id="paperSource" />
      </div>
    </div>
    <hotWordsList class="hot_word_list"/>
  </div>
</template>

<script>
import hotWordsList from '@/components/hot-words-list'

export default {
  name: "index",
  data() {
    return {
      paperSource: {},
      paperSourceSetting: {
        beg: '2000',
        end: '2010'
      },
      keywordsTrendGraph: null,
      paperSourceGraph: null,
      yearOption: []
    }
  },
  methods: {
    setPaperSource() {

      let sourceData = []
      for(let prop in this.paperSource){
        sourceData.push({
          name: prop,
          value: this.paperSource[prop]
        })

      }

      console.log(sourceData)


      this.paperSourceGraph.setOption({
        tooltip: {
          trigger: 'item'
        },
        legend: {
          top: '5%',
          left: 'center'
        },
        series: [
          {
            name: '论文来源会议',
            type: 'pie',
            radius: ['40%', '70%'],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: '#fff',
              borderWidth: 2
            },
            label: {
              show: false,
              position: 'center'
            },
            emphasis: {
              label: {
                show: true,
                fontSize: '40',
                fontWeight: 'bold'
              }
            },
            labelLine: {
              show: false
            },
            data: sourceData
          }
        ]
      })
    }
  },
  components: {
    hotWordsList
  },
  mounted() {

    for(let i = 2000 ; i <= 2020 ; i ++){
      this.yearOption.push(i.toString())
    }

    // this.keywordsTrendGraph = this.$echarts.init(document.getElementById('keywordsTrend'))
    this.paperSourceGraph = this.$echarts.init(document.getElementById('paperSource'))

    this.$api.Data.getPaperSource(this.paperSourceSetting.beg, this.paperSourceSetting.end).then(res => {
          this.paperSource = res.data.data
          this.setPaperSource()
    })



    // this.keywordsTrendGraph.setOption({
    //   title: {
    //     text: '折线图堆叠'
    //   },
    //   legend: {
    //     data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
    //   },
    //   xAxis: {
    //     data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    //   },
    //   yAxis: {
    //     type: 'value'
    //   },
    //   series: [
    //     {
    //       name: '邮件营销',
    //       type: 'line',
    //       data: [120, 132, 101, 134, 90, 230, 210]
    //     },
    //     {
    //       name: '联盟广告',
    //       type: 'line',
    //       data: [220, 182, 191, 234, 290, 330, 310]
    //     },
    //     {
    //       name: '视频广告',
    //       type: 'line',
    //       data: [150, 232, 201, 154, 190, 330, 410]
    //     },
    //     {
    //       name: '直接访问',
    //       type: 'line',
    //       data: [320, 332, 301, 334, 390, 330, 320]
    //     },
    //     {
    //       name: '搜索引擎',
    //       type: 'line',
    //       data: [820, 932, 901, 934, 1290, 1330, 1320]
    //     }
    //   ]
    // })
  }
}
</script>

<style lang="scss" scoped>
#keywordsTrend {
  width: 100%;
  height: 50%;
}

.data_contianer {
  width: 80%;
  display: flex;
}

#paperSource {
  width: 100%;
  height: 100%;
}

.graphs_contianer {
  width: 50%;
  height: 100%;
}

.graph_contianer {
  height: 40%;
  width: 100%;
  border-radius: 5px;
  border: 1px solid #E0E0E0;
  box-shadow:15px 0 15px -15px grey, -15px 0 15px -15px grey;
  margin: 10px;
}

.options_panel {
  display: flex;
  justify-content:flex-end;
}

.select_contianer {

  margin-top: 5px;
  width: 200px;


  .selecter {
    width: 80px;
  }



}

.hot_word_list {
  margin-left: 30px;
}

.graph_title {
  margin-right: 39px;
}
</style>