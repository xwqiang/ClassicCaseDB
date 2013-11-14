package com.test.utils;

import java.security.MessageDigest;
import java.util.UUID;
/**
 * MD5º”√‹π§æﬂ
 * @author zjf
 * @time 1:18:24 PM
 */
public class MD5 {
	 public static String convert(String s){ 
	        char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'}; 
	        try { 
	            byte[] bytes = s.getBytes(); 
	            MessageDigest md = MessageDigest.getInstance("MD5"); 
	            md.update(bytes); 
	            bytes = md.digest(); 
	            int j = bytes.length; 
	            char[] chars = new char[j * 2]; 
	            int k = 0; 
	            for (int i = 0; i < bytes.length; i++) { 
	                byte b = bytes[i]; 
	                chars[k++] = hexChars[b >>> 4 & 0xf]; 
	                chars[k++] = hexChars[b & 0xf]; 
	            } 
	            return new String(chars); 
	        } 
	        catch (Exception e){ 
	            return null; 
	        } 


	 }
	 /**
	  * UUID
	  * @return
	  */
	 public static String getUUID(){
		    UUID uuid = UUID.randomUUID();
		    return uuid.toString().replace("-","");
		    
	}		
	 public static void main(String [] args){
		 System.out.println(MD5.convert("123456"));
	 }
}
