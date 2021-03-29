<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"> 
</head>
<?php
    $f=$_GET['folder'];
    $userid=$_GET['userid'];
    $conn = new mysqli('localhost','root','','paperdb');
    $conn->query("SET NAMES utf8");

    $sql="select * from collection where  userid = '$userid' and folder='$f' ";
    $result = $conn->query($sql);
    $number = mysqli_num_rows($result);
    if ($number){
        echo '  <script>
                    alert("收藏夹已存在！");
                    history.go(-1);
                </script>';
        exit(0);
    }

    $sql = "INSERT INTO collection VALUES ( '$userid', '$f') ";
    $conn->query($sql);
    echo '  <script>
                alert("新建收藏夹成功！");
                window.location.href="../view/collect.php";
            </script>';
?>
