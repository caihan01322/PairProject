<?php
$pid=$_POST['pid'];
$userid=$_POST['userid'];
$remark=$_POST['remark'];
$conn = new mysqli('localhost','root','','paperdb');
$sql = "update userPaper set remarks='$remark'  where pid = '$pid' and userid='$userid' ";
$conn->query($sql);
echo '<script>alert("修改成功！");window.location.href="../view/manage.php";</script>';
?>