<!--  -->
<template>
    <div class='table'>
        <a-table :columns="columns" :data-source="tableData" size="small">
            <router-link 
                slot="keyword"
                slot-scope="text"
                :to="'related?keyword='+text"
            >{{text}}</router-link>
        </a-table>
    </div>
</template>

<script>

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
            ]
        }
    },
    props: {
        tableData: Array,
    },
    methods: {

    },
    mounted() {
        console.log(this.tableData);
    },
}
</script>
<style lang='scss' scoped>
//@import url();
</style>