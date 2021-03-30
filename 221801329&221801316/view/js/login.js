let globalBaseURL = 'http://120.77.84.235:8080';
let instance = axios.create({
    baseURL: 'http://120.77.84.235:8080',
    headers: {
        'X-Requested-With': 'XMLHttpRequest'
    },
});

function login() {
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;
    // console.log(username);
    // console.log(password);
    // swal("登录成功！", "即将为您跳转至主页……", "success");
    // window.setTimeout(1000);
    // window.location.href = 'index.html';
    instance.post('/login', {
            username: username,
            password: password
        })
        .then(res => {
            if (res.data.userId !== -1) {
                window.sessionStorage.setItem('username', username);
                window.sessionStorage.setItem('isLogin', true);
                window.sessionStorage.setItem('userId', res.data.userId);
                swal("登录成功！", "即将为您跳转至主页……", "success");
                window.setTimeout(3000);
                window.location.href = 'index.html';
            } else {
                swal("用户名或密码错误！", "请重新登录", "error")
            }
        })
}