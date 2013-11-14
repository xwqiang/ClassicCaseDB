<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/authtaglib" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>问题查询</title>
<script>
	function onSerach(){
		var key = $.trim($("#searchKey").val());
		if(key=="请输入关键词搜索"||key==""){
			alert("请输入关键字");
			return false;
		}
	}
</script>
<style>
	#mainFrame{ width:100%; }
</style>
</head>

<body>
<div id="Container" class="clearfix">
	<!--sider-->
    <div id="sider" class="fl">
    <jsp:include page="menu.jsp"></jsp:include>
  </div>
  
  <!--mainbox start-->
  <div id="mainbox" class="fr">
  	<!--search-->
    <div class="topbar">
    	<div class="search fl w300">
        	<form action="<%=path%>/searchSolutions.do" method="post" target="mainFrame" onsubmit="javascript:return onSerach();">
	            <input type="text" value="请输入关键词搜索" id="searchKey" name="key" onfocus="if (value =='请输入关键词搜索'){value =''}" onblur="if (value ==''){value='请输入关键词搜索'}" class="s_ipt"/>
	            <input type="submit" value="搜索" class="s_btn" id="searchBtn" onmouseover="this.className='s_btn_up'" onmouseout="this.className='s_btn'" onmousedown="this.className='s_btn_down'" onmouseup="this.className='s_btn_up'"/>
            </form>
        </div>        
        
        <div class="user fr">
        	<a href="javascript:void(0);" class="user_photo fl"><img src="${USER.portrait_url }" alt="<c:out value='${USER.user_name}'/>" title="<c:out value='${USER.user_name}'/>" /></a>
            <a href="javascript:void(0);">修改密码</a>&nbsp;&brvbar;&nbsp;<a href="<%=path%>/logout.do">退出</a>
        </div>
        
        <!-- 系统管理 -->
        <div class="sys_operate"><a href="javascript:void(0);" class="operate_btn">系统管理</a></div>
        <!-- 管理项 -->
        <div class="operate">
        	<div class="triangle"><em>&#9670;</em><em class="triangle01">&#9670;</em></div>
        	<div class="operate_box">
        		<ul>
	        		<s:auth action="listUserInfo.action">
	        			<li><a href="listUserInfo.action" target="_blank"><i class="ope_user"></i>人员管理</a></li>
	        		</s:auth>
	        		<s:auth action="showNoticeList.action">
	        			<li><a href="showNoticeList.action" target="_blank"><i class="ope_log"></i>公告管理</a></li>
	        		</s:auth>
	        		<s:auth action="listUploadFile.action">
	        			<li><a href="listUploadFile.action" target="_blank"><i class="ope_doc"></i>文件管理</a></li>
	        		</s:auth>
	        		<s:auth action="listRole.action">
	        			<li><a href="listRole.action" target="_blank"><i class="ope_role"></i>角色管理</a></li>
	        		</s:auth>
	        		<s:auth action="listModule.action">
	        			<li><a href="listModule.action" target="_blank"><i class="ope_module"></i>模块管理</a></li>
	        		</s:auth>
        		</ul>
        	</div>
        </div>
        
        <div class="clear"></div>
    </div>
    
     <iframe name="mainFrame" id="mainFrame" src="<%=path %>/getHighRankingsolutions.do" scrolling="no" frameborder="0" marginwidth="0" marginheight="0"></iframe>
  </div>
  <!--mainbox end-->
</div>
<script type="text/javascript">
	function resetHeight() {
	     var height = $(window).height();
	     $('#mainFrame').height(height - 84);
	}
	$(function() {
	     resetHeight();
	 });
	$(window).resize(function() {
	     resetHeight();
	});
</script>

<script type="text/javascript">
$(function () {
	var time = 500;
	
	var other = window.document;
	var info = $('.operate', this).css('opacity', 0);		
	var shown = false;
	
	$(other).click(function(){
		if(shown==true){
			$(info).animate({
					width: 20,
					height: 20,
					top: 35,
					left: 450,
					opacity: 0
				},time, 'swing', function() {					
					$(info).css('display', 'none');
			});
		}
		shown = false;
	});
	
	$(".operate_btn").click(function () {
		if($(info).is(":hidden")){	
			$(info).css({
				top: 35,
				left: 450,
				display: 'block'
			}).animate({
				width:260,
				height:110,
				top: 40,
				opacity: 1
			},time, 'swing', function() {
				shown = true;
			});
		}
		
	});	
		
});
</script>
</body>
</html>
