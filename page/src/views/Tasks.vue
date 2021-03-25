<!--  -->
<template>
    <div class='tasks'>
    
        <a-page-header
            class="header"
            title="添加爬虫任务"
            :breadcrumb="{ props: { routes } }"
        >
            <div>
                <div class="search">
                    <div class="search_inputer_container">
                        <div class="search_inputer search_inputer-inline">
                            <span class="search_inputer_title">论文标题</span>
                            <a-select
                                showSearch
                                :value="titleSelected"
                                placeholder="请输入论文标题"
                                class="search_inputer_inner"
                                :showArrow="false"
                                @search="serachPage"
                                @change="handleChange"
                            >
                                <a-select-opt-group label="已爬取">
                                <a-select-option value="00001">
                                    00001
                                </a-select-option>
                                <a-select-option value="00002">
                                    00002
                                </a-select-option>
                                </a-select-opt-group>

                                <a-select-opt-group label="未爬取">
                                <a-select-option :value="titleInput">
                                    点击将"{{titleInput}}"添加至待爬取
                                </a-select-option>
                                </a-select-opt-group>
                            </a-select>
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
                        <a-button type="primary">添加</a-button>
                        <a-button type="link" @click="openImport">批量导入</a-button>
                    </div>
                </div>
            </div>
        </a-page-header>

        <a-modal 
            v-model="showImport" 
            title="批量导入"
            :maskClosable="false"
        >
            <template slot="footer">
                <a-button key="cancel" @click="handleCancel">
                取 消
                </a-button>
                <a-button key="add" @click="handleAdd">
                仅添加到列表
                </a-button>
                <a-button key="crawl" type="primary" @click="handleCrawl">
                导入并爬取
                </a-button>
            </template>

            <div class="import_inner">
                <div class="upload_container">
                    <span>论文表格：</span>
                    <a-button class="upload_btn" icon="upload">上传表格</a-button>
                </div>
                <div class="upload_table">
                    <a-table :columns="uploadColumn" :data-source="uploadData" size="small" :pagination="false" />
                </div>
            </div>
        </a-modal>

        <a-layout-content
            :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
        >
            <div class="table_container">
                <div class="ops">
                    <div class="item_op">
                        <a-button icon="cloud-download" type="primary">爬取</a-button>
                        <a-button class="delete_btn" icon="delete" type="danger">删除</a-button>
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
                        <span slot="action">
                            <a>删除</a>
                            <a :style="{ marginLeft: '12px' }">爬取</a>
                        </span>

                    </a-table>
                </div>
            </div>
        </a-layout-content>
    </div>
</template>

<script>

export default {
    name: 'Tasks',
    components: {},
    data () {
        return {
            routes: [
                {
                    path: 'index',
                    breadcrumbName: '任务列表',
                },
            ],
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
                    keyword: ['t','e','s']


                }
            ],
            uploadColumn: [
                {
                    title: "论文标题",
                    dataIndex: "title",
                    key: "title",
                }
            ],
            uploadData: [
                {
                    title: "111111111"
                },
                {
                    title: "222222222"
                }
            ],
            selectedRowKeys: [],
            titleSelected: " ",
            titleInput: "",
            showImport: false,
        }
    },
    methods: {
        onSelectChange(selectedRowKeys) {
            console.log('selectedRowKeys changed: ', selectedRowKeys);
            this.selectedRowKeys = selectedRowKeys;
        },
        requestList() {

        },
        serachPage(value) {
            this.titleInput = value;
            console.log(value);
        },
        handleChange(value, option) {
            console.log(option);
        },
        openImport() {
            this.showImport = true;
        }
    }
}
</script>
<style lang='scss' scoped>
//@import url();
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
        width: 45%;
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
.import_inner {
    .upload_btn {
        margin-left: 12px;
    }
    .upload_table {
        margin-top: 12px;
    }
}
</style>