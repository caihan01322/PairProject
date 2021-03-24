$(function(){

    init();

    //初始化分页和第一页信息
    function init() {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/total_num",
            success: function(res) {
                console.log(res)
                $(".pagination").pagination({
                    callback: pageCallBack, //翻页回调函数。
                    totalData: res,
                    showData: 4,
                    jump: true,
                    coping: true,
                    homePage: '首页',
                    endPage: '末页',
                    prevContent: '上页',
                    nextContent: '下页',
                })
            },
            //请求失败，包含具体的错误信息
            error: function(res){
                console.log(res.status);
                console.log(res.responseText);
            }
        });
        getPaperByPageNum(0);
    }




    function pageCallBack(api) {
        getPaperByPageNum(api.getCurrent()-1);
    }

    function getPaperByPageNum(pageNum) {
        console.log("pageNum="+pageNum);
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/paper_by_pagenum",
            data: {pageNum: pageNum},
            success: function(res) {
                renderData(res);
            },
            //请求失败，包含具体的错误信息
            error: function(res){
                console.log(res.status);
                console.log(res.responseText);
            }
        });
    }

    function renderData(res) {

        for (let i = 0; i < 4; i++) {
            $(".paper-id").get(i).innerHTML = res[i].id;
            $(".paper-title").get(i).innerHTML = (res[i].publicationTitle == null) ? "暂无数据"
                : res[i].publicationTitle.split('"').join("");
            $(".paper-author").get(i).innerHTML = (res[i].authors == null) ? "暂无数据"
                : res[i].authors.split('"').join("");
            $(".paper-keywords").eq(i).find("span").get(1).innerHTML = (res[i].keywords == null) ? "暂无数据"
                : res[i].keywords.split('"').join("");
            $(".paper-abstract").eq(i).find("span").get(1).innerHTML = (res[i].abstrac == null) ? "暂无数据"
                : res[i].abstrac.split('"').join("");
        }
    }

})