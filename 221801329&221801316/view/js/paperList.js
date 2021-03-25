var globalBaseURL = 'http://121.5.100.116:8080/api';
var instance = axios.create({
    baseURL: 'http://121.5.100.116:8080/api',
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    },
})
var searchStmt;

function getPaperList(pageNum) {
    searchStmt = document.getElementById("search-text").value;
    instance.get('/search', {
            params: {
                keyword: searchStmt,
                pageNum: pageNum
            }
        })
        .then(function (response) {
            panel = document.getElementById('main-panel');
            panel.innerHTML = "";
            //console.log(response.data);
            var data = response.data['data'];
            if (data.totalNum === 0) {
                panel.innerHTML = panel.innerHTML + "<p style=\"text-align:center;color: rgb(127, 127, 127);\">No result</p>";
            } else {
                var list = data.list;
                console.log(list);
                for (var k in list) {
                    var element = list[k];
                    var abstractStr = element['artical']['Abstract'].slice(0, 100) + "...";
                    var authorStr = "";
                    var keywordStr = "";
                    for (var t in element.authors) {
                        authorStr += element.authors[t] + ';';
                        if (t >= 3) {
                            break;
                        }
                    }
                    authorStr = authorStr.slice(0, -1);
                    for (var t in element.keywords) {
                        keywordStr += element.keywords[t] + ';';
                        if (t >= 3) {
                            break;
                        }
                    }
                    keywordStr = keywordStr.slice(0, -1);
                    panel.innerHTML = panel.innerHTML +
                        "<div class=\"paper-list\" id=" +
                        element.artical.academicNum +
                        "><a href=" +
                        element.artical.link +
                        " class=\"paper-title\">" +
                        element.artical.title +
                        "</a>" +
                        "<p class=\"paper-author\">" +
                        authorStr +
                        "</p> <p> <span class=\"paper-abstract-title\">[Abstract]</span>" +
                        "<span class=\"paper-abstract-detial\">" +
                        abstractStr +
                        "</span></p>" +
                        "<p><span class=\"paper-keyword\">[Keyword]</span>" +
                        "<span class=\"paper-keyword-list\">" +
                        keywordStr +
                        "</span></p></div>"
                }
                initPagination(pageNum, list['totalPage']);
            }
        })
        .then(function (error) {
            console.log(error);
        })


}

function initPagination(currentPage, totalPage) {
    panel = document.getElementById('main-panel');
    var start;
    var end;
    if (totalPage < 8) {
        start = 1;
        end = totalPage;
    } else {
        start = currentPage - 4;
        end = currentPage + 3;
        if (start < 1) {
            start = 1;
            end = start + 7;
        }
        if (end > totalPage) {
            end = totalPage;
            start = end - 7;
        }
    }
    var str = '<nav aria-label="Page navigation">' +
        '<ul class="pagination">' +
        '<li>' +
        '<a href=' +
        ' aria-label="Previous">' +
        '<span aria-hidden="true">&laquo;</span>' +
        '</a></li>';
    for (var i = start; i <= end; i++) {
        if (currentPage == i - 1) {
            var li = "<li class=\"active\"><a onclick=getPaperList(" + (i - 1) + ")>" + i + "</a></li>";
        } else {
            var li = "<li><a onclick=getPaperList(" + (i - 1) + ")>" + i + "</a></li>";
        }
        str += li;
    }
    str += '<li>' +
        '<a href=' + +' aria-label="Next">' +
        '<span aria-hidden="true">&raquo;</span>' +
        '</a></li></ul></nav>'
    panel.innerHTML = panel.innerHTML + str;
}

function getTopKwords() {
    panel = document.getElementById('main-panel');
    panel.innerHTML = '';
    instance.get('/url', {
            params: {
                keyword: 'searchStmt',
                pageNum: 'pageNum'
            }
        })
        .then(function (response) {

        })
        .then(function (response) {
            console.log(error);
        })
}

function getIndex() {
    panel = document.getElementById('main-panel');

    panel.innerHTML = '' +
        '<div class="img-panel">' +
        '<img src="../img/u98.png" class="img-box" alt="">' +
        '<span class="img-span">A batch/recursive algorithm for 3D scene reconstruction</span>' +
        '<span class="img-keyword">Camera Rotation</span>' +
        '</div>' +
        '<div class="img-panel">' +
        '<img src="../img/u98.png" class="img-box" alt="">' +
        '<span class="img-span">A batch/recursive algorithm for 3D scene reconstruction</span>' +
        '<span class="img-keyword">Camera Rotation</span>' +
        '</div>';
}