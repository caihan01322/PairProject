<!--  -->
<template>
    <div class='list'>

        <a-page-header
            class="header"
            title="论文检索"
            :breadcrumb="{ props: { routes } }"
        >
            <div>
                <div class="search">
                    <div class="search_inputer_container">
                        <div class="search_inputer search_inputer-inline">
                            <span class="search_inputer_title">论文标题</span>
                            <a-auto-complete
                                class="search_inputer_inner"
                                mode="tags"
                                v-model="titleValue"
                                placeholder="请输入论文标题"
                                @search="updateTitleTips"
                            />
                        </div>
                        <div class="search_inputer search_inputer-inline">
                            <span class="search_inputer_title">关键词</span>
                            <a-select
                                class="search_inputer_selector"
                                mode="tags"
                                v-model="keywordValue"
                                placeholder="请选择关键字"
                                @search="updateKeywordTips"
                            >
                                <a-select-option v-for="key in keywordTips" :key="key">
                                    {{ key }}
                                </a-select-option>
                            </a-select>
                        </div>
                    </div>
                    <div class="search_btn_container">
                        <a-button type="primary" @click="searchPage">检索</a-button>
                    </div>
                </div>
            </div>
        </a-page-header>

        <a-layout-content
            :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
        >
            <div class="table_container">
                <div class="ops">
                    <div class="item_op">
                        <a-button class="delete_btn" icon="delete" type="danger" @click="deletePages">删除</a-button>
                    </div>
                    <div class="label">
                        <a-radio-group v-model="label" @change="changeLabel">
                            <a-radio-button value="0">全部</a-radio-button>
                            <a-radio-button value="1">CVPR</a-radio-button>
                            <a-radio-button value="2">ICCV</a-radio-button>
                            <a-radio-button value="3">ECCV</a-radio-button>
                        </a-radio-group>
                    </div>
                </div>
                <div class="table">
                    <a-table 
                        :columns="columns" 
                        :data-source="listData"
                        @change="reloadTable"
                        :pagination="pager"
                        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
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
                        <span slot="action" slot-scope="{id, link}">
                            <a @click="deletePerPage(id)">删除</a>
                            <a :style="{ marginLeft: '12px' }" @click="openOrigin(link)">查看</a>
                        </span>

                    </a-table>
                </div>
            </div>
        </a-layout-content>
    </div>
</template>

<script>

import request from '../request/request'

export default {
    name: 'List',
    components: {},
    data () {
        return {
            routes: [
                {
                    path: 'index',
                    breadcrumbName: '论文列表',
                },
            ],
            currentLabel: "total",
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
                    width: "30%"
                },
                {
                    title: "作者",
                    dataIndex: "author",
                    key: "author",
                    scopedSlots: { customRender: 'authors' },
                    width: "15%"
                },
                {
                    title: "录取会议",
                    dataIndex: "meeting",
                    key: "meeting",
                    scopedSlots: { customRender: 'meetings' },
                    width: "15%"
                },
                {
                    title: '操作',
                    key: 'action',
                    scopedSlots: { customRender: 'action' },
                    fixed: 'right',
                    align: 'right'
                },
            ],
            listData: [],
            selectedRowKeys: [],
            label: "",
            titleTips: [],
            keywordTips: [],
            titleValue: "",
            keywordValue: [],
            pager: {
                current: 1,
                total: 1,
            }
        }
    },
    methods: {
        // 列表项选择
        onSelectChange(selectedRowKeys) {
            console.log('selectedRowKeys changed: ', selectedRowKeys);
            this.selectedRowKeys = selectedRowKeys;
        },
        // 请求论文列表表格
        requestList(data) {
            let that = this;
            request.search(data)
            .then((res)=>{
                console.log(res);
                if(res.error == 0) {
                    // load data
                    console.log(res);
                    that.listData = res.result;
                    that.pager.total = res.total;
                }
                else {
                    that.$message.error('请求错误，请重试');
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
            console.log("search");
            this.requestList({
                title: that.titleValue,
                keywords: that.keywordValue,
                page: 1,
                status: 0
            })
        },
        // 切换筛选
        changeLabel() {
            // console.log(this.label);
            this.requestList({
                title: that.titleValue,
                keywords: that.keywordValue,
                page: 1,
                status: this.label,
            })
        },
        // 关键词提示
        updateKeywordTips(value) {
            // console.log(value);
            let that = this;
            request.getKeywordTips({
                keyword: value
            })
            .then((res)=>{
                console.log(res);
                that.keywordTips = res.result;
            })
        },
        // 论文标题提示
        updateTitleTips(value) {

        },
        // 访问原文链接
        openOrigin(link) {
            window.open(link);
        },
        // 删除某篇论文
        deletePerPage(id) {
            console.log(id);
            let that = this;
            request.deletePage({
                id: [id]
            })
            .then((res)=>{
                console.log(res);
                if(res.error==0) {
                    that.$message.success('已删除文章');
                    that.requestList({
                        page: that.pager.current
                    });
                }
                else {
                    that.$message.error('删除错误，请重试');
                }
            })
        },
        // 批量删除论文
        deletePages() {
            // console.log(this.selectedRowKeys);
            let keys = this.selectedRowKeys;
            let ids = [];
            for(let i=0; i<keys.length; i++) {
                // console.log(this.listData[keys[i]].id);
                ids.push(this.listData[keys[i]].id);
            }
            let that = this;
            request.deletePage({
                id: ids
            })
            .then((res)=>{
                console.log(res);
                if(res.error==0) {
                    that.$message.success('已删除文章');
                    that.requestList({
                        page: that.pager.current
                    });
                }
                else {
                    that.$message.error('删除错误，请重试');
                }
            })
        },
        // 更新表格数据
        reloadTable(a) {
            console.log(a);
            this.pager.current = a.current;
            this.pager.total = a.total;
            let that = this;
            this.requestList({
                page: that.pager.current
            })
        },
    },
    mounted() {
        this.requestList({
            title: "",
            keywords: [],
            page: 1,
            status: 0,
        });
    },
}
</script>
<style lang='scss' scoped>
.header {
    background-color: #fff;
    border: 1px solid #eeeeee;
}
.search {
    .search_inputer_container {
        display: flex;
        justify-content: space-between;
    }
    .search_inputer {
        display: flex;
        align-items: center;
        width: 100%;
        .search_inputer_title {
            width: 72px;
        }
        .search_inputer_inner {
            width: auto;
            flex-grow: 1;
        }
    }
    .search_inputer-inline {
        width: 48%;
    }
    .search_inputer_selector {
        width: auto;
        flex-grow: 1;
        
    }
    .search_btn_container {
        display: flex;
        justify-content: center;
        width: 100%;
        margin-top: 24px;
    }
}
.table_container {
    display: flex;
    flex-direction: column;
    width: 100%;

    .ops {
        display: flex;
        justify-content: space-between;
        width: 100%;

        .delete_btn {
            margin-left: 12px;
        }
    }

    .table {
        margin-top: 12px;
    }
}
.import_inner {
    .upload_btn {
        margin-left: 12px;
    }
    .upload_table {
        margin-top: 12px;
    }
}
</style>