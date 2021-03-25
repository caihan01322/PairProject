<?php
session_start();
if(isset($_SESSION['userid'])){
    unset($_SESSION['userid']);
    
}
echo '<script>alert("退出登录成功！");window.location.href="../index.php";</script>';
?>