<template>
  <div id="wrap">
    <div id="nav">
      <img src="../assets/css/1.jpg" />
      <input class="nav-button" type="button" value="论文管理" @click="go1" />
      <input class="nav-button" type="button" value="论文分析" @click="go2" />
      <input class="nav-button" id="loginOut" type="button" value="注 销" @click="go3" />
    </div>

    <div id="main">
      <div id="userInfo">
        <div id="face"></div>
        <div id="personInf">
          <div id="name">
            <h2>昵称</h2>
            <p>id:XXXXXX</p>
            <p>
              我的收藏：
              <span id="myCol">10</span>
            </p>
            <p>
              我的分享：
              <span id="myShare">13</span>
            </p>
          </div>
          <div id="history">
            <h4 id="historyTit">历史记录</h4>
            <ul>
              <li>ICCV 论文题目（一）</li>
              <li>ICCV 论文题目（二）</li>
              <li>ICCV 论文题目（三）</li>
              <li>ICCV 论文题目（四）</li>
              <li>ICCV 论文题目（五）</li>
              <li>ICCV 论文题目（六）</li>
            </ul>
          </div>
        </div>
      </div>
      <div id="modular">
        <div id="hotArea">
          <h2>热门词汇分析</h2>
          <p>根据您的论文列表分析热门词汇</p>
          <div id="hot">
            <h3>热门领域分析</h3>
            <div id="areaAnal">
              <div id="mywordcloud" :style="{width: '100%', height: '300px'}" :data="worddata"></div>
              <!-- <div class="verify-line">
                <v-chart :options="lineOption" autoresize />
              </div>-->
            </div>

            <h3>热词占比分析</h3>
            <div id="wordAnal">
              <div id="test3" :style="{width:'100%',height:'100%'}"></div>
            </div>
          </div>
        </div>
        <div id="hotAnal">
          <div id="hotWord">
            <h3>热词分析</h3>
            <p>十年间不同顶会的热词</p>
            <div id="words">
              <div id="test1" :style="{width:'100%',height:'100%'}"></div>
            </div>
          </div>
          <div id="hotWordGo">
            <h3>热词走势</h3>
            <p>Training不同顶会的热度走势对比</p>
            <div id="go">
              <div id="test" :style="{width:'100%',height:'100%'}"></div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div id="footer">
      <input class="footer-button" id="indexButton" type="button" value="首页官网" onclick />
      <input class="footer-button" type="button" value="关于我们" onclick />
      <input class="footer-button" type="button" value="联系我们" onclick />
      <span id="copyright">Copyright © 2020. All rights reserved.</span>
    </div>
  </div>
</template>
<style scoped>
body {
  margin: 0;
  background-color: #101010;
  height: 1043px;
}
div {
  font-family: 微软雅黑;
  vertical-align: top;
}
#main {
  height: 100%;
  width: 100%;
}
#wrap {
  background-color: #101010;
  color: #eae9e9;
  height: 1000px;
}
#main {
  height: 100%;
}
#userInfo,
#modular {
  display: inline-block;
}
#userInfo {
  width: 22%;
  height: 607px;
  margin-top: 39px;

  position: relative;
}
#modular {
  width: 77%;
  height: 89%;

  position: relative;
}
#face {
  background-color: #bbbbbb;
  border: 1px solid #bbbbbb;
  border-radius: 50%;

  width: 121px;
  height: 122px;

  position: absolute;
  left: 90px;
  top: 22px;
  z-index: 3;
}
#face img {
  vertical-align: middle;
  margin: 0 auto;
  padding: 40% 30%;
}
#personInf {
  position: absolute;
  left: 40px;
  top: 70px;

  background-color: #292f37;
  width: 220px;
  height: 493px;
  z-index: 1;
}
#name {
  margin: 100px auto 0px auto;
  text-align: center;
}
#history {
  text-align: center;
}
#history ul li {
  list-style: none;
  margin-top: 3px;
  text-align: center;
}
#historyTit {
  text-align: left;
  margin-left: 25px;
}
#name p {
  margin: 12px;
  padding: 0;
}
#nav,
#footer {
  height: 61px;
  background-color: #353d48;
}
#sign {
  width: 41px;
  height: 41px;
  padding-left: 90px;
  padding-top: 11px;
}
input.nav-button,
input.footer-button {
  background-color: #353d48;
  border-color: #353d48;
  border-bottom-style: none;
  border-right-style: none;

  color: #eae9e9;
  font-family: 思源黑体;
  font-size: 16px;
  vertical-align: top;
  line-height: 24px;
  padding-top: 24px;
  padding-left: 40px;
}
input#findButton {
  padding-left: 73px;
}
input#loginOut {
  float: right;
  padding-right: 126px;
}
#footer {
  margin-top: -61px;

  border-top: 1px solid;
  border-color: #eae9e9;
}
input.footer-button {
  font-size: 14px;
  padding-left: 76px;
}
input.footer-button#indexButton {
  padding-left: 225px;
}
#copyright {
  padding-top: 24px;
  float: right;
  padding-right: 280px;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  font-size: 14px;
}

#modular #hotArea,
#modular #hotAnal {
  display: inline-block;
}
#hotArea {
  width: 71.5%;
  height: 828px;
  margin: 38px 0 0 0;

  position: relative;
}
#hotArea h2,
h3,
#hotAnal h3 {
  padding: 0;
  margin: 0;

  color: #bbe1fa;
  line-height: 27px;
  letter-spacing: 4px;
}
#hotArea p,
#hotAnal p {
  color: #9a9696;
  font-size: 14px;
  font-family: "思源黑体";
}
#hotArea #hot {
  width: 680px;
  height: 740px;
  padding: 14px 0 0 14px;
  background-color: #495464;
}
#hotArea #areaAnal,
#hotArea #wordAnal {
  width: 659px;
  height: 334px;
  margin-top: 9px;
  margin-bottom: 14px;
  border: #d4d7dd 3px solid;
  background-color: #353d48;
}
#hotArea #wordAnal {
  height: 292px;
}
#hotAnal {
  height: 828px;
  width: 27%;
  margin: 38px 0 0 0;
  position: absolute;
  left: 770px;
}
#hotWord #words {
  width: 400px;
  height: 400px;
  margin: 0;

  border: #d4d7dd 3px solid;
  background-color: #353d48;
}
#hotWordGo #go {
  width: 400px;
  height: 300px;
  margin: 0;

  border: #d4d7dd 3px solid;
  background-color: #353d48;
}

#nav img {
  padding-left: 80px;
  width: 70px;
  height: 60px;
}
</style>
<script>
import axios from "axios";
import * as echarts from "echarts";
import wordcloud from "echarts-wordcloud";
export default {
  data() {
    return {
      tableData: [],
      Form: {
        metting: "ECCV",
        year: "2000"
      },
      msg: "",
      //worddata:[]
      worddata: [
        {
          name: "Image",
          value: 3
        },
        {
          name: "Zero-shot",
          value: 3
        },
        {
          name: "Training",
          value: 3
        },
        {
          name: "Spatial",
          value: 3
        },
        {
          name: "Pipelines",
          value: 3
        },
        {
          name: "Computer",
          value: 3
        },
        {
          name: "Cameras",
          value: 3
        },
        {
          name: "Context reasoning",
          value: 2
        },
        {
          name: "Graph interaction",
          value: 1
        },
        {
          name: "Geometric estimation",
          value: 2
        }
      ]
    };
  },
  //此时页面上的DOM元素已经被初始化完毕
  mounted() {
    var myChart = echarts.init(document.getElementById("test1"));
    var test = echarts.init(document.getElementById("test"));
    var circle = echarts.init(document.getElementById("test3"));
    this.initChart();
    // 指定图表的配置项和数据
    var option = {
      tooltip: {
        trigger: "axis",
        axisPointer: {
          // 坐标轴指示器，坐标轴触发有效
          type: "shadow" // 默认为直线，可选为：'line' | 'shadow'
        }
      },
      lengend: {
        data: ["ECCV", "ICCV", "CVPR"]
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true
      },
      xAxis: [
        {
          boundaryGap: [0, 0.01],
          type: "category",
          data: [
            "Training",
            "Task analysis",
            "Deep learning",
            "Feature extraction",
            "Visulization"
          ],
          axisTick: {
            alignWithLabel: true
          }
        }
      ],
      yAxis: [
        {
          type: "value"
        }
      ],
      series: [
        {
          name: "ECCV",
          type: "bar",
          barWidth: "30%",
          data: [20, 33, 21, 37, 39]
        },
        {
          name: "ICCV",
          type: "bar",
          barWidth: "30%",
          data: [329, 154, 209, 217, 154]
        },
        {
          name: "CVPR",
          type: "bar",
          barWidth: "30%",
          data: [155, 107, 55, 93, 70]
        }
      ]
    };

    var option2 = {
      lengend: {
        data: ["ICCV", "CVPR"]
      },
      xAxis: {
        type: "category",
        boundaryGap: false,
        data: ["2016", "2017", "2018", "2019", "2020"]
      },
      grid: {
        left: "3%",
        right: "4%",
        bottom: "3%",
        containLabel: true
      },
      yAxis: {
        type: "value"
      },
      series: [
        {
          name: "ICCV",
          data: [87, 108, 36, 0, 150],
          type: "line"
        },
        {
          name: "CVPR",
          data: [97, 137, 130, 59, 124],
          type: "line"
        }
      ]
    };

    var option3 = {
    tooltip: {
        trigger: 'item'
    },
    legend: {
        orient: 'vertical',
        left: 'left',
    },
    series: [
        {
            name: '访问来源',
            type: 'pie',
            radius: '50%',
            data: [
                {value: 3, name: 'Image'},
                {value: 3, name: 'Training'},
                {value: 3, name: 'Zero-shot'},
                {value: 3, name: 'Spatial'},
                {value: 3, name: 'Computer'}
            ],
            emphasis: {
                itemStyle: {
                    shadowBlur: 0,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0)'
                }
            }
        }
    ]
};

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    test.setOption(option2);
    circle.setOption(option3);
  },
  methods: {
    go1() {
      this.$router.replace("/manage");
    },
    go2() {
      this.$router.replace("/analy");
    },
    go3() {
      this.$router.replace("/tour");
    },
    See(e) {
      // 去除双引号
      e = e.replace(/"/g, "");
      console.log(e);
      window.open(e);
    },
    initChart() {
      this.chart = echarts.init(document.getElementById("mywordcloud"));
      const option = {
        title: {
          text: "",
          x: "center"
        },
        series: [
          {
            type: "wordCloud",
            //用来调整词之间的距离
            gridSize: 10,
            //用来调整字的大小范围
            // Text size range which the value in data will be mapped to.
            // Default to have minimum 12px and maximum 60px size.
            sizeRange: [14, 60],
            // Text rotation range and step in degree. Text will be rotated randomly in range [-90,                                                                             90] by rotationStep 45
            //用来调整词的旋转方向，，[0,0]--代表着没有角度，也就是词为水平方向，需要设置角度参考注释内容
            // rotationRange: [-45, 0, 45, 90],
            // rotationRange: [ 0,90],
            rotationRange: [0, 10],
            //随机生成字体颜色
            // maskImage: maskImage,
            textStyle: {
              normal: {
                color: function() {
                  return (
                    "rgb(" +
                    Math.round(Math.random() * 25) +
                    ", " +
                    Math.round(Math.random() * 255) +
                    ", " +
                    Math.round(Math.random() * 255) +
                    ")"
                  );
                }
              }
            },
            //位置相关设置
            // Folllowing left/top/width/height/right/bottom are used for positioning the word cloud
            // Default to be put in the center and has 75% x 80% size.
            left: "center",
            top: "center",
            right: null,
            bottom: null,
            width: "100%",
            height: "100%",
            //数据
            data: this.worddata
          }
        ]
      };
      this.chart.setOption(option);
      this.chart.on("click", params => {
        //this.$options.methods.getKey(params.data.name);
        var keyword=params.data.name;
        this.$router.push({path:"/sk",query:{key:keyword}})
      });
    }
  }
};
</script>