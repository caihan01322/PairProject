<template>
    <div id="list_page">
        <div id="search_bar">
            <el-input
                class="search_part"
                placeholder="请输入论文编号"
                v-model="page_isbn"
                clearable
            >
            </el-input>
            <el-input
                class="search_part"
                placeholder="请输入论文名"
                v-model="page_name"
                clearable
            >
            </el-input>
            <el-input
                class="search_part"
                placeholder="请输入关键词"
                v-model="page_tag"
                clearable
            >
            </el-input>
            <el-button @click="search_page" plain>搜索</el-button>
        </div>

        <div id="table_part">
            <el-table :data="page_data" stripe>
                <el-table-column
                    prop="isbn"
                    label="论文编号"
                    width="100"
                    align="center"
                ></el-table-column>
                <el-table-column
                    prop="name"
                    label="论文名"
                    width="280"
                    align="center"
                    show-overflow-tooltip
                ></el-table-column>
                <el-table-column
                    prop="tag"
                    label="关键词"
                    width="280"
                    align="center"
                    show-overflow-tooltip
                ></el-table-column>
                <el-table-column align="center">
                    <template slot-scope="scope">
                        <i
                            class="iconfont icon-detail"
                            title="详细"
                            @click="go_detail(scope.row.isbn)"
                        ></i>
                        <i
                            class="iconfont icon-edit"
                            title="修改"
                            @click="go_edit(scope.row.isbn)"
                        ></i>
                        <i
                            class="iconfont icon-delete"
                            title="删除"
                            @click="go_delete"
                        ></i>
                    </template>
                </el-table-column>
            </el-table>
        </div>

        <div id="page_part">
            <el-button :class="{ show_btn: !isShowPre }" plain @click="go_pre"
                >上一页</el-button
            >
            <span>当前第{{ now_page }}页 / 共{{ total_page }}页</span>
            <el-button :class="{ show_btn: !isShowNext }" plain @click="go_next"
                >下一页</el-button
            >
        </div>
    </div>
</template>

<script>
export default {
    name: "Listpage",

    data() {
        return {
            now_page: 1,
            total_page: 4,
            isShowPre: true,
            isShowNext: true,
            page_isbn: "",
            page_name: "",
            page_tag: "",
            page_data: [
                {
                    isbn: "19398261",
                    name:
                        "FaceForensics++: Learning to Detect Manipulated Facial Images",
                    tag:
                        "facial manipulation detection,Deep-Fakes,Face2Face,facial manipulations,random compression level,forgery datasets",
                },
                {
                    isbn: "19398261",
                    name:
                        "FaceForensics++: Learning to Detect Manipulated Facial Images",
                    tag:
                        "facial manipulation detection,Deep-Fakes,Face2Face,facial manipulations,random compression level,forgery datasets",
                },
                {
                    isbn: "19398261",
                    name:
                        "FaceForensics++: Learning to Detect Manipulated Facial Images",
                    tag:
                        "facial manipulation detection,Deep-Fakes,Face2Face,facial manipulations,random compression level,forgery datasets",
                },
                {
                    isbn: "19398261",
                    name:
                        "FaceForensics++: Learning to Detect Manipulated Facial Images",
                    tag:
                        "facial manipulation detection,Deep-Fakes,Face2Face,facial manipulations,random compression level,forgery datasets",
                },
                {
                    isbn: "19398261",
                    name:
                        "FaceForensics++: Learning to Detect Manipulated Facial Images",
                    tag:
                        "facial manipulation detection,Deep-Fakes,Face2Face,facial manipulations,random compression level,forgery datasets",
                },
                {
                    isbn: "2016-05-03",
                    name: "王小虎",
                    tag: "上海市普陀区金沙江路 1516 弄",
                },
            ],
        };
    },

    watch: {
        now_page: {
            handler(newValue) {
                if (newValue == 1) {
                    this.isShowPre = false;
                } else {
                    this.isShowPre = true;
                }

                if (newValue == this.total_page) {
                    this.isShowNext = false;
                } else {
                    this.isShowNext = true;
                }
            },

            immediate: true,
        },
    },

    methods: {
        search_page(e) {
            let target = e.target;
            if (target.nodeName == "SPAN") {
                target = target.parentNode;
            }
            target.blur();
        },

        go_detail(isbn) {
            // console.log(isbn);
            this.$router.push({ name: "detailpage", params: { isbn } });
        },

        go_edit(isbn) {
            // console.log(isbn);
            this.$router.push({ name: "editpage", params: { isbn } });
        },

        go_delete() {
            this.$confirm("是否删除该条论文记录?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning",
            }).then(() => {
                console.log("delete!!");

                this.$message({
                    type: "success",
                    message: "删除成功!",
                });
            });
        },

        go_pre(e) {
            let target = e.target;
            if (target.nodeName == "SPAN") {
                target = target.parentNode;
            }
            target.blur();

            this.now_page--;
        },

        go_next(e) {
            let target = e.target;
            if (target.nodeName == "SPAN") {
                target = target.parentNode;
            }
            target.blur();

            this.now_page++;
        },
    },
};
</script>

<style scoped>
#list_page {
    /* border: 1px red solid;
    box-sizing: border-box; */
    height: 100%;
    width: 77%;
}

#search_bar {
    /* border: 1px red solid;
    box-sizing: border-box; */
    display: flex;
    justify-content: center;
    width: 80%;
    margin: 0 auto;
    margin-top: 5%;
}

.el-input {
    width: 20%;
    margin-right: 5%;
}

.search_part >>> .el-input__inner {
    border-color: #033;
    border-radius: 10px;
}

.el-button {
    border-color: #033;
    color: #033;
    font-weight: bold;
    border-radius: 10px;
}

.el-button:hover {
    color: #fcfdf5;
    background-color: #033;
}

#table_part {
    /* border: 1px red solid;
    box-sizing: border-box; */
    width: 70%;
    margin: 0 auto;
    margin-top: 5%;
}

.iconfont {
    font-size: 24px;
    margin: 0 4%;
    color: #033;
}

.iconfont:hover {
    cursor: pointer;
}

.icon-detail:hover {
    color: #fe7419;
}

.icon-edit:hover {
    color: #d237d0;
}

.icon-delete:hover {
    color: #dd2b1e;
}

#page_part {
    /* border: 1px red solid;
    box-sizing: border-box; */
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 40%;
    margin: 0 auto;
    margin-top: 3%;
    color: #033;
}

.show_btn {
    visibility: hidden;
}
</style>

<style>
.el-table th,
.el-table tr {
    color: #033;
    background-color: #9ecca4;
}

.el-table__empty-block {
    background-color: #fcfdf5;
}

.el-table--striped .el-table__body tr.el-table__row--striped td {
    background-color: #9ecca4;
}

.el-table td,
.el-table th.is-leaf {
    border-bottom: 1px #033 solid;
}

.el-table__header-wrapper {
    background-color: #9ecca4;
}

.el-table {
    color: #033;
}

.el-table__row {
    background-color: #fcfdf5 !important;
}

.el-table__row:hover > td {
    background-color: #fcfdf5 !important;
}

.el-table__row--striped:hover > td {
    background-color: #9ecca4 !important;
}

.el-table .cell {
    white-space: nowrap;
}
</style>