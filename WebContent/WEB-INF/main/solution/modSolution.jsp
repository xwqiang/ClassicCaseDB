<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/authtaglib" prefix="s"%>
<%@ include file="/common/kindEditor.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/content.css" />

<script type="text/javascript" src="js/sly/jquery.min.js"></script>

<script type="text/javascript">
$(function(){
	KindEditor.ready(function(K) {
		var editor = K.create('#mysolution', {
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
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				$("#sbtSolution").click(function(){
					self.sync();
				});
			}
		});
		//prettyPrint();
	});
});


function beforeSubmit(){
	if($.trim($("#mysolution").val())==""){
		alert("请填写内容后提交！");
		return false;
	}
	if($.trim($("#solutionTitle").val())==""||$.trim($("#solutionTitle").val())=="请输入方案标题"){
		alert("请填写标题后提交！");
		return false;
	}
	if($("#solutionTitle").val().len>200){
		alert("标题内容不能超过200字！");
		return false;
	}
}


</script>
</head>
<body>
<div id="main">
	<div id="scrollbar" class="scrollbar">
		<div class="handle">
		</div>
	</div>
	
	<div id="frame" class="frame"><div style="overflow:hidden;">
	
    <!--content-->
    <div class="q_title"><i></i><h2>${menu.name }</h2>
   	<div class="clear"></div>
    </div>
    <div class="remark">
    	<form name="example" action="updateSolutionById.do" method="post" onsubmit="javascript:return beforeSubmit();">
	    	<input type="hidden" name="menu_id" id="menu_id" value="${solution.menu_id}"/>
	    	<input type="hidden" name="id" id="id" value="${solution.id}"/>
	    	<input type="text" id="solutionTitle" name="title" class="remark_ipt" value="${solution.title }" onfocus="if (value =='请输入方案标题'){value =''}" onblur="if (value ==''){value='请输入方案标题'}" />
	    	<textarea id="mysolution" name="content" class="remark_txt" rows="2" style="height:400px;">${solution.content }</textarea>
	        <div class="r_btn">
	        	<input type="submit" id="sbtSolution" value="保存" title="保存" class="remark_btn" onmouseover="this.className='remark_btn_up'" onmouseout="this.className='remark_btn'" onmousedown="this.className='remark_btn_down'" onmouseup="this.className='remark_btn_up'" />
	        	<input type="button" id="backBtn" value="返回" title="返回" class="remark_btn" onmouseover="this.className='remark_btn_up'" onmouseout="this.className='remark_btn'" onmousedown="this.className='remark_btn_down'" onmouseup="this.className='remark_btn_up'" onclick="javascript:history.back(-1);"/>
    		</div>
    	</form>
    </div>
    
	</div></div>

</div>

<script type="text/javascript" src="js/sly/plugins.js"></script>
<script type="text/javascript" src="js/sly/sly.min.js"></script>
<script type="text/javascript" src="js/sly/fullpage.js"></script>
</body>
</html>
  
  <%!
private String htmlspecialchars(String str) {
	str = str.replaceAll("&", "&amp;");
	str = str.replaceAll("<", "&lt;");
	str = str.replaceAll(">", "&gt;");
	str = str.replaceAll("\"", "&quot;");
	return str;
}
%>