<template>

  <div>
    <div id="myChart"
     :style="{ width: '100%', height: '500px' }"></div>
  </div>
</template>

<script>
import { inject, onMounted } from "vue";
import axios from 'axios'
import QS from 'qs'
var x=new Array();
var list;
for(var i=1;i<=10;i++)x.push("热词"+i);
export default {
  name: "data_page",
  setup() {

    let echarts = inject("ec");//引入
    onMounted(() => {//需要获取到element,所以是onMounted的Hook
        
        axios.get('http://106.15.74.153:8080/api/top10').then(response => {
          list=response.data.data;
          // console.log(list);
          let myChart = echarts.init(document.getElementById("myChart"));
      // 绘制图表
      myChart.setOption({
        title: { text: "TOP10热词" },
          tooltip: {
          trigger: 'axis',
          enterable: true,
          alwaysShowContent: true,
          formatter: function (params, ticket, callback) {
               let index=params[0].dataIndex;
               let ieee="https://ieeexplore.ieee.org/";
               var res='';
               res+="热词："+list.keywordName[index]+"<br/>";
               res+="频次："+params[0].value+"<br/>";
               res+="相关论文<br/><div class='barHtml'>";
               for(i=0;i<10;i++){
                    res+="<a target='_blank' href='"+ieee+list.paperList[index][i].url+
                         "' title='"+list.paperList[index][i].title+"'>"+
                         list.paperList[index][i].title+
                         "</a><br/>"
               }
               res+="</div>";
               return res;
          },
          position:function(point){
               return {
                    left:point[0],
                    top:0,
               }
          }
     },
      toolbox: {
          show: true,
          feature: {
               dataView: {
                    readOnly: false
               },
               magicType: {
                    type: ["line", "bar"]
               },
               restore: {},
               saveAsImage: {}
          }
     },
     legend: {
          data: ['热词']
     },
     xAxis: {
          type: 'category',
          data: list.keywordName,
     },
     yAxis: {
          type: 'value'
     },
     dataZoom: [
          {
               type: 'slider',
               show: true,
               xAxisIndex: [0],
               start: 1,
               end: 100
          },
          {
               type: 'slider',
               show: true,
               yAxisIndex: [0],
               left: '93%',
               start: 1,
               end: 100
          },
          {
               type: 'inside',
               xAxisIndex: [0],
               start: 1,
               end: 100
          },
          {
               type: 'inside',
               yAxisIndex: [0],
               start: 1,
               end: 1
          }
     ],
     series: [{
          color:"#409eff",
          name: '热词',
          data: list.keywordNum,
          type: 'bar'
     }]
      },true);
      window.onresize = function () {//自适应大小
        myChart.resize();
      };
    });
        });
      
      return{
      };
  },
  components: {},
  mounted() {},
};
</script>

<style>
.barHtml{
     width:160px;
     text-overflow: ellipsis;
     white-space:nowrap;
     overflow: hidden;
     margin:0 10px;
}
a{
     text-decoration:none;
}
</style>