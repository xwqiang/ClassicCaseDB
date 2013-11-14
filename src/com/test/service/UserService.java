package com.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.core.filter.SystemSession;
import com.test.mapper.UserDAO;
import com.test.model.UserInfo;
import com.test.utils.IPageService;
import com.test.utils.Page;
/**
 * 
 * @author 1207264
 *
 */
@Scope("prototype")
@Service
public class UserService  extends IPageService{
	@Autowired
	private UserDAO userDAO;
	private Page page = null;
	public boolean insertUserInfo(UserInfo userInfo) {
		int flag = userDAO.insertUserInfo(userInfo);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public UserInfo getUserInfoByUserId(String user_id) {
		UserInfo userInfo = userDAO.getUserInfoByUserId(user_id);
		return userInfo;
	}

	public List<UserInfo> getUserInfoList(String user_id, String user_name) {
		if(page!=null){
			List<UserInfo> list = userDAO.getUserInfoList(user_id,user_name,page.getStart(),page.getEnd());
			return list;
		}else{
			return userDAO.getUserInfoList(user_id,user_name,0,0);
		}
	}
	/**
	 * @param pageSize 每页显示多少条
	 * @return UserService
	 */
	public UserService pagination(int pageSize,String...args){
		HttpServletRequest request = SystemSession.getRequest();
		page = initPage(request,pageSize,args);
		return this;
	}
	@Override
	public int getTatal(Map<String,Object> argsmap) {
		return userDAO.countUserInfoList(argsmap);
	}

	public UserInfo getUserInfoById(int id) {
		return userDAO.getUserInfoById(id);
	}

	public boolean updateUserInfoById(UserInfo userInfo) {
		int flag = userDAO.updateUserInfoById(userInfo);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteUserInfoById(int id) {
		int flag = userDAO.deleteUserInfoById(id);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public List<UserInfo> getUserByIdPwd(String user_id, String user_pwd) {
		List<UserInfo> userInfoList = null;
		if(user_id!=null&&user_pwd!=null&&!"".equals(user_id)&&!"".equals(user_pwd)){
			userInfoList = userDAO.getUserByIdPwd(user_id,user_pwd);
		}
		return userInfoList;
	}
}