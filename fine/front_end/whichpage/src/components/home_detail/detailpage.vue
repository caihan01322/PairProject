<template>
    <div id="detail_page">
        <div id="back">
            <el-button plain @click="go_home">
                <i class="iconfont icon-back"></i>
                返回
            </el-button>
        </div>

        <div id="edit">
            <el-button plain @click="go_edit">
                <i class="iconfont icon-edit"></i>
                编辑
            </el-button>
        </div>

        <div id="detail_part">
            <div class="detail_item">
                <div class="first_color list_title">论文题目</div>
                <div class="message_part first_color">{{ page_name }}</div>
            </div>
            <div class="detail_item">
                <div class="second_color list_title">论文年份</div>
                <div class="message_part second_color">{{ page_time }}</div>
            </div>
            <div class="detail_item">
                <div class="first_color list_title">原文链接</div>
                <div class="message_part first_color">{{ page_link }}</div>
            </div>
            <div class="detail_item">
                <div class="second_color list_title">关键词组</div>
                <div class="message_part second_color">{{ page_tag }}</div>
            </div>
            <div class="detail_item page_abstract">
                <div class="first_color list_title">内容摘要</div>
                <div class="message_part first_color">{{ page_abstract }}</div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "Detailpage",

    data() {
        return {
            isbn: this.$route.params.isbn,
            page_name:
                "FaceForensics++: Learning to Detect Manipulated Facial Images",
            page_time: "2020.01.20",
            page_link: "https://www.bilibili.com",
            page_tag:
                "facial manipulation detection,Deep-Fakes,Face2Face,facial manipulations,random compression level,forgery datasets",
            page_abstract:
                "FaceForensics++: Learning to Detect Manipulated Facial ImagesFaceForensics++: Learning to Detect Manipulated Facial ImagesFaceForensics++: Learning to Detect Manipulated Facial ImagesFaceForensics++: Learning to Detect Manipulated Facial ImagesFaceForensics++: Learning to Detect Manipulated Facial ImagesFaceForensics++: Learning to Detect Manipulated Facial ImagesFaceForensics++: Learning to Detect Manipulated Facial ImagesFaceForensics++: Learning to Detect Manipulated Facial ImagesFaceForensics++: Learning to Detect Manipulated Facial Imagesv",
        };
    },

    methods: {
        go_home() {
            this.$router.push({ name: "listpage" });
        },

        go_edit() {
            this.$router.push({
                name: "editpage",
                params: { isbn: this.isbn },
            });
        },
    },
};
</script>

<style scoped>
#detail_page {
    /* border: 1px red solid;
    box-sizing: border-box; */
    width: 77%;
    height: 100%;
}

#back > .el-button {
    margin: 5% 0 0 17%;
    border-bottom: 1px #033 solid;
    padding: 12px 20px 12px 10px;
    /* background-color: white; */
    color: #033;
    font-weight: bold;
}

#edit > .el-button {
    /* border: 1px red solid;
    box-sizing: border-box; */
    margin-left: 77%;
    padding: 12px 20px 12px 10px;
    color: #033;
    font-weight: bold;
}

.iconfont {
    margin-right: 20%;
}

#detail_part {
    width: 67%;
    /* height: 60%;
    overflow: auto; */
    color: #033;
    font-weight: bold;
    margin: 0 auto;
}

.detail_item {
    display: flex;
    justify-content: space-between;
    height: 50px;
}

.list_title {
    border: 1px #033 solid;
    box-sizing: border-box;
    display: flex;
    align-items: center;
    justify-content: center;
    color: #033;
    font-size: 18px;
    font-weight: bold;
    width: 50%;
}

.message_part {
    border: 1px #033 solid;
    box-sizing: border-box;
    display: flex;
    justify-content: center;
    padding: 1%;
    width: 50%;
    font-size: 16px;
    color: #033;
    overflow: auto;
    white-space: normal;
    word-break: break-all;
}

.first_color {
    background-color: #9ecca4;
}

.second_color {
    background-color: #fcfdf5;
}

.page_abstract {
    height: 48%;
}

.page_abstract > .message_part {
    justify-content: flex-start;
    text-align: left;
}

.el-button {
    background-color: #fcfdf5;
    border: 1px #fcfdf5 solid;
    border-radius: 0;
}

.el-button:hover {
    border-color: #033;
    border-radius: 10px;
}
</style>