var globalBaseURL = 'http://120.77.84.235:8080';
var instance = axios.create({
    baseURL: 'http://120.77.84.235:8080',
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    },
});

function login() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;
    console.log(username);
    console.log(password);
    swal("登录成功！", "即将为您跳转至主页……", "success");
    // window.setTimeout(1000);
    // window.location.href = 'index.html';
    // instance.post('/login', {
    //         username: username,
    //         password: password
    //     })
    //     .then(res => {
    //         // if (res.data.message === 'success') {
    //         // window.sessionStorage.setItem('username', username);
    //         // window.sessionStorage.setItem('isLogin', true)
    //         swal("登录成功！", "即将为您跳转至主页……", "success");
    //         window.setTimeout(1000);
    //         window.location.href = 'index.html';
    //         // } else {

    //         // }
    //     })
}