<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>模块管理_知识库</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />
<link type="text/css" rel="stylesheet" href="css/manager.css" />
<link rel="stylesheet" href="js/artDialog/default.css" type="text/css">

<link rel="stylesheet" href="js/ztree/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<script type="text/javascript" src="js/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>


<script type="text/javascript" src="js/artDialog/artDialog.js"></script>
<script type="text/javascript" src="js/artDialog/artDialog.plugins.js"></script>

<SCRIPT type="text/javascript">
		var zNodes;
		var className = "dark";
		function beforeClick(treeId, treeNode, clickFlag) {
		}
		function zTreeOnExpand(event, treeId, treeNode) {
		};
		function onClick(event, treeId, treeNode, clickFlag) {
			if(!treeNode.isParent){
			}	
		}
		function insertNode(){
			var html =[
			           "模块名称：<input name='name' size='20' id='name' type='text' ></input>",
			           "url：<input name='url' size='20' id='url' type='text' ></input>"
			           ];
			art.dialog({
	 			focus: true,
			    id: 'shake-demo',
			    title: '新建模块',
			    content: html.join(","),
			    lock: true,
			    fixed: true,
			    ok: function () {
			        var name = document.getElementById('name');
			        var url = document.getElementById('url');
			        if($.trim(name.value)=="" ||$.trim(name.value)==null){
			        	name.select();
				    	name.focus();
				    	this.shake();
				    	return false;
					}else{
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						var thisNode = zTree.getSelectedNodes();
						var pId = thisNode && thisNode[0]&&thisNode[0].id||0;
						var data = "parent_id="+pId+"&url="+url.value+"&name="+name.value;
						$.ajax({
							type: "post",
							data: data,
							url: "insertModule.do",
							success: function(txt){
								if(txt!="fail"){
									var newNode ={id: txt, pId:pId, name:name.value,url:url.value};
									var parentNode =zTree.getNodeByParam("id",pId,null);
									zTree.addNodes(parentNode, newNode);
								}else{
									alert("新建失败");
								}
							}
						});
					}
			    }
			});
		}
		function updateNode(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var thisNode = zTree.getSelectedNodes();
			var pId = thisNode[0].id||0;
			var html =[
			           "模块名称：<input name='name' size='20' id='name' type='text' value="+thisNode[0].name+"></input>",
			           "url：<input name='url' size='20' id='url' type='text' value="+thisNode[0].url+"></input>"
			           ].join(",");
			art.dialog({
	 			focus: true,
			    id: 'shake-demo',
			    title: '修改模块',
			    content: html,
			    lock: true,
			    fixed: true,
			    ok: function () {
			        var name = document.getElementById('name');
			        var url = document.getElementById('url');
			        if($.trim(name.value)=="" ||$.trim(name.value)==null){
			        	name.select();
				    	name.focus();
				    	this.shake();
				    	return false;
					}else{
						var id = thisNode[0].id;
						var data = "id="+id+"&url="+url.value+"&name="+name.value;
						$.ajax({
							type: "post",
							data: data,
							url: "updateModule.do",
							success: function(txt){
								if(txt=="success"){
									thisNode[0].name=name.value;
									thisNode[0].url=url.value;
									zTree.updateNode(thisNode[0]);
								}else{
									alert("更新失败");
								}
							}
						});
					}
			    }
			});
		}
		function deleteNode(){
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var thisNode = zTree.getSelectedNodes();
			if(thisNode&&thisNode[0]&&thisNode[0].id){
				var data = "id="+thisNode[0].id;
				if(confirm("确定要删除"+thisNode[0].name)){
					$.ajax({
						type: "post",
						data: data,
						url: "deleteModule.do",
						success: function(txt){
							if(txt=="success"){
								zTree.removeNode(thisNode[0]);
							}else{
								alert("删除失败");
							}
						}
					});
				}
			}else{
				alert("请选择要删除的节点");
			}
		}
			
		function beforeDrag(treeId, treeNodes) {
			return false;
		}
		function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
			if(confirm("确定要移动"+targetNode.name)){
				return true;
			}else{
				return false;
			}
		};

		function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
			if(!targetNode){
				return false;
			}
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			var thisNode = zTree.getSelectedNodes();
			var data = "id="+thisNode[0].id+"&parent_id="+targetNode.id;
			var url = "updateMenuParent.do";
			var oEvent = window.event;
			if(oEvent.ctrlKey){
				url="copyMenuToAnother.do";
			}
			$.ajax({
				type:"post",
				data:data,
				url:url,
				success:function(txt){
					if(txt=="success"){
						alert("更新成功");
					}else{
						alert("更新失败,清刷新恢复");
					}
				}
			});
		};
		function zTreeOnDblClick(event, treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			if (treeNode&&treeNode.length>0) {
				treeObj.expandNode(treeNode, true, false, true);
			}
		};

		var setting = {
				view: {
					selectedMulti: false
				},
				edit: {
					enable: true,
					showRemoveBtn: false,
					showRenameBtn: false,
					drag: {
						prev: false, 
						next: false,
						inner: true,
						isCopy: true,
						isMove: true
					}
				},
				data: {
					key: {
						url:"url",
						title:"name"
					},
					simpleData: {
						idKey: "id",
						pIdKey: "parent_id",
						rootPId: 0,
						enable: true
					}
				},
				callback: {
					beforeDrag: beforeDrag,
					beforeDrop: zTreeBeforeDrop,
					beforeClick: beforeClick,
					onClick: onClick,
					onDrop: zTreeOnDrop,
					onExpand: zTreeOnExpand,
					onDblClick: zTreeOnDblClick
				}
			};
		$(function(){
			zNodes = ${module_json};
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			treeObj.expandAll(false);
			
			$("#is_expand").toggle(function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.expandAll(true);
				$(this).html("全部收起");
			},function(){
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				zTree.expandAll(false);
				$(this).html("全部展开");
			});
		});
	</SCRIPT>
	<style>
		.ztree{ width:auto;}
	</style>
</head>
<body>
<div id="Container" class="clearfix">
	<div id="ac-module">
		<%@include file="/common/navigation/navigator.jsp" %>
	</div>
	
	<div class="mainbox">
		<div class="manager_title"><i></i><h2>模块管理</h2>
		   	<div class="clear"></div>
		</div>
		
		<!--ztree-->
		<div id="treeDemo" class="ztree">
		</div>
		<!---->
		<div class="f-footer">
			<a href="javascript:void(0);" id="is_expand" title="展开/收起" class="btn">展开/收起</a>&nbsp;
			<a href="javascript:void(0);" onclick="javascript:insertNode();" id="is_expand" title="新建节点" class="btn">新建节点</a>
			<a href="javascript:void(0);" onclick="javascript:updateNode();" id="is_expand" title="修改节点" class="btn">修改节点</a>
			<a href="javascript:void(0);" onclick="javascript:deleteNode();" id="is_expand" title="删除节点" class="btn">删除节点</a>
		</div>
		
	</div>
</div>
</html>