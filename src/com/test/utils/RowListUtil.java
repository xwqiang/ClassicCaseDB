package com.test.utils;

import java.util.List;

public class RowListUtil {
	private static int SHOWlISTSIZE = 10 ;//一共列举多少个
	private static int status ;//0 左边 1 右边
	private static int indexLeft ;//左边页数
	private static int indexRight ;//右边页数
	private static int leftBound ;//左边边界
	private static int rightBound ;//右边边界
	private static int PAGENUM ;//一共多少页
	public static void init(int pageIndex,int pageNum,int showlist){
		//初始化
		status = 0;
		leftBound= -1;
		rightBound = -1;
		
		SHOWlISTSIZE = showlist;
		indexLeft = pageIndex;
		indexRight = pageIndex;
		PAGENUM = pageNum;
	}
	public static List<Integer> initRowList(List<Integer> list){
		
		//判断边界问题时该网那边添加
		if(indexLeft<=1&&indexRight>=PAGENUM){
			status = -1;//加满了
			return list;
		}else if(indexLeft<=1){
			status=1;//右边(左边加满了)
			leftBound = -2;
		}else if(indexRight>=PAGENUM){
			status = 0;//左边（右边加满了）
			rightBound = -2;
		}
		//添加 并切换下次添加的位置
		if(status==0){//左边
			list.add(0,--indexLeft);
		}else if(status==1){
			list.add(list.size(),++indexRight);
		}else{
			return list;
		}
		status=(++status)%2;//切换
		return list;
	}
	public static Page addBounds(List<Integer> list,Page page){
		if(leftBound==-1){
			leftBound = list.get(list.size()/2)-SHOWlISTSIZE;
			leftBound = leftBound>1?leftBound:1;
			if(leftBound<list.get(0)){
				page.setStartMore(leftBound);
			}
		}
		if(rightBound==-1){
			rightBound = list.get(list.size()/2)+SHOWlISTSIZE;
			rightBound = rightBound<PAGENUM?rightBound:PAGENUM;
			if(rightBound>list.get(list.size()-1)){
				page.setEndMore(rightBound);
			}
		}
		return page;
	}
}
