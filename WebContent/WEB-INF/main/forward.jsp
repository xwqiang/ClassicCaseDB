<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<base href="<%=basePath %>"/>
<script type="text/javascript">
<c:if test="${not empty message }">
	alert("${message}");
</c:if>


<c:if test="${not empty url }">
	window.location.href='${url}';
</c:if>
<c:if test="${empty url }">
	history.back();
</c:if>
</script>
</head>
<body>
</body>