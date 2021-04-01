<template>
    <div>
        <div id="container"></div>
        <div id="container1"></div>
    </div>
</template>
<script>
import { WordCloud ,Plot,Line} from '@antv/g2plot';
export default {
    mounted() {
        this.fetchData();
        this.fetchMagazine();
    },
    
    methods: {
        fetchData() {
            fetch('http://47.98.152.179:5000/xjbs/api/v1/keyword/top10?token=' + localStorage.getItem("token"))
            .then((res) => res.json())
            .then((data) => {
                data = data.data
                const wordCloud = new WordCloud('container', {
                    data,
                    wordField: 'keyword',
                    weightField: 'count',
                    colorField: 'keyword',
                    wordStyle: {
                        fontFamily: 'Verdana',
                        fontSize: [12, 48],
                        rotation: 0,
                    },
                // 返回值设置成一个 [0, 1) 区间内的值，
                // 可以让每次渲染的位置相同（前提是每次的宽高一致）。
                random: () => 0.5,
                });
                wordCloud.render();
                const plot = new Plot();
                plot.on('label:click',(...args) => {
                    window.location.href="www.baidu.com"
                });
            });
        },
        fetchMagazine() {
                fetch('https://gw.alipayobjects.com/os/bmw-prod/1d565782-dde4-4bb6-8946-ea6a38ccf184.json')
                .then((res) => res.json())
                .then((data) => {
                    const line = new Line('container1', {
                    data,
                    padding: 'auto',
                    xField: 'Date',
                    yField: 'scales',
                    xAxis: {
                        // type: 'timeCat',
                        tickCount: 5,
                    },
                    smooth: true,
                    });

                    line.render();
                });
        },
    },
};
</script>
<style>
.demo-infinite-container {
  border: 1px solid #e8e8e8;
  border-radius: 4px;
  overflow: auto;
  padding: 8px 24px;
  height: 300px;
}
.demo-loading-container {
  position: absolute;
  bottom: 40px;
  width: 100%;
  text-align: center;
}
</style>