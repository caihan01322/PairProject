<?php
$pid=$_GET['pid'];
$r=$_GET['r'];
$user=$_GET['userid'];
$conn = new mysqli('localhost','root','','paperdb');
$sql = "INSERT INTO userPaper VALUES ('$pid', '$user', '$r','0') ";
$conn->query($sql);
echo '<script>alert("添加成功！");window.location.href="../view/manage.php";</script>';
?>