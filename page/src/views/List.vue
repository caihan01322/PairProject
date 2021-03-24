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
                            <a-input class="search_inputer_inner" placeholder="请输入论文标题" />
                        </div>
                        <div class="search_inputer search_inputer-inline">
                            <span class="search_inputer_title">论文编号</span>
                            <a-input class="search_inputer_inner" placeholder="请输入论文编号" />
                        </div>
                    </div>
                    <div class="search_inputer_container search_inputer_container-next">
                        <div class="search_inputer">
                            <span class="search_inputer_title">关键词</span>
                            <a-select
                            class="search_inputer_selector"
                            mode="multiple"
                            placeholder="请选择关键字"
                            :default-value="['a1', 'b2']"
                            @change="handleChange"
                            >
                                <a-select-option v-for="i in 25" :key="(i + 9).toString(36) + i">
                                    {{ (i + 9).toString(36) + i }}
                                </a-select-option>
                            </a-select>
                        </div>
                    </div>
                    <div class="search_btn_container">
                        <a-button type="primary">检索</a-button>
                        <a-button type="link">批量导入</a-button>
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
                        <a-button icon="cloud-download" type="primary">爬取</a-button>
                        <a-button class="delete_btn" icon="delete" type="danger">删除</a-button>
                    </div>
                    <div class="label">
                        <a-radio-group v-model="currentLabel" @change="changeLabel">
                            <a-radio-button value="total">全部</a-radio-button>
                            <a-radio-button value="loaded">已爬取</a-radio-button>
                            <a-radio-button value="unload">待爬取</a-radio-button>
                    </a-radio-group>
                    </div>
                </div>
                <div class="table">
                    <a-table 
                        :columns="columns" 
                        :data-source="listData"
                        :row-selection="{ selectedRowKeys: selectedRowKeys, onChange: onSelectChange }"
                    >
                        <span slot="action">
                            <a>删除</a>
                            <a :style="{ marginLeft: '12px' }">爬取</a>
                            <a :style="{ marginLeft: '12px' }">查看</a>
                        </span>
                    </a-table>
                </div>
            </div>
        </a-layout-content>
    </div>
</template>

<script>

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
                    width: "25%"
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
                    width: "25%"
                },
                {
                    title: "状态",
                    dataIndex: "status",
                    key: "status",
                    width: "10%"
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
                    number: 123456,
                    keyword: "t",
                    status: "已爬取",


                }
            ],
            selectedRowKeys: [],
        }
    },
    methods: {
        changeLabel(){

        },
        onSelectChange(selectedRowKeys) {
            console.log('selectedRowKeys changed: ', selectedRowKeys);
            this.selectedRowKeys = selectedRowKeys;
        },
    }
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
            width: 96px;
        }
        .search_inputer_inner {
            width: auto;
            flex-grow: 1;
        }
    }
    .search_inputer-inline {
        width: 40%;
    }
    .search_inputer_container-next {
        margin-top: 12px;
    }
    .search_inputer_selector {
        width: auto;
        flex-grow: 1;
        
    }
    .search_btn_container {
        display: flex;
        justify-content: center;
        width: 100%;
        margin-top: 12px;
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
</style>