<?php session_start();?>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,minimal-ui">
    <title>首页</title>
    <link rel='stylesheet' href='css/myCss.css' type='text/css'/>
    <link rel='stylesheet' href='css/myCss3.css' type='text/css'/>
    <link rel='stylesheet' href='css/myCss4.css' type='text/css'/>
</head>

<body style="background-color:#F8F8F8 ;">
	<div id="app" class="surface--content">
    	<el-backtop></el-backtop>
    	<header class="metabar">
        	<div class="container u-flex">
            	<nav class="header-nav">
                	<ul class="subnav-ul">
                    	<li class="menu-item">
                    		<a  href="index.php">
                    			<b style="font-size: larger">首页</b>
                    		</a>
                    	</li>
                    	<li class="menu-item">
                    		<a   data-id="37" data-adid="41" href="view/import.php">论文导入</a>
                    	</li>
                    	<li class="menu-item">
                    		<a   data-id="37" data-adid="41" href="view/manage.php">论文管理</a>
                    	</li>
                    	<li class="menu-item">
                    		<a data-id="37" data-adid="41" href="view/analysis.php">动态分析</a>
                    	</li>
                	</ul>
            	</nav>
            	<div class="metabar__right u-flex">
                	<div class="metabarItem u-flex">
                		<a  class="u-flex" title="分享网站链接" onclick="copyLink()">
                    		<svg t="1616220697148" class="icon" width="25" height="24"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2435" width="200" height="200"><path d="M522.24 760.32c-15.36 0-25.6-10.24-25.6-25.6V448c0-15.36 10.24-25.6 25.6-25.6s25.6 10.24 25.6 25.6v289.28c0 12.8-12.8 23.04-25.6 23.04z" fill="#333333" p-id="2436"></path><path d="M601.6 545.28c-7.68 0-12.8-2.56-17.92-7.68l-61.44-61.44L460.8 537.6c-10.24 10.24-25.6 10.24-35.84 0s-10.24-25.6 0-35.84l79.36-79.36c10.24-10.24 25.6-10.24 35.84 0l79.36 79.36c10.24 10.24 10.24 25.6 0 35.84-5.12 5.12-10.24 7.68-17.92 7.68z" fill="#333333" p-id="2437"></path><path d="M755.2 780.8h-151.04c-15.36 0-25.6-10.24-25.6-25.6s10.24-25.6 25.6-25.6h151.04c79.36 0 143.36-64 143.36-143.36s-64-143.36-143.36-143.36c-23.04 0-48.64 5.12-69.12 17.92-7.68 5.12-17.92 5.12-25.6 0-7.68-5.12-12.8-12.8-12.8-23.04 0-71.68-56.32-128-128-128-58.88 0-110.08 40.96-125.44 97.28-2.56 7.68-5.12 12.8-12.8 15.36-5.12 2.56-12.8 5.12-20.48 2.56-10.24-2.56-20.48-5.12-28.16-5.12-61.44 0-110.08 48.64-110.08 110.08 0 10.24-7.68 20.48-17.92 23.04-35.84 12.8-58.88 46.08-58.88 81.92 0 48.64 38.4 87.04 87.04 87.04h168.96c15.36 0 25.6 10.24 25.6 25.6s-10.24 25.6-25.6 25.6H243.2h-2.56c-79.36 5.12-145.92-58.88-145.92-138.24 0-53.76 30.72-102.4 76.8-125.44 7.68-81.92 76.8-145.92 161.28-145.92h17.92c25.6-69.12 92.16-115.2 166.4-115.2 87.04 0 158.72 61.44 176.64 143.36 17.92-5.12 38.4-10.24 58.88-10.24 107.52 0 194.56 87.04 194.56 194.56 0 117.76-87.04 204.8-192 204.8z" fill="#707070" p-id="2438"></path></svg>
                    		<span style="color:#707070 ">分享网站</span>
                    	</a>
                	</div>
                	<div class="metabarItem u-flex u-cursorPointer" v-on:click="switchSearch">
                		<a href="view/collect.php" class="u-flex" title="进入收藏夹" >
                    		<svg t="1616220263438" class="svgIcon-use" width="18" height="17" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1653" width="200" height="200"><path d="M890.233 419.484c-1.895 0-3.81 0-5.71 0l-28.555 0L855.968 310.982c0-43.782-34.26-43.782-45.68-43.782L524.759 267.2l-36.167-78.042c-5.712-11.42-15.227-17.132-26.65-17.132L153.57 172.026c-34.262 0-59.007 47.587-59.007 72.332l0 586.286c0 28.555 28.55 64.715 59.007 64.715l727.143 0c30.46 0 53.305-34.26 53.305-64.715L934.018 459.456C932.108 440.424 920.688 419.484 890.233 419.484zM798.868 324.304l0 95.18L593.283 419.484l-43.785-95.18L798.868 324.304zM875.008 830.643c0 1.9 0 5.71-1.91 7.615L157.38 838.258c-1.907-1.905-5.712-5.715-5.712-7.615L151.668 244.357c0-3.805 3.805-11.42 7.612-15.225l283.627 0 104.697 230.324c3.81 9.517 15.23 17.135 26.65 17.135l300.754 0L875.008 830.643z" p-id="1654"></path></svg>
                    	收藏夹</a>
                	</div>
                	<metabar-notice v-bind:noticenum="noticeNum" v-bind:islogin="islogin"></metabar-notice>
                	<div class="metabarItem u-flex u-paddingLeft20 dropdown" >
                    <?php 
                        $conn = new mysqli('localhost','root','','paperdb');
                        if(!isset($_SESSION["userid"])){
                            echo '<a href="view/login.php" class="u-flex" title="上传" ><span class="" >注册 | 登录</span></a>';
                        }
                        else{
                            $userid=$_SESSION["userid"];
                            $sql = "select * from user where userid = '$userid' ";
                            $result = $conn->query($sql);
                            $number = mysqli_num_rows($result);
                            $row=$result ->fetch_assoc();
                            echo '
                                <a href="" class="u-flex" title="用户" >
                                    <svg t="1616238407449" class="icon" width="40" height="39" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2253" width="200" height="200"><path d="M512 0a512 512 0 1 0 512 512A512 512 0 0 0 512 0zM213.333 832A298.667 298.667 0 0 1 512 533.333a170.667 170.667 0 1 1 170.667-170.666A170.667 170.667 0 0 1 512 533.333 298.667 298.667 0 0 1 810.667 832z" p-id="2254" fill="#e6e6e6"></path></svg>
                                </a>';
                            echo $row["username"];
                            echo '
                            <div class="dropdown-content">
    				            <a onclick="myMessage()">个人中心</a><br/>
    				            <a onclick="exitLogin()">退出登录</a>
  					         </div>
                            ';
                        }
                    ?>
                	</div>
            	</div>
        	</div>
    	</header>
	<div/>
	
	<div style="width:80%;padding:6% 0% 0% 15%">
		<h1 style="font-size:42px;magin:0;color:#606060 ">欢迎来到</h1>
		<h1 style="font-size:100px;magin:0;position:relative;top:-170px">论文
			<span style="font-size:200px;color:#FC9D9A">爬</span>取站！
		</h1>
		<h1 style="font-size:30px;magin:0;position:relative;top:-280px;color:#505050  ">Welcome to paper crawling station!</h1>
	</div>   
    
	<div style="float:left;width:100%;magin:0;text-align:center;font:"黑体">
		<small>Copyright ©2020-2021 - XXL&XXY</small> 
	</div>

</body>

<script type="text/javascript">

function exitLogin(){
	window.event.returnValue=false;     
	window.location.href="form/exit.php";
}

function copyLink(){
	window.event.returnValue=false;
	if (window.event.preventDefault) 
		window.event.preventDefault();
	if (!!window.ActiveXObject || "ActiveXObject" in window){
		window.clipboardData.setData("text",'http://222.77.0.199:8090/');
		alert("已复制网址http://222.77.0.199:8090/至剪切板");
	}
  	else{
  		alert("当前浏览器暂不支持改功能！");
	}
}

function myMessage(){
	alert("抱歉！该功能暂未开放！");
}

</script>
