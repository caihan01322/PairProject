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
                    <a-radio-button value="0">全部</a-radio-button>
                    <a-radio-button value="1">CVPR</a-radio-button>
                    <a-radio-button value="2">ICCV</a-radio-button>
                    <a-radio-button value="3">ECCV</a-radio-button>
                </a-radio-group>
            </div>
            <div class="table">
                <a-table 
                    :columns="columns" 
                    :data-source="tableData" 
                    @change="reloadTable" 
                    :pagination="pager"
                >
                    <span slot="keywords" slot-scope="keywords">
                        <a-tag v-for="keyword in keywords" :key="keyword">{{keyword}}</a-tag>
                    </span>
                    <span slot="authors" slot-scope="authors">
                        <a-tag v-for="author in authors" :key="author">{{author}}</a-tag>
                    </span>
                    <span slot="meetings" slot-scope="meetings">
                        <a-tag v-for="meet in meetings" :key="meet">{{meet}}</a-tag>
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
            label: "0",
            columns: [
                {
                    title: "论文名",
                    dataIndex: "title",
                    key: "title",
                    scopedSlots: { customRender: 'pageTitle' },
                    width: "25%",
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
                    scopedSlots: { customRender: 'meetings' },
                    width: "25%"
                },
                {
                    title: "作者",
                    dataIndex: "author",
                    key: "author",
                    scopedSlots: { customRender: 'authors' },
                    width: "15%"
                },
            ],
            tableData: [],
            keyword: "",
            pager: {
                current: 1,
                total: 1,
            }
        }
    },
    methods: {
        // 切换标签
        changeLabel(){
            console.log(this.label);
            let that = this;
            this.requestList({
                keywords: [that.keyword],
                page: 1,
                status: that.label
            })
        },
        // 更新表格数据
        reloadTable(page) {
            console.log(page);
            this.requestList({
                title: "",
                keywords: [that.keyword],
                page: page.page,
                status: that.label
            })
        },
        // 请求论文列表表格
        requestList(data) {
            let that = this;
            request.search(data)
            .then((res)=>{
                console.log(res);
                if(res.error == 0) {
                    // load data
                    that.tableData = res.result;
                    that.pager.total = res.total;
                }
                else {
                    // alert error
                }
            })
        },
        // 搜索论文
        searchPage() {
            // console.log("search:");
            // console.log(this.titleValue);
            // console.log(this.keywordValue);
            let that = this;
            this.label = "0",
            this.pager = {
                current: 1,
                total: 1
            };
            this.requestList({
                title: that.titleValue,
                keywords: [that.keywordValue],
                page: 1,
                status: 0
            })
        },
        // 访问原文链接
        openOrigin(link) {
            window.open(link);
        },
        // 更新表格数据
        reloadTable(a) {
            console.log(a);
            this.pager.current = a.current;
            this.pager.total = a.total;
            let that = this;
            this.requestList({
                title: "",
                keywords: [that.keyword],
                page: that.pager.current,
                status: that.label
            })
        },
    },
    mounted() {
        // console.log()
        const url = decodeURI(window.location.hash.split("?")[1].split("=")[1]);
        console.log(url);
        this.keyword = url;
        let that = this;
        this.requestList({
            title: "",
            keywords: [that.keyword],
            page: 1,
            status: 0
        });
    },
}
</script>
<style lang='scss' scoped>
//@import url();
.table {
    margin-top: 12px;
}
</style>