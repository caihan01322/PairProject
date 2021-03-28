<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <title>论文查找</title>
    <link rel="stylesheet" type="text/css" href="css/indexmain.css"/>
    <link rel="stylesheet" type="text/css" href="css/table.css"/>
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
                 <li class="icon">
                 	<div><a href="index.jsp"><i><img src="./ImageResources/database.png"></i>论文管理</a></div>
                 	<div class="titleright"><a href="index_two_search.jsp">论文查找</a></div>
                 </li>
                 <li class="icon"><a href="DoPaperPie?"><i><img src="./ImageResources/fund.png"></i>论文分析</a></li>
                 <li class="icon"><a href="index_four.jsp"><i><img src="./ImageResources/read.png"></i>背景知识</a></li>
            </ul>
        </div>
    </div>
    <div class="contentright">
    	<div>
    		<form action="dopapersearch" method="get">
    			<select name="plugin">
  					<option value ="title">题目</option>
  					<option value="conclude">摘要</option>
  					<option value ="keyword">关键词</option>
				</select>
	            <input class="searchInput" type="text" name="searchContent" placeholder="请输入论文题目，关键词等" size="40" />
	            <input class="searchBtn" id="button" type="submit" value="搜索"/>
            </form>
    	</div>
    	
    	<table style="border-bottom: 2px solid #666; border-collapse: collapse; margin-top: 10px;width:100%;display: ${paperlist.size()==0?none:block};">
    		<tr>
    			<th class="td">标题</th>
    			<th class="td">关键词</th>
    			<th class="td">摘要</th>
    			<th class="td">原文地址</th>
    			<th class="td">操作</th>
    		</tr>
    		
    		<c:forEach var="u" items="${paperlist}">
    			<tr style="border-bottom: 2px solid #666; height: 50px;">
    				<td class="td"><div class="tdcontent">${u.title}</div></td>
	    			<td class="td"><div class="tdcontent">${u.keyword}</div></td>
	    			<td class="td"><div class="tdcontent">${u.abst}</div></td>
	    			<td class="td"><div class="tdcontent"><a href="${u.link}">${u.link}</a></div></td>
	    			<td class="td">
	    				<div class="tdcontent">
	    					<a class="button" href="dopaperdetail?id=${u.paperNum }">查看</a>
	    					<a class="button" href="javascript:Delete('你确定要删除该项吗？','dopaperdelete?id=${u.paperNum }&cp=${curPage}&content=${searchContent}')">删除</a>
	    				</div>
	    			</td>
    			</tr>
    			<span style="height: 2px; background-color: #666;"></span>
    		</c:forEach>
    	</table>
    	
    	<script type="text/javascript">
    		function Delete(mess, url) {
    			if(confirm(mess )) {
    				window.location.href=url;
    			}
    		}
    		
    	</script>
    	
    	<div class="tableFooter" >
    		共 ${totalNum} 条记录，当前 ${totalPage == 0?0:curPage} / ${totalPage} 页
    		<a href="dopapersearch?cp=1">首页</a>	
    		<a href="dopapersearch?cp=${curPage-1>0?curPage-1:1 }">上一页</a>	
    		<a href="dopapersearch?cp=${curPage+1>totalPage?totalPage:curPage+1 }">下一页</a>	
    		<a href="dopapersearch?cp=${totalPage}">尾页</a>	
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