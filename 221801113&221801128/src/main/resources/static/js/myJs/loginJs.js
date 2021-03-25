$(function(){
    let active = false;

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