$(function(){
    $(".close").click(function(){
        $(".lookCard").css("display","none")
        $(".editCard").css("display","none")
        $(".deleteCard").css("display","none")
        $("td a").css("cursor","pointer")
    })
})
function LookCard(){
    $(".lookCard").css("display","block")
    $("td a").css("cursor","not-allowed")
    $("tr").hover(function (){
            $("tr").css("background-color","#fff")
        }
    )
}
function EditCard(){
    $(".editCard").css("display","block")
    $("td a").css("cursor","not-allowed")
    $("tr").hover(function (){
            $("tr").css("background-color","#fff")
        }
    )
}
function DeleteCard(){
    $(".deleteCard").css("display","block")
    $("td a").css("cursor","not-allowed")
    $("tr").hover(function (){
            $("tr").css("background-color","#fff")
        }
    )
}