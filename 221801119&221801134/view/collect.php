<?php session_start();?>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,minimal-ui">
    <title>论文收藏夹</title>
    <link rel='stylesheet' href='../css/myCss.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss3.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss4.css' type='text/css'/>
</head>

<body style="background-color:#F8F8F8 ;">
    <div id="app" class="surface--content">
    	<header class="metabar">
        	<div class="container u-flex">
            	<nav class="header-nav">
                	<ul class="subnav-ul">
                    	<li class="menu-item">
                    		<a  href="../index.php">首页</a>
                    	</li>
                    	<li class="menu-item">
                    		<a   data-id="37" data-adid="41" href="import.php">论文导入</a>
                    	</li>
                    	<li class="menu-item">
                    		<a   data-id="37" data-adid="41" href="manage.php">论文管理</a>
                    	</li>
                    	<li class="menu-item">
                    		<a data-id="37" data-adid="41" href="analysis.php">动态分析</a>
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
                	<div class="metabarItem u-flex u-cursorPointer" v-on:click="switchSearch"><a href="collect.php" class="u-flex" title="进入收藏夹" >
                    	<svg t="1616220263438" class="svgIcon-use" width="18" height="17" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1653" width="200" height="200"><path d="M890.233 419.484c-1.895 0-3.81 0-5.71 0l-28.555 0L855.968 310.982c0-43.782-34.26-43.782-45.68-43.782L524.759 267.2l-36.167-78.042c-5.712-11.42-15.227-17.132-26.65-17.132L153.57 172.026c-34.262 0-59.007 47.587-59.007 72.332l0 586.286c0 28.555 28.55 64.715 59.007 64.715l727.143 0c30.46 0 53.305-34.26 53.305-64.715L934.018 459.456C932.108 440.424 920.688 419.484 890.233 419.484zM798.868 324.304l0 95.18L593.283 419.484l-43.785-95.18L798.868 324.304zM875.008 830.643c0 1.9 0 5.71-1.91 7.615L157.38 838.258c-1.907-1.905-5.712-5.715-5.712-7.615L151.668 244.357c0-3.805 3.805-11.42 7.612-15.225l283.627 0 104.697 230.324c3.81 9.517 15.23 17.135 26.65 17.135l300.754 0L875.008 830.643z" p-id="1654"></path></svg>
                    	<b style="font-size: larger">收藏夹</b></a>
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
    						<a onclick="myMessage()">个人中心</a><br/>
    						<a onclick="exitLogin()">退出登录</a>
  						</div>
                	</div>
            	</div>
        	</div>
    	</header>
 	</div>

	<div class="sider" style="margin-left:15%;width:15%">
        <div class="siders" >
			<div style="padding:5% 0 ;">
				<div class="collectFolder" onclick="addFolder()">
					<svg t="1616726314010" class="icon" viewBox="0 0 1024 1024" width="30" height="29"  version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="9735" width="200" height="200"><path d="M187.264 888.746667l9.173333-22.186667c8.448 3.498667 17.749333 5.461333 27.562667 5.461333h36.010667V896H224c-13.013333 0-25.429333-2.56-36.736-7.253333z m576.725333 7.253333v-24.021333h36.010667c9.813333 0 19.114667-1.92 27.562667-5.418667l9.173333 22.186667c-11.306667 4.693333-23.722667 7.253333-36.736 7.253333h-36.010667zM896 260.010667h-24.021333V224c0-9.813333-1.92-19.114667-5.418667-27.562667l22.186667-9.173333c4.693333 11.306667 7.253333 23.722667 7.253333 36.736v36.010667zM260.010667 128H224c-13.013333 0-25.429333 2.56-36.736 7.253333l9.173333 22.186667a71.68 71.68 0 0 1 27.562667-5.418667h36.010667V128zM128 763.989333h24.021333v36.010667c0 9.813333 1.92 19.114667 5.418667 27.562667l-22.186667 9.173333A95.701333 95.701333 0 0 1 128 800v-36.010667z m0-71.978666h24.021333V619.946667H128v72.021333z m0-144h24.021333v-72.021334H128v72.021334z m0-144h24.021333V331.946667H128V404.053333z m0-144h24.021333V224c0-9.813333 1.92-19.114667 5.418667-27.562667l-22.186667-9.173333c-4.693333 11.306667-7.253333 23.722667-7.253333 36.736v36.010667zM331.989333 128v24.021333H404.053333V128H331.946667z m144 0v24.021333h72.021334V128h-72.021334z m144 0v24.021333h72.021334V128H619.946667z m144 0v24.021333h36.010667c9.813333 0 19.114667 1.92 27.562667 5.418667l9.173333-22.186667A95.701333 95.701333 0 0 0 800 128h-36.010667zM896 331.989333h-24.021333V404.053333H896V331.946667z m0 144h-24.021333v72.021334H896v-72.021334z m0 144h-24.021333v72.021334H896V619.946667z m0 144h-24.021333v36.010667c0 9.813333-1.92 19.114667-5.418667 27.562667l22.186667 9.173333c4.693333-11.306667 7.253333-23.722667 7.253333-36.736v-36.010667zM692.010667 896v-24.021333H619.946667V896h72.021333z m-144 0v-24.021333h-72.021334V896h72.021334z m-144 0v-24.021333H331.946667V896H404.053333z" fill="#8a8a8a" p-id="9736"></path><path d="M392.021333 512c0-13.226667 10.709333-24.021333 23.978667-24.021333h192a23.978667 23.978667 0 1 1 0 48.042666h-192A23.978667 23.978667 0 0 1 391.978667 512z" fill="#8a8a8a" p-id="9737"></path><path d="M512 392.021333c13.226667 0 24.021333 10.709333 24.021333 23.978667v192a23.978667 23.978667 0 1 1-48.042666 0v-192c0-13.226667 10.752-24.021333 24.021333-24.021333z" fill="#8a8a8a" p-id="9738"></path></svg>
					<span style="position: relative;top:-8px;left:5%">新建收藏夹</span>
				</div>
				<HR align=center width=80% color=#ccc SIZE=1>
				<div class="collectFolder" id="默认收藏夹" onclick="folderButton(this)">
					<svg t="1616730211575" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="33673" width="30" height="29"><path d="M174.592 378.88c10.752-36.352 44.032-61.952 81.92-63.488h600.064c0-54.272-44.032-98.304-98.304-98.304H389.12l-36.864-67.584c-7.168-18.432-25.6-30.72-45.568-31.232H118.784C64.512 118.784 20.48 162.816 20.48 217.088v516.096c0 37.888 22.016 72.704 56.832 88.576l97.28-442.88zM844.8 778.24c5.632-10.24 9.728-20.992 11.776-32.256L844.8 778.24z" fill="#bfbfbf" p-id="33674"></path><path d="M915.968 364.544H305.664c-37.888 1.024-71.168 26.624-81.92 63.488l-104.448 477.696h688.128c37.888-1.536 71.168-27.136 81.92-63.488l108.544-344.064c22.016-64.512-20.48-133.632-81.92-133.632z m-202.24 251.904l-62.976 61.952 14.848 87.552c1.536 7.168-1.024 14.336-6.144 18.944-2.56 2.56-6.144 3.584-9.728 3.072-3.584 0-7.68-1.024-10.752-3.072l-77.824-40.96-77.824 41.472c-6.144 4.096-14.336 4.096-19.968 0-5.632-4.608-8.192-11.776-6.144-18.944l14.848-87.552-62.976-62.464c-7.68-5.632-9.728-15.872-4.096-23.552 3.072-4.608 8.192-7.168 13.824-7.168l87.552-12.8 39.424-79.872c3.072-8.704 12.8-13.312 21.504-10.24 4.608 1.536 8.704 5.632 10.24 10.24l39.424 79.872 87.552 12.8c9.216 0 16.896 7.68 16.896 17.408 0 5.12-3.072 10.24-7.68 13.312z" fill="#bfbfbf" p-id="33675"></path></svg>
					<span style="position: relative;top:-8px;left:5%" >默认收藏夹</span>
				</div>
				<?php 
					$conn = new mysqli('localhost','root','','paperdb');
					$conn->query("SET NAMES utf8");
					$sql = "select * from collection where userid='$userid' and folder!='默认收藏夹' ";
					$res=$conn->query($sql);
					if($res->num_rows >0){
					   while( $row= $res->fetch_assoc()){
						  $f=$row["folder"];
						  echo'
                           <div class="collectFolder" id="'.$f.'" onclick="folderButton(this)">
						      <svg t="1616730211575" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="33673" width="30" height="29"><path d="M174.592 378.88c10.752-36.352 44.032-61.952 81.92-63.488h600.064c0-54.272-44.032-98.304-98.304-98.304H389.12l-36.864-67.584c-7.168-18.432-25.6-30.72-45.568-31.232H118.784C64.512 118.784 20.48 162.816 20.48 217.088v516.096c0 37.888 22.016 72.704 56.832 88.576l97.28-442.88zM844.8 778.24c5.632-10.24 9.728-20.992 11.776-32.256L844.8 778.24z" fill="#bfbfbf" p-id="33674"></path><path d="M915.968 364.544H305.664c-37.888 1.024-71.168 26.624-81.92 63.488l-104.448 477.696h688.128c37.888-1.536 71.168-27.136 81.92-63.488l108.544-344.064c22.016-64.512-20.48-133.632-81.92-133.632z m-202.24 251.904l-62.976 61.952 14.848 87.552c1.536 7.168-1.024 14.336-6.144 18.944-2.56 2.56-6.144 3.584-9.728 3.072-3.584 0-7.68-1.024-10.752-3.072l-77.824-40.96-77.824 41.472c-6.144 4.096-14.336 4.096-19.968 0-5.632-4.608-8.192-11.776-6.144-18.944l14.848-87.552-62.976-62.464c-7.68-5.632-9.728-15.872-4.096-23.552 3.072-4.608 8.192-7.168 13.824-7.168l87.552-12.8 39.424-79.872c3.072-8.704 12.8-13.312 21.504-10.24 4.608 1.536 8.704 5.632 10.24 10.24l39.424 79.872 87.552 12.8c9.216 0 16.896 7.68 16.896 17.408 0 5.12-3.072 10.24-7.68 13.312z" fill="#bfbfbf" p-id="33675"></path></svg>
						      <span style="position: relative;top:-8px;left:5%">'.$f.'</span>
						  </div>
                                ';
						}
					}
					if(isset($_GET["cFolder"])){
						$folder=$_GET["cFolder"];
					}
					else 
						$folder="默认收藏夹";
				?>
				<script type="text/javascript">
					var f='<?php echo $folder;?>';
					document.getElementById(f).style.color="white";
					document.getElementById(f).style.background="#FFCCCC";
				</script>
            </div>           
		</div>   
	</div>		
        
	<div class="mainPaper" style="margin:0">
		<div class="paper" style="padding-top:0;padding-bottom:0">
		<ol class="breadcrumb hidden-xs-down" style="margin:0">
            <li class="breadcrumb-item active">收藏列表</li>
            <li class="breadcrumb-item active">
            	<?php echo $folder;?>
            </li>
            <li class="breadcrumb-item active" style="float:right">共 
            	<span id="resultNum"></span> 个收藏
            </li>
        </ol>
		</div>
    	<?php 
    	   $conn = new mysqli('localhost','root','','paperdb');
    	   $conn->query("SET NAMES utf8");
    	   if(!isset($_SESSION["userid"])){
    	       echo '<script>alert("请先登录！");window.location.href="../view/login.php";</script>';
    	   }
    	   $userid=$_SESSION["userid"];
    	   $sql = "select * from userPaper where userid = '$userid' and collect='1' and folder='$folder'  ORDER BY pid";
    	   $res = $conn->query($sql);
    	   $resultNum=0;
    	   if( $res->num_rows >0 ){
    	       while( $row= $res->fetch_assoc()){
    	           $pid=$row["pid"];
    	           $sql="select * from paper where pid = '$pid' ";
    	           $result = $conn->query($sql);
    	           $row2=$result->fetch_assoc();
    	           echo '
    	               <div class="paper"> 
	                        <div class="page-wrapper">
                            <div class="blog-title-area" >  
                    ';
    	           if($row["remarks"]!=""){
    	               echo '<span style="color:#FE4365">['.$row["remarks"].']</span>';
    	           }
    	           else{
    	               echo '<span style="color:#C0C0C0">[点击添加备注]</span>';
    	           }
    	        
    	           $resultNum++;
                    echo '
    	               <a href="" onclick="remarkButton(this)" id="'.$pid.'">
    	                   <svg t="1616329101167" class="icon" width="20" height="19" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2155" width="200" height="200"><path d="M791.04 188.373333l45.226667 45.226667-306.986667 306.986667-72.96 27.733333 27.733333-72.96L791.04 188.373333m0-81.706666a21.333333 21.333333 0 0 0-15.146667 6.186666L432.213333 456.533333a21.333333 21.333333 0 0 0-4.906666 7.466667l-64 170.666667A21.333333 21.333333 0 0 0 390.4 661.333333l170.666667-64a21.333333 21.333333 0 0 0 7.466666-4.906666l343.253334-343.68a21.333333 21.333333 0 0 0 0-30.08l-105.6-105.813334a21.333333 21.333333 0 0 0-15.146667-6.186666z" fill="#333333" p-id="2156"></path><path d="M789.333333 523.946667v330.666666H170.666667v-618.666666h330.666666a32 32 0 0 0 32-32 32 32 0 0 0-32-32H128a21.333333 21.333333 0 0 0-21.333333 21.333333v704a21.333333 21.333333 0 0 0 21.333333 21.333333h704a21.333333 21.333333 0 0 0 21.333333-21.333333v-373.333333a32 32 0 0 0-32-32 32 32 0 0 0-32 32z" fill="#333333" p-id="2157"></path></svg>
        	           </a>  
                        <a href=""  style="float:right;" onclick="deleteCollect(this)" id="'.$pid.'">
                            <svg t="1616311672560" class="icon" width="30" height="29" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2138" width="200" height="200"><path d="M799.2 874.4c0 34.4-28 62.4-62.368 62.4H287.2a62.496 62.496 0 0 1-62.4-62.4V212h574.4v662.4zM349.6 100c0-7.2 5.6-12.8 12.8-12.8h300c7.2 0 12.768 5.6 12.768 12.8v37.6H349.6V100z m636.8 37.6H749.6V100c0-48-39.2-87.2-87.2-87.2h-300a87.392 87.392 0 0 0-87.2 87.2v37.6H37.6C16.8 137.6 0 154.4 0 175.2s16.8 37.6 37.6 37.6h112v661.6A137.6 137.6 0 0 0 287.2 1012h449.6a137.6 137.6 0 0 0 137.6-137.6V212h112c20.8 0 37.6-16.8 37.6-37.6s-16.8-36.8-37.6-36.8zM512 824c20.8 0 37.6-16.8 37.6-37.6v-400c0-20.8-16.768-37.6-37.6-37.6-20.8 0-37.6 16.8-37.6 37.6v400c0 20.8 16.8 37.6 37.6 37.6m-175.2 0c20.8 0 37.6-16.8 37.6-37.6v-400c0-20.8-16.8-37.6-37.6-37.6s-37.6 16.8-37.6 37.6v400c0.8 20.8 17.6 37.6 37.6 37.6m350.4 0c20.8 0 37.632-16.8 37.632-37.6v-400c0-20.8-16.8-37.6-37.632-37.6-20.768 0-37.6 16.8-37.6 37.6v400c0 20.8 16.8 37.6 37.6 37.6" p-id="2139"></path></svg>
                        </a>  
                        <a href=""  style="float:right;" onclick="checkButton(this)" id="'.$pid.'">
                            <svg t="1616548870602" class="icon" width="30" height="29" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2787" width="200" height="200"><path d="M607.839811 895.957102H214.69447A86.82497 86.82497 0 0 1 127.880338 809.070721V214.784781A86.839419 86.839419 0 0 1 214.69447 127.880338h594.28594a86.33729 86.33729 0 0 1 61.436749 25.456857c16.400473 16.400473 25.362934 38.208767 25.373771 61.411462L894.439878 607.821749v0.10476a32.031496 32.031496 0 0 0 64.059379 0.101149L959.825022 214.889542v-0.104761A150.775976 150.775976 0 0 0 808.98041 63.940169H214.69447A150.638703 150.638703 0 0 0 63.940169 214.784781v594.28594a150.638703 150.638703 0 0 0 150.757914 150.82655h393.141728a31.970084 31.970084 0 0 0 0-63.940169z" fill="" p-id="2788"></path><path d="M950.544667 905.331381l-122.071536-122.071536a192.217875 192.217875 0 1 0-45.213286 45.213286l122.071536 122.071536a31.970084 31.970084 0 0 0 45.213286-45.213286z m-278.547941-105.302594a128.028448 128.028448 0 1 1 90.531332-37.497116 127.193975 127.193975 0 0 1-90.527719 37.497116zM768.004516 352.212795c17.653989 0 31.966472-14.402794 31.970084-32.056783s-14.308871-32.074845-31.966472-32.078457L256.002709 287.911382a32.020659 32.020659 0 0 0-31.970084 32.024271c0 17.657601 14.308871 32.092907 31.966472 32.092908L768.004516 352.212795zM448.000226 544.033302a31.96286 31.96286 0 1 0 0-63.940169h-192.001129a31.937573 31.937573 0 1 0 0 63.878758l192.001129 0.061411zM256.017159 671.91364a31.959247 31.959247 0 1 0 0 63.922107l127.999549 0.018062a31.941185 31.941185 0 1 0 0-63.878757z" fill="" p-id="2789"></path></svg>
                        </a> 
                        <span class="moveButton">
                            <a href=""   style="float:right;"  id="'.$pid.'">
                            <svg t="1616740443077" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3535" width="29" height="28"><path d="M861.0304 290.816a32.3072 32.3072 0 0 1-23.2448-10.24l-73.5232-78.7968-73.5232 78.7456a31.9488 31.9488 0 1 1-46.4384-43.8784L742.4 132.096a32.3072 32.3072 0 0 1 23.2448-10.24 30.208 30.208 0 0 1 23.2448 10.24l98.048 104.4992A32.3072 32.3072 0 0 1 885.76 281.6a52.224 52.224 0 0 1-24.5248 9.0112zM762.88 911.36a30.208 30.208 0 0 1-23.2448-10.24l-98.048-104.4992a31.9488 31.9488 0 1 1 46.5408-43.9808l73.5232 78.6944L835.2256 752.64a31.9488 31.9488 0 0 1 46.4384 43.8784L783.616 901.12c-2.56 6.4512-11.6224 10.24-20.48 10.24z" p-id="3536"></path><path d="M762.88 859.8528a31.9488 31.9488 0 0 1-32.256-32.256V200.4992a32.256 32.256 0 1 1 64.512 0v627.0976a31.9488 31.9488 0 0 1-32.256 32.256zM510.0544 374.6816H170.7008a32.256 32.256 0 0 1 0-64.512h339.3536a31.9488 31.9488 0 0 1 32.256 32.256 32.768 32.768 0 0 1-32.256 32.256zM510.0544 537.2928H170.7008a32.256 32.256 0 0 1 0-64.512h339.3536a31.9488 31.9488 0 0 1 32.256 32.256 32.768 32.768 0 0 1-32.256 32.256zM508.7744 723.0976H169.4208a32.256 32.256 0 1 1 0-64.512h339.3536a31.9488 31.9488 0 0 1 32.256 32.256 32.768 32.768 0 0 1-32.256 32.256z" p-id="3537"></path></svg>
                            <div class="collect-content">
                    ';
                    $sql = "select * from collection where userid='$userid' and folder!='$folder' ";
                    $res3=$conn->query($sql);
                    $num=0;
                    if($res3->num_rows >0){
                        while( $row3= $res3->fetch_assoc()){
                            $num++;
                            if($num!=1)
                                echo '<br/>';
                            $f=$row3["folder"];
                            echo'
                                <a href="" onclick="moveButton(this)" id="'.$pid.'">
                                    <small>移动到</small>
                                    <span style="color:pink" id="'.$f.'">['.$f.']</span>
                                </a>';
                        }   
                    }

                    echo'
  					             </div>
                               </a>
                             </span>

                                <br/><h3 >'.$row2["title"].'</h3>
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
    	       echo '<div class="paper" style="text-align:center;">没有更多了……</div>';
    	   }
    	   else{
    	       echo '<div class="paper" style="text-align:center;">暂无论文</div>';
    	   }
    	?>
    	
      	<script type="text/javascript">
   			var n='<?php echo $resultNum;?>';
   			document.getElementById("resultNum").innerHTML=n;
   		</script>
   		
	</div>
	
	<div style="height:40px;float:left;width:100%;magin:0;padding:20px 0;text-align:center;font:"黑体">
		<small>Copyright ©2020-2021 - XXL&XXY</small> 
	</div>
	
</body>

<script>

function remarkButton(e){
	window.event.returnValue=false;
	if (window.event.preventDefault) 
		window.event.preventDefault();
	var str=prompt("请输入备注：");
	if (str==null||str==false||str==""){
		return;
	}
	var pid=e.id;
	var userid='<?php echo $userid;?>';
	window.location.href="../form/remark.php? pid="+pid+"&userid="+userid+"&remark="+str+"&view=3";
}

function keyButton(e){
	window.event.returnValue=false;                    
    if (window.event.preventDefault)
		window.event.preventDefault();
	var x=e.id;
	window.location.href="search.php? searchName="+x+"&searchSelect=3";
}

function exitLogin(){
	window.location.href="../form/exit.php";
}

function deleteCollect(e){
	window.event.returnValue=false;
	var pid=e.id;
	var r=confirm("您确定取消收藏这篇论文？");
	if (r==false){
		return;
	}
	var userid='<?php echo $userid;?>';
	window.location.href="../form/collected.php? pid="+pid+"&userid="+userid+"&c=0"+"&view=3";
}

function exitLogin(){
	window.event.returnValue=false;     
	window.location.href="../form/exit.php";
}

function checkButton(e){
	window.event.returnValue=false;                    
    if (window.event.preventDefault) 
    	window.event.preventDefault();
	var pid=e.id;
	window.location.href="check.php? pid="+pid+"&view=3";
}

function folderButton(e){
	var f=e.id;
	window.location.href="collect.php? cFolder="+f;
}

function addFolder(){
	var str=prompt("请输入收藏夹名：");
	if (str==null||str==false||str==""){
		return;
	}
	var userid='<?php echo $userid;?>';
	window.location.href="../form/addFolder.php? folder="+str+"&userid="+userid;
}

function moveButton(e){
	window.event.returnValue=false;                    
    if (window.event.preventDefault) 
    	window.event.preventDefault();
    var pid=e.id;
    var userid='<?php echo $userid;?>';
    var f=e.childNodes.item(3).id;
    window.location.href="../form/updateFolder.php? folder="+f+"&userid="+userid+"&pid="+pid;
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
