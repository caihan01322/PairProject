$(function(){

    /**
     * 搜索事件
     */
    $(".searchform button").click(() => {
        console.log(2)
        let searchInfo = $(".searchform input").val().trim();
        localStorage.setItem("searchInfo", searchInfo);
        getPaperData(searchInfo);
    })

    /**
     * 页面初始化
     */
    $(".searchform button").click();



    function getPaperData(searchInfo, pageNum = 0) {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/get_search_paper",
            data: {searchInfo: searchInfo, pageNum: pageNum},
            success: function(res) {
                console.log(res)
                initPagination(res);
                renderCard(res.list);
            },
            //请求失败，包含具体的错误信息
            error: function(res){
                console.log(res.status);
                console.log(res.responseText);
            }
        });
    }

    /**
     * 初始化分页器
     * @param res
     */
    function initPagination(res) {
        $(".pagination").pagination({
            callback: pageCallBack, //翻页回调函数。
            totalData: res.count,
            showData: 4,
            jump: true,
            coping: true,
            homePage: '首页',
            endPage: '末页',
            prevContent: '上页',
            nextContent: '下页',
        })
    }


    /**
     * 翻页回调函数
     * @param api
     */
    function pageCallBack(api) {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/get_search_paper",
            data: {searchInfo: localStorage.getItem("searchInfo")
                , pageNum: api.getCurrent()-1},
            success: function(res) {
                renderCard(res.list);
            },
            //请求失败，包含具体的错误信息
            error: function(res){
                console.log(res.status);
                console.log(res.responseText);
            }
        });
    }

    /**
     * 将数据库数据渲染至卡片上
     * @param res paper的list数据
     */
    function renderCard(res) {
        console.log(res);
        initCard();
        for (let i = 0; i < res.length; i++) {
            $(".paper-id").get(i).innerHTML = res[i].id;
            $(".paper-title").get(i).innerHTML = (res[i].publicationTitle == null) ? "暂无数据"
                : res[i].publicationTitle.split('"').join("");
            $(".paper-link a").get(i).innerHTML = (res[i].authors == null) ? "暂无数据"
                : res[i].authors.split('"').join("");
            $(".paper-keywords").eq(i).find("span").get(1).innerHTML = (res[i].keywords == null) ? "暂无数据"
                : res[i].keywords.split('"').join("");
            $(".paper-abstract").eq(i).find("span").get(1).innerHTML = (res[i].abstrac == null) ? "暂无数据"
                : res[i].abstrac.split('"').join("");
        }
    }

    /**
     * 初始化卡片内容
     */
    function initCard() {
        for (let i = 0; i < 4; i++) {
            $(".paper-id").get(i).innerHTML = 0;
            $(".paper-title").get(i).innerHTML = "好像找不到标题...";
            $(".paper-link a").get(i).innerHTML = "好像找不到链接...";
            $(".paper-keywords").eq(i).find("span").get(1).innerHTML = "好像找不到关键词...";
            $(".paper-abstract").eq(i).find("span").get(1).innerHTML = "好像找不到摘要...";
        }
    }
})