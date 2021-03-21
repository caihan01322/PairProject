<template>
    <div id="insert_page">
        <div class="search_part">
            <el-input
                placeholder="请输入论文题目"
                v-model="page_name"
                clearable
            >
            </el-input>
            <el-button plain>搜索 </el-button>
            <el-button plain>文件导入</el-button>
        </div>
        <div class="list">
            <el-table :data="insert_data" stripe>
                <el-table-column prop="num" label="论文编号" width="180">
                </el-table-column>
                <el-table-column prop="name" label="论文题目" width="180">
                </el-table-column>
                <el-table-column prop="key_name" label="关键词">
                </el-table-column>
                <el-table-column prop="insert_button" label="按钮">
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
        };
    },

    methods: {
        //函数
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
    width: 55%;
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