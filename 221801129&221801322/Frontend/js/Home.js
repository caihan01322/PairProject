$(function () {
    var url = window.location.href;
    if($("#status").text()!="登录"){
        $("#status").css("pointer-events","none");
    }
    $("#logo").click(function(){
        window.location.href = url;
    })
    $("#status").click(function(){
        $("#login").fadeIn("slow");
    });
    $(document).bind("click", function (e) {
        var target = $(e.target);
        if (target.closest("#status").length == 0 && target.closest("#login").length == 0) {
            $("#login").fadeOut("slow");
        }
    })
    $("#searchPaper").on('keypress',function(event){
        if(event.keyCode == 13){
            var searchVal = $("#searchPaper").val();
            localStorage.setItem("searchVal",searchVal);
            window.location.href = "../html/Result.html";
        }
    })
})