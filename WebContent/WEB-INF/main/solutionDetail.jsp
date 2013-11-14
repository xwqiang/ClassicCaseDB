<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/authtaglib" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/content.css" />
<link rel="stylesheet" type="text/css" href="js/fancyBox/source/jquery.fancybox.css?v=2.1.5" media="screen" />
<script type="text/javascript" src="<%=path%>/js/jquery.min.js"></script>
<script type="text/javascript" src="js/fancyBox/source/jquery.fancybox.js?v=2.1.5"></script>
<style type="text/css">
.q_ol ol li{list-style:none;}
</style>
<script>


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


	
	function supportIt(obj,id){
		var data = "id="+id
		$.ajax({
			url: "supportIt.do",
			data: data,
			method: 'post',
			success: function(txt){
				if(txt=="success"){
					$(obj).removeAttr('onclick');
					$(obj).removeAttr('href');
					$(obj).attr('title','已经赞过  (⊙o⊙)…');
					var ranking = $(obj).next("span").html();
					$(obj).next("span").html("+1");
					window.setTimeout(function(){
						$(obj).next("span").html(parseInt(ranking)+1);
					},1000);
				}
			}
		});
	}
</script>
</head>
<body>
<div id="main" class="clearfix">
    <!--content-->
    <div class="q_ol"><ol>
       <li>
	      <div class="q_ol_title clearfix">
	    	<h3>${solution.title}</h3>    			
	        <div>作者：${solution.user_name } &nbsp;&nbsp;日期：2013-9-27</div>
		  </div>
		  <div class="q_ol_content">
	           	${ content}
	      	<div class="q_operate">
		        <span class="q_i"><a href="javascript:void(0);" onclick="javascript:supportIt(this,'${solution.id}')" class="q_btn" title="赞">赞 </a><span>${solution.ranking }</span></span>
		  	</div>
	      </div> 
       </li>
        
    </ol></div>
    <div class="div_back"><a href="javascript:history.back(-1);" class="ipt_back">返回上一页</a></div>
    
 </div>
</body>
</html>