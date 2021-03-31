$(function () {
    $("#status").click(function(){
        $("#login").fadeIn("slow");
    });

    $(document).bind("click", function (e) {
        var target = $(e.target);
        if (target.closest("#status").length == 0 && target.closest("#login").length == 0) {
            $("#login").fadeOut("slow");
        }
    })

    $("#upload").click(function(){
        var inputObj=document.createElement('input');
        inputObj.setAttribute('id','file');
        inputObj.setAttribute('type','file');
        inputObj.setAttribute('name','file');
        inputObj.setAttribute("style",'visibility:hidden');
        $("body").append(inputObj);
        //inputObj.value;
        inputObj.click();
        //console.log(inputObj);
    })

    $("#loginBtn").click(function(){
        if($("user").val() == ""){
            alert("请输入用户名");
        } else if($("pw").val() == ""){
            alert("请输入密码");
        } else {
            var info = {
                username: user.value,
                password: pw.value
            };
            //var urlStr = "https://mock.mengxuegu.com/mock/60634842f2e38f3a2f6ba3ec/example_copy/login";
            var urlStr = "http://192.168.0.110:8000/login";
            PostHandle(urlStr, JSON.stringify(info), function(data){
                if(data.code == 200){
                    $("#mark").css("display","block");
                    $("#status").text(data.data.username);
                    $("#status").css("pointer-events","none");
                    $("#status").css("cursor","none");
                    localStorage.setItem("username",data.data.username);
                    $("#searchBox input").removeAttr("disabled");
                    $("#login").fadeOut("slow");
                    localStorage.setItem("uid",data.data.id);
                } else {
                    alert(data.code + " " + data.message);
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