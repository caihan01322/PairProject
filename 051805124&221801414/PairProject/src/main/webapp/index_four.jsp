<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>背景知识</title>
    <link rel="stylesheet" type="text/css" href="css/indexmain.css"/>
</head>
<body>
<div>
    <div class="head">
        <div class="head1">
            <h1>论文信息平台</h1>
        </div>
        <div class="head2">
        	<a><label>用户名</label></a>
        	<a><img src="./ImageResources/user.png"/></a>
        	<a><img src="./ImageResources/question.png"/></a>
        	<a><img src="./ImageResources/bell.png" /></a>
        	<a><img src="./ImageResources/link.png" /></a>
            <a><img src="./ImageResources/redo.png" /></a>
        </div>
    </div>
</div>
<div class="content">
    <div>
        <div class="contentleft">
            <ul>
                 <li class="icon"><a href="index_one_add.jsp"><i><img src="./ImageResources/folderadd.png"></i>论文爬取</a></li>
                 <li class="icon"><a href="index_two_edit.jsp"><i><img src="./ImageResources/database.png"></i>论文管理</a></li>
                 <li class="icon"><a href="DoPaperPie?"><i><img src="./ImageResources/fund.png"></i>论文分析</a></li>
                 <li class="icon"><a href="index_four.jsp"><i><img src="./ImageResources/read.png"></i>背景知识</a></li>
            </ul>
        </div>
    </div>
    <div>
        <div class="contentright">
            <p>CVPR</p> <img class="imgCVPR" src="./ImageResources/cvpr.PNG"  alt="cvpr" />
            <p>国际计算机视觉与模式识别会议（CVPR）是IEEE一年一度的学术性会议，
            会议的主要内容是计算机视觉与模式识别技术。CVPR是世界顶级的计算机视 
            觉会议（三大顶会之一，另外两个是ICCV和ECCV），近年来每年有约1500名 
            参加者，收录的论文数量一般300篇左右。本会议每年都会有固定的研讨主题 
            ，而每一年都会有公司赞助该会议并获得在会场展示的机会。</p>
           
            <p>ICCV</p> <img class="imgICCV" src="./ImageResources/ICCV2021_logox.jpg"  alt="iccv" />
            <p>ICCV 的全称是 IEEE International Conference on Computer Vision，
            即国际计算机视觉大会，由IEEE主办，与计算机视觉模式识别会议（CVPR）和欧
            洲计算机视觉会议（ECCV）并称计算机视觉方向的三大顶级会议，被澳大利亚ICT
            学术会议排名和中国计算机学会等机构评为最高级别学术会议，在业内具有极高的
            评价。不同于在美国每年召开一次的CVPR和只在欧洲召开的ECCV，ICCV在世界范围
            内每两年召开一次。ICCV论文录用率非常低，是三大会议中公认级别最高的。</p>
            
            <p>ECCV</p> <img class="imgECCV" src="./ImageResources/eccv.png"  alt="eccv" />
            <p>ECCV的全称是European Conference on Computer Vision(欧洲计算机视觉
            国际会议) ，两年一次，是计算机视觉三大会议（另外两个是ICCV和CVPR）之一。
            每次会议在全球范围录用论文300篇左右，主要的录用论文都来自美国、欧洲等顶
            尖实验室及研究所，中国大陆的论文数量一般在10-20篇之间。ECCV2010的论文录
            取率为27%。</p>
            
        </div>
    </div>
</div>
<div>
	<div class="footer">
		<label>Copyright © 2021 </label>
	</div>
	<div class="footer">
		<label>Powered by .NET 5.0 on Kubernetes & Theme Silence v3.0.0</label>
	</div>
</div>
</body>
</html>