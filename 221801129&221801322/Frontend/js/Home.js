$(function () {
    var url = window.location.href;
    if($("#status").text()!="登录"){
        $("#status").css("pointer-events","none");
    }
    $("#logo").click(function(){
        window.location.href = url;
    })
})