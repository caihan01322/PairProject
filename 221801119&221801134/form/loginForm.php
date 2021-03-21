<?php
header("Content-type: text/html; charset=utf-8");
$username = preg_replace("/\s|　/", "", $_POST['name']);
$password =$_POST['password'];
$conn = new mysqli('localhost','root','','paperdb');
if ($conn->connect_error){
    echo '数据库连接失败！';
    exit(0);
}else{
    if ($username == ''){
        echo '<script>alert("请输入用户名！");history.go(-1);</script>';
        exit(0);
    }
    if ($password == ''){
        echo '<script>alert("请输入密码！");history.go(-1);</script>';
        exit(0);
    }
    $sql = "select * from user where username = '$username' and password = '$password'";
    $result = $conn->query($sql);
    $number = mysqli_num_rows($result);
    if ($number) {
        echo '<script>alert("登录成功！");window.location="../view/manage.php";</script>';
    } else {
        echo '<script>alert("用户名或密码错误！");history.go(-1);</script>';
    }
}
?>