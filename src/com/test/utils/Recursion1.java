package com.test.utils;

public class Recursion1 {
	private int hit_count = 0;
	public Recursion1(String content,String key){
		recursion(content,key,0);
	}
	private void recursion(String content,String key,int i){
		int next_i ;
		if((next_i = content.indexOf(key,i))> 0 && next_i >= i){
				hit_count ++ ;
				recursion(content,key,next_i+1);
		}
	}
	public int getHitCount(){
		return hit_count;
	}
}
