<template>
    <div id="insert_page">
        <div class="search_part">
            <el-input
                placeholder="请输入论文题目"
                v-model="page_name"
                clearable
            >
            </el-input>
            <el-button plain @click="search_page">搜索</el-button>
            <el-button plain @click="file_insert">文件导入</el-button>
        </div>
        <div class="list">
            <el-table :data="insert_data" stripe>
                <el-table-column
                    prop="isbn"
                    label="论文编号"
                    width="100"
                    align="center"
                >
                </el-table-column>
                <el-table-column
                    prop="name"
                    label="论文题目"
                    width="280"
                    align="center"
                    show-overflow-tooltip
                >
                </el-table-column>
                <el-table-column
                    prop="tag"
                    label="关键词"
                    width="280"
                    align="center"
                    show-overflow-tooltip
                >
                </el-table-column>
                <el-table-column align="center">
                    <template slot-scope="scope">
                        <el-button
                            class="insert_button"
                            plain
                            @click="insert_page(scope.row.isbn, $event)"
                            >导入</el-button
                        >
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
export default {
    name: "Insertpage",

    data() {
        return {
            page_name: "",
            insert_data: [
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

    methods: {
        search_page(e) {
            let target = e.target;
            if (target.nodeName == "SPAN") {
                target = target.parentNode;
            }
            target.blur();
        },

        file_insert(e) {
            let target = e.target;
            if (target.nodeName == "SPAN") {
                target = target.parentNode;
            }
            target.blur();
        },

        insert_page(isbn, e) {
            let target = e.target;
            if (target.nodeName == "SPAN") {
                target = target.parentNode;
            }
            target.blur();

            console.log(isbn);
        },
    },
};
</script>

<style scoped>
#insert_page {
    height: 100%;
    width: 77%;
}

.search_part {
    /* border: 1px red solid;
    box-sizing: border-box; */
    display: flex;
    width: 90%;
    margin: 0 auto;
    margin-top: 5%;
    justify-content: center;
    align-items: center;
}

.el-input {
    width: 30%;
    margin: 0 30px;
}

.search_part >>> .el-input__inner {
    border-color: #033;
    border-radius: 10px;
}

.el-button {
    /* border: 1px red solid;
    box-sizing: border-box; */
    margin: 0 15px;
    border-color: #033;
    border-radius: 10px;
    color: #033;
    font-weight: bold;
}

.el-button:hover {
    background-color: #033;
    color: #fcfdf5;
}

.list {
    width: 70%;
    margin: 0 auto;
    margin-top: 5%;
}

.insert_button {
    padding: 5% 10%;
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