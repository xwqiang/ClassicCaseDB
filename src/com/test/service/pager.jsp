<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
		$(function(){
			document.onkeydown=function(event){
				var e = event||window.event||arguments.callee.caller.arguments[0];
				if(e&&e.keyCode==13){
					jumpPage();
				}
			}
		});
			function jumpPage(){
		   	 	var allCount=parseInt($("#jumpPageCount").val());
		    	var arg=document.getElementById("jumpPage").value;
		    	var url=document.getElementById("jumpPageUrl").value;
		    	var jumpPagequeryString=document.getElementById("jumpPagequeryString").value;
		    	if(arg ==null || arg.replace(/\ /g,"") ==""){
		        	return false;
		    	}else if(isNaN(arg)){
		    	}else{
		        	var inputCount=parseInt(arg);
		        	if(inputCount<=0){
		           inputCount=1;
		        }else if(inputCount>allCount){
		           inputCount=allCount;
		        }
		        location.href=url+"?pageIndex="+inputCount+jumpPagequeryString;
		    	}
			}
  </script>
    	<!-- 分页page start -->
            <c:if test="${not empty  page and page.tatal>page.pageSize}">
			<div class="pages clearfix s_t_page">
            	<ul class="fl">
            	   <li class="p"><a href="${page.url}?pageIndex=${page.firstPage }&${page.queryString}">&lt;&lt;首页</a></li>
                
                   <li><a href="${page.url}?pageIndex=${ page.prePage}&${page.queryString}" class="ph">&lt;上一页</a></li>

                   
                   <li><a href="${page.url}?pageIndex=${page.nextPage}&${page.queryString}" class="ph">下一页&gt;</a></li>
                   
                   <li><a href="${page.url}?pageIndex=${page.lastPage}&${page.queryString}" class="ph">尾页&gt;&gt;</a></li>                
                </ul>
                <ul class="fr">
                   <li>共${page.pageIndex } / ${page.pageNum} 页记录</li>
                
                   <li class="p_p15">跳转至</li>
                   <li><input type="text" name="jumpPage" id="jumpPage" class="page_ipt" /> 页
                       <input type="hidden" id="jumpPageCount" name="jumpPageCount" value="${page.pageNum}" class="page_ipt" />
                       <input type="hidden" id="jumpPagequeryString" name="jumpPagequeryString" value="${page.queryString}" class="page_ipt" />
					   <input type="hidden" id="jumpPageUrl" name="jumpPageUrl" value="${page.url}" class="page_ipt" />
				   </li>   
                   <li><input type="button" value="跳转" class="ipt_btn page_btn" onclick="jumpPage();"/></li>
        		</ul>
            </div>
            </c:if>
		    <!-- 分页 page end -->