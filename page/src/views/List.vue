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
                                :default-value="['a1', 'b2']"
                                @search="updateKeywordTips"
                            >
                                <a-select-option v-for="i in 25" :key="(i + 9).toString(36) + i">
                                    {{ (i + 9).toString(36) + i }}
                                </a-select-option>
                            </a-select>
                        </div>
                    </div>
                    <div class="search_btn_container">
                        <a-button type="primary">检索</a-button>
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
                        <a-button class="delete_btn" icon="delete" type="danger">删除</a-button>
                    </div>
                    <div class="label">
                        <a-radio-group v-model="label" @change="changeLabel">
                            <a-radio-button value="total">全部</a-radio-button>
                            <a-radio-button value="cvpr">CVPR</a-radio-button>
                            <a-radio-button value="iccv">ICCV</a-radio-button>
                            <a-radio-button value="eccv">ECCV</a-radio-button>
                        </a-radio-group>
                    </div>
                </div>
                <div class="table">
                    <a-table 
                        :columns="columns" 
                        :data-source="listData"
                        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
                    >
                        <router-link 
                            slot="pageTitle" 
                            :style="{ color: 'rgba(0, 0, 0, 0.65)' }" 
                            slot-scope="text"
                            to="detail"
                        >{{text}}</router-link>

                        <span slot="keywords" slot-scope="keywords">
                            <a-tag v-for="keyword in keywords" :key="keyword">{{keyword}}</a-tag>
                        </span>
                        <span slot="meetings" slot-scope="meetings">
                            <a-tag v-for="meet in meetings" :key="meet">{{meet}}</a-tag>
                        </span>
                        <span slot="action">
                            <a>删除</a>
                            <a :style="{ marginLeft: '12px' }">查看</a>
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
                    width: "20%"
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
            listData: [
                {
                    title: "test",
                    keyword: ['t','e','s'],
                    meeting: ['CVPR','ICCV','ECCV'],
                }
            ],
            selectedRowKeys: [],
            label: "",
            titleTips: [],
            keywordTips: [],
            titleValue: [],
            keywordValue: [],
        }
    },
    methods: {
        onSelectChange(selectedRowKeys) {
            console.log('selectedRowKeys changed: ', selectedRowKeys);
            this.selectedRowKeys = selectedRowKeys;
        },
        requestList() {
            let that = this;
            request.search({
                key: "test"
            })
            .then((res)=>{
                // console.log(res);
                if(res.error == 0) {
                    // load data
                    that.listData = res.result;
                }
                else {
                    // alert error
                }
            })
        },
        serachPage(value) {
            console.log(value);
        },
        handleChange(value, option) {
            console.log(option);
        },
        changeLabel() {

        },
        updateTitleTips(value) {
            let that = this;
            // console.log(value);
            // console.log(this.titleValue);
            request.getTitleTips()
            .then((res)=>{
                console.log(res);
                that.titleTips = res.result;
            })
        },
        updateKeywordTips(value) {
            // console.log(value);
            let that = this;
            request.getKeywordTips()
            .then((res)=>{
                console.log(res);
                that.keywordTips = res.result;
            })
        }
    },
    mounted() {
        this.requestList();
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