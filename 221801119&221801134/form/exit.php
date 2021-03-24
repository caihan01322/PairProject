<?php
if(isset($_SESSION['userid'])){
    unset($_SESSION['userid']);
    
}
echo '<script>window.location.href="../view/index.php";</script>';
?>