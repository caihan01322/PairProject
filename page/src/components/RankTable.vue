<!--  -->
<template>
    <div class='table'>
        <a-table 
            :columns="columns" 
            :data-source="rankData" 
            :pagination="pager"
            @change="changeTable"
            size="small">
            <router-link 
                slot="keyword"
                slot-scope="text"
                :to="'related?keyword='+text"
            >{{text}}</router-link>
        </a-table>
    </div>
</template>

<script>

import request from '../request/request'

export default {
    name: 'RankTable',
    components: {},
    data () {
        return {
            columns: [
                {
                    title: '排名',
                    dataIndex: 'index',
                    width: "10%"
                },
                {
                    title: '关键词',
                    dataIndex: 'keyword',
                    scopedSlots: { customRender: 'keyword' },
                    width: "40%"
                },
                {
                    title: '论文数',
                    dataIndex: 'pages',
                    align: 'right',
                    width: "25%"
                },
                {
                    title: '年涨幅',
                    dataIndex: 'riserate',
                    align: 'right',
                    width: "25%"
                },
            ],
            rankData: [
                {
                    rank: 1,
                    keyword: "关键词1",
                    size: 100,
                    growth: "20%"
                }
            ],
            pager: {
                current: 1,
                total: 1,
            }
        }
    },
    props: {
        label: Number,
    },
    methods: {
        changeTable(a) {
            console.log(a);
            this.pager.current = a.current;
            this.pager.total = a.total;
            let that = this;
            this.requestTable(that.pager.current)
        },
        requestTable(page) {
            let that = this;
            request.getRank({
                meeting: that.label,
                page: page
            })
            .then((res)=>{
                console.log(res);
                that.pager.total = res.total;
                that.rankData = res.result;
            })
        }
    },
    mounted() {
        // console.log(this.label);
        this.requestTable(1);
    },
}
</script>
<style lang='scss' scoped>
//@import url();
</style>