var instance = axios.create({
    baseURL: 'http://121.5.100.116:8080/api',
    headers: {'X-Requested-With': 'XMLHttpRequest'},
})

function getPaperList(){
    var searchStmt = document.getElementById("search-text").value;
    var pageNum = 0;
    instance.get('/search',{
        params: {
            keyword: searchStmt,
            pageNum: pageNum
        }
    })
    .then(function(response) {
        panel = document.getElementById('main-panel');
        panel.innerHTML="";
        //console.log(response.data);
        var data = response.data['data'];
        if(data.totalNum === 0) {
            panel.innerHTML = panel.innerHTML + "<p style=\"text-align:center;color: rgb(127, 127, 127);\">No result</p>";
        } else {
            var list = data.list;
            console.log(list);
            for(var k in list) {
                var element = list[k];
                var abstractStr = element['artical']['Abstract'].slice(0,100)+"...";
                var authorStr = "";
                var keywordStr = "";
                for(var t in element.authors) {
                    authorStr += element.authors[t]+';';
                    if(t>=3) {
                        break;
                    } 
                }
                authorStr = authorStr.slice(0,-1);
                for(var t in element.keywords) {
                    keywordStr += element.keywords[t]+';';
                    if(t>=3) {
                        break;
                    }
                }
                keywordStr = keywordStr.slice(0,-1);
                panel.innerHTML=panel.innerHTML+
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
                "<span class=\"paper-keyword-list\">"+
                keywordStr +
                "</span></p></div>"
            }
        }
    })
    .then(function(error) {
        console.log(error);
    })
        

}