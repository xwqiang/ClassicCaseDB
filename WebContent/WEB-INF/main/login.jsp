<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="<%=path%>/css/login.css" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<title></title>

<script>
	function validate(){
		if($.trim($("#user_id").val())=="请输入用户名"||$.trim($("#user_pwd").val())=="请输入密码"){
			alert("请输入！");
			return false;
		}
		return true;
	}
</script>

</head>
<body>
<div class="login_center">
    <div class="login">
    	<div class="h115"></div>
        <h1><img src="images/logo.png" alt="知识库" width="188" height="90"/></h1>
        	<form action="<%=path %>/localLogin.do" method="post" onsubmit="javascript:return validate();">
	            <div class="login_content">
	            	<div class="l_item l_border"><label for="user">用户名</label>
	                	<input type="text" id="user_id" name="user_id" value="请输入用户名" onfocus="if (value =='请输入用户名'){value =''}" onblur="if (value ==''){value='请输入用户名'}" style="color:#CCC;" />
	                </div>
	                 <div class="l_item"><label for="pwd">密&nbsp;&nbsp;码</label>
	                <input type="text" id="user_pwd" name ="user_pwd" value="请输入密码" onfocus="if(this.value==defaultValue) {this.value='';this.type='password'}" onblur="if(!value) {value=defaultValue; this.type='text';}" style="color:#CCC;" />
	                </div>
	            </div>
	            <div class="l_txt"><a href="javascript:void(0);">忘记密码？</a></div>
	            <div class="l_btn"><input type="submit" class="btn" value="登 录" onmouseover="this.className='btn_up'" onmouseout="this.className='btn'" onmousedown="this.className='btn_down'" onmouseup="this.className='btn_up'"/></div>
            </form>
    </div>
    
</div>
</body>
</html>