<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"> 
</head>

<?php
    $f=$_GET['folder'];
    $userid=$_GET['userid'];
    $f2=$_GET['f'];
    $conn = new mysqli('localhost','root','','paperdb');
    $conn->query("SET NAMES utf8");

    $sql = "update userPaper set folder='$f' where userid='$userid' and folder='$f2' ";
    $conn->query($sql);
    $sql = "update collection set folder='$f' where userid='$userid' and folder='$f2' ";
    $conn->query($sql);
    echo '  <script>
                alert("修改收藏夹名成功！");
                window.location.href="../view/collect.php";
            </script>';
?>
