<template>
<div>
    <div>
    <div id="HotWords" :style="{ width: '100%', height: '700px' }"></div>
  </div>
</div>
</template>
<script>
import { inject, onMounted } from "vue";
import axios from 'axios';
var list;
var data;
var colors = ['#FFAE57', '#FF7853', '#EA5151', '#CC3F57', '#9A2555'];
var bgColor = '#ffffff';
export default {

  name: "data_page",
  setup() {
    let echarts = inject("ec");//引入
    
    onMounted(() => {//需要获取到element,所以是onMounted的Hook
    let myChart = echarts.init(document.getElementById("HotWords"));
      axios.get('http://106.15.74.153:8080/api/sunburst').then(response => {
        list=response.data.data.sunburst;
        console.log(response);
        console.log(list);
        console.log(data);
        data=list;
        var option= {
            title:{ text: "近年热词"},
    backgroundColor: bgColor,
    color: colors,
    series: [{
        type: 'sunburst',
        center: ['50%', '48%'],
        data: data,
        sort: function (a, b) {
            if (a.depth === 1) {
                return b.getValue() - a.getValue();
            }
            else {
                return a.dataIndex - b.dataIndex;
            }
        },
        label: {
            rotate: 'radial',
            color: bgColor
        },
        itemStyle: {
            borderColor: bgColor,
            borderWidth: 2,
            borderRadius:3,
        },
        levels: [{}, {
            r0: 0,
            r: 60,

        }, {
            r0: 64,
            r:130,
            label:{
                minAngle:6
            }
        }, {
            r0: 135,
            r: 200,
            itemStyle: {
                                shadowBlur: 2,
                shadowColor: colors[2],
                color: 'transparent',
                 borderWidth: 1
            },
            label: {
                // position: 'outside',
                fontSize: 10,
                color: '#000000',
                fontSize:13,
                minAngle:4,
//                backgroundColor:"red",
                width:50,
                overflow:'truncate'
            }

        },{
            r0: 210,
            r:216,
            label:{
                minAngle:6,
                position: 'outside',
                color: '#000000',
                minAngle:10,
            }
        },]
    }]
};
      myChart.setOption(option);
      });
      
      
      window.onresize = function () {myChart.resize();};
        });
      
      return{
      };
  },
  components: {},
  mounted() {},
};
</script>