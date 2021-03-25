<?php session_start();?>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,minimal-ui">
    <title>论文管理</title>
    <link rel='stylesheet' href='../css/myCss.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss3.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss4.css' type='text/css'/>
	<script type="text/javascript" async="" src="//cdn.jsdelivr.net/npm/mathjax@2.7.5/MathJax.js?config=TeX-AMS-MML_HTMLorMML.js"></script>
</head>

<body style="background-color:#F8F8F8 ;">
    <div id="app" class="surface--content">

    <header class="metabar">
        <div class="container u-flex">
            <!--
            <a href="/" class="u-flex u-relative"><img class="logo" src=".jpg" alt="图标"></a>
            -->
            <nav class="header-nav">
                <ul class="subnav-ul">
                    <li class="menu-item"><a  href="../index.php">首页</a></li>
                    <li class="menu-item"><a   data-id="37" data-adid="41" href="import.php">论文导入</a></li>
                    <li class="menu-item"><a   data-id="37" data-adid="41" href="manage.php">论文管理</a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="analysis.php">动态分析</a></li>
                </ul>
            </nav>
            <div class="metabar__right u-flex">
                <div class="metabarItem u-flex"><a href="" class="u-flex" title="上传" >
                    <svg t="1616220697148" class="icon" width="25" height="24"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2435" width="200" height="200"><path d="M522.24 760.32c-15.36 0-25.6-10.24-25.6-25.6V448c0-15.36 10.24-25.6 25.6-25.6s25.6 10.24 25.6 25.6v289.28c0 12.8-12.8 23.04-25.6 23.04z" fill="#333333" p-id="2436"></path><path d="M601.6 545.28c-7.68 0-12.8-2.56-17.92-7.68l-61.44-61.44L460.8 537.6c-10.24 10.24-25.6 10.24-35.84 0s-10.24-25.6 0-35.84l79.36-79.36c10.24-10.24 25.6-10.24 35.84 0l79.36 79.36c10.24 10.24 10.24 25.6 0 35.84-5.12 5.12-10.24 7.68-17.92 7.68z" fill="#333333" p-id="2437"></path><path d="M755.2 780.8h-151.04c-15.36 0-25.6-10.24-25.6-25.6s10.24-25.6 25.6-25.6h151.04c79.36 0 143.36-64 143.36-143.36s-64-143.36-143.36-143.36c-23.04 0-48.64 5.12-69.12 17.92-7.68 5.12-17.92 5.12-25.6 0-7.68-5.12-12.8-12.8-12.8-23.04 0-71.68-56.32-128-128-128-58.88 0-110.08 40.96-125.44 97.28-2.56 7.68-5.12 12.8-12.8 15.36-5.12 2.56-12.8 5.12-20.48 2.56-10.24-2.56-20.48-5.12-28.16-5.12-61.44 0-110.08 48.64-110.08 110.08 0 10.24-7.68 20.48-17.92 23.04-35.84 12.8-58.88 46.08-58.88 81.92 0 48.64 38.4 87.04 87.04 87.04h168.96c15.36 0 25.6 10.24 25.6 25.6s-10.24 25.6-25.6 25.6H243.2h-2.56c-79.36 5.12-145.92-58.88-145.92-138.24 0-53.76 30.72-102.4 76.8-125.44 7.68-81.92 76.8-145.92 161.28-145.92h17.92c25.6-69.12 92.16-115.2 166.4-115.2 87.04 0 158.72 61.44 176.64 143.36 17.92-5.12 38.4-10.24 58.88-10.24 107.52 0 194.56 87.04 194.56 194.56 0 117.76-87.04 204.8-192 204.8z" fill="#333333" p-id="2438"></path></svg>
                    上传论文</a>
                </div>
                <div class="metabarItem u-flex u-cursorPointer" v-on:click="switchSearch"><a href="collect.php" class="u-flex" title="收藏" >
                    <svg t="1616220263438" class="svgIcon-use" width="18" height="17" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1653" width="200" height="200"><path d="M890.233 419.484c-1.895 0-3.81 0-5.71 0l-28.555 0L855.968 310.982c0-43.782-34.26-43.782-45.68-43.782L524.759 267.2l-36.167-78.042c-5.712-11.42-15.227-17.132-26.65-17.132L153.57 172.026c-34.262 0-59.007 47.587-59.007 72.332l0 586.286c0 28.555 28.55 64.715 59.007 64.715l727.143 0c30.46 0 53.305-34.26 53.305-64.715L934.018 459.456C932.108 440.424 920.688 419.484 890.233 419.484zM798.868 324.304l0 95.18L593.283 419.484l-43.785-95.18L798.868 324.304zM875.008 830.643c0 1.9 0 5.71-1.91 7.615L157.38 838.258c-1.907-1.905-5.712-5.715-5.712-7.615L151.668 244.357c0-3.805 3.805-11.42 7.612-15.225l283.627 0 104.697 230.324c3.81 9.517 15.23 17.135 26.65 17.135l300.754 0L875.008 830.643z" p-id="1654"></path></svg>
                    收藏夹</a>
                </div>
                <metabar-notice v-bind:noticenum="noticeNum" v-bind:islogin="islogin"></metabar-notice>
                
                
                <div class="metabarItem u-flex u-paddingLeft20 dropdown" >
                    <a href="" class="u-flex" title="用户" >
                    <svg t="1616238407449" class="icon" width="40" height="39" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2253" width="200" height="200"><path d="M512 0a512 512 0 1 0 512 512A512 512 0 0 0 512 0zM213.333 832A298.667 298.667 0 0 1 512 533.333a170.667 170.667 0 1 1 170.667-170.666A170.667 170.667 0 0 1 512 533.333 298.667 298.667 0 0 1 810.667 832z" p-id="2254" fill="#e6e6e6"></path></svg>
                    </a>
                    <?php 
                    $conn = new mysqli('localhost','root','','paperdb');
                    if(!isset($_SESSION["userid"])){
                        echo '<script>alert("请先登录！");window.location.href="login.php";</script>';
                    }
                    $userid=$_SESSION["userid"];
                    $sql = "select * from user where userid = '$userid' ";
                    $result = $conn->query($sql);
                    $number = mysqli_num_rows($result);
                    $row=$result ->fetch_assoc();
                    echo $row["username"];
                    ?>
                    <div class="dropdown-content">
    				<a href="">个人中心</a><br/>
    				<a onclick="exitLogin()">退出登录</a>
  					</div>
                </div>
            </div>
        </div>
    </header>
 </div>

<div class="mainPaper2">
		<div class="paper" style="marin:0 200px;padding-top:0;padding-bottom:0">
		<ol class="breadcrumb hidden-xs-down" style="margin:0">
            
    	<?php 
    	$conn = new mysqli('localhost','root','','paperdb');
    	$conn->query("SET NAMES utf8");
    	if(!isset($_SESSION["userid"])){
    	    echo '<script>alert("请先登录！");window.location.href="../view/login.php";</script>';
    	}
    	$userid=$_SESSION["userid"];
    	if(!isset($_GET["pid"]))
    	{
    	    echo '<script>alert("错误请求！");window.location.href="../view/manage.php";</script>';
    	}
    	$view=$_GET["view"];
    	$pid=$_GET["pid"];
    	if($view==1){
    	    echo '
                <li class="breadcrumb-item"><a href="manage.php"><span style="color:#888888  ">本地论文列表</span></a></li>
                ';
    	}
    	else{
    	    echo '
                <li class="breadcrumb-item"><a href="collect.php">收藏列表</a></li>
                ';
    	}
    	echo'
            <li class="breadcrumb-item active">论文详情页</li>
            <li class="breadcrumb-item active">论文编号：'.$pid.'</li>
            </ol>
		  </div>
        ';
    	$sql = "select * from paper where pid = '$pid' ";
    	$res = $conn->query($sql);
    	$sql = "select * from userPaper where userid = '$userid' and pid='$pid' ";
    	$res2 = $conn->query($sql);
    	if( $res->num_rows >0 ){
    	    $row2= $res->fetch_assoc();
    	    
    	    echo '
    	               <div class="paper">
	                        <div class="page-wrapper">
                            <div class="blog-title-area" >
                ';
    	    if($res2->num_rows >0 ){
    	        $row= $res2->fetch_assoc();
    	    if($row["remarks"]!=""){
    	        echo '<span style="color:#FE4365">['.$row["remarks"].']</span>';
    	    }
    	    }
    	    else{
    	        echo'
<a href=""  style="float:right;" onclick="addButton(this)" id="'.$pid.'">
    	           <svg t="1616654219630" class="icon" viewBox="0 0 1024 1024"  width="30" height="29"  version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="12459" width="200" height="200"><path d="M800 992.512 224 992.512C153.312 992.512 96 935.2 96 864.512L96 160.512C96 89.824 153.312 32.512 224 32.512L635.616 32.512C646.688 30.432 658.448 33.04 667.024 41.6L916.704 291.28C922.256 296.816 925.456 303.76 926.544 310.96 927.488 313.968 928 317.184 928 320.512L928 864.512C928 935.2 870.688 992.512 800 992.512ZM672 146.448 672 288.512 814.064 288.512 672 146.448ZM864 352.512 640 352.512C622.32 352.512 608 338.192 608 320.512L608 96.512 224 96.512C188.656 96.512 160 125.168 160 160.512L160 864.512C160 899.856 188.656 928.512 224 928.512L800 928.512C835.344 928.512 864 899.856 864 864.512L864 352.512ZM704 608.512 544 608.512 544 768.512C544 786.192 529.664 800.512 512 800.512 494.32 800.512 480 786.192 480 768.512L480 608.512 320 608.512C302.32 608.512 288 594.192 288 576.512 288 558.832 302.32 544.512 320 544.512L480 544.512 480 384.512C480 366.832 494.32 352.512 512 352.512 529.664 352.512 544 366.832 544 384.512L544 544.512 704 544.512C721.68 544.512 736 558.832 736 576.512 736 594.192 721.68 608.512 704 608.512Z" p-id="12460"></path></svg>
    	            </a>
                        
                    ';
    	    }
                echo '
                                <br/><h3>'.$row2["title"].'</h3>
                                <div class="blog-meta big-meta">
                                    <small>'.$pid.'</small>
                                    <small>'.$row2["meeting"].' '.$row2["year"].'</small>
                                    <small>'.$row2["ptime"].'</small>
                                </div>
                                <div class="blog-meta big-meta">
                                    <small><a href="'.$row2["link"].'" title="" target="_blank"><i class="fa fa-eye"></i>原文链接：'.$row2["link"].'</a></small>
                                </div>
                            <div class="blog-title-area">
                                <div class="tag-cloud-single">
                                    <a href="" onclick="keyButton(this)" id="'.$row2["key1"].'"><span style="background-color:#FC9D9A">'.$row2["key1"].'</span></a>
                                    <a href="" onclick="keyButton(this)" id="'.$row2["key2"].'"><span style="background-color:#ffacac">'.$row2["key2"].'</span></a>
                                    <a href="" onclick="keyButton(this)" id="'.$row2["key3"].'"><span style="background-color:#FF9999">'.$row2["key3"].'</span></a>
                                    <a href="" onclick="keyButton(this)" id="'.$row2["key4"].'"><span style="background-color:#ffacac">'.$row2["key4"].'</span></a>
                                    <a href="" onclick="keyButton(this)" id="'.$row2["key5"].'"><span style="background-color:#FC9D9A">'.$row2["key5"].'</span></a>
                                </div>
                            </div>
  
                            </div>

                            <div class="blog-content" >  
                                <div class="pp" >
                                    <p>'.$row2["summary"].'</p>
                                </div><!-- end pp -->
                            </div>
                        </div>
                    </div>
                ';  
    	} 
    	?>
		<div class="paper"> <div class="page-wrapper">
	         <?php 
	         $text=file_get_contents($row2["link"]);
	         preg_match_all('/<section id="Sec([0-9])*"(.*?)>(.*?)<\/section>/is', $text, $match);
	         
	         for($i=0;$i<count($match[0]);$i++){
	             print_r($match[0][$i]);
	         }
	         
	         ?>
	    </div></div>
</div>
    


</body>

<script>
function keyButton(e){
	window.event.returnValue=false;                    
    if (window.event.preventDefault) window.event.preventDefault();
	var x=e.id;
	window.location.href="search.php? searchName="+x+"&searchSelect=3";
}
function exitLogin(){
	window.event.returnValue=false;     
	window.location.href="../form/exit.php";
}
function addButton(e){
	window.event.returnValue=false;                    
    if (window.event.preventDefault) window.event.preventDefault();
    var r=confirm("您确定添加这篇论文？");
    if (r==false){
		return;
	}
	var str=prompt("请输入备注：");
	if (str==null||str==false||str==""){
		str="";
	}	
	var pid=e.id;
	var userid='<?php echo $userid;?>';
		window.location.href="../form/addPaper.php? pid="+pid+"&userid="+userid+"&r="+str;
}
</script>