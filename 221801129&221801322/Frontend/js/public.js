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

function GetHandle(url,data,callback) {
  $.ajax({
      type: "GET",
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

function showList(urlStr,searchVal){
    PostHandle(urlStr, JSON.stringify(searchVal), function(data){
        if(data.code == 200){
            //console.log(data.data.articlelist[0].article_id);
            var articlelist = data.data.articlelist;
            var len = articlelist.length;
            var lists = [];
            for(var i = 0 ; i < len ; i++){
                var keywords = [];
                var keylen = articlelist[i].Keywords.length;
                if(keylen <= 3){
                    for(var j = 0 ; j < keylen ; j++){
                        keywords[j] = articlelist[j].Keywords[j].name;
                    }
                } else {
                    for(var k = 0 ; k < 3 ; k++){
                        keywords[k] = articlelist[k].Keywords[k].name;
                    }
                }
                lists[i] = {
                    title: articlelist[i].title,
                    abstract: articlelist[i].abstract,
                    article_id: articlelist[i].article_id,
                    keyword: keywords
                };
            };
            // var app = new Vue({
            //     el: ".list",
            //     data: {
            //         lists: lists
            //     }
            // });
            localStorage.setItem("lists",JSON.stringify(lists));
            localStorage.setItem("totalPage",data.data.pagetotal);
            $("#btn").trigger("click");
        } else {
            alert(data.code + " " + data.msg);
        }
    });
}
