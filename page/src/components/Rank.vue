<!--  -->
<template>
    <div class='rank'>
        <div class="tabs">
            <a-tabs default-active-key="1" @change="changeTab">
                <a-tab-pane key="1" tab="ECCV">
                    <div class="title">
                        <h3>热门领域</h3>
                    </div>
                    <div class="table_container">
                        <rank-table :tableData="tableData" :label="1"></rank-table>
                    </div>
                </a-tab-pane>
                <a-tab-pane key="2" tab="ICCV">
                    <div class="title">
                        <h3>热门领域</h3>
                    </div>
                    <div class="table_container">
                        <rank-table :tableData="tableData" :label="2"></rank-table>
                    </div>
                </a-tab-pane>
                <a-tab-pane key="3" tab="CVPR">
                    <div class="title">
                        <h3>热门领域</h3>
                    </div>
                    <div class="table_container">
                        <rank-table :label="3"></rank-table>
                    </div>
                </a-tab-pane>
            </a-tabs>
        </div>
    </div>
</template>

<script>

import RankTable from './RankTable.vue'

import request from '../request/request'

export default {
    name: 'Rank',
    components: {RankTable},
    data () {
        return {
            tableData: [],
            label: 1
        }
    },
    methods: {
        changeTab(value){
            console.log(value);
            this.label = value;
        },
    },
    mounted() {
    },
}
</script>
<style lang='scss' scoped>
//@import url();
.tabs {
    padding: 0 12px;
}
.title {
    width: 100%;
    padding: 0 12px;
    box-sizing: border-box;
    h3 {
        margin: 0;
        font-weight: 700;
    }
}
.table_container {
    width: 100%;
    margin-top: 12px;
    padding: 0 12px;
    box-sizing: border-box;
}
</style>