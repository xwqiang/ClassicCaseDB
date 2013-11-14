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
<title>上传文件_文件管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
function beforeSubmit(){
	if(!$("#file_desc").val()){
		alert("请填写文件说明");
		return false;
	}
	if(!$("#file").val()){
		alert("请选择文件");
		return false;
	}
}
</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-file">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>上传文件</h2>
		   	<div class="clear"></div>
		</div>
		<form action="uploadFile.do" method="post" name="fileFrm" enctype="multipart/form-data" onsubmit="javascript:return beforeSubmit();">
			<div class="f-item">
				<div class="f-left">文件说明：</div>
				<div class="f-cont"><input type="text" name="file_desc" id="file_desc"/></div>
			</div>
			<div class="f-item">
				<div class="f-left">文件类型：</div>
				<div class="f-cont">
					<select name="file_type" id="file_type">
						<option value="1">通道材料</option>
						<option value="2">制度文档</option>
						<option value="3">其他</option>
					</select>
				</div>
			</div>
			<div class="f-item">
				<div class="f-left">选择文件：</div>
				<div class="f-cont"><input type="file" name="file" id="file"/></div>
			</div>
			
			<div class="f-item f-footer">
				<input type="submit" value="上传" class="btn"/>
				<input type="button" value="取消" class="btn" onclick="javascript :history.back(-1);" />
			</div>
		</form>
	</div>
</div>
</body>
</html>
