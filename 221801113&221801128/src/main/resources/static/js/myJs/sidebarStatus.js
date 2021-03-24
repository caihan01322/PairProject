$(function(){

    //记录sidebar点击状态
    $(".parent-content").click(function (){
        localStorage.setItem("sidebarParentActive",$(this).find("span").eq(0).html().trim());
        pageJump();
    })
    $(".child-content").click(function (){
        localStorage.setItem("sidebarChildActive",$(this).find("a").eq(0).html().trim());
    })

    //清除侧边栏active
    function removeSidebarActive() {
        for (let i = 0; i < $(".parent-content").length; i++) {
            $(".parent-content").eq(i).removeClass("nav-active");
        }
        for (let i = 0; i < $(".child-content").length; i++) {
            $(".child-content").eq(i).removeClass("active");
        }
    }

    //页面跳转
    function pageJump() {
        let url = window.location.pathname;
        url = (url.indexOf(".") != -1) ? url.substring(0, url.indexOf('.')) : url;
        for (let i = 0; i < $(".child-content").find("a").length; i++) {
            if ($(".child-content").eq(i).find("a").html().trim()
                == localStorage.getItem("sidebarChildActive").trim()) {
                if ($(".child-content").eq(i).find("a").attr("href") != url) {
                    $(".child-content").eq(i).find("a").get(0).click();
                }
            }
        }
    }


    //添加侧边栏active
    function addSidebarActive() {
        if (localStorage.getItem("sidebarParentActive") != null
            && localStorage.getItem("sidebarParentActive") != "") {

            for (let i = 0; i < $(".parent-content").length; i++) {
                if ($(".parent-content").eq(i).find("span").html().trim()
                    == localStorage.getItem("sidebarParentActive").trim()) {
                    $(".parent-content").eq(i).addClass("nav-active");
                }
            }
            for (let i = 0; i < $(".child-content").length; i++) {
                if ($(".child-content").eq(i).find("a").html().trim()
                    == localStorage.getItem("sidebarChildActive").trim()) {
                    $(".child-content").eq(i).addClass("active");
                    console.log(window.location.href);
                }
            }
        } else {
            localStorage.setItem("sidebarParentActive", "论文列表");
            localStorage.setItem("sidebarChildActive", "论文查询");
            $(".parent-content").eq(0).addClass("nav-active");
            $(".child-content").eq(0).addClass("active");
        }
    }
    removeSidebarActive();
    addSidebarActive();
    pageJump();
})