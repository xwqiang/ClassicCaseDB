package com.test.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.test.model.Recursion;

@SuppressWarnings("rawtypes")
public class RecursionUtil {
	private static int depth;
	private List list ;
	private List<Recursion> resultList = null;
	private List<Integer> rtnList = null;
	private String id ;
	private String parent_id;
	public RecursionUtil(List list,String id,String parent_id){
		this.depth = 0;
		this.list = list;
		this.id = id;
		this.parent_id = parent_id;
		if(this.id==null){
			this.id = "id";
		}
		if(this.parent_id==null){
			this.parent_id = "parent_id";
		}
		this.resultList = new ArrayList<Recursion>();
		this.rtnList = new ArrayList<Integer>();
		copyVnK();
	}
	public RecursionUtil(List list){
		this(list,null,null);
	}
	public RecursionUtil recursion(int id){
		for(Recursion r:resultList){
			if(r.getParent_id()==id&&depth<30){
				depth++;
				rtnList.add(r.getId());
				recursion(r.getId());
			}
		}
		return this;
	}
	private void copyVnK(){
		for(Object obj:list){
			Class<? extends Object> clz = obj.getClass();
			Field[] field = clz.getDeclaredFields();
			Recursion r = new Recursion();
			for(Field f:field){
				f.setAccessible(true);
				try {
					if(f.getName().equals(id)){
						String m_id = "get"+id.substring(0,1).toUpperCase()+id.substring(1);
						Method m = obj.getClass().getDeclaredMethod(m_id);
						r.setId((Integer) m.invoke(obj));
					}
					if(f.getName().equals(parent_id)){
						String m_pid = "get"+parent_id.substring(0,1).toUpperCase()+parent_id.substring(1);
						Method m = obj.getClass().getDeclaredMethod(m_pid);
						r.setParent_id((Integer) m.invoke(obj));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			resultList.add(r);
		}
	}
	public List<Integer> getResult(){
		return rtnList;
	}
	
//	public static void main(String[] args) {
//		List list = new ArrayList<Module>();
//		Module m  = new Module();
//		m.setId(1);
//		m.setParent_id(0);
//		list.add(m);
//		
//		Module m1  = new Module();
//		m1.setId(2);
//		m1.setParent_id(1);
//		list.add(m1);
//		
//		Module m2  = new Module();
//		m2.setId(3);
//		m2.setParent_id(2);
//		list.add(m2);
//		
//		Module m3  = new Module();
//		m3.setId(4);
//		m3.setParent_id(2);
//		list.add(m3);
//		RecursionUtil r = new RecursionUtil(list);
//		r.recursion(1);
//		System.out.println();
//		System.out.print(r.getResult());
//	}
}