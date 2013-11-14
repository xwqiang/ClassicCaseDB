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
<title>人员管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		var user_id = '${page.argsmap.user_id }'||'请输入用户ID';
		$("#user_id").val(user_id);
	})
	function updateUserInfo(id){
		$("#userInfo_"+id).children().find("input").removeAttr("readonly");
		$("#userInfo_"+id).children().find("input").attr("style","border:1px solid #89D5D8;padding:2px;color=#333");
		$("#updateUserInfoBtn_"+id).attr("style","display:none;");
		$("#saveUserInfoBtn_"+id).attr("style","display:inline-block;");
	}
	function saveUserInfo(id){
		var data = "id="+id+"&user_id="+$("#user_id_"+id).val()+"&user_name="+$("#user_name_"+id).val();
		$.ajax({
			data: data,
			url: 'updateUserInfo.do',
			type: 'post',
			success: function(txt){
				if(txt=="duplicated"){
					alert("用户ID被使用，请重新填写");
				}else if(txt=="success"){
					alert("修改成功");
				}else{
					alert("修改失败");
				}
			}
		});
		$("#userInfo_"+id).children().find("input").attr("readonly","readonly");
		$("#userInfo_"+id).children().find("input").attr("style","border:0;");
		$("#updateUserInfoBtn_"+id).attr("style","display:inline-block;");
		$("#saveUserInfoBtn_"+id).attr("style","display:none;");
	}
	function deleteUserInfo(id){
		var data ="id="+id;
		$.ajax({
			data: data,
			url: 'deleteUserInfo.do',
			type: 'post',
			success: function(txt){
				if(txt=="success"){
					alert("删除成功");
					$("#userInfo_"+id).remove();
				}else{
					alert("修改失败");
				}
			}
		});
	}
</script>
</head>

<body>
<div id="Container" class="clearfix">
	<div id="ac-user">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>人员管理</h2>
		   	<div class="clear"></div>
		</div>
		<div class="ope-box">
			<div class="ope-item"><a href="showInsertUserInfo.do" class="btn">新建用户</a></div>
			<div class="sear-box">
				<form action="listUserInfo.do" method="post">
					<input type="text" name="user_id" id="user_id" class="ipt"/>
					<input type="submit" value="搜索" class="ipt_btn"/>
				</form>
			</div>
		</div>
		
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
						<td id="insert_time_${each.id }"><span class="color-grey">${each.insert_time }</span></td>
						<td>
							<a id="updateUserInfoBtn_${each.id }" href="javascipt:void(0);" onclick="javascript:updateUserInfo('${each.id}');" title="修改" class="td-btn">修改</a>
							<a id="saveUserInfoBtn_${each.id }" style="display:none;" href="javascipt:void(0);" onclick="javascript:saveUserInfo('${each.id}');" title=“保存”  class="td-btn">保存</a>
						  	<s:auth action="updatePriorityByUserId.do">
							  	<a href="updatePriorityByUserId.do?user_id=${each.id }" title="分配权限" class="td-btn">分配权限</a>
						  	</s:auth>
						  	<a href="javascript:void(0);" onclick="javascript:deleteUserInfo('${each.id}')" title="" class="td-btn">删除</a>						  	
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
