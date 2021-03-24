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
                    <li class="menu-item"><a  href="">首页</a></li>
                    <li class="menu-item"><a   data-id="37" data-adid="41" href="import.php">论文导入</a></li>
                    <li class="menu-item"><a   data-id="37" data-adid="41" href="manage.php"><b style="font-size: larger">论文管理</b></a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="analysis.php">动态分析</a></li>
                </ul>
            </nav>
            <div class="metabar__right u-flex">
                <div class="metabarItem u-flex"><a href="" class="u-flex" title="上传" >
                    <svg t="1616220697148" class="icon" width="25" height="24"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2435" width="200" height="200"><path d="M522.24 760.32c-15.36 0-25.6-10.24-25.6-25.6V448c0-15.36 10.24-25.6 25.6-25.6s25.6 10.24 25.6 25.6v289.28c0 12.8-12.8 23.04-25.6 23.04z" fill="#333333" p-id="2436"></path><path d="M601.6 545.28c-7.68 0-12.8-2.56-17.92-7.68l-61.44-61.44L460.8 537.6c-10.24 10.24-25.6 10.24-35.84 0s-10.24-25.6 0-35.84l79.36-79.36c10.24-10.24 25.6-10.24 35.84 0l79.36 79.36c10.24 10.24 10.24 25.6 0 35.84-5.12 5.12-10.24 7.68-17.92 7.68z" fill="#333333" p-id="2437"></path><path d="M755.2 780.8h-151.04c-15.36 0-25.6-10.24-25.6-25.6s10.24-25.6 25.6-25.6h151.04c79.36 0 143.36-64 143.36-143.36s-64-143.36-143.36-143.36c-23.04 0-48.64 5.12-69.12 17.92-7.68 5.12-17.92 5.12-25.6 0-7.68-5.12-12.8-12.8-12.8-23.04 0-71.68-56.32-128-128-128-58.88 0-110.08 40.96-125.44 97.28-2.56 7.68-5.12 12.8-12.8 15.36-5.12 2.56-12.8 5.12-20.48 2.56-10.24-2.56-20.48-5.12-28.16-5.12-61.44 0-110.08 48.64-110.08 110.08 0 10.24-7.68 20.48-17.92 23.04-35.84 12.8-58.88 46.08-58.88 81.92 0 48.64 38.4 87.04 87.04 87.04h168.96c15.36 0 25.6 10.24 25.6 25.6s-10.24 25.6-25.6 25.6H243.2h-2.56c-79.36 5.12-145.92-58.88-145.92-138.24 0-53.76 30.72-102.4 76.8-125.44 7.68-81.92 76.8-145.92 161.28-145.92h17.92c25.6-69.12 92.16-115.2 166.4-115.2 87.04 0 158.72 61.44 176.64 143.36 17.92-5.12 38.4-10.24 58.88-10.24 107.52 0 194.56 87.04 194.56 194.56 0 117.76-87.04 204.8-192 204.8z" fill="#333333" p-id="2438"></path></svg>
                    上传论文</a>
                </div>
                <div class="metabarItem u-flex u-cursorPointer" v-on:click="switchSearch"><a href="" class="u-flex" title="收藏" >
                    <svg t="1616220263438" class="svgIcon-use" width="18" height="17" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1653" width="200" height="200"><path d="M890.233 419.484c-1.895 0-3.81 0-5.71 0l-28.555 0L855.968 310.982c0-43.782-34.26-43.782-45.68-43.782L524.759 267.2l-36.167-78.042c-5.712-11.42-15.227-17.132-26.65-17.132L153.57 172.026c-34.262 0-59.007 47.587-59.007 72.332l0 586.286c0 28.555 28.55 64.715 59.007 64.715l727.143 0c30.46 0 53.305-34.26 53.305-64.715L934.018 459.456C932.108 440.424 920.688 419.484 890.233 419.484zM798.868 324.304l0 95.18L593.283 419.484l-43.785-95.18L798.868 324.304zM875.008 830.643c0 1.9 0 5.71-1.91 7.615L157.38 838.258c-1.907-1.905-5.712-5.715-5.712-7.615L151.668 244.357c0-3.805 3.805-11.42 7.612-15.225l283.627 0 104.697 230.324c3.81 9.517 15.23 17.135 26.65 17.135l300.754 0L875.008 830.643z" p-id="1654"></path></svg>
                    收藏夹</a>
                </div>
                <metabar-notice v-bind:noticenum="noticeNum" v-bind:islogin="islogin"></metabar-notice>
                <div class="metabarItem u-flex u-paddingLeft20" >
                    <a href="" class="u-flex" title="用户" >
                    <svg t="1616238407449" class="icon" width="40" height="39" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2253" width="200" height="200"><path d="M512 0a512 512 0 1 0 512 512A512 512 0 0 0 512 0zM213.333 832A298.667 298.667 0 0 1 512 533.333a170.667 170.667 0 1 1 170.667-170.666A170.667 170.667 0 0 1 512 533.333 298.667 298.667 0 0 1 810.667 832z" p-id="2254" fill="#e6e6e6"></path></svg>
                    </a>
                    <?php 
                    $conn = new mysqli('localhost','root','','paperdb');
                    if(!isset($_SESSION["userid"])){
                        echo '<script>alert("请先登录！");window.location.href="../view/login.php";</script>';
                    }
                    $userid=$_SESSION["userid"];
                    $sql = "select * from user where userid = '$userid' ";
                    $result = $conn->query($sql);
                    $number = mysqli_num_rows($result);
                    $row=$result ->fetch_assoc();
                    echo $row["username"];
                    ?>
                </div>
            </div>
        </div>
    </header>
 </div>

		
        
<div class="mainPaper">
		<div class="paper" style="padding-top:0;padding-bottom:0">
		<ol class="breadcrumb hidden-xs-down" style="margin:0 50% 0 0">
            <li class="breadcrumb-item active">论文列表</li>
        </ol>
		</div>
    	<?php 
    	$conn = new mysqli('localhost','root','','paperdb');
    	$conn->query("SET NAMES utf8");
    	$userid=$_SESSION["userid"];
    	$sql = "select * from userPaper where userid = '$userid' ORDER BY pid";
    	$res = $conn->query($sql);
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
    	        

                echo '
    	            <a href="" onclick="remarkButton(this)" id="'.$pid.'">
    	            <svg t="1616329101167" class="icon" width="20" height="19" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2155" width="200" height="200"><path d="M791.04 188.373333l45.226667 45.226667-306.986667 306.986667-72.96 27.733333 27.733333-72.96L791.04 188.373333m0-81.706666a21.333333 21.333333 0 0 0-15.146667 6.186666L432.213333 456.533333a21.333333 21.333333 0 0 0-4.906666 7.466667l-64 170.666667A21.333333 21.333333 0 0 0 390.4 661.333333l170.666667-64a21.333333 21.333333 0 0 0 7.466666-4.906666l343.253334-343.68a21.333333 21.333333 0 0 0 0-30.08l-105.6-105.813334a21.333333 21.333333 0 0 0-15.146667-6.186666z" fill="#333333" p-id="2156"></path><path d="M789.333333 523.946667v330.666666H170.666667v-618.666666h330.666666a32 32 0 0 0 32-32 32 32 0 0 0-32-32H128a21.333333 21.333333 0 0 0-21.333333 21.333333v704a21.333333 21.333333 0 0 0 21.333333 21.333333h704a21.333333 21.333333 0 0 0 21.333333-21.333333v-373.333333a32 32 0 0 0-32-32 32 32 0 0 0-32 32z" fill="#333333" p-id="2157"></path></svg>
        	         </a>  
                  <a href=""  style="float:right" onclick="deleteButton(this)" id="'.$pid.'">
                    <svg t="1616311672560" class="icon" width="30" height="29"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2138" width="200" height="200"><path d="M799.2 874.4c0 34.4-28 62.4-62.368 62.4H287.2a62.496 62.496 0 0 1-62.4-62.4V212h574.4v662.4zM349.6 100c0-7.2 5.6-12.8 12.8-12.8h300c7.2 0 12.768 5.6 12.768 12.8v37.6H349.6V100z m636.8 37.6H749.6V100c0-48-39.2-87.2-87.2-87.2h-300a87.392 87.392 0 0 0-87.2 87.2v37.6H37.6C16.8 137.6 0 154.4 0 175.2s16.8 37.6 37.6 37.6h112v661.6A137.6 137.6 0 0 0 287.2 1012h449.6a137.6 137.6 0 0 0 137.6-137.6V212h112c20.8 0 37.6-16.8 37.6-37.6s-16.8-36.8-37.6-36.8zM512 824c20.8 0 37.6-16.8 37.6-37.6v-400c0-20.8-16.768-37.6-37.6-37.6-20.8 0-37.6 16.8-37.6 37.6v400c0 20.8 16.8 37.6 37.6 37.6m-175.2 0c20.8 0 37.6-16.8 37.6-37.6v-400c0-20.8-16.8-37.6-37.6-37.6s-37.6 16.8-37.6 37.6v400c0.8 20.8 17.6 37.6 37.6 37.6m350.4 0c20.8 0 37.632-16.8 37.632-37.6v-400c0-20.8-16.8-37.6-37.632-37.6-20.768 0-37.6 16.8-37.6 37.6v400c0 20.8 16.8 37.6 37.6 37.6" p-id="2139"></path></svg>
                </a> 
  
                    <a href=""  style="float:right;" id="'.$pid.'">
                      <svg t="1616548870602" class="icon" width="30" height="29" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2787" width="200" height="200"><path d="M607.839811 895.957102H214.69447A86.82497 86.82497 0 0 1 127.880338 809.070721V214.784781A86.839419 86.839419 0 0 1 214.69447 127.880338h594.28594a86.33729 86.33729 0 0 1 61.436749 25.456857c16.400473 16.400473 25.362934 38.208767 25.373771 61.411462L894.439878 607.821749v0.10476a32.031496 32.031496 0 0 0 64.059379 0.101149L959.825022 214.889542v-0.104761A150.775976 150.775976 0 0 0 808.98041 63.940169H214.69447A150.638703 150.638703 0 0 0 63.940169 214.784781v594.28594a150.638703 150.638703 0 0 0 150.757914 150.82655h393.141728a31.970084 31.970084 0 0 0 0-63.940169z" fill="" p-id="2788"></path><path d="M950.544667 905.331381l-122.071536-122.071536a192.217875 192.217875 0 1 0-45.213286 45.213286l122.071536 122.071536a31.970084 31.970084 0 0 0 45.213286-45.213286z m-278.547941-105.302594a128.028448 128.028448 0 1 1 90.531332-37.497116 127.193975 127.193975 0 0 1-90.527719 37.497116zM768.004516 352.212795c17.653989 0 31.966472-14.402794 31.970084-32.056783s-14.308871-32.074845-31.966472-32.078457L256.002709 287.911382a32.020659 32.020659 0 0 0-31.970084 32.024271c0 17.657601 14.308871 32.092907 31.966472 32.092908L768.004516 352.212795zM448.000226 544.033302a31.96286 31.96286 0 1 0 0-63.940169h-192.001129a31.937573 31.937573 0 1 0 0 63.878758l192.001129 0.061411zM256.017159 671.91364a31.959247 31.959247 0 1 0 0 63.922107l127.999549 0.018062a31.941185 31.941185 0 1 0 0-63.878757z" fill="" p-id="2789"></path></svg>
                    </a>    

                <a href="" style="float:right">
                <svg t="1616549001186" class="icon" viewBox="0 0 1059 1024"  width="28" height="27" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3703" width="200" height="200"><path d="M253.488042 1024c-16.9 0-33.2875-5.1125-47.6125-15.3625-26.625-18.425-39.425-49.6625-34.3125-81.925l40.9625-251.9c1.5375-10.2375-1.5375-20.475-8.7-27.65L28.213042 466.4375c-22.0125-22.525-29.1875-55.3-19.45-84.9875 9.725-29.7 35.325-51.2 66.05-55.8125l237.575-36.35c10.75-1.5375 19.4625-8.1875 24.0625-17.925L441.388042 48.125c13.825-29.7 42.5-48.125 75.2625-48.125s61.4375 18.4375 75.2625 48.125l104.45 223.2375c4.6125 9.725 13.825 16.375 24.0625 17.925L958.000542 325.625a82.355 82.355 0 0 1 66.05 55.8125c10.2375 29.7 2.5625 62.4625-19.45 84.9875l-175.625 180.7375c-7.1625 7.175-10.2375 17.925-8.7 27.65l40.9625 251.9c5.125 31.75-8.1875 63.4875-34.3 81.925-26.1125 18.4375-59.9 20.4875-88.0625 4.6125l-206.85-114.6875c-9.725-5.1125-20.9875-5.1125-30.7125 0l-207.3625 115.2c-12.8125 6.65-26.6375 10.2375-40.4625 10.2375zM516.650542 51.2c-12.8 0-23.55 7.1625-29.1875 18.4375L383.525542 292.875c-11.775 25.0875-35.325 43.0125-62.975 47.1l-237.575 36.35c-12.2875 2.05-21.5 9.7375-25.6 21.5-4.1 11.775-1.025 24.0625 7.675 32.775L240.688042 611.325c18.4375 18.95 26.625 45.5625 22.525 71.675L222.250542 934.9125c-2.05 12.8 3.075 24.575 13.3125 31.7375 10.2375 7.175 23.0375 7.6875 33.7875 1.5375l207.3625-115.2c25.0875-13.825 55.3-13.825 80.3875 0l207.3625 115.2c10.75 6.1375 23.55 5.625 33.8-1.5375 10.2375-7.1625 15.3625-18.95 13.3125-31.7375L770.625542 683.0125c-4.1-26.1125 4.1-52.7375 22.525-71.675l175.625-180.7375c8.7-8.7 11.2625-20.9875 7.675-32.775-4.0875-11.775-13.3125-19.9625-25.6-21.5l-237.5625-36.35c-27.65-4.0875-51.2-22.0125-62.975-47.1L545.838042 69.6375c-5.625-11.2625-16.375-18.4375-29.1875-18.4375z m0 0" p-id="3704" fill="#2c2c2c"></path></svg>
                </a>
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
    	    echo '<div class="paper" style="text-align:center;">没有更多了……</div>';
    	}
    	else{
    	    echo '<div class="paper" style="text-align:center;">暂无论文</div>';
    	}

    	?>
   
</div>
<div class="sider">
	<div class="siders">
	
				<div>
				<div class="newsletter-widget text-center align-self-center">
                                <svg t="1616329463006" class="icon" width="29" height="28" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3990" width="200" height="200"><path d="M441.66 783.66c-44.57 0-88.1-8.23-129.47-24.48-44.12-17.33-83.8-43.06-117.95-76.46-34.14-33.4-60.73-72.51-79.03-116.25-17.66-42.22-26.87-86.93-27.38-132.88s7.73-90.85 24.46-133.45c17.33-44.12 43.06-83.8 76.46-117.95 33.4-34.14 72.51-60.73 116.25-79.03 42.22-17.66 86.93-26.87 132.88-27.38 45.93-0.51 90.85 7.73 133.45 24.46 44.12 17.33 83.8 43.06 117.95 76.46 34.14 33.4 60.73 72.51 79.03 116.24 17.66 42.22 26.87 86.93 27.38 132.88 0.5 45.95-7.73 90.85-24.46 133.45-17.33 44.12-43.06 83.8-76.46 117.95-33.4 34.14-72.51 60.73-116.25 79.03-42.22 17.66-86.93 26.87-132.88 27.38-1.33 0.02-2.65 0.03-3.98 0.03z m0.13-637.83c-73.78 0-147.5 28.49-203.05 85.26-109.52 111.94-107.54 292.12 4.4 401.64 54.23 53.05 125.88 81.8 201.73 80.98 75.86-0.83 146.85-31.15 199.91-85.38 53.05-54.23 81.81-125.87 80.98-201.73s-31.16-146.85-85.38-199.9c-55.17-53.97-126.91-80.87-198.59-80.87z" fill="#231815" p-id="3991"></path><path d="M238.81 529.87c-13.44 0-25.45-9.46-28.18-23.14-9.09-45.46-5.95-93.84 9.07-139.9 14.92-45.74 41.33-88.45 76.38-123.51 11.24-11.24 29.45-11.24 40.69-0.01 11.24 11.23 11.24 29.45 0.01 40.69-58.44 58.46-84.5 137.5-69.72 211.44 3.12 15.58-6.99 30.74-22.57 33.86-1.91 0.39-3.81 0.57-5.68 0.57z" fill="#F7B52C" p-id="3992"></path><path d="M860.4 874.25c-8.82 0-17.64-3.32-24.45-9.97l-178.56-174.7c-13.8-13.5-14.04-35.64-0.54-49.44 13.5-13.8 35.64-14.04 49.44-0.54l178.56 174.7c13.8 13.5 14.04 35.64 0.54 49.44a34.9 34.9 0 0 1-24.99 10.51z" fill="#231815" p-id="3993"></path></svg>
                                    	<form action="search.php" method="POST" class="form-inline">
			
            <select name="searchSelect" class="form-control" >
                <option value="0">请选择搜索方式</option>
                <option value="1">按标题</option>
                <option value="2">按编号</option>
                <option value="3">按关键词</option>
                <option value="4">不限</option>
            </select>
            <input type="text"  placeholder=" 请输入检索内容……" id="searchName" name="searchName" required class="form-control" >
            <input type="submit" value="搜索" class="btn btn-default btn-block" style="height:40px;"/>
            </div>
        </form> 
                                          
                </div>
      </div>
        <div class="siders">
        
				<div style="padding:0 5% 2% 5%">
                            <h2 class="widget-title">My Categories</h2>
                            <div class="link-widget">
                                <ul style="list-style:none;margin: 0;padding: 0;">
                                	<?php 
                                	$conn = new mysqli('localhost','root','','paperdb');
                                	$userid=$_SESSION["userid"];
                                	$sql = "select * from paper";
                                	$res = $conn->query($sql);
                                	$result_array = array();
                                	$key_array = array();
                                	if( $res->num_rows >0 ){
                                	    while( $row= $res->fetch_assoc()){
                                	        $pid=$row["pid"];
                                	        $sql2 = "select * from userPaper where userid = '$userid' and pid='$pid' ";
                                	        $res2 = $conn->query($sql2);
                                	        if( $res2->num_rows >0 ){
                                	            $result_array[] = $row["key1"];
                                	            $result_array[] = $row["key2"];
                                	            $result_array[] = $row["key3"];
                                	            $result_array[] = $row["key4"];
                                	            $result_array[] = $row["key5"];
                                	        }
                                	    }
                                	    
                                	}
                                	foreach($result_array as $value){
                                	    if(!empty($value)){
                                	        $value = ucfirst($value);
                                	        if(array_key_exists($value, $key_array)){
                                	            $key_array[$value] += 1;   //+=1
                                	        }
                                	        else{
                                	            $key_array[$value] = 1;   //=1
                                	        }
                                	    }
                                	}
                                	arsort($key_array);
                                	$top_key_array = array_keys($key_array);
                                	$top_value_array = array_values($key_array);
                                	$len=10;
                                	if(sizeof($top_key_array)<10)
                                	    $len=sizeof($top_key_array);
                                	for($i=0;$i<$len;$i++)
                                	    echo '
                                        <li><a href="" onclick="keyButton(this)" id="'.$top_key_array[$i].'">'.$top_key_array[$i].'<span>('.$top_value_array[$i].')</span></a></li>
                                           ';
                                	?>

                                </ul>
                            </div>
                </div>           
	</div>   
</div>

</body>

<script>
function deleteButton(e){
	window.event.returnValue=false;
	var r=confirm("您确定删除这篇论文？");
	if (r==false){
		return;
	}
	var pid=e.id;
	window.location.href="../form/delete.php? pid="+pid;
}
function remarkButton(e){
	window.event.returnValue=false;
	var str=prompt("请输入备注：");
	if(str==""){
	    return;
	}
	var pid=e.id;
	var userid='<?php echo $userid;?>';

	$.ajax({
                        type: "POST",
                        url: "../form/remark.php",
                        data: "remark" + str+ "pid"+pid+ "userid"+userid ,
                        dataType : 'JSON',
                        success: function(res) {
                            
                        }
                    });
	
	//window.location.href="../form/delete.php? pid="+pid;
}
function keyButton(e){
	window.event.returnValue=false;
	var x=e.id;
	window.location.href = window.location.href="search.php? searchName="+x+"&searchSelect=3";
}
</script>