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
<title>角色管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	function deleteRole(id){
	var flag = window.confirm("确定要删除吗？");
	if(flag){
		window.location.href="deleteRole.do?id="+id;
	}
	}
</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-role">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>角色管理</h2>
		   	<div class="clear"></div>
		</div>
		<div class="ope-box">
			<div class="ope-item"><a href="showInsertRole.do" class="btn">新建角色</a></div>
		</div>
		
		<table class="tablestyle" width="100%" border="0" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<th width="100">角色名称</th>
					<th class="td-time">修改时间</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				 <c:forEach items="${roleList }" var="each">
					<tr>
						<td nowrap="nowrap">${each.role_name }</td>
						<td><span class="color-grey">${each.update_time }</span></td>
						<td>
							<a href="showUpdateRole.do?id=${each.id }" title="修改" class="td-btn">修改</a>
							<a href="javascript:deleteRole('${each.id}');" title="删除" class="td-btn">删除</a>
							<a href="getPriorityByRoleId.do?role_id=${each.id }" title="修改权限" class="td-btn">修改权限</a>
							<a href="grantPriorityToUserByRoleId.do?role_id=${each.id }" title="分配用户" class="td-btn">分配用户</a>
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
