<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"> 
</head>
<?php
$f=$_GET['folder'];
$userid=$_GET['userid'];
$pid=$_GET['pid'];
$conn = new mysqli('localhost','root','','paperdb');
$conn->query("SET NAMES utf8");
$sql = "update userPaper set folder='$f' where pid = '$pid' and userid='$userid' ";
$conn->query($sql);
echo '<script>alert("移动成功！");window.location.href="../view/collect.php";</script>';
?>
