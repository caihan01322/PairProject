$(function(){
    let active = false;

    //login
    /*$('#login').click(() => {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/t1",
            data: {},
            success: function(res) {
                console.log(res);
            },
            //请求失败，包含具体的错误信息
            error: function(res){
                console.log(res.status);
                console.log(res.responseText);
            }
        });
    })*/

    //toggle
    $(".sbutton").click(() => {
        $('input').val("");
        active = !active;
        if (active) {
            $(".big-box").addClass("active");
            $(".small-box").addClass("active");
            $(".login").attr("style","display: none");
            $(".register").attr("style","");
        } else {
            $(".big-box").removeClass("active");
            $(".small-box").removeClass("active");
            $(".login").attr("style","");
            $(".register").attr("style","display: none");
        }
    })
})