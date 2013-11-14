<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Global Error</title>



</head>
<body>
<center><h1>出错了</h1></center>
<p>错误信息：</p>
<%    
Exception e = (Exception)request.getAttribute("exception");    
out.print(e.getMessage());    
%>    
</body>
</html>