<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auto" uri="/WEB-INF/authtaglib.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/content.css" />
<script type="text/javascript" src="js/sly/jquery.min.js"></script>

<script type="text/javascript" src="js/sly/plugins.js"></script>
<script type="text/javascript" src="js/sly/sly.min.js"></script>
<script type="text/javascript" src="js/sly/fullpage.js"></script>

<script type="text/javascript">
$(document).ready(function(){
	$("#type_id").val('${page.argsmap.type_id}');
	$("#server_id").val('${page.argsmap.server_id}');
	var title = '${page.argsmap.title }'||'请输入标题';
	$("#title").val(title);
});
function getDetail(id){
	window.location.href="getCaseShareById.do?id="+id
}
function init(){
	if($("#title").val()=='请输入标题'){
		$("#title").val("");
	}
}
function deleteCaseShare(id){
	var flag = window.confirm("确定要删除吗？");
	if(flag){
		window.location.href="deleteCaseShare.do?id="+id;
	}
}
</script>
<style>
.sys_operate{ padding-right:30px;}
</style>
</head>

<body>
<div id="main" class="clearfix">
	<div id="scrollbar" class="scrollbar">
		<div class="handle">
		</div>
	</div>
	
	<div id="frame" class="frame" style="overflow:hidden;"><div>
	<!--页面内容 start-->
	<div class="q_title"><i></i><h2>案例分享</h2>
		<div class="clear"></div>
	</div>
	 <div class="search s_case clearfix">
	 	<auto:auth action="insertCaseShare.label">
       		<div class="sys_operate"><a href="showInsertCaseShare.do" class="operate_btn">新建案例</a></div>
       	</auto:auth>
       	
   		<form action="listCaseShare.do" method="post" onsubmit="return init();" class="fl">
	    	<select name="type_id" id="type_id">
       			<option value="0">请选择问题类型</option>
        		<c:forEach items="${caseTypeList }" var="each">
        			<option value="${each.id }">${each.name }</option>
        		</c:forEach>
        	</select>
        	<select name="server_id" id="server_id">
       			<option value="0">请选择服务器</option>
        		<c:forEach items="${serverTypeList }" var="each">
        			<option value="${each.id }">${each.name }</option>
        		</c:forEach>
        	</select>
        	<input type="text" name="title" id="title" onfocus="if (value =='请输入标题'){value =''}" onblur="if (value ==''){value='请输入标题'}"/>
        	<input type="submit" value="搜索" class="s_btn" id="searchBtn" onmouseover="this.className='s_btn_up'" onmouseout="this.className='s_btn'" onmousedown="this.className='s_btn_down'" onmouseup="this.className='s_btn_up'"/>
   		</form>
    </div>
   		
    <!--案例列表-->
    <c:forEach items="${caseShareList }" var="each">
	    <div class="case_item">
	    	<p class="grey">案例编号:<span>${each.case_code }</span>&nbsp;&nbsp;&nbsp;&nbsp;分享日期:<span>${each.insert_time}</span></p>
			<p class="c_info"><span>分享人：${each.user_name }</span> <span>${each.type_name }</span> <span>服务器：${each.server_name }</span> <span>接入号：${each.service_code }</span></p>
			<div class="q_operate">
				<a href="javascript:getDetail('${each.id}')"  class="q_btn">查看</a>
				<auto:auth action="updateCaseShare.label">
					<a href="showUpdateCaseShare.do?id=${each.id }" class="q_btn">修改</a>
				</auto:auth>
				<auto:auth action="deleteCaseShare.label">
					<a href="javascript:deleteCaseShare('${each.id}');" class="q_btn">删除</a>				
				</auto:auth>
			</div>
	    </div>
    </c:forEach>
    <c:if test="${empty caseShareList }">
    	<p class="case_null">没有案例 或 没有找到您所需要的!</p>
    </c:if>
    <!-- end -->
   	<jsp:include page="/common/pager.jsp" />
    <!--页面内容 end-->
	</div></div>
	
</div>
</body>
</html>