package com.test.utils;

import java.util.List;

public class RowListUtil {
	private static int SHOWlISTSIZE = 10 ;//һ���оٶ��ٸ�
	private static int status ;//0 ��� 1 �ұ�
	private static int indexLeft ;//���ҳ��
	private static int indexRight ;//�ұ�ҳ��
	private static int leftBound ;//��߽߱�
	private static int rightBound ;//�ұ߽߱�
	private static int PAGENUM ;//һ������ҳ
	public static void init(int pageIndex,int pageNum,int showlist){
		//��ʼ��
		status = 0;
		leftBound= -1;
		rightBound = -1;
		
		SHOWlISTSIZE = showlist;
		indexLeft = pageIndex;
		indexRight = pageIndex;
		PAGENUM = pageNum;
	}
	public static List<Integer> initRowList(List<Integer> list){
		
		//�жϱ߽�����ʱ�����Ǳ����
		if(indexLeft<=1&&indexRight>=PAGENUM){
			status = -1;//������
			return list;
		}else if(indexLeft<=1){
			status=1;//�ұ�(��߼�����)
			leftBound = -2;
		}else if(indexRight>=PAGENUM){
			status = 0;//��ߣ��ұ߼����ˣ�
			rightBound = -2;
		}
		//��� ���л��´���ӵ�λ��
		if(status==0){//���
			list.add(0,--indexLeft);
		}else if(status==1){
			list.add(list.size(),++indexRight);
		}else{
			return list;
		}
		status=(++status)%2;//�л�
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
