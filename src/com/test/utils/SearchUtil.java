package com.test.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.test.model.Solution;

public class SearchUtil {
	private final static int RANGE = 100;
	private final static List<String> getKey(String key){
		key = key.replaceAll("\\s{1,}", " ");
		String[] keyarr = key.split(" ");
		List<String> keylist = new ArrayList<String>();
		for(String ss:keyarr){
			if(!keylist.contains(ss)){
				keylist.add(ss);
			}
		}
		return keylist;
	}
	private static Solution getRanking(String key,Solution solution){
		int hit_count1 = new Recursion(solution.getTitle(), key).getHitCount()*RANGE;
		int hit_count2 = new Recursion(solution.getContent(), key).getHitCount()*RANGE;
		solution.setRanking(hit_count1 + hit_count2 + solution.getRanking());
		return solution;
	}
	private static void sort(List<Solution> list,List<String> keyList){
		for(String key : keyList){
			for(Solution solution :list){
				solution = getRanking(key,solution);
			}
		}
		
		Collections.sort(list);
		Collections.reverse(list);
	}
	
	public static String getSumaryContent(String content,String key,List<Integer> sequencelist){
		//最大字数200字 	 前后间隔10字
		List<Integer> list = new ArrayList<Integer>();
		String result_content = "";
		for(int i = 0;i<sequencelist.size()-1;i++){
			list.add(sequencelist.get(i)-10);
			list.add(sequencelist.get(i)+10+key.length());
		}
		for(int i = 1 ; i <list.size()-1 ; i++){
			if(list.get(i) > list.get(i+1)){
				list.remove(i);
				list.remove(i+1);
			}
		}
		for(int i = 0;i<list.size();){
			result_content += content.substring(Math.max(0,list.get(i)),list.get(i+1));
			result_content +="...";
			i = i+2;
		}
		return result_content;
	}
	public List<Solution> searchSolution(String keys,List<Solution> list,int start ,int end){
		//排序
		List<String> keyList = getKey(keys);
		sort(list,keyList);
		//--end of 排序
		
		List<Solution> resultList = list.subList(start, end);
		for(Solution solution : resultList){
			SumaryView sumaryView = new SumaryView();
			sumaryView.resetList();
			for(String key : keyList){
				sumaryView.getHitIndex(solution.getContent(), key, 0);
			}
			solution.setContent(sumaryView.getSumaryContent(solution.getContent()));
			for(String key : keyList){
				if(solution.getTitle()!=null&&!"".equals(solution.getTitle())){
					solution.setTitle(solution.getTitle().replaceAll(key, "<font color='"+"red'>"+key+"</font>"));
				}
				if(solution.getContent()!=null&&!"".equals(solution.getContent())){
					solution.setContent(solution.getContent().replaceAll(key, "<font color='"+"red'>"+key+"</font>"));
				}
			}
		}
		return resultList;
	}
	public List<Solution> searchSolution(String keys,List<Solution> list,Page page){
		int start = (page.getPageIndex()-1)*page.getPageSize();
		int end = page.getPageIndex()*page.getPageSize()>page.getTatal()?page.getTatal():page.getPageIndex()*page.getPageSize();
		return searchSolution(keys,list,start,end);
	}
	/**
	 * 通过递归方式，获取一段文本中跟某个关键字的匹配度
	 * getHitCount反应了匹配程度
	 * 
	 * @author 1207264
	 *
	 */
	static class Recursion {
		private int hit_count = 0;
		public Recursion(String content,String key){
			recursion(content,key,0);
		}
		private void recursion(String content,String key,int i){
			if(content!=null&&!"".equals(content)){
				int next_i ;
				if((next_i = content.indexOf(key,i))> 0 && next_i >= i){
					hit_count ++ ;
					recursion(content,key,next_i+1);
				}
			}
		}
		public int getHitCount(){
			return hit_count;
		}
	}
	/**
	 * 获取文本匹配关键字之后的预览
	 * 
	 * 预览处理主要将包含关键字部分及其附近的文本选取出来
	 * @author 1207264
	 *
	 */
	class SumaryView{
		private final int ITEMLENG= 10;
		List<Integer> sequencelist;
		public void resetList(){
			sequencelist = new ArrayList<Integer>();
		}
		private String result_content="";
		public SumaryView(){}
		public void getHitIndex(String content,String key,int index){
			getSumaryIndexList(content,key,index);
			sequencelist.remove(sequencelist.size()-1);
		}
		private List<Integer> getSumaryIndexList(String content,String key,int index){
			int next = content.indexOf(key,index);
			sequencelist.add(next);
			if(next>=index){
				getSumaryIndexList(content,key,next+1);
			}
			return sequencelist;
		}
		private List<Integer> getListTruncated(List<Integer> list){
			List<Integer> truncatedList = new ArrayList<Integer>();
			if(list!=null&&list.size()>0){
				truncatedList.add(list.get(0));
				for(int i = 1 ; i <list.size()-1 ; i+=2){
					if( list.get(1) < list.get(i+1)){
						truncatedList.add(list.get(i));
						truncatedList.add(list.get(i+1));
					}
				}
				truncatedList.add(list.get(list.size()-1));
			}
			return truncatedList;
		}
		public String getSumaryContent(String content){
			//最大字数200字 	 前后间隔10字
			List<Integer> list = new ArrayList<Integer>();
			Collections.sort(sequencelist);
			for(int i = 0;i<sequencelist.size();i++){
				list.add(sequencelist.get(i)-ITEMLENG);
				list.add(sequencelist.get(i)+ITEMLENG+5);	
			}
			List<Integer> truncatedList = getListTruncated(list);
			for(int i = 0;i<truncatedList.size();){
				result_content += content.substring(Math.max(0,truncatedList.get(i)),(Math.min(content.length(), truncatedList.get(i+1))));
				result_content +="<font size='"+3+"'>&nbsp;...</font>";
				i = i+2;
			}
			return result_content;
		}
	}
	public static void main(String[] args) {
		List<Solution> list = new ArrayList<Solution>();
		Solution s = new Solution();
		String content="汉子2432432423423333333333汉d333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333汉d333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333汉d333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333汉d333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333汉d333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423汉子2432432423423333333333333333333333432432423423423";
		s.setContent(content);
		list.add(s);
		List<Solution> li = new SearchUtil().searchSolution("333汉d 汉d 汉",list,0,1);
		String ss = li.get(0).getContent();
		System.out.println(ss);
		
	}
}
