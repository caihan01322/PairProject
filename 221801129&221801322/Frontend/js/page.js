var pageBar = new Vue({
    el: '.page-bar',
    data: {
        all: localStorage.getItem("totalPage"), //总页数
        cur: 1  //当前页码
    },
    /*watch: {
        cur: function(oldValue , newValue){
            console.log("123"+arguments);
        }
    },*/
    methods: {
        btnClick: function(data){
            if(data != this.cur){
                this.cur = data;
            }
            console.log(this.cur+'页');
            //var urlStr = "https://mock.mengxuegu.com/mock/60634842f2e38f3a2f6ba3ec/example_copy/list";
            var urlStr = "http://192.168.0.110:8000/list";
            var searchVal = {
                pagenum: this.cur,
                type: 1,
                searchval: localStorage.getItem("searchVal")
            };
            showList(urlStr,searchVal);

        },
        pageClick: function(){
            console.log('现在在'+this.cur+'页');
            //var urlStr = "https://mock.mengxuegu.com/mock/60634842f2e38f3a2f6ba3ec/example_copy/list";
            var urlStr = "http://192.168.0.110:8000/list";
            var searchVal = {
                pagenum: this.cur,
                type: 1,
                searchval: localStorage.getItem("searchVal")
            };
            showList(urlStr,searchVal);
        },
    },
    computed: {
        indexs: function(){
            var left = 1;
            var right = this.all;
            var ar = [];
            if(this.all >= 5){
            if(this.cur > 3 && this.cur < this.all-1){
                left = this.cur - 2
                right = this.cur + 2
            } else {
                if(this.cur <= 3){
                    left = 1
                    right = 5
                } else {
                    right = this.all
                    left = this.all - 4
                }
            }
        }
        while (left <= right){
            ar.push(left)
            left ++
        }
        console.log(ar);
        return ar
        }
    }
})

function getList(){
    return JSON.parse(localStorage.getItem("lists"));
}

var app1 = new Vue({
    el: '.list',
    data: {
        lists: getList()
    },
    methods:{
        getLists : function(){
            this.lists = getList();
        }
    }
});
