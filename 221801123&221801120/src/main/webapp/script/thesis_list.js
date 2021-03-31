$(function(){
    $(".close").click(function(){
        $(".lookCard").css("display","none")
        $(".editCard").css("display","none")
        $(".deleteCard").css("display","none")
        $("td button").css("cursor","pointer")
        document.getElementById("thesis_edit_error").innerHTML="";
    })
})
function LookCard(btn){
    $(".lookCard").css("display","block")
    $("td button").css("cursor","not-allowed")
    var $linkTd = $(btn).parent().prev();
    $("#thesis_view_link").val($linkTd.html());
    $("#thesis_view_abstractContent").val($linkTd.prev().html());
    $("#thesis_view_keyword").val($linkTd.prev().prev().html());
    $("#thesis_view_year").val($linkTd.prev().prev().prev().html());
    $("#thesis_view_meet").val($linkTd.prev().prev().prev().prev().html());
    $("#thesis_view_title").val($linkTd.prev().prev().prev().prev().prev().html());

}
function EditCard(btn){
    $(".editCard").css("display","block")
    $("td button").css("cursor","not-allowed");
    var $linkTd = $(btn).parent().prev();
    $("#thesis_edit_link").val($linkTd.html());
    $("#thesis_edit_abstractContent").val($linkTd.prev().html());
    $("#thesis_edit_keyword").val($linkTd.prev().prev().html());
    $("#thesis_edit_year").val($linkTd.prev().prev().prev().html());
    $("#thesis_edit_meet").val($linkTd.prev().prev().prev().prev().html());
    $("#thesis_edit_title").val($linkTd.prev().prev().prev().prev().prev().html());
    $("#thesis_edit_id").val($linkTd.prev().prev().prev().prev().prev().prev().html());
}
function DeleteCard(btn){
    /*$(".deleteCard").css("display","block")
    $("td button").css("cursor","not-allowed")*/
    var id = $(btn).parent().prev().prev().prev().prev().prev().prev().prev().html()
    if (confirm("真的要删除它吗"))
        sendDeleteRequest(id);
}

/**
 * [[发送删除请求]]
 * @param {[[String]]} [[请求参数]]
 */
function sendDeleteRequest(id) {
    $.ajax({
        "url" : "user/thesis/delete/" + id,
        "dataType" : "json",
        "async" : false,
        "success" : function(json) {
            if(json.result == 0) {
                Tips.showError(json.message);
            }else if(json.result == 1) {
                Tips.showSuccess(json.message);
                window.location.reload();
            }
        }
    });
}

function editThesis(form) {
    var error = document.getElementById("thesis_edit_error");
    var result = _checkThesis(form, error);
    if(result.result) {
        $.ajax({
            "url": "user/thesis/edit",
            "data": "id=" + result.id + "&title=" + result.title+ "&meet=" + result.meet+ "&year=" + result.year
                + "&keyword=" + result.keyword+ "&abstractContent=" + result.abstract+ "&link=" + result.link,
            "async": false,
            "dataType": "json",
            "success": function(json) {
                confirm(json.list);
            }
        });
    }
    return false;
}

function _checkThesis(form, error) {
    var id = form.id;
    var idValue = id.value.trim();
    //校验姓名
    var title = form.title;
    var titleValue = title.value.trim();
    if(titleValue == "") {
        error.innerHTML = "请输入论文题目";
        title.focus();
        return new CheckResult(false);
    }
    var meet = form.meet;
    var meetValue = meet.value.trim();
    if(meetValue == "") {
        error.innerHTML = "请输入论文来源";
        meet.focus();
        return new CheckResult(false);
    }
    var year = form.year;
    var yearValue = year.value.trim();
    if(yearValue == "") {
        error.innerHTML = "请输入论文年份";
        year.focus();
        return new CheckResult(false);
    }
    var keyword = form.keyword;
    var keywordValue = keyword.value.trim();
    if(keywordValue == "") {
        error.innerHTML = "请输入论文关键词";
        keyword.focus();
        return new CheckResult(false);
    }
    var abstract = form.abstract;
    var abstractValue = abstract.value.trim();
    if(abstractValue == "") {
        error.innerHTML = "请输入论文摘要";
        abstract.focus();
        return new CheckResult(false);
    }
    var link = form.link;
    var linkValue = link.value.trim();
    if(linkValue == "") {
        error.innerHTML = "请输入论文原文链接";
        link.focus();
        return new CheckResult(false);
    }
    return new CheckResult(true, idValue, titleValue, meetValue, yearValue, keywordValue, abstractValue, linkValue);
}


function CheckResult(result, id, title, meet, year, keyword, abstract, link) {
    this.result = result;
    this.id = id;
    this.title = title;
    this.meet = meet;
    this.year = year;
    this.keyword = keyword;
    this.abstract = abstract;
    this.link = link;
}

