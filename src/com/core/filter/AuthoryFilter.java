package com.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.utils.HttpGetServletRequestWrapper;
public class AuthoryFilter implements Filter {
	protected FilterConfig filterConfig;
	private static ThreadLocal<HttpServletRequest> requestLocal= new ThreadLocal<HttpServletRequest>();
	public static ThreadLocal<HttpServletRequest> getRequestLocal() {
		return requestLocal;
	}
	public static void setRequestLocal(ThreadLocal<HttpServletRequest> requestLocal) {
		AuthoryFilter.requestLocal = requestLocal;
	}
	public void init(FilterConfig arg0) throws ServletException {
		this.filterConfig = arg0;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		SystemSession.setResponse((HttpServletResponse) response);
		if(((HttpServletRequest) request).getMethod().equalsIgnoreCase("get")){
			SystemSession.setRequest(new HttpGetServletRequestWrapper((HttpServletRequest) request));  
			chain.doFilter(new HttpGetServletRequestWrapper((HttpServletRequest) request), response);
		}else{
			SystemSession.setRequest((HttpServletRequest) request);  
			chain.doFilter(request, response);
		}
	}
	public void destroy() {
		this.filterConfig = null;
	}
}