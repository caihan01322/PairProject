$(function () {
    $("#mark").css("display","block");
    $("#status").text(localStorage.getItem("username"));
    $("#status").css("pointer-events","none");
    $("#status").css("cursor","none");
    $("#searchBox input").removeAttr("disabled");

    var urlStr = "http://192.168.0.110:8000/showmark";
    var uid = {
        user_id:localStorage.getItem("uid"),
        pagenum:1
    };
    showList(urlStr,searchVal);
    
    $(".markedSvg").click(function(){
        alert("取消收藏！");
        $(this).attr("src","../img/Bookmark/mark.svg");
        $(this).attr("title","未收藏");
        $(this).css("cursor","default");
        $(this).css("pointer-events","none");
    });
})