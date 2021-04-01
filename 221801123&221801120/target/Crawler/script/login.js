// 验证码
function image(image) {
    image.src = "verify_code.jsp?" + Math.random();
}

// 登录验证
function check(form) {
    var error = document.getElementById("login_error");
    return _validate(form.username, error, mb_convert_encoding( "用户名不能为空", 'UTF-8' )) && _validate(form.password, error, mb_convert_encoding( "密码不能为空", 'UTF-8' ))
        && _validate(form.verify, error, mb_convert_encoding( "验证码不能为空", 'UTF-8' )) && _checkVerify(form.verify, error, mb_convert_encoding( "error", 'UTF-8' ));
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