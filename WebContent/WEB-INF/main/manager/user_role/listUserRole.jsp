<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>角色分配_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var user_id = '${page.argsmap.user_id }'||'请输入用户ID';
		$("#user_id").val(user_id);
	})
	function updatePriorityByUserId(user_id){//yuser的主键id
		window.location.href="updatePriorityByUserId.do?user_id="+user_id;
	}
</script>
</head>

<body>
<div id="Container" class="clearfix">
	<%@include file="/common/navigation/navigator.jsp" %>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>角色分配</h2>
		   	<div class="clear"></div>
		</div>
		<form action="listUserRole.do" method="post">
			<input type="text" name="user_id" id="user_id"/>
			<input type="submit" value="搜索"/>
		</form>
			<table class="tablestyle" width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="120">工号</th>
						<th width="100">姓名</th>
						<th class="td-time">创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					 <c:forEach items="${userlist }" var="each">
						<tr id="userInfo_${each.id }">
							<td><input type="text" id="user_id_${each.id }" readonly="readonly" class="noborder ipt-w100" value="${each.user_id }"/></td>
							<td><input type="text" id="user_name_${each.id }" readonly="readonly" class="noborder ipt-w80" value="${each.user_name }"/></td>
							<td id="insert_time_${each.id }">${each.insert_time }</td>
							<td>
							  	<a href="javascript:void(0);" onclick="javascript:updatePriorityByUserId('${each.id}')">分配权限</a>
						  	</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/common/pager.jsp"></jsp:include>
		</div>
</div>
</body>
</html>
