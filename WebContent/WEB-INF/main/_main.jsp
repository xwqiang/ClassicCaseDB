<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>



</head>
<body onload="func()">
<h1>正在打开。。。。</h1>
<script>
function func(){
	window.location.href="<%=path%>/loginController/redirect.do";
}
</script>
</body>
</html>