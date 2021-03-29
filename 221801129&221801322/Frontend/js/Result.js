$(function () {
    $("#mark").css("display","block");
    $("#status").text(localStorage.getItem("username"));
    $("#searchBox input").removeAttr("disabled");

    var urlStr = "/Result";
    var searchVal = {
        searchStatus:"all",
        searchVal:localStorage.getItem("searchVal")
    };
    PostHandle(urlStr, JSON.stringify(searchVal), function(data){
        //数据接收与展示
    });

    $("#searchPaper").on('keypress',function(event){
        if(event.keyCode == 13){
            var urlStr = "/Result";
            var searchVal = {
                searchStatus:"list",
                searchVal:$("#searchPaper").val()
            };
            PostHandle(urlStr, searchVal, function(data){
                //搜索结果展示
            });
        }
    })

    $(".name").click(function(){
        var urlStr = "/Result";
        var searchVal = {
            searchStatus:"list",
            searchVal:$(this).text()
        };
        PostHandle(urlStr, JSON.stringify(searchVal), function(data){
            //搜索结果展示
        });
    })

    $(".markSvg").click(function(){
        alert("收藏成功！");
        $(".markSvg").attr("src","../img/Result/marked.svg");
        $(".markSvg").attr("title","已收藏");
        $(".markSvg").css("cursor","default");
    });

    $(".deleteSvg").click(function(){
        alert("删除成功！");
        $(".deleteSvg").attr("src","../img/Result/deleted.svg");
        $(".deleteSvg").attr("title","已删除");
        $(".deleteSvg").css("cursor","default");
        var urlStr = "/Delete";
        var paperUid = {
            paperUid:$(".title").attr("data-paper-uid")
        };
        PostHandle(urlStr, JSON.stringify(paperUid), function(data){
            //删除论文列表
        });
    });
})