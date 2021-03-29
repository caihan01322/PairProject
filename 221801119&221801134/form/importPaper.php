<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"> 
</head>
<?php
    $str=nl2br($_GET["str"]);
    $userid=$_GET["userid"];
    $conn = new mysqli('localhost','root','','paperdb');
    $conn->query("SET NAMES utf8");

    $t= array_values(explode("\n",$str));
    $num=0;
    $z=0;
    for($i=0;$i<sizeof($t);$i++){
        $c=$t[$i];
        $sql="select * from paper where title like '$c'  " ;
        $res = $conn->query($sql);
        if( $res->num_rows >0 ){
            $row= $res->fetch_assoc();
            $pid=$row['pid'];
            $sql2 = "select * from userPaper where pid = '$pid' and userid='$userid' ";
            $res2 = $conn->query($sql2);
            $number = mysqli_num_rows($res2);
            if (!$number) {
                $sql3 ="INSERT INTO userPaper VALUES ('$pid', '$userid', '','0','') ";
                $conn->query($sql3);
                $num++;
            }
            else{
                $z=1;
            }
        }
    }

    if($num>0) {
        echo '  <script>
                    alert("已成功添加论文！");
                    window.location.href="../view/manage.php";
                </script>';
    }
    else {
        if($z==0){
            echo '  <script>
                        alert("无法搜索到相关论文！");
                        window.location.href="../view/import.php";
                    </script>';
        }
        else {
            echo '  <script>
                        alert("相关论文已存在！");
                        window.location.href="../view/manage.php";
                    </script>';
        }
    }
?>
