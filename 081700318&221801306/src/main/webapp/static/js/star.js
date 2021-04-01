$(document).ready(function () {
  $(".thesis-star").click(function () {
    if($(this).text()!="收藏") {
      variable = new XMLHttpRequest();
      variable.open("POST", "Like", true);
      variable.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      variable.send("type=remove&id=" + $(this).attr("thesisid"));
      $(this).text("收藏");
    }
    else {
      variable = new XMLHttpRequest();
      variable.open("POST", "Like", true);
      variable.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      variable.send("type=add&id=" + $(this).attr("thesisid"));
      $(this).text("已收藏");
    }
    });

});
