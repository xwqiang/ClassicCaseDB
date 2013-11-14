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
<title>角色分配_人员管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-user">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>角色分配</h2>
		   	<div class="clear"></div>
		</div>
		
		<form action="updateUserRoleByUserId.do" method="post">
			<input type="hidden" value="${id}" name="id" id="id"/>
			<table class="tablestyle" width="100%" border="0" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th width="100">角色名称</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					 <c:forEach items="${roleList }" var="each">
						<tr>
							<c:if test="${each.checked==true}">
								<td><input type="checkbox" name="role_id" value="${each.id }" checked="checked" class="ipt-check" /> ${each.role_name }</td>
							</c:if>
							<c:if test="${ each.checked==false}">
								<td><input type="checkbox" name="role_id" value="${each.id }" class="ipt-check" /> ${each.role_name }</td>
							</c:if>
							<td>
								<a href="getPriorityByRoleId.do?role_id=${each.id }" title="修改权限" class="td-btn">修改权限</a>
						  	</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="f-item f-footer">
				<input type="submit" value="保存" class="btn" />
				<input type="button" value="取消" class="btn" onclick="javascript :history.back(-1);" />
			</div>
		</form>
		
	</div>
</div>
</body>
</html>
