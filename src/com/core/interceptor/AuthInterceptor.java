package com.core.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.test.model.Module;
import com.test.model.UserInfo;
public class AuthInterceptor implements HandlerInterceptor{
	private static List<Module> moduleList = null;
	String[] exCludded={"localLogin.do","logout.do","ssoLogin"};
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object obj, Exception e)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj, ModelAndView mv) throws Exception {
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		moduleList = (List<Module>) request.getSession().getAttribute("modulelist");
		UserInfo user = (UserInfo) request.getSession().getAttribute("USER");
		String url =((HttpServletRequest) request).getRequestURL().toString();
		boolean bool = hasPriority(url,moduleList,user);
		if(!isLogin(url,user)){
			request.getRequestDispatcher("localLogin.do").forward(request,response);
			return bool;
		}
		if(!bool){
			response.setStatus(403);
			request.getRequestDispatcher("/WEB-INF/main/error/noAuto.jsp").forward(request,response);
			return bool;
		}
		return bool;
	} 
	
	private boolean isLogin(String url,UserInfo user) {
		for(String ex :exCludded){
			if(url.contains(ex)){
				return true;
			}
		}
		if(user==null){
			return false;
		}
		return true;
	}

	private boolean hasPriority(String url,List<Module> moduleList,UserInfo user) {
		boolean hasPriority = false;
		boolean isIncluded = false;
		for(String ex :exCludded){
			if(url.contains(ex)){
				return true;
			}
		}
		if(moduleList!=null&&user!=null){
			for(Module m :moduleList){
				//是否纳入管理
				if(url.contains(m.getUrl())){
					isIncluded = true;
					break;
				}
			}
			if(isIncluded){
				for(Module m:moduleList){
					if(m.isChecked()&&url.contains(m.getUrl())){
						hasPriority = true;
						break;
					}
				}
			}
		}
		return hasPriority||!isIncluded;
	}

}
