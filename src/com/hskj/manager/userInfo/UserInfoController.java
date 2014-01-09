package com.hskj.manager.userInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.UserInfo;
import com.test.service.UserService;
import com.test.utils.MD5;
@Controller
public class UserInfoController {
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="listUserInfo")
	public String listUserInfo(String user_id,String user_name,ModelMap modelMap){
		List<UserInfo> userList = userService.pagination(15).getUserInfoList(user_id,user_name);
		modelMap.addAttribute("userlist",userList);
		return "manager/userInfo/listUserInfo";
	}
	
	
	/**
	 * 新建用户
	 * @param user_id
	 * @param user_name
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="insertUserInfo")
	public String insertUserInfo(String user_id,String user_name,String user_pwd,ModelMap modelMap){
		UserInfo userInfo = new UserInfo();
		userInfo.setUser_id(user_id);
		userInfo.setUser_name(user_name);
		userInfo.setUser_pwd(MD5.convert(user_pwd));
		UserInfo u = userService.getUserInfoByUserId(user_id);
		if(u!=null){
			modelMap.addAttribute("message","该用户id已经存在");
			return "forward";
		}
		boolean flag = userService.insertUserInfo(userInfo);
		if(flag){
			modelMap.addAttribute("message","添加成功").addAttribute("url","listUserInfo.do");
		}else{
			modelMap.addAttribute("message","添加失败");
		}
		return "forward";
	}
	
	@RequestMapping(value="updateUserInfo")
	public String updateUserInfo(int id,String user_id,String user_name,ModelMap modelMap,HttpServletResponse response){
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UserInfo userInfo = userService.getUserInfoById(id);
		if(!userInfo.getUser_id().equals(user_id)){
			UserInfo u = userService.getUserInfoByUserId(user_id);
			if(u!=null){
				out.write("duplicated");
				return null;
			}
		}
		userInfo.setUser_id(user_id);
		userInfo.setUser_name(user_name);
		boolean flag = userService.updateUserInfoById(userInfo);
		if(flag){
			out.write("success");
		}else{
			out.write("fail");
		}
		return null;
	}
	
	@RequestMapping(value="showInsertUserInfo")
	public String showInsertUserInfo(){
		return "manager/userInfo/addUserInfo";
	}
	
	@RequestMapping(value="deleteUserInfo")
	public String deleteUserInfo(int id,HttpServletResponse response){
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean flag = userService.deleteUserInfoById(id);
		if(flag){
			out.write("success");
		}else{
			out.write("fail");
		}
		return null;
	}
	
}