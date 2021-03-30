<!--  -->
<template>
    <div class="container" :style="{'width': '100%'}">
        <!--
        <a-empty v-if="lineData.length==0" :style="{'margin': '48px'}" />
        -->
        <div id='lineChart'></div>
    </div>
    
</template>

<script>

import { Chart } from '@antv/g2';

import request from '../request/request'


export default {
    name: 'LineChart',
    components: {},
    data () {
        return {
            chart: {}
        }
    },
    props: {
        lineData: Array,
    },
    methods: {
        createLine() {
            console.log(this.lineData);
            let chart = this.chart;
            chart = new Chart({
                container: 'lineChart',
                autoFit: true,
                height: 500,
                width: "100%",
                padding: [60,72,60,72]
            });

            chart.data(this.lineData);
            chart.scale({
                ICCV: {
                min: 0,
                max: 200
                },
                CVPR: {
                min: 0,
                max: 200
                },
                ECCV: {
                min: 0,
                max: 200
                }
            });

            chart.axis('ICCV', false);

            chart.legend({
                custom: true,
                items: [
                { name: 'CVPR', value: 'CVPR', marker: { symbol: 'line', style: { stroke: '#1890ff', lineWidth: 2 } } },
                { name: 'ICCV', value: 'ICCV', marker: { symbol: 'line', style: { stroke: '#2fc25b', lineWidth: 2 } } },
                { name: 'ECCV', value: 'ECCV', marker: { symbol: 'line', style: { stroke: '#f7bf14', lineWidth: 2 } } },
                ],
            });

            chart.line().position('date*CVPR').color('#1890ff');
            chart.line().position('date*ICCV').color('#2fc25b');
            chart.line().position('date*ECCV').color('#f7bf14');

            chart.removeInteraction('legend-filter'); // 自定义图例，移除默认的分类图例筛选交互
            this.chart = chart;
            chart.render();
        },
        updatedLine(data) {
            console.log("update line");
            console.log(this.chart);
            this.chart.changeData(data);
        },
    },
    mounted() {
        this.createLine();
    },
}
</script>
<style lang='scss' scoped>
//@import url();
#lineChart {
    width: 100%;
}
</style>