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
                    <li class="menu-item"><a  href="../index.php">首页</a></li>
                    <li class="menu-item"><a   data-id="37" data-adid="41" href="import.php">论文导入</a></li>
                    <li class="menu-item"><a   data-id="37" data-adid="41" href="manage.php"><b style="font-size: larger">论文管理</b></a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="analysis.php">动态分析</a></li>
                </ul>
            </nav>
            <div class="metabar__right u-flex">
                <div class="metabarItem u-flex"><a  class="u-flex" title="分享网站链接" onclick="copyLink()">
                    <svg t="1616220697148" class="icon" width="25" height="24"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2435" width="200" height="200"><path d="M522.24 760.32c-15.36 0-25.6-10.24-25.6-25.6V448c0-15.36 10.24-25.6 25.6-25.6s25.6 10.24 25.6 25.6v289.28c0 12.8-12.8 23.04-25.6 23.04z" fill="#333333" p-id="2436"></path><path d="M601.6 545.28c-7.68 0-12.8-2.56-17.92-7.68l-61.44-61.44L460.8 537.6c-10.24 10.24-25.6 10.24-35.84 0s-10.24-25.6 0-35.84l79.36-79.36c10.24-10.24 25.6-10.24 35.84 0l79.36 79.36c10.24 10.24 10.24 25.6 0 35.84-5.12 5.12-10.24 7.68-17.92 7.68z" fill="#333333" p-id="2437"></path><path d="M755.2 780.8h-151.04c-15.36 0-25.6-10.24-25.6-25.6s10.24-25.6 25.6-25.6h151.04c79.36 0 143.36-64 143.36-143.36s-64-143.36-143.36-143.36c-23.04 0-48.64 5.12-69.12 17.92-7.68 5.12-17.92 5.12-25.6 0-7.68-5.12-12.8-12.8-12.8-23.04 0-71.68-56.32-128-128-128-58.88 0-110.08 40.96-125.44 97.28-2.56 7.68-5.12 12.8-12.8 15.36-5.12 2.56-12.8 5.12-20.48 2.56-10.24-2.56-20.48-5.12-28.16-5.12-61.44 0-110.08 48.64-110.08 110.08 0 10.24-7.68 20.48-17.92 23.04-35.84 12.8-58.88 46.08-58.88 81.92 0 48.64 38.4 87.04 87.04 87.04h168.96c15.36 0 25.6 10.24 25.6 25.6s-10.24 25.6-25.6 25.6H243.2h-2.56c-79.36 5.12-145.92-58.88-145.92-138.24 0-53.76 30.72-102.4 76.8-125.44 7.68-81.92 76.8-145.92 161.28-145.92h17.92c25.6-69.12 92.16-115.2 166.4-115.2 87.04 0 158.72 61.44 176.64 143.36 17.92-5.12 38.4-10.24 58.88-10.24 107.52 0 194.56 87.04 194.56 194.56 0 117.76-87.04 204.8-192 204.8z" fill="#707070" p-id="2438"></path></svg>
                    <span style="color:#707070 ">分享网站</span></a>
                </div>
                <div class="metabarItem u-flex u-cursorPointer" v-on:click="switchSearch"><a href="collect.php" class="u-flex" title="进入收藏夹" >
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

		
        
<div class="mainPaper">
		<div class="paper" style="padding-top:0;padding-bottom:0">
		<ol class="breadcrumb hidden-xs-down" style="margin:0">
            <li class="breadcrumb-item active">本地论文列表</li>
            <li class="breadcrumb-item active" style="float:right">共 <span id="resultNum"></span> 篇论文</li>
        </ol>
		</div>
    	<?php 
    	$conn = new mysqli('localhost','root','','paperdb');
    	$conn->query("SET NAMES utf8");
    	if(!isset($_SESSION["userid"])){
    	    echo '<script>alert("请先登录！");window.location.href="../view/login.php";</script>';
    	}
    	$userid=$_SESSION["userid"];
    	$sql = "select * from userPaper where userid = '$userid' ORDER BY pid";
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
    	            <svg t="1616329101167" class="icon" width="20" height="19"  viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2155" width="200" height="200"><path d="M791.04 188.373333l45.226667 45.226667-306.986667 306.986667-72.96 27.733333 27.733333-72.96L791.04 188.373333m0-81.706666a21.333333 21.333333 0 0 0-15.146667 6.186666L432.213333 456.533333a21.333333 21.333333 0 0 0-4.906666 7.466667l-64 170.666667A21.333333 21.333333 0 0 0 390.4 661.333333l170.666667-64a21.333333 21.333333 0 0 0 7.466666-4.906666l343.253334-343.68a21.333333 21.333333 0 0 0 0-30.08l-105.6-105.813334a21.333333 21.333333 0 0 0-15.146667-6.186666z" fill="#333333" p-id="2156"></path><path d="M789.333333 523.946667v330.666666H170.666667v-618.666666h330.666666a32 32 0 0 0 32-32 32 32 0 0 0-32-32H128a21.333333 21.333333 0 0 0-21.333333 21.333333v704a21.333333 21.333333 0 0 0 21.333333 21.333333h704a21.333333 21.333333 0 0 0 21.333333-21.333333v-373.333333a32 32 0 0 0-32-32 32 32 0 0 0-32 32z" fill="#333333" p-id="2157"></path></svg>
        	         </a>  

                  <a href=""  style="float:right" onclick="deleteButton(this)" id="'.$pid.'" >
                    <svg t="1616311672560" class="icon" width="30" height="29" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2138" width="200" height="200"><path d="M799.2 874.4c0 34.4-28 62.4-62.368 62.4H287.2a62.496 62.496 0 0 1-62.4-62.4V212h574.4v662.4zM349.6 100c0-7.2 5.6-12.8 12.8-12.8h300c7.2 0 12.768 5.6 12.768 12.8v37.6H349.6V100z m636.8 37.6H749.6V100c0-48-39.2-87.2-87.2-87.2h-300a87.392 87.392 0 0 0-87.2 87.2v37.6H37.6C16.8 137.6 0 154.4 0 175.2s16.8 37.6 37.6 37.6h112v661.6A137.6 137.6 0 0 0 287.2 1012h449.6a137.6 137.6 0 0 0 137.6-137.6V212h112c20.8 0 37.6-16.8 37.6-37.6s-16.8-36.8-37.6-36.8zM512 824c20.8 0 37.6-16.8 37.6-37.6v-400c0-20.8-16.768-37.6-37.6-37.6-20.8 0-37.6 16.8-37.6 37.6v400c0 20.8 16.8 37.6 37.6 37.6m-175.2 0c20.8 0 37.6-16.8 37.6-37.6v-400c0-20.8-16.8-37.6-37.6-37.6s-37.6 16.8-37.6 37.6v400c0.8 20.8 17.6 37.6 37.6 37.6m350.4 0c20.8 0 37.632-16.8 37.632-37.6v-400c0-20.8-16.8-37.6-37.632-37.6-20.768 0-37.6 16.8-37.6 37.6v400c0 20.8 16.8 37.6 37.6 37.6" p-id="2139"></path></svg>
                </a> 
  
                    <a href=""  style="float:right;" onclick="checkButton(this)" id="'.$pid.'">
                      <svg t="1616548870602" class="icon" width="30" id="'.$row2["meeting"].'" height="29" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2787" width="200" height="200"><path d="M607.839811 895.957102H214.69447A86.82497 86.82497 0 0 1 127.880338 809.070721V214.784781A86.839419 86.839419 0 0 1 214.69447 127.880338h594.28594a86.33729 86.33729 0 0 1 61.436749 25.456857c16.400473 16.400473 25.362934 38.208767 25.373771 61.411462L894.439878 607.821749v0.10476a32.031496 32.031496 0 0 0 64.059379 0.101149L959.825022 214.889542v-0.104761A150.775976 150.775976 0 0 0 808.98041 63.940169H214.69447A150.638703 150.638703 0 0 0 63.940169 214.784781v594.28594a150.638703 150.638703 0 0 0 150.757914 150.82655h393.141728a31.970084 31.970084 0 0 0 0-63.940169z" fill="" p-id="2788"></path><path d="M950.544667 905.331381l-122.071536-122.071536a192.217875 192.217875 0 1 0-45.213286 45.213286l122.071536 122.071536a31.970084 31.970084 0 0 0 45.213286-45.213286z m-278.547941-105.302594a128.028448 128.028448 0 1 1 90.531332-37.497116 127.193975 127.193975 0 0 1-90.527719 37.497116zM768.004516 352.212795c17.653989 0 31.966472-14.402794 31.970084-32.056783s-14.308871-32.074845-31.966472-32.078457L256.002709 287.911382a32.020659 32.020659 0 0 0-31.970084 32.024271c0 17.657601 14.308871 32.092907 31.966472 32.092908L768.004516 352.212795zM448.000226 544.033302a31.96286 31.96286 0 1 0 0-63.940169h-192.001129a31.937573 31.937573 0 1 0 0 63.878758l192.001129 0.061411zM256.017159 671.91364a31.959247 31.959247 0 1 0 0 63.922107l127.999549 0.018062a31.941185 31.941185 0 1 0 0-63.878757z" fill="" p-id="2789"></path></svg>
                    &nbsp;</a>    
                ';
                        
                if($row["collect"]==0){
                    echo ' 
                    <a href="" style="float:right" onclick="addCollect(this)" id="'.$pid.'">
                <svg t="1616588418305" class="icon" viewBox="0 0 1024 1024" width="28" height="27" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2572" width="200" height="200"><path d="M773.458 1005.43c-14.403 0-30.592-4.132-45.599-11.638L513.567 886.61 297.948 993.618c-13.655 7.27-29.286 11.09-45.225 11.09a96.164 96.164 0 0 1-57.549-18.842c-30.177-22.64-45.64-61.85-38.502-97.561l44.099-228.772L31.744 501.996a104.059 104.059 0 0 1-26.281-100.66l0.338-1.162c12.288-36.828 42.772-62.633 79.734-67.584l234.839-42.7L425.779 75.811c16.932-33.895 51.37-55.675 87.788-55.675 37.99 0 73.318 22.6 88.13 56.29l105.068 213.4L941.64 330.89a95.672 95.672 0 0 1 78.264 68.373 98.97 98.97 0 0 1-24.474 101.002l-0.578 0.579-168.55 158.72L868.9 888.745c6.86 36.649-7.716 73.672-38.062 96.742a90.409 90.409 0 0 1-57.38 19.943z m-259.81-196.055l227.667 113.853c6.686 3.37 14.392 5.489 19.665 5.489 8.116 0 15.652-2.499 21.187-7.117l1.03-0.85c12.937-9.707 19.245-25.134 16.414-40.238l-45.814-246.804 181.212-170.65c11.126-11.28 14.93-27.433 9.995-42.286l-0.497-1.562a37.31 37.31 0 0 0-30.971-27.003l-1.034-0.163L661.76 348.21 548.91 118.99c-5.478-12.805-20.347-22.098-35.359-22.098-14.75 0-28.861 9.236-36.009 23.496L365.414 348.109l-252.062 45.788c-15.31 1.92-27.203 12.088-32.697 27.945-3.901 14.49 0.722 31.949 11.372 42.834l181.422 169.057-47.61 246.933c-2.863 14.28 3.9 30.714 16.409 40.1 6.272 4.715 14.397 7.316 22.917 7.316 6.569 0 12.866-1.531 18.294-4.45l0.783-0.46 229.407-113.797z" fill="#2c2c2c" p-id="2573"></path></svg>
                &nbsp;</a>';
                }
                else{
                    echo'
                    <a href="" style="float:right" onclick="deleteCollect(this)" id="'.$pid.'">
                    <svg t="1616588825145" class="icon" viewBox="0 0 1024 1024" width="28" height="27" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5641" width="200" height="200"><path d="M1004.125 382.534c-4.282-13.103-17.943-36.003-61.58-42.357l-173.66-25.227c-42.683-6.214-94.93-44.149-114.06-82.874l-77.661-157.37c-19.526-39.564-45.522-45.475-59.3-45.475s-39.772 5.91-59.298 45.474l-77.685 157.37c-19.106 38.703-71.33 76.661-114.036 82.875L93.184 340.177c-43.66 6.33-57.32 29.254-61.58 42.357-4.235 13.102-6.679 39.657 24.925 70.446l125.673 122.485c30.906 30.138 50.851 91.531 43.543 134.074l-29.649 172.963c-5.748 33.466 3.514 52.922 12.311 63.371 17.874 21.179 49.897 24.088 83.642 6.377l155.3-81.664c36.258-19.014 104.773-19.037 141.032 0l155.346 81.664c14.987 7.866 29.277 11.87 42.519 11.87 16.547 0 31.139-6.47 41.076-18.247 8.797-10.426 18.06-29.905 12.335-63.348l-29.65-172.986c-7.284-42.52 12.637-103.913 43.544-134.051l125.672-122.484c31.581-30.813 29.138-57.368 24.902-70.47z" fill="#ffacac" p-id="5642"></path></svg>
                    &nbsp;</a>';
                }
               echo'

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
   	<script type="text/javascript">
   		var n='<?php echo $resultNum;?>';
   		document.getElementById("resultNum").innerHTML=n;
   	</script>
</div>
<div class="sider">
	<div class="siders">
	
				<div>
				<div class="newsletter-widget text-center align-self-center">
                                <svg t="1616661905005" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="11611" width="75" height="75" style="margin-bottom: 30px"><path d="M428.6 2C193.5 2 2.2 193.3 2.2 428.4s191.3 426.4 426.4 426.4S855 663.5 855 428.4 663.8 2 428.6 2z m0 823.4c-218.9 0-397-178.1-397-397s178.1-397 397-397 397 178.1 397 397-178 397-397 397z" fill="#333234" p-id="11612"></path><path d="M447 751.9c-170.3 0-308.8-145.1-308.8-323.5 0-8.1-6.6-14.7-14.7-14.7s-14.7 6.6-14.7 14.7c0 194.6 151.7 352.9 338.2 352.9 8.1 0 14.7-6.6 14.7-14.7s-6.6-14.7-14.7-14.7z" fill="#333234" p-id="11613"></path><path d="M527.9 733.5m-16.5 0a16.5 16.5 0 1 0 33 0 16.5 16.5 0 1 0-33 0Z" fill="#333234" p-id="11614"></path><path d="M1019.7 998.6L810.2 796.5c-5.9-5.6-15.2-5.5-20.8 0.4-5.7 5.8-5.5 15.1 0.3 20.8l209.5 202.2c2.9 2.8 6.5 4.1 10.2 4.1 3.8 0 7.7-1.5 10.6-4.5 5.7-5.9 5.5-15.2-0.3-20.9z" fill="#333234" p-id="11615"></path></svg>
                                    	<form action="search.php" method="POST" class="form-inline">
			                                    <span style="float:left"><input type="radio" name="searching" value="1" checked>本地搜索</span>
                                    <span style="float:right"><input type="radio" name="searching" value="2" >论文库搜索</span>
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
                                        <li><a href="" onclick="keyButton1(this)" id="'.$top_key_array[$i].'">'.$top_key_array[$i].'<span>('.$top_value_array[$i].')</span></a></li>
                                           ';
                                	?>

                                </ul>
                            </div>
                </div>           
	</div>   
</div>
<div style="height:40px;float:left;width:100%;magin:0;padding:20px 0;text-align:center;font:"黑体">
	<small>Copyright ©2020-2021 - XXL&XXY</small> 
</div>

</body>
<script type="text/javascript">
function remarkButton(e){
	window.event.returnValue=false;
	if (window.event.preventDefault) window.event.preventDefault();
	var str=prompt("请输入备注：");
	if (str==null||str==false||str==""){
		return;
	}
	var pid=e.id;
	var userid='<?php echo $userid;?>';
	window.location.href="../form/remark.php? pid="+pid+"&userid="+userid+"&remark="+str+"&view=1";
}
function deleteCollect(e){
	window.event.returnValue=false;
	var pid=e.id;
	var r=confirm("您确定取消收藏这篇论文？");
	if (r==false){
		return;
	}
	var userid='<?php echo $userid;?>';
	window.location.href="../form/collected.php? pid="+pid+"&userid="+userid+"&c=0"+"&view=1";
}
function addCollect(e){
	window.event.returnValue=false;
	var pid=e.id;
	var userid='<?php echo $userid;?>';
	window.location.href="../form/collected.php? pid="+pid+"&userid="+userid+"&c=1"+"&view=1";
}
function deleteButton(e){
	window.event.returnValue=false;
	var r=confirm("您确定删除这篇论文？");
	if (r==false){
		return;
	}
	var pid=e.id;
	window.location.href="../form/delete.php? pid="+pid;
}
function keyButton(e){
	window.event.returnValue=false;                    
    if (window.event.preventDefault) window.event.preventDefault();
	var x=e.id;
	window.location.href="search.php? searchName="+x+"&searchSelect=3"+"&searching=2";
}
function keyButton1(e){
	window.event.returnValue=false;                    
    if (window.event.preventDefault) window.event.preventDefault();
	var x=e.id;
	window.location.href="search.php? searchName="+x+"&searchSelect=3"+"&searching=1";
}
function checkButton(e){
	window.event.returnValue=false;                    
    if (window.event.preventDefault) window.event.preventDefault();
	var pid=e.id;
	var meet=e.childNodes.item(1).id;
	if(meet=="ECCV")
		window.location.href="check.php? pid="+pid+"&view=1";
	else
		alert("抱歉！暂无版权查看！");
}
function exitLogin(){
	window.event.returnValue=false;     
	window.location.href="../form/exit.php";
}
function copyLink(){
	window.event.returnValue=false;if (window.event.preventDefault) window.event.preventDefault();
	if (!!window.ActiveXObject || "ActiveXObject" in window){
		window.clipboardData.setData("text",'http://222.77.0.199:8090/');
		alert("已复制网址http://222.77.0.199:8090/至剪切板");
	}
  	else{
  		alert("当前浏览器暂不支持改功能！");
	}
}
</script>