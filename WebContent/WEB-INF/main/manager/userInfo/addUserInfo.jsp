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
<title>新建人员_人员管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
function beforeSubmit(){
	if($.trim($("#user_id").val())==""){
		alert("请填写用户ID后提交！");
		return false;
	}
	if($.trim($("#user_name").val())==""){
		alert("请填写姓名！");
		return false;
	}
	if($.trim($("#user_pwd").val())==""){
		alert("请输入密码！");
		return false;
	}
	return true;
}

</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-user">
	<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>新建用户</h2>
		   	<div class="clear"></div>
		</div>
		<form action="insertUserInfo.do" method="post" onsubmit="javascript:return beforeSubmit();">
			<div class="f-item">
				<div class="f-left">用户ID：</div>
				<div class="f-cont"><input type="text" name="user_id" id="user_id" class="ipt" /></div>
			</div>
			<div class="f-item">
				<div class="f-left">姓名：</div>
				<div class="f-cont"><input type="text" name="user_name" id="user_name" class="ipt" /></div>
			</div>
			<div class="f-item">
				<div class="f-left">密码：</div>
				<div class="f-cont"><input type="password" name="user_pwd" id="user_pwd" class="ipt" /></div>
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
