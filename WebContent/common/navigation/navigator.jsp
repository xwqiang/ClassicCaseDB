<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/WEB-INF/authtaglib.tld"%>

<div id="Container" class="clearfix">
	<div class="manager_nav">
		<ul>
			<s:auth action="listUserInfo.action">
       			<li><a href="listUserInfo.action" target="_self" class="ac-user">人员管理</a></li>
       		</s:auth>
       		<s:auth action="showNoticeList.action">
       			<li><a href="showNoticeList.action" target="_self" class="ac-notice">公告管理</a></li>
       		</s:auth>
       		<s:auth action="listUploadFile.action">
       			<li><a href="listUploadFile.action" target="_self" class="ac-file">文件管理</a></li>
       		</s:auth>
       		<s:auth action="listRole.action">
       			<li><a href="listRole.action" target="_self" class="ac-role">角色管理</a></li>
       		</s:auth>
       		<s:auth action="listModule.action">
       			<li><a href="listModule.action" target="_self" class="ac-module">模块管理</a></li>
       		</s:auth>
       		<li><a href="showWelcomePage.do" target="_self" class="ac-return">返回平台</a></li>
		</ul>
	</div>
</div>
