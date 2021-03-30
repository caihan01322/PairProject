var pageBar = new Vue({
    el: '.page-bar',
    data: {
        all: 8, //总页数
        cur: 1//当前页码
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
            var url = "/page";
            var data = {
                pageNum:this.cur
            };
            PostHandle(url,JSON.stringify(data),function(data){
                //返回数据
            });
        },
        pageClick: function(){
            console.log('现在在'+this.cur+'页');
            var url = "/page";
            var data = {
                pageNum:this.cur
            };
            PostHandle(url,JSON.stringify(data),function(data){
                //返回数据
            });
        }
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