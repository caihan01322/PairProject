// 验证码
function image(image) {
    image.src = "verify_code.jsp?" + Math.random();
}

// 登录验证
function check(form) {
    if(form == null || form == undefined) {
        throw new Error("The form element shouldn't be null or undefined");
    }
    var error = document.getElementById("login_error");
    return _validate(form.username, error, "用户名不能为空") && _validate(form.password, error, "密码不能为空")
        && _validate(form.verify, error, "验证码不能为空") && _checkVerify(form.verify, error, "error");
}

function _validate(element, error, message) {
    var ele_value = element.value.trim();
    if(ele_value == "") {
        error.innerHTML = message;
        element.focus();
        return false;
    }
    return true;
}