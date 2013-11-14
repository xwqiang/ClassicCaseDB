<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/common/kindEditor.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新建角色_角色管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">

</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-role">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>新建角色</h2>
		   	<div class="clear"></div>
		</div>
		<form action="insertRole.do" method="post" onsubmit="javascript:return true;">
			<div class="f-item">
				<div class="f-left">角色名称：</div>
				<div class="f-cont"><input type="text" name="role_name" id="role_name" class="ipt"/></div>
			</div>
			<div class="f-item f-footer">
				<input type="submit" value="保存" class="btn"/>
				<input type="button" value="取消" class="btn" onclick="javascript :history.back(-1);" />				
			</div>
		</form>
		
	</div>
</div>
</body>
</html>
