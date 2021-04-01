$(function(){

    /**
     * 提示框参数配置
     * @type {{hideEasing: string, positionClass: string, hideDuration: string, debug: boolean, showMethod: string, closeButton: boolean, extendedTimeOut: string, showEasing: string, progressBar: boolean, onclick: null, showDuration: string, hideMethod: string}}
     */
    toastr.options = {
        closeButton: false,
        debug: false,
        progressBar: false,
        positionClass: "toast-top-center",
        onclick: null,
        showDuration: "300",
        hideDuration: "1000",
        // timeOut: "1500",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    };



    getHotSearch();

    /**
     * 获取热词搜索
     */
    function getHotSearch() {
        if (localStorage.getItem("hot") != null) {
            $('.searchform .form-control').eq(0).val(localStorage.getItem("hot"))
            $(".searchform button").click();
            localStorage.removeItem("hot");
        }
    }


    /**
     * 阻止点击论文链接产生的事件冒泡
     */
    $(".paper-link a").click((e) => {
        if ( e && e.stopPropagation )
            e.stopPropagation();
        else
            window.event.cancelBubble = true; //满足ie
    })

    /**
     *  判断当前页面是收藏夹还是论文查询
     */
    function isCollectPage() {
        return location.pathname == "/paper_collect"
    }

    /**
     * 搜索事件
     */
    $(".searchform button").eq(0).click(() => {
        let searchInfo = $(".searchform input").val().trim();
        localStorage.setItem("searchInfo", searchInfo);
        if (!isCollectPage()) {
            getPaperData(searchInfo);
        } else {
            let userName = $(".menu-right a").eq(0).text().replace('"', "").trim();
            getCollectPaperData(searchInfo, userName);
        }
    })

    let crawlPaperList = [];

    /**
     *  爬取事件
     */
    $(".searchform button").eq(1).click(() => {
        let searchInfo = $(".searchform input").val().trim();
        getCrawlPaper(searchInfo);
    })

    function getCrawlPaper(searchInfo) {
        $(".searchform button").eq(1).text("爬取中...")
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/get_crawl_paper",
            data: {searchInfo: searchInfo},
            success: function(res) {
                toastr.success(res.info);
                $(".searchform button").eq(1).html("<i class=\"fa fa-cloud\"></i>");
                crawlPaperList = res;
                crawlPaperPagination(res);
                renderCard(res.list.slice(0, 4));
            },
            //请求失败，包含具体的错误信息
            error: function(res){
                toastr.error('好像哪里出了点问题！');
                $(".searchform button").eq(1).html("<i class=\"fa fa-cloud\"></i>");
                console.log(res.status);
                console.log(res.responseText);
            }
        });
    }

    /**
     * 爬取分页器
     */
    function crawlPaperPagination(res) {
        $(".pagination").pagination({
            callback: pageCallBack3, //翻页回调函数。
            totalData: (res.count == 0) ? 1 : res.count,
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
     * 爬取分页回调
     * @param api
     */
    function pageCallBack3(api) {
        let start = (api.getCurrent()-1)*4;
        renderCard(crawlPaperList.list.slice(start, start + 4));
    }


    /**
     * 页面初始化
     */
    $(".searchform button").eq(0).click();

    /**
     * 获取收藏夹的数据
     * @param searchInfo
     * @param pageNum
     * @param userName
     */
    function getCollectPaperData(searchInfo, userName, pageNum = 0) {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/get_collect_paper",
            data: {searchInfo: searchInfo, userName: userName, pageNum: pageNum},
            success: function(res) {
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
     * 获取论文列表的数据
     * @param searchInfo
     * @param pageNum
     */
    function getPaperData(searchInfo, pageNum = 0) {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/get_search_paper",
            data: {searchInfo: searchInfo, pageNum: pageNum},
            success: function(res) {
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
        let pageCallBack = (isCollectPage() == true) ? pageCallBack2 : pageCallBack1;
        $(".pagination").pagination({
            callback: pageCallBack, //翻页回调函数。
            totalData: (res.count == 0) ? 1 : res.count,
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
     * 所有论文翻页回调函数
     * @param api
     */
    function pageCallBack1(api) {
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
     * 收藏夹翻页回调函数
     * @param api
     */
    function pageCallBack2(api) {
        $.ajax({
            type: "GET",
            contentType: "application/json;charset=UTF-8",
            url: "/get_collect_paper",
            data: {searchInfo: localStorage.getItem("searchInfo")
                , userName : $(".menu-right a").eq(0).text().replace('"', "").trim()
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
            $(".paper-link a").get(i).innerHTML = (res[i].persistentLink == null) ? "暂无数据"
                : res[i].persistentLink.split('"').join("");
            $(".paper-link a").eq(i).attr("href", (res[i].persistentLink == null) ? "#"
                : res[i].persistentLink.split('"').join(""));
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
            $(".paper-id").get(i).innerHTML = "-1";
            $(".paper-title").get(i).innerHTML = "好像找不到标题...";
            $(".paper-link a").get(i).innerHTML = "好像找不到链接...";
            $(".paper-keywords").eq(i).find("span").get(1).innerHTML = "好像找不到关键词...";
            $(".paper-abstract").eq(i).find("span").get(1).innerHTML = "好像找不到摘要...";
        }
    }

    /**
     * 点击卡片将数据填充至模态框中
     */
    $(".card-content").click(function () {
            $("#edit-id").val($(this).find(".paper-id").html());
            $("#edit-title").val($(this).find(".paper-title").html());
            $("#edit-link").val($(this).find(".paper-link a").html());
            $("#edit-keywords").val($(this).find(".paper-keywords span").eq(1).html());
            $("#edit-abstract").val($(this).find(".paper-abstract span").eq(1).html());
    })

    /**
     * 更新收藏夹
     * @param url
     */
    function updateCollection(url) {
        if ($("#edit-id").val() == "-1") {
            toastr.warning('该卡片没有东西哦！');
        } else {
            $.ajax({
                type: "GET",
                contentType: "application/json;charset=UTF-8",
                url: url,
                data: {
                    userName: $(".menu-right a").eq(0).text().replace('"', "").trim(),
                    paperId: $("#edit-id").val().trim(),
                    keywords: $("#edit-keywords").val().trim(),
                    abstrac: $("#edit-abstract").val().trim(),
                    publicationTitle: $("#edit-title").val().trim(),
                    persistentLink: $("#edit-link").val().trim(),
                },
                success: function(res) {
                    if (res == '收藏失败！(可能原因：该论文已被收藏)' || res == '修改失败！' || res == '删除失败！') {
                        console.log(res)
                         toastr.error(res);
                    } else {
                         toastr.success(res);
                        console.log(res)
                    }
                },
                //请求失败，包含具体的错误信息
                error: function(res){
                    console.log(res.status);
                    console.log(res.responseText);
                }
            });
        }

    }

    /**
     * 收藏
     */
    $("#btn-collect").click(() => {
        updateCollection("/collect");
    })

    /**
     * 保存（修改）
     */
    $("#btn-update").click(() => {
        updateCollection("/update");
        getCollectPaperData("", $(".menu-right a").eq(0).text().replace('"', "").trim(), 0);
        setTimeout(() => {
            location.reload();
        }, 1500)
    })

    /**
     * 删除
     */
    $("#btn-delete").click(() => {
        updateCollection("/delete");
        getCollectPaperData("", $(".menu-right a").eq(0).text().replace('"', "").trim(),0);
        setTimeout(() => {
            location.reload();
        }, 1500)

    })

    /**
     * 监听登出事件
     */
    $('.leave').click(() => {
        localStorage.removeItem('avatarSrc');
    })

    /**
     * 随机头像
     */
    function randAvatar() {
        let avatarSrc = null;
        if (localStorage.getItem('avatarSrc') == null) {
            avatarSrc = 'images/photos/' + Math.floor(Math.random()*10+1) + '.png'
            localStorage.setItem('avatarSrc', avatarSrc);
        } else {
            avatarSrc = localStorage.getItem('avatarSrc');
        }
        $('.menu-right img').eq(0).attr('src', avatarSrc);
    }
    randAvatar();
})