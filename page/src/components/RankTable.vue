<!--  -->
<template>
    <div class='table'>
        <a-table :columns="columns" :data-source="rankData" size="small">
            <router-link 
                slot="keyword"
                slot-scope="text"
                to="related"
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
                    dataIndex: 'rank',
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
                    dataIndex: 'size',
                    align: 'right',
                    width: "25%"
                },
                {
                    title: '年涨幅',
                    dataIndex: 'growth',
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
    methods: {

    }
}
</script>
<style lang='scss' scoped>
//@import url();
</style>