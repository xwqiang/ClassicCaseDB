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
<title>知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/content.css" />
<script type="text/javascript" src="js/sly/jquery.min.js"></script>

<script type="text/javascript" src="js/sly/plugins.js"></script>
<script type="text/javascript" src="js/sly/sly.min.js"></script>
<script type="text/javascript" src="js/sly/fullpage.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("#server_id").val('${caseShare.server_id}');
		$("#type_id").val('${caseShare.type_id}');
	});
</script>
</head>

<body>
<div id="main" class="clearfix">
	<div id="scrollbar" class="scrollbar">
		<div class="handle">
		</div>
	</div>
	
	<div id="frame" class="frame" style="overflow:hidden;"><div>
	<!--页面内容 start-->
	<div class="q_title">
    	<i></i><h2>修改案例</h2>
   		<div class="clear"></div>
    </div>
    <!--案例详细内容-->
    <div class="case_detail">
    <form action="insertCaseShare.do" method="post">
	    <div class="case_m_item">
	    	<div class="case_left">通道名称:</div>
	    	<div class="case_cont"><input type="text" name="title" id="title" value="${caseShare.title }"/></div>
	    </div>
	        
	    <div class="case_m_item">
		    <div class="case_left">案例编码:</div>
	        <div class="case_cont">${caseShare.case_code }
	        	<input type="hidden" name="case_code" id="case_code" value="${caseShare.case_code }"/>
	        </div>
	    </div>
	    <div class="case_m_item">
		    <div class="case_left">分享日期:</div>
	        <div class="case_cont">${caseShare.insert_time }
	        	<input type="hidden" name="insert_time" id="insert_time" value="${caseShare.insert_time }"/>
	        </div>
	    </div>
	    
	    <div class="case_m_item">
	    	<div class="case_left">分享人:</div>
	        <div class="case_cont">${caseShare.user_name }
	        	<input type="hidden" name="user_id" id="user_id" value="${caseShare.user_id }"/>
	        </div>
	    </div>
	    <div class="case_m_item">
	    	<div class="case_left">服务器:</div>
	        <div class="case_cont">
	        	<select name="server_id" id="server_id">
	        		<c:forEach items="${serverTypeList }" var="each">
	        			<option value="${each.id }">${each.name }</option>
	        		</c:forEach>
	        	</select>
	        </div>
	    </div>
	    <div class="case_m_item">
	    	<div class="case_left">接入号:</div>
	        <div class="case_cont"><input type="text" name="service_code" id="service_code" value="${caseShare.service_code }"/></div>
	    </div>
	    
	    <div class="case_m_item">
	    	<div class="case_left">问题类型:</div>
	        <div class="case_cont">
	        	<select name="type_id" id="type_id">
	        		<c:forEach items="${caseTypeList }" var="each">
	        			<option value="${each.id }">${each.name }</option>
	        		</c:forEach>
	        	</select>
	        </div>
	    </div>
	    <div class="case_m_item">
	    	<div class="case_left">问题描述:</div>
	        <div class="case_cont">
	        	<textarea name="summa" id="summa" cols="90" rows="5">${caseShare.summa }</textarea>
	        </div>
	     </div>
	      <div class="case_m_item">
	    	<div class="case_left">原因分析:</div>
	        <div class="case_cont">
		        <textarea name="analysize" id="analysize" cols="90" rows="5">${caseShare.analysize }</textarea>
	        </div>
	      </div>
	      <div class="case_m_item">
	    	<div class="case_left">处理结果:</div>
	        <div class="case_cont">
	        	<textarea name="deal_result" id="deal_result" cols="90" rows="5">${caseShare.deal_result }</textarea>	
	       	</div>
	      </div>
	      <div class="case_m_item">
	    	<div class="case_left">得到结论:</div>
	        <div class="case_cont">
				<textarea name="conclusion" id="conclusion" cols="90" rows="5">${caseShare.conclusion }</textarea>	
			</div>
	      </div>
	      <div class="case_m_item">
	    	<div class="case_left">备注:</div>
	        <div class="case_cont">
				<textarea name="comment" id="comment" cols="90" rows="3">${caseShare.comment }</textarea>	
			</div>
	      </div>
	    
	    <div class="case_m_item">
		    <div class="case_btn"><input type="submit" class="iptBtn" value="修改"/></div>
	    </div>
	</form>
    </div>
    
    <div class="div_back"><a href="javascript:history.back(-1);" class="ipt_back">返回上一页</a></div>
    <!--页面内容 end-->
	</div></div>
	
</div>
</body>
</html>