$(function () {
    $("#mark").css("display","block");
    $("#status").text(localStorage.getItem("username"));
    $("#status").css("pointer-events","none");
    $("#status").css("cursor","none");
    $("#searchBox input").removeAttr("disabled");

    var urlStr = "/list";
    var searchVal = {
        pagenum: 1,
        type: 1,
        searchval: localStorage.getItem("searchVal")
    };
    PostHandle(urlStr, JSON.stringify(searchVal), function(data){
        var totalPage;
        localStorage.setItem("totalPage",totalPage);
        //论文列表展示
    });

    var urlStr = "/trend";
    var trendVal = {
        type:"trend"
    };
    PostHandle(urlStr, JSON.stringify(trendVal), function(data){
        //趋势图与展示
    });

    var urlStr = "/rank";
    var rankVal = {
        type:"rank"
    };
    PostHandle(urlStr, JSON.stringify(rankVal), function(data){
        //排名展示
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
            var urlStr = "/list";
            var searchVal = {
                pagenum: 1,
                type: $("#search option:selected").val(),
                searchval: localStorage.getItem("searchVal")
            };
            GetHandle(urlStr, searchVal, function(data){
                //论文列表展示
            });
        }
    })

    $(".name").click(function(){
        var urlStr = "/list";
        var searchVal = {
            searchVal:$(this).text()
        };
        GetHandle(urlStr, JSON.stringify(searchVal), function(data){
            //论文列表展示
        });
    })

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