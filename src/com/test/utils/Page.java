package com.test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")

public class Page {
	private int pageSize;//ÿҳ��ʾ����.
	private int pageNum;//������ҳ.
	private int pageIndex;//��ǰ�ڼ�ҳ.
	private int tatal;//��¼������.
	private int prePage;//��һҳ
	private int nextPage;//��һҳ
	private int firstPage;//��ҳ
	private int lastPage;//βҳ
	private String url;//url --> "/getSoluitonlist.do"
	private String queryString;//��ѯ����String,���ط�ҳ
	private Map<String,Object> argsmap ;//��ѯ������װ������ count��ѯʱֱ�ӵ��ø�map
	private int pageList;
	private List<Integer> rowList ;
	private int startMore;
	private int endMore;
	private int start ;//mysql��ҳ��ѯlimit��start
	private int end ;//mysql��ҳ��ѯlimit��end
	private boolean using;
	private String getRueryString(Map<String,String[]> map){
		return null;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getTatal() {
		return tatal;
	}
	public void setTatal(int tatal) {
		this.tatal = tatal;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageList() {
		return pageList;
	}

	public void setPageList(int pageList) {
		this.pageList = pageList;
	}

	
	public List<Integer> getRowList() {
		return rowList;
	}

	public void setRowList(List<Integer> rowList) {
		this.rowList = rowList;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public Map<String, Object> getArgsmap() {
		return argsmap;
	}

	public void setArgsmap(Map<String, Object> argsmap) {
		this.argsmap = argsmap;
	}

	public int getStartMore() {
		return startMore;
	}

	public void setStartMore(int startMore) {
		this.startMore = startMore;
	}

	public int getEndMore() {
		return endMore;
	}

	public void setEndMore(int endMore) {
		this.endMore = endMore;
	}

	public boolean isUsing() {
		return using;
	}

	public void setUsing(boolean using) {
		this.using = using;
	}
	
}
