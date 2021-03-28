<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"> 
</head>
<?php
session_start();
$str=$_POST["condition"];
$userid=$_SESSION["userid"];
$conn = new mysqli('localhost','root','','paperdb');
$conn->query("SET NAMES utf8");

$sql="select * from paper where title like '$str'  " ;
$res = $conn->query($sql);
if( $res->num_rows >0 ){
    $row= $res->fetch_assoc();
    $pid=$row['pid'];
    $sql2 = "select * from userPaper where pid = '$pid' and userid='$userid' ";
    $res2 = $conn->query($sql2);
    $number = mysqli_num_rows($res2);
    if (!$number) {
        $sql3 ="INSERT INTO userPaper VALUES ('$pid', '$userid', '','0','') ";
        $conn->query($sql3);
        echo '<script>alert("已成功添加论文！");window.location.href="../view/manage.php";</script>';
    }
    else{
        echo '<script>alert("相关论文已存在！");window.location.href="../view/manage.php";</script>';
    }
}
else{
    echo '<script>alert("无法搜索到相关论文！");window.location.href="../view/import.php";</script>';
}


?>