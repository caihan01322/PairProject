$(function(){
    $(".close").click(function(){
        $(".lookCard").css("display","none")
        $(".editCard").css("display","none")
        $(".deleteCard").css("display","none")
        $("td button").css("cursor","pointer")
    })
})
function LookCard(){
    $(".lookCard").css("display","block")
    $("td button").css("cursor","not-allowed")
}
function EditCard(){
    $(".editCard").css("display","block")
    $("td button").css("cursor","not-allowed")
}
function DeleteCard(){
    $(".deleteCard").css("display","block")
    $("td button").css("cursor","not-allowed")
}