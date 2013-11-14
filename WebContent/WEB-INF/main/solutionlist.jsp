<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/authtaglib" prefix="s"%>
<%@ include file="/common/kindEditor.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "方案内容";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath %>" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
<link type="text/css" rel="stylesheet" href="<%=path %>/css/content.css" />
<link rel="stylesheet" type="text/css" href="js/fancyBox/source/jquery.fancybox.css?v=2.1.5" media="screen" />

<script type="text/javascript" src="js/sly/jquery.min.js"></script>
<script type="text/javascript" src="js/fancyBox/source/jquery.fancybox.js?v=2.1.5"></script>

<script type="text/javascript" src="js/sly/plugins.js"></script>
<script type="text/javascript" src="js/sly/sly.min.js"></script>
<script type="text/javascript" src="js/sly/fullpage.js"></script>

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
$(function(){
	KindEditor.ready(function(K) {
		var editor = K.create('#mysolution', {
			showRemote : false,
			cssPath : 'js/kindeditor-4.1.7/plugins/code/prettify.css',
			uploadJson : 'upload.do',
			fileManagerJson : '../jsp/file_manager_json.jsp',
			allowFileManager : true,
			items : [  //配置工具栏
			           'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
			           'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
			           'insertunorderedlist', '|', 'emoticons', 'image', 'link', '|' ,'table'],
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					if(beforeSubmit()){
						document.forms['example'].submit();
					}
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					if(beforeSubmit()){
						document.forms['example'].submit();
					}
				});
				$("#sbtSolution").click(function(){
					self.sync();
				});
			}
		});
		//prettyPrint();
	});
});

function trigger_edit_title(id){
	$("#solution_title_"+id).hide();
	$("#edit_title_btn_"+id).show();
}
function saveTitle(id){
	var data = "title="+$("#edit_title_content_"+id).val()+"&id="+id;
	var url = "<%=path%>/updateSolutionTitleById.do";
	$.ajax({
		type:"post",
		url:url,
		async: false,
		data:data,
		success:function(txt){
			if(txt=="success"){
				alert("修改成功");
				$("#solution_title_"+id).html($("#edit_title_content_"+id).val());
				$("#solution_title_"+id).show();
				$("#edit_title_btn_"+id).hide();
			}else{
				alert("修改失败");
			}
		}
	});
}
function cancelSaveTitle(id){
	$("#edit_title_btn_"+id).hide();
	$("#solution_title_"+id).show();
}
function modSolution(id){
	window.location.href="showUpdateSolutionById.do?id="+id;
}
function deleteSolution(id){
	var flag = confirm("确定要删除吗？");
	if(flag){
		var url="<%=path%>/deleteSolutionById.do?id="+id;
		$.ajax({
			type:"post",
			url:url,
			data:'',
			success:function(txt){
				if(txt=="success"){
					alert("删除成功");
					window.location.reload();
				}else{
					alert(txt);
				}
			}
		});
	}
}
function beforeSubmit(){
	if($.trim($("#mysolution").val())==""){
		alert("请填写内容后提交！");
		return false;
	}
	if($.trim($("#solutionTitle").val())==""||$.trim($("#solutionTitle").val())=="请输入方案标题"){
		alert("请填写标题后提交！");
		return false;
	}
	if($("#solutionTitle").val().len>200){
		alert("标题内容不能超过200字！");
		return false;
	}
	return true;
}

function addRemarkTo(solution_id,remark,user_id,insert_time){
	var html =[
				'<div class=remark_list><p class=gray>'+user_id+'&nbsp;'+insert_time+'</p><div>'+remark+'</div></div>'
	           ];
	$(html.join(",")).prependTo($("#remark_list_"+solution_id));
}


function ajaxloadRemark(solution_id){
	var data = "solution_id="+solution_id;
	$.ajax({
		data: data,
		type: 'post',
		url: 'showRemarkList.do',
		success: function(txt){
			if(typeof(JSON)!='undefined'){
				txt=JSON.parse(txt);
			}else{
				txt=eval(txt);
			}
			for(var i in txt){
				if(typeof(txt[i])!='function' && txt[i].hasOwnProperty("remark")){
					addRemarkTo(solution_id,txt[i].remark,txt[i].user_id,txt[i].insert_time);
				}
			}
		}
	});
}
function hideRemark(solution_id){
	$("#show_remark_list_"+solution_id).slideUp('slow',function(){
		$("#remark_list_"+solution_id).empty();
		$(window).resize();
	});
}
function showRemarkList(solution_id){
	if($("#show_remark_list_"+solution_id).css("display")=='block'){		//hide
		hideRemark(solution_id);
	}else{		//show
		ajaxloadRemark(solution_id);
		$("#show_remark_list_"+solution_id).slideDown('slow',function(){
			$(window).resize();
		});
	}
	
}

function insertRemark(solution_id){
	var data = "remark="+encodeURIComponent($("#remark").val())+"&solution_id="+solution_id;
	$.ajax({
		type: 'post',
		data: data,
		url: 'insertRemark.do',
		success: function(txt){
			if(txt=='fail'){
				alert("插入失败");
				return ;
			}
			if(typeof(JSON)!='undefined'){
				txt=JSON.parse(txt);
			}else{
				txt=eval(txt);
			}
			addRemarkTo(solution_id,txt[0].remark,txt[0].user_id,txt[0].insert_time);
			$("#remark").val("");
			$(window).resize();
		}
	});
}

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
	<div id="scrollbar" class="scrollbar">
		<div class="handle">
		</div>
	</div>
	
	<div id="frame" class="frame" style="overflow:hidden;"><div>
	
    <!--content-->
    <div class="q_title"><i></i><h2>${requestScope.menu.name}</h2>
   	<div class="clear"></div>
    </div>
    
    <div class="q_ol">
	    <ol>
	        <c:forEach items="${solutionlist }" var="solution">
		    <li>
		    	<div class="q_ol_title clearfix">
	    			<h3><a id="solution_title_${solution.id }" href="javascript:void(0);" title="${solution.title}">${solution.title}</a></h3>
	    			<div class="actions">
	                    <div class="inr">
	                    <s:auth action="updateSolutionTitle.label">
	                    	<a href="javascript:trigger_edit_title('${solution.id }');" title="编辑标题">编辑标题</a>	                    	
	                    </s:auth>
	                    </div>
	                </div>
	                
	                <div class="q_t_edit" id="edit_title_btn_${solution.id }">
	                	                	
	               		  <input type="text" value="${solution.title}" id="edit_title_content_${solution.id }" class="ipt_edit" />
	                        <div class="q_e_btn">
	                        	<input type="submit" title="保存" onclick="javascript:saveTitle('${solution.id }');" id="edit_title_saveBtn_${solution.id }"  value="保存" class="e_sbtn" onmouseover="this.className='e_sbtn_up'" onmouseout="this.className='e_sbtn'" onmousedown="this.className='e_sbtn_down'" onmouseup="this.className='e_sbtn_up'"/>
	                       	    <input type="button"  value="取消" title="取消" onclick="javascript:cancelSaveTitle('${solution.id}');" id="edit_title_cancelBtn_${solution.id }" class="e_btn" onmouseover="this.className='e_btn_up'" onmouseout="this.className='e_btn'" onmousedown="this.className='e_btn_down'" onmouseup="this.className='e_btn_up'"/>
	                      	</div>
	                </div>
	                
	                <div>作者：${solution.user_name } &nbsp;&nbsp;修改日期：${solution.update_time }</div>
		    	</div>
		        <div class="q_ol_content" id="solution_div_${solution.id }">
		         	<div class="test_box" id="content_box_${solution.id }">${solution.content}</div>
		         	<div class="q_operate">
		         		<a href="javascript:void(0);" onclick="javascript:showRemarkList('${solution.id }');" class="q_btn" title="评论">评论</a>
		         		<span class="q_i"><a href="javascript:void(0);" onclick="javascript:supportIt(this,'${solution.id}')" class="q_btn" title="赞">赞 </a><span>${solution.ranking }</span></span>
		         		<s:auth action="updateSolution.label">
			         		<a href="javascript:modSolution('${solution.id }');" class="q_btn" title="修改">修改</a>
		         		</s:auth>
		         		<s:auth action="deleteSolution.label">
			         		<a href="javascript:deleteSolution('${solution.id }');" class="q_btn" title="删除">删除</a>
		         		</s:auth>
		         	</div>
		         	
		         	<div id="show_remark_list_${solution.id }" class="show_remark" style="display:none">
		        		<textarea name="remark" id="remark" onfocus="this.value=''; this.onfocus=null;">请输入评论</textarea>
		        		<div class="d_ipt">
		        			<input type="button" value="取消" onclick="javascript:hideRemark('${solution.id}');" class="ipt_btn" />
		        			<input type="button" value="提交评论" onclick="javascript:insertRemark('${solution.id}');" class="ipt_btn" />
		        		</div>
				        <div id="remark_list_${solution.id }">
			       		</div>
		        	</div>
		        	
		        </div> 		        	
	        </li>
	        
	        </c:forEach>
	    </ol>
    </div>
    <jsp:include page="/common/pager.jsp"></jsp:include>
    
    <!--我的解决方案-->
    <s:auth action="insertSolution.label">
    <div class="remark">
    	<h3>我的解决方案</h3>
    	<form name="example" action="insertSolution.do" method="post" onsubmit="javascript:return beforeSubmit();">
	    	<input type="hidden" name="menu_id" id="menu_id" value="${menu.id}"/>
	    	<input type="text" id="solutionTitle" name="title" class="remark_ipt" value="请输入方案标题" onfocus="if (value =='请输入方案标题'){value =''}" onblur="if (value ==''){value='请输入方案标题'}" />
	    	<textarea id="mysolution" name="content" class="remark_txt" rows="2" style="height:140px;"></textarea>
	    	<div class="r_btn">
	        	<input type="submit" id="sbtSolution" value="提交方案" title="提交方案" class="remark_btn" onmouseover="this.className='remark_btn_up'" onmouseout="this.className='remark_btn'" onmousedown="this.className='remark_btn_down'" onmouseup="this.className='remark_btn_up'" />
    		</div>
    	</form>
    </div>
    </s:auth>

	</div></div>

 </div>
  </body>
  </html>
