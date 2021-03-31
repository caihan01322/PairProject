<template>
  <div class="data_contianer">
    <div class="graph_contianer">
      <div id="keywordsTrend" />
      <div id="paperSource" />
    </div>
    <hotWordsList />
  </div>
</template>

<script>
import hotWordsList from '@/components/hot-words-list'

export default {
  name: "index",
  components: {
    hotWordsList
  },
  mounted() {
    const keywordsTrendGraph = this.$echarts.init(document.getElementById('keywordsTrend'))
    const paperSourceGraph = this.$echarts.init(document.getElementById('paperSource'))

    paperSourceGraph.setOption({
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: '访问来源',
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
          data: [
            {value: 1048, name: 'CVPR'},
            {value: 735, name: 'ICCV'},
            {value: 580, name: 'ECCV'},
          ]
        }
      ]
    })

    keywordsTrendGraph.setOption({
      title: {
        text: '折线图堆叠'
      },
      legend: {
        data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
      },
      xAxis: {
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          name: '邮件营销',
          type: 'line',
          data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
          name: '联盟广告',
          type: 'line',
          data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
          name: '视频广告',
          type: 'line',
          data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
          name: '直接访问',
          type: 'line',
          data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
          name: '搜索引擎',
          type: 'line',
          data: [820, 932, 901, 934, 1290, 1330, 1320]
        }
      ]
    })
  }
}
</script>

<style scoped>
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
  height: 50%;
}

.graph_contianer {
  width: 50%;
  height: 100%;
}
</style>