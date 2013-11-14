<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公告管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
function deleteNotice(id){
	var flag = window.confirm("确定要删除吗？");
	if(flag){
		window.location.href="deleteNotice.do?id="+id;
	}
}
</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-notice">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>公告管理</h2>
		   	<div class="clear"></div>
		</div>
		<div class="ope-box">
			<div class="ope-item"><a href="showInsertNotice.do" class="btn">新建公告</a></div>
		</div>
		
		<ol class="notice-box">
			<c:forEach items="${noticeList }" var="each">
			<li>	
				<h3>${each.title }<span>${each.operator_id }</span></h3>
				<div class="notice-cont">${each.content }</div>
				<div class="notice-footer">${each.update_time }
					<a href="showUpdateNotice.do?id=${each.id }" title="修改" class="btn">修改</a>
					<a href="javascript:deleteNotice('${each.id}');" title="删除" class="btn">删除</a>
				</div>
			</li>
			</c:forEach>
		</ol>
		<jsp:include page="/common/pager.jsp"></jsp:include>
	</div>
</div>
</body>
</html>
