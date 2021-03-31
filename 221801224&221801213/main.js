var main = new Vue({

    el: "#wrap",

    data() {
        return {
            info: [], //存放接收的论文信息
            outputInfo: [],
            hotWordArr: [], //存放接收的top10信息
            pageNum: 10,
            pageNow: 1,
            userInput: "",
            isShow: false,
        }
    },

    mounted() {
        //axios.get("http://localhost:8080/getKeyWordBySeg").then(response => (this.hotWordArr = response.data.data));
        axios.get("./hotWord.json").then(response => (this.hotWordArr = response.data.data));
    },

    methods: {

        doSearching() {
            //搜索时默认从第一页获取
            //向后端发送最新的userInput
            this.pageNow = 1;
            axios.get("./search.json").then(response => {
                this.info = response.data.data; //获取论文信息   
                this.pageNum = Math.ceil(this.info.length / 6); //获取条数
                this.outputInfo = this.info.slice(0, 6);

            });

            this.isShow = true;
        },

        doSearchingwithHotWord(p) {
            alert("查询：" + p);
        },

        pageUp: function() {
            if (this.pageNow > 1) {
                this.pageNow--;
                //
                this.outputInfo = this.info.slice((this.pageNow - 1) * 6, (this.pageNow - 1) * 6 + 6); //(0,6)获取012345 (6,12)获取6 7 8 9 10 11
            } else {
                //alert("已经是第一页！");
            }
        },

        pageDown: function() {

            if (this.pageNow < this.pageNum) {
                this.pageNow++;
                //
                if (this.info.length >= this.pageNow * 6) { //有充足的项目渲染
                    this.outputInfo = this.info.slice((this.pageNow - 1) * 6, (this.pageNow - 1) * 6 + 6); //(0,6)获取012345 (6,12)获取6 7 8 9 10 11
                } else {
                    this.outputInfo = this.info.slice((this.pageNow - 1) * 6, this.info.length);
                }

            } else {
                //alert("已经是最后一页！");
            }
        },

        deleteArticle: function(p) {
            alert("正在删除" + p);
        },
    }

})