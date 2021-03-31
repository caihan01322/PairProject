$(function () {
    $("#mark").css("display","block");
    $("#status").text(localStorage.getItem("username"));
    $("#status").css("pointer-events","none");
    $("#status").css("cursor","none");
    $("#searchBox input").removeAttr("disabled");

    //var urlStr = "https://mock.mengxuegu.com/mock/60634842f2e38f3a2f6ba3ec/example_copy/list";
    var urlStr = "http://192.168.0.110:8000/list";
    var val = localStorage.getItem("searchVal");
    if(val === ""){
        val = " ";
    }
    var searchVal = {
        pagenum: 1,
        type: 1,
        searchval: val
    };
    showList(urlStr,searchVal);

    //var urlStr = "https://mock.mengxuegu.com/mock/60634842f2e38f3a2f6ba3ec/example_copy/rank";
    var urlStr = "http://192.168.0.110:8000/rank";
    var rankVal = {
        type:"rank"
    };
    PostHandle(urlStr, JSON.stringify(rankVal), function(data){
        if(data.code == 200){
            var topList = data.data.top_list;
            var topRanks = [];
            for(var i = 0 ; i < 10 ; i++){
                topRanks[i] = {
                    name: topList[i].name,
                    count: topList[i].count,
                    index: i+1
                };
            }
            var app = new Vue({
                el: '#rank',
                data: {
                    topRanks: topRanks,
                },
                methods: {
                    num: function(index){
                        return "num" + index;
                    },
                    name: function(index){
                        return "name" + index;
                    },
                    rankClick :function(data){
                        //var urlStr = "https://mock.mengxuegu.com/mock/60634842f2e38f3a2f6ba3ec/example_copy/list";
                        var urlStr = "http://192.168.0.110:8000/list";
                        var searchVal = {
                            pagenum: 1,
                            type: 2,
                            searchval: data
                        };
                        showList(urlStr,searchVal);
                    }
                }
            });
        } else {
            alert(data.code + " " + data.message);
        }
    });

    $("#trend_nav div").click(function(){
        var nav_div = document.getElementById("trend_nav").getElementsByTagName("div");
        var content_div = document.getElementById("trend").getElementsByClassName("chart");
        for(var i = 0, len = nav_div.length; i < len; i++){
            if(nav_div[i] === this){
                nav_div[i].className = 'nav_selected';
                content_div[i].className = 'chart display_chart';
            }
            else{
                nav_div[i].className = '';
                content_div[i].className = 'chart content_selected';
            }
        }
    })

    $("#searchPaper").on('keypress',function(event){
        if(event.keyCode == 13){
            console.log($("#search option:selected").val());
            //var urlStr = "https://mock.mengxuegu.com/mock/60634842f2e38f3a2f6ba3ec/example_copy/list";
            var urlStr = "http://192.168.0.110:8000/list";
            var searchVal = {
                pagenum: 1,
                type: parseInt($("#search option:selected").val()),
                searchval: $("#searchBox input").val()
            };
            showList(urlStr,searchVal);
        }
    });

    $(".markSvg").click(function(){
        alert("收藏成功！");
        $(this).attr("src","../img/Result/marked.svg");
        $(this).attr("title","已收藏");
        $(this).css("cursor","default");
        $(this).css("pointer-events","none");
    });

    $(".deleteSvg").click(function(){
        alert("删除成功！");
        $(this).attr("src","../img/Result/deleted.svg");
        $(this).attr("title","已删除");
        $(this).css("cursor","default");
        $(this).css("pointer-events","none");
        $(this).parent().children("div.title").css("text-decoration","line-through");
        $(this).next().css("cursor","default");
        $(this).next().css("pointer-events","none");
        var urlStr = "/Delete";
        var paperUid = {
            paperUid:$(this).parent().children("div.title").attr("data-paper-uid")
        };
        GetHandle(urlStr, JSON.stringify(paperUid), function(data){
            //删除论文列表
        });
    });
})
