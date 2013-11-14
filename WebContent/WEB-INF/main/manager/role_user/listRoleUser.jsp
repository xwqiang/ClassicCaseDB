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
<title>分配用户_角色管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$("input[type=checkedbox]").bind("change",function(){
		if($(this).attr("checked")=="checked"){
			$(this.addClass("ckdItem"));
		}else{
			$(this.removeClass("ckdItem"));
		}
	});
</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-role">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>分配用户</h2>
		   	<div class="clear"></div>
		</div>
		
		<form action="grantPriorityToUserByRoleIdSubmit.do" method="post">
			<input name="role_id" id="role_id" value="${role.id }" type="hidden"/>
			<h3 class="min-title">角色：${role.role_name }</h3>
			<div class="user-box">
				 <c:forEach items="${userlist }" var="each">
						<c:if test="${each.checked==true}">
							<div class="item ckdItem">
								<input type="checkbox" name="id" value="${each.id }" id="name_${each.id }" class="ipt-check" checked="checked"/>
								<label for="name_${each.id }"> ${each.user_name }</label>					
							</div>
						</c:if>
						<c:if test="${each.checked==false}">
							<div class="item">
								<input type="checkbox"name="id" value="${each.id }" id="name_${each.id }" class="ipt-check"/>
								<label for="name_${each.id }"> ${each.user_name }</label>
							</div>
						</c:if>
				</c:forEach>
			</div>
			<div class="f-footer">
				<input type="submit" value="保存" class="btn" />
				<input type="button" value="取消" class="btn" onclick="javascript :history.back(-1);" />
			</div>
		</form>
	</div>
</div>
</body>
</html>
