package com.test.utils;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class HttpGetServletRequestWrapper extends HttpServletRequestWrapper {
	private String charset = "UTF-8";
	public HttpGetServletRequestWrapper(HttpServletRequest request, String charset) {
		super(request);
		this.charset = charset;
	}
	public HttpGetServletRequestWrapper(HttpServletRequest request) {
		super(request);
	}
	public String getParameter(String name){
		String value = super.getParameter(name);
		value = value ==null?null:convert(value);
		return value;
	}
	private String convert(String value) {
		try {
			return new String(value.getBytes("ISO-8859-1"),charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return value;
		}
	}

}
