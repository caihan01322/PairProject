<?php
    $pid=$_GET['pid'];
    $conn = new mysqli('localhost','root','','paperdb');
    $sql = "delete from userPaper where pid = '$pid' ";
    $conn->query($sql);
    echo '<script>alert("删除成功！");window.location.href="../view/manage.php";</script>';
?>