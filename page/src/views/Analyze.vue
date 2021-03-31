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
                    <a-select
                        class="search_inputer_inner"
                        show-search
                        v-model="searchKey"
                        placeholder="请输入想要查询的热词"
                        @search="updateKeyTips"
                        :default-active-first-option="false"
                        :show-arrow="false"
                        :filter-option="false"
                        :not-found-content="null"
                        style="width: 200px"
                    >
                        <a-select-option v-for="key in keywordTips" :key="key">
                        {{ key }}
                        </a-select-option>
                    </a-select>
                </div>
                <div class="date">
                    <a-month-picker 
                        v-model="startDate" 
                        placeholder="请选择开始年份" 
                        suffix-icon="起" 
                        @change="changeStartMonth" 
                    />
                    <a-month-picker 
                        v-model="endDate" 
                        class="endMonthPicker" 
                        placeholder="请选择结束年份" 
                        :disabled-date="disabledDate"
                        suffix-icon="止" 
                        @change="changeEndMonth" 
                    />
                    <a-button type="primary" :style="{'margin-left': '12px'}" @click="search">查 询</a-button>
                </div>
            </div>
            <div class="chart_container block">
                <line-chart ref="lineChart" :lineData="lineData"></line-chart>
            </div>
        </div>
        
    </div>
</template>

<script>

import LineChart from '../components/LineChart.vue'
import Rank from '../components/Rank.vue'
import WordCloud from '../components/WordCloud.vue'

import moment from 'moment';
import request from '../request/request';

export default {
    name: 'Analyze',
    components: {},
    data () {
        return {
            searchKey: undefined,
            startDate: "",
            endDate: "",
            keywordTips: [],
            lineData: [],
        }
    },
    components: {
        WordCloud, Rank, LineChart,
    },
    methods: {
        moment,
        oninput(value) {
            console.log(value);
        },
        changeStartMonth() {

        },
        changeEndMonth() {

        },
        // 请求关键词折线图数据
        requestLine(data) {
            let that = this;
            request.getHotwordLine(data)
            .then((res)=>{
                // console.log(that.$refs);
                that.lineData = res.result;
                that.$refs.lineChart.updatedLine(res.result);
            })
        },
        // 搜索热词折线图
        search() {
            // console.log(this.startDate._d.getFullYear());
            // console.log(this.endDate);
            let that = this;
            if(this.searchKey=="" || this.startDate=="" || this.endDate=="") {
                that.$message.warning('搜索参数不能为空');
            }
            else {
                this.requestLine({
                    keyword: this.searchKey,
                    stime: this.startDate._d.getFullYear(),
                    etime: this.endDate._d.getFullYear()
                });
            }
        },
        // 截止日期限制
        disabledDate(current) {
            // Can not select days before today and today
            return current<this.startDate || current>moment().endOf('day');
        },
        // 关键词输入搜索提示
        updateKeyTips(value) {
            // console.log(value);
            let that = this;
            request.getKeywordTips({
                keyword: value
            })
            .then((res)=>{
                console.log(res);
                that.keywordTips = res.result;
            })
        }
    },
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