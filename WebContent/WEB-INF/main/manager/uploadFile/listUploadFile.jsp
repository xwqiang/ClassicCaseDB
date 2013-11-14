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
<title>文件管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-file">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>文件管理</h2>
		   	<div class="clear"></div>
		</div>
		<div class="ope-box">
			<div class="ope-item"><a href="showUploadFile.do" class="btn">上传文件</a></div>
		</div>
		<table class="tablestyle" width="100%" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th width="200">文件说明</th>
					<th width="140">文件名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				 <c:forEach items="${uploadFileList }" var="each">
					<tr>
						<td>${each.file_desc }</td>
						<td nowrap="nowrap">${each.file_name }</td>
						<td>
							<a href="downLoadUploadFile.do?id=${each.id }" title="下载" class="td-btn">下载</a>
							<a href="deleteUploadFile.do?id=${each.id }" title="删除" class="td-btn">删除</a>
					  	</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>
