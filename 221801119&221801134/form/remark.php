<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"> 
</head>

<?php
    $pid=$_GET['pid'];
    $userid=$_GET['userid'];
    $remark=$_GET['remark'];
    $view=$_GET['view'];
    $conn = new mysqli('localhost','root','','paperdb');
    $conn->query("SET NAMES utf8");
    $sql = "update userPaper set remarks='$remark'  where pid = '$pid' and userid='$userid' ";
    $conn->query($sql);
    
    if($view==1) {
        echo '  <script>
                    alert("修改成功！");
                    window.location.href="../view/manage.php";
                </script>';
    }
    else {
        echo '  <script>
                    alert("修改成功！");
                    window.location.href="../view/collect.php";
                </script>';
    }

?>
