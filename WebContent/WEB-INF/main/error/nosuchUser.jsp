<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
.error{ position:relative; margin:0 auto; margin-top:100px; background:url(<%=path%>/images/error01.gif) no-repeat left top; width:686px; height:358px;}
.clear{ clear:both;}
.error_content{ padding:130px 0 0 140px; font: normal 22px/1.7 "微软雅黑"; color:#333;}
.e_icon{ float:left; width:46px; height:46px; background:url(<%=path%>/images/error01.gif) no-repeat 0 -474px;}
.icon1{background:url(<%=path%>/images/error01.gif) no-repeat -62px -474px;}
.e_txt{ float:left; width:380px; padding-left:20px;}
</style>
<title>提示</title>
</head>

<body>
<div class="error">
	<div class="error_content">
        <div class="e_icon icon1"></div>
        <div class="e_txt">资源库中暂无此用户，请与管理员联系</div>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>