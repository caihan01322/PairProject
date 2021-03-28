<!--  -->
<template>
    <div class='related'>
        <a-page-header
            style="border: 1px solid rgb(235, 237, 240); background: #fff;"
            :title="keyword"
            :breadcrumb="{ props: { routes } }"
        />
        <a-layout-content
            :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
        >
            <div class="label">
                <a-radio-group v-model="label" @change="changeLabel">
                    <a-radio-button value="total">全部</a-radio-button>
                    <a-radio-button value="cvpr">CVPR</a-radio-button>
                    <a-radio-button value="iccv">ICCV</a-radio-button>
                    <a-radio-button value="eccv">ECCV</a-radio-button>
                </a-radio-group>
            </div>
            <div class="table">
                <a-table :columns="columns" :data-source="tableData">
                    <router-link 
                        slot="pageTitle" 
                        :style="{ color: 'rgba(0, 0, 0, 0.65)' }" 
                        slot-scope="text"
                        to="detail"
                    >{{text}}</router-link>
                    <span slot="keywords" slot-scope="keywords">
                        <a-tag v-for="keyword in keywords" :key="keyword">{{keyword}}</a-tag>
                    </span>
                </a-table>
            </div>
        </a-layout-content>
    </div>
</template>

<script>

import request from '../request/request'

export default {
    name: 'Related',
    components: {},
    data () {
        return {
            routes: [
                {
                    path: 'analyze',
                    breadcrumbName: '数据分析',
                },
                {
                    path: 'related',
                    breadcrumbName: '相关论文',
                },
            ],
            label: "total",
            columns: [
                {
                    title: "论文名",
                    dataIndex: "title",
                    key: "title",
                    scopedSlots: { customRender: 'pageTitle' },
                    width: "25%",
                },
                {
                    title: "编号",
                    dataIndex: "number",
                    key: "number",
                    width: "25%"
                },
                {
                    title: "关键词",
                    dataIndex: "keyword",
                    key: "keyword",
                    scopedSlots: { customRender: 'keywords' },
                    width: "25%"
                },
                {
                    title: "录取会议",
                    dataIndex: "meeting",
                    key: "meeting",
                    width: "25%"
                }
            ],
            tableData: [
                {
                    title: "论文标题1",
                    number: "1234566767",
                    keyword: ['1','2','3'],
                    meeting: "CCCC"
                }
            ],
            keyword: "",
        }
    },
    methods: {
        changeLabel(){
            
        },
        getTableData(page) {
            // request.
        }
    },
    mounted() {
        // console.log()
        const url = decodeURI(window.location.hash.split("?")[1].split("=")[1]);
        console.log(url);
        this.keyword = url;
    },
}
</script>
<style lang='scss' scoped>
//@import url();
.table {
    margin-top: 12px;
}
</style>