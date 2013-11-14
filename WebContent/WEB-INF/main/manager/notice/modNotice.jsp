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
<title>修改公告_公告管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	KindEditor.ready(function(K) {
		var editor = K.create('#content', {
			showRemote : false,
			cssPath : 'js/kindeditor-4.1.7/plugins/code/prettify.css',
			uploadJson : 'upload.do',
			fileManagerJson : '../jsp/file_manager_json.jsp',
			allowFileManager : true,
			items : [  //配置工具栏
			           'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			           'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			           'insertunorderedlist', '|', 'emoticons', 'image', 'link', '|' ],
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['modNoticeFrm'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['modNoticeFrm'].submit();
				});
				$("#sbtUpdateNotice").click(function(){
					self.sync();
				});
			}
		});
	});
});


function validate(){
	return true;
}
</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-notice">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>修改公告</h2>
		   	<div class="clear"></div>
		</div>
		
		<form action="updateNotice.do" name="modNoticeFrm" method="post" onsubmit="javascript:return validate();">
			<input type="hidden" name="id" id="id" value="${notice.id}"/>
			<div class="f-item">
				<div class="f-left">标题：</div>
				<div class="f-cont"><input type="text" name="title" id="title" value="${notice.title }"/></div>
			</div>
			<div class="f-item">
				<div class="f-cont"><textarea name="content" id="content">${notice.content}</textarea></div>
			</div>
			<div class="f-item f-footer">
				<input type="submit" id="sbtUpdateNocite" value="保存"class="btn"/>
				<input type="button" value="取消" class="btn" onclick="javascript :history.back(-1);" />
			</div>
		</form>
		
	</div>
</div>
</body>
</html>
