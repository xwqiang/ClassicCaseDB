package com.test.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public abstract class IPageService {
	private Map<String,Object> argsmap = new HashMap<String,Object>();
	private final int SHOWLIST = 10;
	private Page page;
	/**
	 * @param request
	 * @param qString 查询参数类似 -->  a=1&b=2 结构
	 * @return Page
	 */
	public Page initPage(HttpServletRequest request,int pageSize,String... args){
		page = new Page();
		
		//1、 获取第几页 pageIndex
		String pIndex= request.getParameter("pageIndex");
		if (pIndex != null&&!"".equals(pIndex)) {
			page.setPageIndex(Integer.parseInt(pIndex.trim()));
		}else{
			page.setPageIndex(1);
		}
		
		String pSize = request.getParameter("pageSize");
		if(pSize!=null&&!"".equals(pSize)){
			page.setPageSize(Integer.parseInt(pSize));
		}else{
			page.setPageSize(pageSize);
		}
		
		argsmap.put("pageSize", page.getPageSize());
		//获取查询参数
		Enumeration<String> querynames = request.getParameterNames();
		while(querynames.hasMoreElements()){
			String key = null;
			String value = request.getParameter(key = querynames.nextElement());
			argsmap.put(key, value);
		}
//		if(args!=null&&args.length!=0){
//			for(String p:args){
//				String value = request.getParameter(p);
//				argsmap.put(p, value);
//			}
//		}
		//获取总数（确保getTatal()方法之前已将args放入map中）
		String sum = request.getParameter("tatal");
		if(sum==null||sum.equals("")){
			page.setTatal(getTatal(argsmap));
		}else{
			page.setTatal(Integer.parseInt(sum));
		}
		argsmap.put("tatal", page.getTatal());
		page.setArgsmap(argsmap);
		
		//2、获取路径
		page.setUrl(request.getServletPath().substring(1));		//   "/getSoluitonlist.do"
		
		//querystring
		String queryString = argsFactory(argsmap);
		page.setQueryString(queryString);
		
		
		
		//4、获取总页数
		page.setPageNum(page.getTatal()/page.getPageSize() + (page.getTatal()%page.getPageSize()==0?0:1));
		
		
		//6、上一页
		page.setPrePage(page.getPageIndex()-1<=0?1:page.getPageIndex()-1);
		
		//7、下一页
		page.setNextPage(page.getPageIndex()+1>=page.getPageNum()?page.getPageNum():page.getPageIndex()+1);
		
		//8、首页
		page.setFirstPage(1);
		
		//9、尾页
		page.setLastPage(page.getPageNum());
		
		//10、页数展示 
		
		List<Integer> rowList = new ArrayList<Integer>();
		rowList.add(page.getPageIndex());
		RowListUtil.init(page.getPageIndex(), page.getPageNum(),SHOWLIST);
		for(int i = 1;i<SHOWLIST;i++){
			RowListUtil.initRowList(rowList);
		}
		page = RowListUtil.addBounds(rowList,page);
		page.setRowList(rowList);
		
		
		//11、mysql查询时的start 、 end
		page.setStart((page.getPageIndex()-1)*page.getPageSize());
		page.setEnd(page.getPageSize());
		//结果放在request中
		request.setAttribute("page", page);
		return page;
	}
	private static String argsFactory(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		String str = "&";
		String str1 = "=";
		if (map != null && map.size() > 0) {
			Set<Map.Entry<String, Object>> set = map.entrySet();
			for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it
					.hasNext();) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it
						.next();
				if (entry.getValue() != null && !entry.getValue().equals("")) {
					sb.append(str);
					sb.append(entry.getKey());
					sb.append(str1);
					sb.append(entry.getValue());
				} else {
				}
			}
		}
		return sb.toString();
	}
	public abstract int getTatal(Map<String,Object> p);
	public abstract Object pagination(int pageSize,String...args);
	
}

