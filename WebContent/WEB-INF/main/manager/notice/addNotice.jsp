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
<title>新建公告_公告管理_知识库</title>
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
					if(beforeSubmit()){
						document.forms['addNoticeFrm'].submit();
					}
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					if(beforeSubmit()){
						document.forms['addNoticeFrm'].submit();
					}
				});
				$("#sbtNotice").click(function(){
					self.sync();
				});
			}
		});
	});
});
function beforeSubmit(){
	if($.trim($("#content").val())==""){
		alert("请填写内容后提交！");
		return false;
	}
	if($.trim($("#title").val())==""){
		alert("请填写标题后提交！");
		return false;
	}
	if($("#content").val().len>200){
		alert("标题内容不能超过200字！");
		return false;
	}
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
		<div class="manager_title"><i></i><h2>新建公告</h2>
		   	<div class="clear"></div>
		</div>
		
		<form action="insertNotice.do" method="post" name="addNoticeFrm" onsubmit="javascript:return beforeSubmit();">
			<div class="f-item">
				<div class="f-left">标题：</div>
				<div class="f-cont"><input type="text" name="title" id="title" class="ipt"/></div>
			</div>
			<div class="f-item">
				<div class="f-cont"><input type="text" name="content" id="content"/></div>
			</div>
			<div class="f-item f-footer">
				<input type="submit" id="sbtNotice" value="保存" class="btn"/>
				<input type="button" value="取消" class="btn" onclick="javascript :history.back(-1);" />
			</div>
		</form>
		
	</div>
</div>
</body>
</html>
