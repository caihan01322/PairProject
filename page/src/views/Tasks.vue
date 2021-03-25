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
                            <a-input v-model="pageTitle" placeholder="请输入论文标题" class="search_inputer_inner" ></a-input>
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
                <a-button key="crawl" type="primary" @click="handleCrawl">
                添 加
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

        <a-modal 
            v-model="showCrawl" 
            title="论文爬取"
            :maskClosable="false"
            :closable="!crawling"
        >
            <template slot="footer">
                <a-button key="close" :disabled="crawling" @click="crawlClose">
                关闭
                </a-button>
                <a-button key="show" :disabled="crawling || !crawSuccess" type="primary" @click="crawlShow">
                查看数据
                </a-button>
            </template>

            <div class="crawl_inner">
                <a-spin v-if="crawling" size="large" />
                <a-result
                    status="success"
                    title="爬取已完成"
                    sub-title="点击查看立即查看爬取结果"
                    v-if="!crawling && crawSuccess"
                ></a-result>
                <a-result
                    status="warning"
                    title="爬取错误"
                    sub-title="请尝试重新添加任务"
                    v-if="!crawling && !crawSuccess"
                ></a-result>
            </div>
        </a-modal>

        <a-layout-content
            :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
        >
            <div class="table_container">
                <div class="ops">
                    <div class="item_op">
                        <a-button icon="cloud-download" :disabled="selectedRowEmpty()" type="primary" @click="crawl">爬取</a-button>
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
                    title: "论文标题",
                    dataIndex: "title",
                    key: "title",
                    scopedSlots: { customRender: 'pageTitle' },
                    width: "25%",
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
                },
                {
                    title: "test",
                },
                {
                    title: "test",
                },
                {
                    title: "test",
                },
                {
                    title: "test",
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
            pageTitle: "",
            showImport: false,
            showCrawl: false,
            crawling: true,
            crawSuccess: false
        }
    },
    methods: {
        onSelectChange(selectedRowKeys) {
            // console.log('selectedRowKeys changed: ', selectedRowKeys);
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
        },
        crawl() {
            this.showCrawl = true;
            this.crawling = true;
            let that = this;
            setTimeout(()=>{
                that.crawling = false;
            },2000)
        },
        handleCrawl() {

        },
        crawlShow() {

        },
        crawlClose() {

        },
        handleCancel() {

        },
        selectedRowEmpty() {
            // console.log(this.selectedRowKeys.length);
            return this.selectedRowKeys.length==0;
        }
    },
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
        justify-content: center;
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
.crawl_inner {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 480px;
}
</style>