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
	protected Page page;
	/**
	 * @param request
	 * @param qString ��ѯ�������� -->  a=1&b=2 �ṹ
	 * @return Page
	 */
	public final Page initPage(HttpServletRequest request,int pageSize){
		page = new Page();
		
		//1�� ��ȡ�ڼ�ҳ pageIndex
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
		//��ȡ��ѯ����
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
		//��ȡ������ȷ��getTatal()����֮ǰ�ѽ�args����map�У�
		String sum = request.getParameter("tatal");
		if(sum==null||sum.equals("")){
			page.setTatal(getTatal(argsmap));
		}else{
			page.setTatal(Integer.parseInt(sum));
		}
		argsmap.put("tatal", page.getTatal());
		page.setArgsmap(argsmap);
		
		//2����ȡ·��
		page.setUrl(request.getServletPath().substring(1));		//   "/getSoluitonlist.do"
		
		//querystring
		String queryString = argsFactory(argsmap);
		page.setQueryString(queryString);
		
		
		
		//4����ȡ��ҳ��
		page.setPageNum(page.getTatal()/page.getPageSize() + (page.getTatal()%page.getPageSize()==0?0:1));
		
		
		//6����һҳ
		page.setPrePage(page.getPageIndex()-1<=0?1:page.getPageIndex()-1);
		
		//7����һҳ
		page.setNextPage(page.getPageIndex()+1>=page.getPageNum()?page.getPageNum():page.getPageIndex()+1);
		
		//8����ҳ
		page.setFirstPage(1);
		
		//9��βҳ
		page.setLastPage(page.getPageNum());
		
		//10��ҳ��չʾ 
		
		List<Integer> rowList = new ArrayList<Integer>();
		rowList.add(page.getPageIndex());
		RowListUtil.init(page.getPageIndex(), page.getPageNum(),SHOWLIST);
		for(int i = 1;i<SHOWLIST;i++){
			RowListUtil.initRowList(rowList);
		}
		page = RowListUtil.addBounds(rowList,page);
		page.setRowList(rowList);
		
		
		//11��mysql��ѯʱ��start �� end
		page.setStart((page.getPageIndex()-1)*page.getPageSize());
		page.setEnd(page.getPageSize());
		//�������request��
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
	public abstract Object pagination(int pageSize);
	
}

