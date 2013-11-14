<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/content.css" />
<script type="text/javascript" src="js/sly/jquery.min.js"></script>

<script type="text/javascript" src="js/sly/plugins.js"></script>
<script type="text/javascript" src="js/sly/sly.min.js"></script>
<script type="text/javascript" src="js/sly/fullpage.js"></script>
<title>首页_知识库</title>
<script type="text/javascript">
function showDetails(id){
	window.location.href="<%=path%>/getSolutionById.do?id="+id;
}
</script>
</head>


<body>
<div id="main" class="welcome">
	<div id="scrollbar" class="scrollbar">
		<div class="handle">
		</div>
	</div>
	
	<div id="frame" class="frame" style="overflow:hidden;"><div>
		<div class="q_title"><h2>知识库 — 常见问题</h2>	
		   	<div class="clear"></div>
		   </div>
		   
		   <div class="question_cloud clearfix">
		   <c:forEach var="solution" items="${rankingList }">
		   	<a href="javascript:showDetails('${solution.id}')">${solution.title }</a>
		   </c:forEach>
		   </div>
		   
		   <div class="q_title"><h2>系统公告</h2>
		   	<div class="clear"></div>
		   </div>
		   <ol class="notice_ol">
		   	 <c:forEach var="notice" items="${noticeList }" begin="0" end="5">
		    	<li><h4 class="log-title">${notice.title }</h4>
		    	${notice.content }</li>
		    </c:forEach>
		   </ol>
		   <div class="q_title"><h2>相关文件下载</h2>
		   	<div class="clear"></div>
		   </div>
		   <ol class="notice_ol">
		   	 <c:forEach var="file" items="${uploadFileList }" begin="0" end="5">
		    	<li class="downfile"><p>${file.file_desc }</p>
		    		<a href="downLoadUploadFile.do?id=${file.id }" class="link-file"><i></i><span>${file.file_name }</span></a>
		    	</li>
		    </c:forEach>
		   </ol>
		   
		   <div class="q_title"><h2>平台更新内容</h2>
		   	<div class="clear"></div>
		   </div>
		   <ol class="notice_ol">
		   	<li>增强了搜索功能，支持多关键字搜索（多关键字用空格隔开）</li>
		       <li>增加了富文本框</li>
		       <li>添加了评论模块，用户可以针对问题发表自己的见解</li>
		   </ol>
	</div></div>
</div>
</body>
</html>