<template>
    <div id="container"></div>
</template>
<script>
import { WordCloud } from '@antv/g2plot';
export default {
    mounted() {
    this.fetchData();
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
                        fontSize: [8, 32],
                        rotation: 0,
                    },
                // 返回值设置成一个 [0, 1) 区间内的值，
                // 可以让每次渲染的位置相同（前提是每次的宽高一致）。
                random: () => 0.5,
                });
                wordCloud.render();
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