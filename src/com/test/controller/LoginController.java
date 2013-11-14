package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.model.Menu;
import com.test.model.Module;
import com.test.model.UserInfo;
import com.test.service.MenuService;
import com.test.service.UserRoleService;
import com.test.service.UserService;
import com.test.utils.JSONUtil;
import com.test.utils.MD5;
@Controller
public class LoginController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleService userRoleService ;
	
	
	@RequestMapping(value="localLogin")
	public String localLogin(@RequestParam(value="user_id",required=false) String user_id,
			@RequestParam(value="user_pwd",required=false) String user_pwd,
			HttpServletRequest request,ModelMap modelMap){
		
		List<UserInfo> userInfoList= userService.getUserByIdPwd(user_id,MD5.convert(user_pwd));
		if(userInfoList!=null&&userInfoList.size()>0){
			List<Module> moduleList = userRoleService.getModuleListByUserId(userInfoList.get(0).getId());
			request.getSession().setAttribute("modulelist",moduleList);
			request.getSession().setAttribute("USER", userInfoList.get(0));
			return "redirect:showWelcomePage.do";
		}else{
			return "login";
		}
		
	}

	@RequestMapping(value="logout")
	public String adminlogout(HttpServletRequest request){
		request.getSession().invalidate();
		return "login";
	}
	
	
	
	@RequestMapping(value="adminlogin")
	public String loginWithoutValidate(UserInfo user,HttpServletRequest request,ModelMap modelMap){
		user.setUser_id("111");
		user.setUser_name("¿ª·¢²âÊÔ");
		request.getSession().setAttribute("USER", user);
		List<Menu> list = menuService.getMenuList();
		String json = JSONUtil.changeJSON(list);
		modelMap.addAttribute("json",json);
		return "index";
	}
	public static void main(String[] args) {
		System.out.println(MD5.convert("111"));
	}
}