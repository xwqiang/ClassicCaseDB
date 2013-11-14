<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作执行情况</title>


	
<link href="js/uploadify/uploadify.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.6.4.min.js"></script>
<script type="text/javascript" src="js/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript">
	
		$(function() {
			$("#file_upload_1").uploadify({
				height:30,
				upload_url: '/js/uploadify/uploadify.swf',
				flash_url:'/<%=path%>/test/aa',
				buttonText:'选择文件',
				fileTypeExts:'*.gif;*.jpg;*.zip',
				width:120,
				auto:true
			});
		});
</script>
</head>
<body>
<form  method="post">
	<input id="file_upload_1" name="file_upload_1" type="file" />（大小不超过6M的jpg或zip文件）
</form>
</body>
</html>

