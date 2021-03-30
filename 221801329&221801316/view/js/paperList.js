let globalBaseURL = 'http://120.77.84.235:8080';
let instance = axios.create({
    baseURL: 'http://120.77.84.235:8080',
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    },
})
let searchStmt = '';

let isLogin = window.sessionStorage.getItem('isLogin')
if (!isLogin) window.location.href = 'login.html'


function getPaperList(pageNum) {

    searchStmt = document.getElementById("search-text").value;
    let offset = document.getElementById('checklist').value;
    let router = '';
    if (offset === 'author') router = '/queryByAuthor'
    else if (offset === 'title') router = '/queryByTitle'
    else if (offset === 'keyword') router = '/queryByKeyword'
    instance.get(router, {
            params: {
                start: pageNum * 10,
                rows: 10,
                word: searchStmt,
                userId: sessionStorage.getItem('userId')
            }
        })
        .then(function(response) {
            panel = document.getElementById('main-panel');
            panel.innerHTML = "";
            //console.log(response.data);
            let data = response.data;
            // console.log(JSON.stringify(data))
            setList(data, pageNum);

        })
        .then(function(error) {
            console.log(error);
        })
}

function setList(data, pageNum) {
    if (data.length === 0) {
        panel.innerHTML = panel.innerHTML + "<p style=\"text-align:center;color: rgb(127, 127, 127);\">No result</p>";
    } else {
        let list = data;
        for (let k in list) {
            let element = list[k].data;
            let abstractStr = element['abstractContent'].slice(0, 100) + "...";
            let authorStr = "";
            let keywordStr = "";
            for (let t in element.author) {
                authorStr += element.author[t] + ';';
                if (t >= 3) {
                    break;
                }
            }
            authorStr = authorStr.slice(0, -1);
            for (let t in element.keywords) {
                keywordStr += element.keywords[t] + ';';
                if (t >= 3) {
                    break;
                }
            }
            keywordStr = keywordStr.slice(0, -1);
            let sytle = "like";
            let link = globalBaseURL + '/'
            let src = '../img/gary-star.svg'
            if (list[k].isLike === 1) {
                src = '../img/orange-star.svg'
            }
            panel.innerHTML = panel.innerHTML +
                "<div class=\"paper-list\" id=" +
                element.id +
                "><a href=" +
                element.link +
                " class=\"paper-title\">" +
                element.title +
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
                "</span></p>" +
                "<img src=" + src + ' onclick=like(' +
                element.id + ')' + ' id=Like' +
                element.id + ' class=' + sytle + '>'
            "</div>"
        }
        initPagination(pageNum, list['totalPage']);
    }
}

function like(data) {

    console.log(data)
    let ID = 'Like' + data;
    let star = document.getElementById(ID);
    let src = star.getAttribute('src');
    let router = '';
    if (src === '../img/gary-star.svg') {
        //收藏路由
        router = '/addLike'
    } else {
        //取消收藏路由
        router = '/deleteLike'
    }
    instance.get(router, { params: { userId: sessionStorage.getItem('userId'), paperId: data } })
        .then(res => {
            swal("收藏成功！", "点击继续", 'success')
            star.setAttribute('src', (src == '../img/gary-star.svg') ? '../img/orange-star.svg' : '../img/gary-star.svg');
        })

}

function initPagination(currentPage, totalPage) {
    panel = document.getElementById('main-panel');
    let start;
    let end;
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
    let str = '<nav aria-label="Page navigation">' +
        '<ul class="pagination">';

    for (let i = start; i <= end; i++) {
        if (currentPage == i - 1) {
            var li = "<li class=\"active\"><a onclick=getPaperList(" + (i - 1) + ")>" + i + "</a></li>";
        } else {
            var li = "<li><a onclick=getPaperList(" + (i - 1) + ")>" + i + "</a></li>";
        }
        str += li;
    }
    str += '</ul></nav>'
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
        .then(function(response) {

        })
        .then(function(response) {
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