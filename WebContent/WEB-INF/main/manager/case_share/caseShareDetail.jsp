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
    	<i></i><h2>案例分享</h2>
   		<div class="clear"></div>
    </div>
    <!--案例详细内容-->
    <div class="case_detail">
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <caption>
        ${caseShare.title }
      </caption>
      <tr>
        <td class="t_bt" nowrap="nowrap">案例编码</td>
        <td colspan="3">${caseShare.case_code }</td>
        <td class="t_bt">分享日期</td>
        <td>${caseShare.insert_time }</td>
      </tr>
      <tr>
        <td class="t_bt">分享人</td>
        <td><p>${caseShare.user_name }</p></td>
        <td class="t_bt">服务器</td>
        <td>${caseShare.server_name }</td>
        <td class="t_bt">接入号</td>
        <td>${caseShare.service_code }</td>
      </tr>
      <tr>
        <td class="t_bt">问题类型</td>
        <td colspan="5">${caseShare.type_name }</td>
      </tr>
      <tr>
        <td class="t_bt">问题描述</td>
        <td colspan="5">${caseShare.summa }</td>
      </tr>
      <tr>
        <td class="t_bt">原因分析</td>
        <td colspan="5">${caseShare.analysize }</td>
      </tr>
      <tr>
        <td class="t_bt">处理结果</td>
        <td colspan="5">${caseShare.deal_result }</td>
      </tr>
      <tr>
        <td class="t_bt">得到结论</td>
        <td colspan="5">${caseShare.conclusion }</td>
      </tr>
      <tr>
        <td class="t_bt">备注</td>
        <td colspan="5">${caseShare.comment }</td>
      </tr>
    </table>
    </div>

    
    <div class="div_back"><a href="javascript:history.back(-1);" class="ipt_back">返回上一页</a></div>
    <!--页面内容 end-->
	</div></div>
	
</div>
</body>
</html>