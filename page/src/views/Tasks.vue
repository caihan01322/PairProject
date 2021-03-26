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
                        <a-button type="primary" @click="addTask">添加</a-button>
                        <a-button type="link" @click="openImport">批量导入</a-button>
                    </div>
                </div>
            </div>
        </a-page-header>

        <a-modal 
            v-model="showImport" 
            title="批量导入"
            :maskClosable="false"
            :closable="!importing"
        >
            <template slot="footer">
                <a-button key="cancel" @click="handleCancel" :disabled="importing">
                取 消
                </a-button>
                <a-button 
                    key="crawl" 
                    type="primary" 
                    @click="importTask" 
                    :loading="importing"
                    :disabled="!exectImportFile()"
                >
                添 加
                </a-button>
            </template>

            <div class="import_inner">
                <div class="upload_container">
                    <span>论文表格：</span>
                    <a-upload 
                        class="upload_btn"
                        accept=".xlsx, .xls"
                        :file-list="fileList"
                        :remove="handleFileRemove"
                        :before-upload="beforeUpload"
                    >
                        <a-button> <a-icon type="upload" /> 上传表格 </a-button>
                    </a-upload>
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
                关 闭
                </a-button>
                <a-button key="show" :disabled="crawling || !crawlSuccess" type="primary" @click="crawlConfirm">
                完 成
                </a-button>
            </template>
            <div class="crawl_inner">
                <a-spin v-if="crawling" size="large" />
                <a-result
                    status="success"
                    title="爬取已完成"
                    sub-title="点击查看立即查看爬取结果"
                    v-if="!crawling && crawlSuccess"
                ></a-result>
                <a-result
                    status="warning"
                    title="爬取错误"
                    sub-title="请尝试重新添加任务"
                    v-if="!crawling && !crawlSuccess"
                ></a-result>
            </div>
        </a-modal>

        <a-layout-content
            :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
        >
            <a-spin v-if="loadingTasks" />
            <div class="table_container" v-if="!loadingTasks">
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
import request from '../request/request';

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

            showImport: false,
            importing: false,
            fileList: [],

            selectedRowKeys: [],

            pageTitle: "",

            showCrawl: false,
            crawling: true,
            crawlSuccess: false,
            crawlKey: "",

            loadingTasks: false,
        }
    },
    methods: {
        // 添加爬虫任务
        addTask() {
            // console.log(this.pageTitle);
            this.loadingTasks = true;
            let that = this;
            request.addTask({
                title: that.pageTitle
            })
            .then((res)=>{
                // console.log(res);
                that.listData = res.result;
                that.loadingTasks = false;
            })
        },
        // 选择任务
        onSelectChange(selectedRowKeys) {
            // console.log('selectedRowKeys changed: ', selectedRowKeys);
            this.selectedRowKeys = selectedRowKeys;
        },
        // 请求任务列表
        requestList(data) {
            this.loadingTasks = true;
            let that = this;
            request.getTaskList(data)
            .then((res)=>{
                console.log(res);
                that.listData = res.result;
                that.loadingTasks = false;
            })
        },
        // ???
        handleChange(value, option) {
            console.log(option);
        },
        // 打开批量导入窗口
        openImport() {
            this.showImport = true;
        },
        // 开始爬虫
        crawl() {
            this.showCrawl = true;
            this.crawling = true;
            this.crawlKey = "";
            let that = this;
            let keys = [];
            for(let i=0; i<this.selectedRowKeys.length; i++) {
                keys.push(that.listData[that.selectedRowKeys[i]].title);
            }
            // console.log(keys);
            request.runCrawl({
                titles: keys
            })
            .then((res)=>{
                console.log(res);
                if(res.error==0) {
                    that.crawlSuccess = true;
                    that.crawlKey = res.result
                }
                else {
                    that.crawlSuccess = false;
                }
                that.crawling = false;
            })
        },
        // 跳转查看爬虫结果
        crawlConfirm() {
            this.showCrawl = false;
        },
        // 关闭爬虫窗口
        crawlClose() {
            this.showCrawl = false;
        },
        // 关闭批量导入窗口
        handleCancel() {
            this.fileList = [];
            this.showImport = false;
        },
        // 返回多选列表是否为空
        selectedRowEmpty() {
            // console.log(this.selectedRowKeys.length);
            return this.selectedRowKeys.length==0;
        },
        // 删除某个导入文件
        handleFileRemove(file) {
            const index = this.fileList.indexOf(file);
            const newFileList = this.fileList.slice();
            newFileList.splice(index, 1);
            this.fileList = newFileList;
        },
        // 拦截文件上传
        beforeUpload(file) {
            if(this.fileList.length>=1) {
                this.$message.warning('一次只能上传一个文件哦~');
            }
            else {
                this.fileList = [...this.fileList, file];
            }
            return false;
        },
        // 批量添加爬虫任务
        importTask() {
            this.importing = true;
            // console.log(this.fileList[0]);
            let that = this;
            request.importTask({
                file: that.fileList[0]
            })
            .then((res)=>{
                console.log(res);
                if(res.error == 0) {
                    that.showImport = false;
                    that.importing = false;
                    that.$message.success("导入成功");
                    that.requestList({
                        page: 1
                    });
                }
            })
        },
        // 文件是否上传过
        exectImportFile() {
            return this.fileList.length>0;
        }
    },
    mounted() {
        this.requestList({
            page: 1
        });
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