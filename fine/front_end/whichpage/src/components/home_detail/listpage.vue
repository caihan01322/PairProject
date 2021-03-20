<template>
    <div id="list_page">
        <div id="search_bar">
            <el-input
                class="search_part"
                placeholder="请输入论文编号"
                v-model="page_id"
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
                <el-table-column prop="id" label="论文编号" width="180">
                </el-table-column>
                <el-table-column prop="name" label="论文名" width="180">
                </el-table-column>
                <el-table-column prop="tag" label="关键词"> </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
export default {
    name: "Listpage",

    data() {
        return {
            page_id: "",
            page_name: "",
            page_tag: "",
            page_data: [
                {
                    id: "2016-05-02",
                    name: "王小虎",
                    tag: "上海市普陀区金沙江路 1518 弄",
                },
                {
                    id: "2016-05-04",
                    name: "王小虎",
                    tag: "上海市普陀区金沙江路 1517 弄",
                },
                {
                    id: "2016-05-01",
                    name: "王小虎",
                    tag: "上海市普陀区金沙江路 1519 弄",
                },
                {
                    id: "2016-05-03",
                    name: "王小虎",
                    tag: "上海市普陀区金沙江路 1516 弄",
                },
            ],
        };
    },

    methods: {
        search_page() {},
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
</style>