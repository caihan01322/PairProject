<?php session_start(); ?>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0,user-scalable=no,minimal-ui">
    <title>论文导入</title>
    <link rel='stylesheet' href='../css/myCss.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss2.css' type='text/css'/>
    <link rel='stylesheet' href='../css/myCss4.css' type='text/css'/>
    
    <script>
        function findFile(){
        	var inputObj=document.createElement('input')
            inputObj.setAttribute('id','_ef');
            inputObj.setAttribute('type','file');
            inputObj.setAttribute("style",'visibility:hidden');
            document.body.appendChild(inputObj);
            inputObj.click();
            inputObj.value ;
        }
    </script>
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
                    <li class="menu-item"><a href="../index.php">首页</a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="import.php"><b style="font-size: larger">论文导入</b></a></li>
                    <li class="menu-item"><a data-id="37" data-adid="41" href="manage.php">论文管理</a></li>
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
                <div class="metabarItem u-flex u-paddingLeft20 dropdown" >
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
                    <div class="dropdown-content">
    				<a href="">个人中心</a><br/>
    				<a onclick="exitLogin()">退出登录</a>
  					</div>
                </div>
            </div>
        </div>
    </header>
</div>

<div id="import_container">
    <div id="search_img_wrap">
        <svg t="1616296868325" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2105" xmlns:xlink="http://www.w3.org/1999/xlink" width="200" height="200"><defs><style type="text/css"></style></defs><path d="M510.81 288a224.45 224.45 0 1 0 158.71 65.74A222.93 222.93 0 0 0 510.81 288z m0 384.9c-88.48 0-160.45-72-160.45-160.45S422.33 352 510.81 352s160.45 72 160.45 160.45S599.28 672.9 510.81 672.9z" p-id="2106" fill="#515151"></path><path d="M893.36 350.83a415.59 415.59 0 1 0-163.74 514.54l-46.81-46.81a349.1 349.1 0 0 1-172 45.08c-193.65 0-351.2-157.54-351.2-351.19s157.55-351.2 351.2-351.2S862 318.8 862 512.45a349 349 0 0 1-43.89 169.87L865 729.21a416 416 0 0 0 28.35-378.38z" p-id="2107" fill="#515151"></path><path d="M690.616 735.875l45.254-45.254 157.204 157.204-45.254 45.254z" p-id="2108" fill="#515151"></path></svg>
    </div>
	<div id="search_container">
		<form id="search_form" action="#" method="post">
            <svg onclick="findFile()" t="1616297592638" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4952" data-spm-anchor-id="a313x.7781069.0.i4" width="40" height="40"><path d="M938.3936 464.896l-158.72 300.35968c-9.96352 21.0944-30.14656 29.16352-39.40352 29.16352l-612.00384 0.13312c-24.77056 0-44.92288-20.15232-44.92288-44.92288l0.77824-385.65888L83.74272 250.05056c-0.03072-5.45792 2.10944-10.69056 5.94944-14.56128 3.85024-3.87072 9.08288-6.0416 14.53056-6.0416l159.1808 0c8.4992 0 16.10752 5.25312 19.13856 13.18912l29.11232 76.41088 174.17216 0c-17.23392 11.264-33.49504 24.96512-48.85504 40.96L297.54368 360.00768c-8.4992 0-16.10752-5.25312-19.13856-13.18912l-29.11232-76.41088L124.29312 270.40768l0.02048 93.44 0 385.78176c0 2.18112 1.78176 3.96288 3.96288 3.96288l48.11776 0L319.09888 464.896c8.05888-16.14848 23.28576-29.16352 39.40352-29.16352l22.74304 0 170.26048 0 35.26656 0c-23.71584 17.29536-33.9968 48.93696-22.97856 77.6192 9.82016 25.53856 34.78528 42.69056 62.12608 42.69056 8.16128 0 16.1792-1.49504 23.8592-4.43392l179.24096-68.84352c21.3504-8.20224 36.05504-26.28608 40.92928-47.03232l39.28064 0C925.3376 435.74272 947.87584 446.6176 938.3936 464.896zM438.91712 409.6l91.8528 0c0.80896 0 1.50528 0.17408 2.28352 0.22528 17.32608-15.88224 35.77856-27.22816 55.296-33.4848 56.33024-18.05312 112.85504 6.8608 145.12128 26.112l-107.48928 41.27744c-18.4832 7.09632-27.70944 27.83232-20.61312 46.30528 5.4784 14.25408 19.05664 22.99904 33.46432 22.99904 4.28032 0 8.62208-0.768 12.8512-2.38592l179.23072-68.84352c18.4832-7.09632 27.70944-27.83232 20.61312-46.30528L782.68416 216.2688c-7.08608-18.4832-27.8528-27.67872-46.30528-20.61312-18.4832 7.09632-27.6992 27.83232-20.60288 46.30528l33.54624 87.3472c-45.13792-22.81472-111.77984-43.73504-182.272-21.42208C517.77536 323.52256 475.00288 357.55008 438.91712 409.6z" p-id="4953" fill="#686f72" data-spm-anchor-id="a313x.7781069.0.i5" class="selected"></path></svg>			<input type="text" name="condition" placeholder="请输入搜索条件">
		    <input type="submit" value="搜索" >
		</form>	
	</div>
</div>

</body>
</html>

<<script type="text/javascript">
	function exitLogin(){
	window.event.returnValue=false;     
	window.location.href="../form/exit.php";
}
</script>
