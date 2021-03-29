$(function () {
    var url = window.location.href;
    if($("#status").text()!="登录"){
        $("#status").css("pointer-events","none");
    }
    $("#logo").click(function(){
        window.location.href = url;
    })
    $("#mark").click(function(){
        window.location.href = "../html/Bookmark.html";
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
    $("#loginBtn").click(function(){
        if($("user").val() == ""){
            alert("请输入用户名");
        }
        else if($("pw").val() == ""){
            alert("请输入密码");
        }
        else {
            var info = {
                phonenumber: user.value,
                password: pw.value
            };
            var urlStr = " https://mock.mengxuegu.com/mock/60606976f2e38f3a2f6b9c4a/Home";
            PostHandle(urlStr, JSON.stringify(info), function(data){
                if(data.code == 1){
                    alert(data.message);
                    $("#mark").css("display","block");
                    $("#status").text(data.userName);
                    $("#searchBox input").removeAttr("disabled");
                    $("#login").fadeOut("slow");
                    localStorage.setItem("uid",data.uid);
                } else if(data.code == 0){
                    alert(data.message);
                }
            });
        }
    });
    $("#searchPaper").on('keypress',function(event){
        if(event.keyCode == 13){
            var searchVal = $("#searchPaper").val();
            localStorage.setItem("searchVal",searchVal);
            window.location.href = "../html/Result.html";
        }
    })
})