package com.test.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.company.business.developInterface.Interactive;
import com.company.business.developInterface.impl.InteractiveImpl;
import com.company.business.developInterface.model.CheckedModel;
import com.test.mapper.UserDAO;
import com.test.model.Module;
import com.test.model.UserInfo;
import com.test.service.UserRoleService;
import com.test.service.UserService;
import com.test.utils.PropertyReader;
@Controller
public class SSOLogin {
	protected final transient Log log = LogFactory.getLog(SSOLogin.class);
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserService userService;
	private static PropertyReader prop = new PropertyReader();
	
	/**
	 * 采用本系统的权限进行登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/ssoLogin")
	public String userLogin(HttpServletRequest request,HttpServletResponse response){
		Interactive interactive = new InteractiveImpl();
		CheckedModel checkModel = new CheckedModel();
		checkModel.setAppId(prop.getSSOProperty("app_id"));
		checkModel.setApp_pwd(prop.getSSOProperty("app_pwd"));
		String time = request.getParameter("sso_time");
		String interactiveData = request.getParameter("interactiveData");
		String userId = request.getParameter("userId");
		checkModel.setUserId(userId);
		checkModel.setTime_from_sso_server(time);
		checkModel.setInteractiveData(interactiveData);
		String sso_server_url=prop.getSSOProperty("SSOSERVERURL");
		checkModel.setSso_server_url(sso_server_url);
		long client_time = System.currentTimeMillis();
		checkModel.setClient_time(String.valueOf(client_time));
		
		String dest="/error/error";
		
		boolean result = interactive.reveivecheckMsg(checkModel);
		if(result){
			com.company.business.developInterface.model.User ssoUser=null;
			try {
				ssoUser = interactive.getUserById(checkModel);
			} catch (HttpException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			UserInfo admin = new UserInfo();
			admin.setUser_id(userId);
			UserInfo userInfo =  userService.getUserInfoByUserId(userId);
			if(userInfo==null){
				return "/error/nosuchUser";
			}
			userInfo.setPortrait_url(ssoUser.getHeadportraint_url());
			//获取权限信息
			List<Module> moduleList = userRoleService.getModuleListByUserId(userInfo.getId());
			request.getSession().setAttribute("modulelist", moduleList);
			request.getSession().setAttribute("USER", userInfo);
			log.info("用户"+userInfo.getUser_id()+"从单点登陆本系统");
			dest = "redirect:showWelcomePage.do";
		}else{
			dest="/error/404";
		}
		return dest;
	}

	
}