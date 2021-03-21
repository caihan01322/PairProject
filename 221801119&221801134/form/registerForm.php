<?php
header("Content-type: text/html; charset=utf-8");
$username = preg_replace("/\s|　/", "", $_POST['name']);
$password1 =$_POST['password1'] ;
$password2 =$_POST['password2'];
$conn = new mysqli('localhost','root','','paperdb');
if ($conn->connect_error){
    echo '数据库连接失败！';
    exit(0);
}else{
    if ($username == ''){
        echo '<script>alert("请输入用户名！");history.go(-1);</script>';
        exit(0);
    }
    if ($password1 == ''|| $password2 == ''){
        echo '<script>alert("请输入密码！");history.go(-1);</script>';
        exit(0);
    }
    if ($password1 != $password2){
        echo '<script>alert("两次密码必须一致！");history.go(-1);</script>';
        exit(0);
    }
    $sql="select * from user where  username = '$username'";
    $result = $conn->query($sql);
    $number = mysqli_num_rows($result);
    if ($number){
        echo '<script>alert("用户名已存在！");history.go(-1);</script>';
        exit(0);
    }
    
    $sql="insert into user(username,password) values('$username','$password1')";
    if( $conn->query($sql) === TRUE )  {
        echo '<script>alert("注册成功！");window.location="../view/login.php";</script>';
    }
       
}
?>