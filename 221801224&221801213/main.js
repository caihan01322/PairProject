var main = new Vue({

    el: "#wrap",

    data() {
        return {
            info: [], //存放接收的论文信息
            outputInfo: [], //存放接收的论文信息修改后用于渲染的论文信息
            hotWordArr: [], //存放接收的top10信息
            pageNum: 10,
            pageNow: 1,
            userInput: "",
            isShow: false,
            isShow2: false,
            isSearch: true,
            isAnalysis: false,
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
            if (this.userInput != "") //输入不为空
            {

                axios.get("./" + this.userInput + ".json") //请求
                    .then(response => {
                        if (response.data.data == "") //data为空即搜索无结果
                        {
                            this.info = response.data.data; //获取论文信息 
                            this.pageNow = 0; //获取条数
                            this.pageNum = 0; //获取条数
                            this.outputInfo = this.info;
                            this.isShow = false;
                            this.isShow2 = true;
                        } else { //成功
                            this.info = response.data.data; //获取论文信息   
                            this.pageNum = Math.ceil(this.info.length / 6); //获取条数
                            this.outputInfo = this.info.slice(0, 6); //第一页渲染
                            this.isShow = true; //修改v-show显示结果
                            this.isShow2 = false;
                        }
                    })

                // var that = this;
                // axios.get('https://api.gugudata.com/news/joke/demo')
                //     .then(function(response) {
                //         console.log(response);

                //     })
                //     .catch(function(err) {})

            } else {
                alert("？");
            };


        },

        doSearchingwithHotWord(p) {
            this.userInput = p;
            this.doSearching();
            this.toSearch(); //跳转至search功能页
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
            alert("正在删除论文 编号:" + p);
            alert("删除成功，重新加载");
            this.doSearching(); //重新搜索刷新页面
        },

        toSearch: function() {
            this.isSearch = true;
            this.isAnalysis = false;
        },

        toAnalysis: function() {
            this.isSearch = false;
            this.isAnalysis = true;
        }
    }

})