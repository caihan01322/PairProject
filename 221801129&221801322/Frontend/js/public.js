function isIE() {
    var myNav = navigator.userAgent.toLowerCase();
    return (myNav.indexOf('msie') > 0) ? parseInt(myNav.split('msie')[1]) : false;
}
if (isIE()) {
  alert(
    '您正在使用的 IE 浏览器可能会导致兼容性问题。请使用 Edge，Chrome，Firefox 或其他现代浏览器。'
    + '\n\nThe browser Internet Explorer you\'re currently using might cause compatibility issues.' 
    + ' Please switch to more recent versions of Microsoft Edge, Google Chrome, or Firefox.'
  );
}

if($("#status").text()!="登录"){
    $("#status").css("pointer-events","none");
}
$("#mark").click(function(){
  window.location.href = "../html/Bookmark.html";
})
$("#logo").click(function(){
    window.location.href = "../html/Home.html";
})

function PostHandle(url,data,callback) {
  $.ajax({
      type: "POST",
      url: url,
      data: data,
      dataType: 'json',
      headers: {
          "Content-Type": "application/json"
      },
      success: function (data){
          callback(data);
      },
      error: function(xhr){
          alert("错误提示： " + xhr.status + " " + xhr.statusText);
      }
  });
}