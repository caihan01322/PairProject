<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"> 
</head>

<?php
    $s=$_GET['str'];
    $userid=$_GET['userid'];
    $pid=$_GET['pid'];
    $view=$_GET['view'];
    $conn = new mysqli('localhost','root','','paperdb');
    $conn->query("SET NAMES utf8");
    
    $d=date("Y.m.d");
    $sql = "INSERT INTO note(pid,userid,content,time) VALUES ( '$pid','$userid', '$s','$d') ";
    $conn->query($sql);
?>

<script type="text/javascript">
	var pid='<?php echo $pid;?>';
	var view='<?php echo $view;?>';
	alert("添加笔记成功！");
	window.location.href="../view/check.php?pid="+pid+"&view="+view;
</script>
