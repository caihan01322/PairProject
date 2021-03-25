<!--  -->
<template>
    <div class='analyze'>

        <div class="line">
            <div class="wordCloud_block block" >
                <div class="title">
                    <h3>热门领域</h3>
                </div>
                <div class="wordCloud_container">
                    <word-cloud></word-cloud>
                </div>
            </div>

            <div class="rank_block block" >
                <rank></rank>
            </div>
        </div>

        <div class="line line2">
            <div class="title">
                <div class="search">
                    <a-input placeholder="请输入想要查询的热词" v-model="searchKey" allow-clear @change="onInput" />
                </div>
                <div class="date">
                    <a-month-picker placeholder="请选择开始月份" suffix-icon="起" @change="changeStartMonth" />
                    <a-month-picker class="endMonthPicker" placeholder="请选择结束月份" suffix-icon="止" @change="changeEndMonth" />
                </div>
            </div>
            <div class="chart_container block">
                <line-chart></line-chart>
            </div>
        </div>
        
    </div>
</template>

<script>
import LineChart from '../components/LineChart.vue'
import Rank from '../components/Rank.vue'

import WordCloud from '../components/WordCloud.vue'

export default {
    name: 'Analyze',
    components: {},
    data () {
        return {
            searchKey: '',
        }
    },
    components: {
        WordCloud, Rank, LineChart,
    },
    methods: {
        oninput(value) {
            console.log(value);
        },
        changeStartMonth() {

        },
        changeEndMonth() {

        }
    }
}
</script>

<style lang='scss' scoped>
//@import url();
.analyze {
    display: flex;
    flex-direction: column;
    padding: 24px 16px;
    box-sizing: border-box;
}
.line {
    display: flex;
    width: 100%;
    .block {
        background-color: #fff;
    }
    .wordCloud_block {
        margin-right: 12px;
        .title {
            display: flex;
            align-items: center;
            height: 48px;
            width: 100%;
            padding: 0 24px;
            box-sizing: border-box;
            border-bottom: 1px solid #eeeeee;
            h3 {
                margin: 0;
                font-weight: 700;
            }
        }
        .wordCloud_container {
            padding: 12px;
        }
    }
    .rank_block {
        width: auto;
        flex-grow: 1;
    }
}

.line2 {
    display: flex;
    flex-direction: column;
    margin-top: 12px;
    background-color: #fff;
    .title {
        display: flex;
        align-items: center;
        justify-content: space-between;
        height: 60px;
        width: 100%;
        padding: 0 24px;
        box-sizing: border-box;
        border-bottom: 1px solid #eeeeee;
        .endMonthPicker {
            margin-left: 6px;
        }
    }
    .chart_container {
        width: 100%;
    }
}

</style>