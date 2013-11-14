<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="auth" uri="/WEB-INF/authtaglib.tld"%>
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
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>menu</title>
<link type="text/css" rel="stylesheet" href="css/common.css" />

<link rel="stylesheet" href="js/artDialog/default.css" type="text/css">

<link rel="stylesheet" href="js/ztree/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="js/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="js/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="js/ztree/js/jquery.ztree.exedit-3.5.js"></script>


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
					$("#mainFrame").attr("src","getSolutionListByMenuId.do?menu_id="+treeNode.id);
			}	
		}
		
		function beforeEditName(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			return true;
		}
		function beforeRemove(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.selectNode(treeNode);
			return confirm("确认删除 节点 :" + treeNode.name + " 吗？");
		}
		function onRemove(e, treeId, treeNode) {
			var data ="id="+treeNode.id;
			$.ajax({
				type:"post",
				data:data,
				url:"deleteMenuById.do",
				success:function(txt){
					if(txt=="success"){
					}else{
						alert("删除失败");
					}
				}
			});
		}
		function beforeRename(treeId, treeNode, newName) {
			className = (className === "dark" ? "":"dark");
			if (newName.length == 0) {
				alert("节点名称不能为空.");
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				setTimeout(function(){zTree.editName(treeNode);}, 10);
				return false;
			}
			return true;
		}
		function onRename(e, treeId, treeNode) {
			var data ="id="+treeNode.id+"&name="+encodeURIComponent(treeNode.name);
			$.ajax({
				type:"post",
				data:data,
				url:"updateMenuName.do",
				success:function(txt){
					if(txt=="success"){
					}else{
						alert("修改失败");
					}
				}
			});
		}
		
		
		
		
		function insertNode(){
			var html =[
			           "模块名称：<input name='name' size='20' id='name' type='text' ></input>"
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
			        if($.trim(name.value)=="" ||$.trim(name.value)==null){
			        	name.select();
				    	name.focus();
				    	this.shake();
				    	return false;
					}else{
						var zTree = $.fn.zTree.getZTreeObj("treeDemo");
						var thisNode = zTree.getSelectedNodes();
						var pId = thisNode && thisNode[0]&&thisNode[0].id||0;
						var data = "parent_id="+pId+"&name="+name.value;
						$.ajax({
							type: "post",
							data: data,
							url: "insertNewMenu.do",
							success: function(txt){
								if(!isNaN(txt)){
									var newNode ={id: txt, pId:pId, name:name.value};
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
			if(thisNode&&thisNode[0]){
				var html =[
				           "模块名称：<input name='name' size='20' id='name' type='text' value="+thisNode[0].name+"></input>"
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
				        if($.trim(name.value)=="" ||$.trim(name.value)==null){
				        	name.select();
					    	name.focus();
					    	this.shake();
					    	return false;
						}else{
							var id = thisNode[0].id;
							var data = "id="+id+"&name="+name.value;
							$.ajax({
								type: "post",
								data: data,
								url: "updateMenuName.do",
								success: function(txt){
									if(txt=="success"){
										thisNode[0].name=name.value;
										zTree.updateNode(thisNode[0]);
									}else{
										alert("更新失败");
									}
								}
							});
						}
				    }
				});
			}else{
				alert("请选择要修改的节点");
			}
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
						url: "deleteMenuById.do",
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
			if($("#moveNcopy").length>0){
				return true;
			}else{
				return false;
			}
		}
		function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
			if(confirm("确定要移动"+treeNodes[0].name+" 到 "+targetNode.name)){
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
						alert("更新失败,清刷新");
					}
				}
			});
		};
		function zTreeOnDblClick(event, treeId, treeNode) {
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			if (treeNode.length>0) {
				treeObj.expandNode(treeNode, true, false, true);
			}
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.id).unbind().remove();
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
						url:"",
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
					beforeRemove: beforeRemove,
					onRemove: onRemove,
					beforeClick: beforeClick,
					onClick: onClick,
					onDrop: zTreeOnDrop,
					onExpand: zTreeOnExpand,
					onDblClick: zTreeOnDblClick
				}
			};
		$(function(){
			zNodes = ${json};
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
</head>
<body>


<div class="logo">
	<h1><a href="javascript:window.location.reload();" title="点击返回首页"><img src="images/logo.gif" alt="知识库" width="144" height="78" /></a></h1>
</div>
<div class="op_links">&nbsp;
<a href="javascript:void(0);" id="is_expand" title="全部展开/收起">全部展开</a>&nbsp;
<auth:auth action="insertMenu.label">
	<a href="javascript:insertNode()">新建</a>
</auth:auth>
<auth:auth action="updateMenu.label">
	<a href="javascript:updateNode()">修改</a>
</auth:auth>
<auth:auth action="deleteMenu.label">
	<a href="javascript:deleteNode()">删除</a>
</auth:auth>
</div>
<!--ztree-->
<div id="treeDemo" class="ztree">
</div>        
<!---->
<div class="menu_item"><a href="listCaseShare.do" target="mainFrame" class="menu_btn">案例分享</a></div>

<div class="sider_info"><a href="javascript:void(0);">使用规则说明</a>
<p>&nbsp;</p>
<p>
 	* 您可以使用方向键控制菜单位置
</p>
<auth:auth action="moveNcopyMenu.label">
	<p id="moveNcopy"> * 拖动菜单可以实现移动功能；按住ctrl键，拖动菜单可以实现复制功能</p>
</auth:auth>
<auth:auth action="insertMenu.label">
	<p> * 当未选中任何节点时，选择新建按钮则为新建根节点</p>
</auth:auth>
</div>
</html>