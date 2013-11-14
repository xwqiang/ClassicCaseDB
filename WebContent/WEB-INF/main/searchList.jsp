<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
	<link type="text/css" rel="stylesheet" href="<%=path %>/css/content.css" />
	<link rel="stylesheet" type="text/css" href="js/fancyBox/source/jquery.fancybox.css?v=2.1.5" media="screen" />
	<script type="text/javascript" src="js/sly/jquery.min.js"></script>
	<script type="text/javascript" src="js/fancyBox/source/jquery.fancybox.js?v=2.1.5"></script>
	
	<script type=”text/javascript”>
		function clearDefault(el) {if (el.defaultValue==el.value) el.value = ""   }
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			$("img").bind('click',function(){
				var src = $(this).attr('src');
				if(src.indexOf("/emoticons/")<0){
					$.fancybox.open(src);
				}
			});
			$("img").hover(function(){
				var src = $(this).attr('src');
				if(src.indexOf("/emoticons/")<0){
					this.style.cursor="pointer";
				}
			});
		});
		function showDetails(id){
			window.location.href="<%=path%>/getSolutionById.do?id="+id;
		}
	</script>
	<title>搜索结果_知识库</title>
</head>

<body>
<div id="main">
	<div id="scrollbar" class="scrollbar">
		<div class="handle">
		</div>
	</div>
	
	<div id="frame" class="frame"><div style="overflow:hidden;">
	
		<div class="q_ol">
			<ol>
				<c:forEach items="${list }" var="solution">
		    	<li>
		    		<div class="q_ol_title clearfix">
		    			<h3><a href="javascript:showDetails('${solution.id}');" title="${solution.title }">${solution.title }</a></h3>
		    			<div>作者：${solution.user_id } &nbsp;&nbsp;日期：2013-9-27</div>
		        	</div>
		        	<div class="s_ol_content">
		            	<a href="javascript:void(0);" onclick="javascript:showDetails('${solution.id}')">${fn:substring(solution.content,0,1000) }</a>
		            </div>
		        </li>
				</c:forEach>
		  </ol>
		</div>
		 <jsp:include page="/common/pager.jsp"></jsp:include>
		 
  </div></div>  
  <!-- end-->
</div>

<script type="text/javascript" src="js/sly/plugins.js"></script>
<script type="text/javascript" src="js/sly/sly.min.js"></script>
<script type="text/javascript" src="js/sly/fullpage.js"></script>
</body>
</html>
