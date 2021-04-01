$(function(){
    lockSearchBtn();
    removeSidebarActive();
    href();
    /**
     * 不在搜索页不让搜索
     */
    function lockSearchBtn() {
        if (location.pathname != "/main.html" && location.pathname != "/main"
            && location.pathname != "/paper_collect") {
            let isLock = $(".searchform button").attr("class");
            isLock = (isLock.indexOf("disabled") == -1) ? false : true;
            if (!isLock) {
                $(".searchform button").addClass("disabled");
            }
        } else {
            let isLock = $(".searchform button").attr("class");
            isLock = (isLock.indexOf("disabled") == -1) ? false : true;
            if (isLock) {
                $(".searchform button").removeClass("disabled");
            }
        }
        if (location.pathname == "/paper_collect") {
            let isLock = $(".searchform button").eq(1).attr("class");
            isLock = (isLock.indexOf("disabled") == -1) ? false : true;
            if (!isLock) {
                $(".searchform button").eq(1).addClass("disabled");
            }
        }
    }

    /**
     * 判断url
     */
    function href() {
        let pathname = location.pathname;
        if (pathname == '/main.html' || pathname == '/main' || pathname == '/paper_collect') {
            $(".parent-content").eq(0).addClass("nav-active");
        } else if (pathname == '/hot_areas' || pathname == '/trend_compare') {
            $(".parent-content").eq(1).addClass("nav-active");
        }
        if (pathname == '/main.html' || pathname == '/main') {
            $(".child-content").eq(0).addClass("active");
        } else if (pathname == '/paper_collect') {
            $(".child-content").eq(1).addClass("active");
        } else if (pathname == '/hot_areas') {
            $(".child-content").eq(2).addClass("active");
        } else if (pathname == '/trend_compare') {
            $(".child-content").eq(3).addClass("active");
        }
    }

    /**
     * 移除侧边栏激活状态
     */
    function removeSidebarActive() {
        for (let i = 0; i < $(".parent-content").length; i++) {
            $(".parent-content").eq(i).removeClass("nav-active");
        }
        for (let i = 0; i < $(".child-content").length; i++) {
            $(".child-content").eq(i).removeClass("active");
        }
    }

})