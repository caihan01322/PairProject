<?php
$pid=$_GET['pid'];
$userid=$_GET['userid'];
$c=$_GET['c'];
$view=$_GET['view'];
$conn = new mysqli('localhost','root','','paperdb');
$conn->query("SET NAMES utf8");
$sql = "update userPaper set collect='$c'  where pid = '$pid' and userid='$userid' ";
$conn->query($sql);
if($c==0)
    echo '<script>alert("取消收藏成功！");</script>';
else
    echo '<script>alert("收藏成功！");</script>';
if($view==1)
    echo '<script>window.location.href="../view/manage.php";</script>';
else 
    echo '<script>window.location.href="../view/collect.php";</script>';
?>