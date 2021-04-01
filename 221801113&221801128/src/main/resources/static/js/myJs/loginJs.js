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

    let active = false;

    /**
     * 登录验证
     */
    $("#login").click(() => {
        let username = $("#login-username").val().trim(), password = $("#login-password").val().trim();
        console.log(username)
        if (username == '' || password == '') {
            toastr.warning('用户名或密码不能为空');
        } else {
            $('#login-form').submit();
        }
    })

    /**
     * 注册验证
     */
    $("#register").click(() => {
        let username = $("#register-username").val().trim(), password = $("#register-password").val().trim();
        let regLogin = /^[a-zA-Z0-9]{6,16}$/,
            regRegister = /^(?=.*[a-zA-Z])(?=.*[0-9])[A-Za-z0-9]{6,16}$/;
        if (username == '' || password == '') {
            toastr.warning('用户名或密码不能为空');
        } else if (!regLogin.test(username)) {
            toastr.warning('用户名必须由6-16位字母或数字组成！(例adminxx,admin123)');
        } else if (!regRegister.test(password)) {
            toastr.warning('密码必须由6-16位字母和数字组成！(例test123)');
        } else {
            $('#register-form').submit();
        }
    })


    /**
     * toggle
     */
    $(".sbutton").click(() => {
        $('input').val("");
        active = !active;
        if (active) {
            $(".big-box").addClass("active");
            $(".small-box").addClass("active");
            $(".login").attr("style","display: none");
            $(".register").attr("style","");
        } else {
            $(".big-box").removeClass("active");
            $(".small-box").removeClass("active");
            $(".login").attr("style","");
            $(".register").attr("style","display: none");
        }
    })
})