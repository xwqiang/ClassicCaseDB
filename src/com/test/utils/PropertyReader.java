package com.test.utils;

import java.io.IOException;
import java.util.Properties;

import com.test.controller.SSOLogin;

public class PropertyReader {
	private static Properties prop = new Properties();
	public PropertyReader(){
		try {
			prop.load(SSOLogin.class.getClassLoader().getResourceAsStream("SSOServer.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public PropertyReader(String source){
		try {
			prop.load(SSOLogin.class.getClassLoader().getResourceAsStream(source));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getSSOProperty(String key){
		return prop.getProperty(key);
	}
}
