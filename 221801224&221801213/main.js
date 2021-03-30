var searchBar = new Vue({
    el: "#searchBar",
    data: {
        joke: "一个笑话",

    },
    methods: {
        testfunc: function() {
            var that = this;
            axios.get('https://api.gugudata.com/news/joke/demo')
                .then(function(response) {
                    console.log(response);
                    that.joke = response.data.Data[1].Title;
                })
                .catch(function(err) {


                })

        }
    }

})

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

var resultBar = new Vue({
    el: "#resultBar",
    data: {
        listData: [
            { heading: "标题1", content: "内容1" },
            { heading: "标题2", content: "内容2" },
            { heading: "标题3", content: "内容3" },
            { heading: "标题4", content: "内容4" },
            { heading: "标题5", content: "内容5" },
            { heading: "标题6", content: "内容6" }
        ]


    },
    methods: {
        func: function() {}
    }

})