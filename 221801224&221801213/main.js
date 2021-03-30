// var searchBar = new Vue({
//     el: "#searchBar",
//     data: {
//         joke: "一个笑话",

//     },
//     methods: {
//         // testfunc: function() {
//         //     var that = this;
//         //     axios.get('http://localhost:5000/pair/api/v1/paper/search?' +
//         //             'page=2&key=a&token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoxLCJleHAiOjEwMDAwMDAwMDAwMDAwMDAwMTU5MDI4OTExMDk3NTk5MTgwNDY4MzYwODA4NTYzOTQ1MjgxMzg5NzgxMzI3NTU3NzQ3ODM4NzcyMTcwMzgxMDYwODEzNDY5OTg1ODU2ODE1MTA0fQ.fOFAk2NIOfaQMam_QmrNFtghOyto_7eA6tZ4O_uIams')
//         //         .then(function(response) {
//         //             console.log(response);
//         //             that.joke = response.data.Data[1].Title;
//         //         })
//         //         .catch(function(err) {})

//         // }

//         testfunc: function() {
//             var that = this;
//             axios.get('https://api.gugudata.com/news/joke/demo')
//                 .then(function(response) {
//                     that.joke = response.data.Data[1].Title;
//                     resultBar.data.listData[0].heading = response.data.Data[1].Title;
//                 })
//                 .catch(function(err) {})

//         }
//     }

// })

var rightsideBar = new Vue({
    el: "#rightsideBar",
    data: {
        hotWordArr: ["机器学习", "机器学习", "机器学习", "机器学习", "机器学习",
            "机器学习", "机器学习", "机器学习", "机器学习", "机器学习"
        ],

    },
    methods: {
        func: function() {}
    }

})

var main = new Vue({
    el: "#main",
    data: {
        pageNum: 10,
        pageNow: 1,
        listData: [
            { num: 1, heading: "标题1", content: "内容1", keyword1: "这是一个关键词", source: "http://www.baidu.com" },
            { num: 2, heading: "标题2", content: "内容2", keyword1: "这是一个关键词", source: "http://www.bilibili.com" },
            { num: 3, heading: "标题3", content: "内容3", keyword1: "这是一个关键词", source: "http://www.baidu.com" },
            { num: 4, heading: "标题4", content: "内容4", keyword1: "这是一个关键词", source: "http://www.baidu.com" },
            { num: 5, heading: "标题5", content: "内容5", keyword1: "这是一个关键词", source: "http://www.baidu.com" },
            { num: 6, heading: "标题6", content: "内容6", keyword1: "这是一个关键词", source: "http://www.baidu.com" }
        ],
        userInput: "",
    },
    methods: {

        doSearching() {
            //搜索时默认从第一页获取
            //向后端发送最新的userInput和page=1
            this.pageNow = 1;
            this.update();
        },

        pageUp: function() {
            if (this.pageNow > 1) {
                this.pageNow--;
                //向后端发送已有的userInput和当前page
                this.update();
            } else {
                alert("已经是第一页！");
            }
        },

        pageDown: function() {

            if (this.pageNow < this.pageNum) {
                this.pageNow++;
                //向后端发送已有的userInput和当前page
                this.update();
            } else {
                alert("已经是最后一页！");
            }
        },

        deleteArticle: function() {

        },

        update: function() {

            for (var i = 0; i < 6; i++) {
                this.listData[i].num = (this.pageNow - 1) * 6 + i + 1;
                this.listData[i].heading = "标题" + ((this.pageNow - 1) * 6 + i + 1);
                this.listData[i].content = "  搜索 (" + this.userInput + ") 的第" + this.pageNow + "分页";
            }

        },
    }

})