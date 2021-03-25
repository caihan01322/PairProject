<!--  -->
<template>
    <div id='lineChart'></div>
</template>

<script>

import { Chart } from '@antv/g2';




export default {
    name: 'LineChart',
    components: {},
    data () {
        return {

        }
    },
    methods: {

    },
    mounted() {
        fetch('https://gw.alipayobjects.com/os/antvdemo/assets/data/blockchain.json')
        .then(res => res.json())
        .then(data => {
            const chart = new Chart({
                container: 'lineChart',
                autoFit: true,
                height: 500,
                width: "100%",
                padding: [60,72,60,72]
            });

            chart.data(data);
            chart.scale({
                nlp: {
                min: 0,
                max: 100
                },
                blockchain: {
                min: 0,
                max: 100
                }
            });

            chart.axis('nlp', false);

            chart.legend({
                custom: true,
                items: [
                { name: 'blockchain', value: 'blockchain', marker: { symbol: 'line', style: { stroke: '#1890ff', lineWidth: 2 } } },
                { name: 'nlp', value: 'nlp', marker: { symbol: 'line', style: { stroke: '#2fc25b', lineWidth: 2 } } },
                ],
            });

            chart.line().position('date*blockchain').color('#1890ff');
            chart.line().position('date*nlp').color('#2fc25b');

            chart.removeInteraction('legend-filter'); // 自定义图例，移除默认的分类图例筛选交互
            chart.render();
        });
    },
}
</script>
<style lang='scss' scoped>
//@import url();
#lineChart {
    width: 100%;
}
</style>