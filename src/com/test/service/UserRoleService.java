package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.ModuleDAO;
import com.test.mapper.RoleModuleDAO;
import com.test.mapper.UserRoleDAO;
import com.test.model.Module;
import com.test.model.RoleModule;
import com.test.model.UserRole;
@Service
public class UserRoleService  {
	@Autowired
	private UserRoleDAO userRoleDAO;
	@Autowired
	private RoleModuleDAO roleModuleDAO;
	@Autowired
	private ModuleDAO moduleDAO;

	public List<UserRole> getUserRoleByUserId(int user_id) {
		return userRoleDAO.getUserRoleByUserId(user_id);
	}

	public boolean deleteUserRoleByUserId(int user_id) {
		int flag = userRoleDAO.deleteUserRoleByUserId(user_id);
		if(flag>=0){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * @param user_id 用户的主键ID
	 * @param role_id
	 * @return
	 */
	public boolean insertUserRole(int user_id, int role_id) {
		int flag = userRoleDAO.insertUserRole(user_id,role_id);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public List<Module> getModuleListByUserId(int id) {
		List<UserRole> userRoleList = getUserRoleByUserId(id);
		List<Module> allList = filterModuleList(moduleDAO.getModuleList());
		if(userRoleList!=null && userRoleList.size()>0){
			for(UserRole ur:userRoleList){
				List<RoleModule> roleModuleList = roleModuleDAO.getPriorityByRoleId(ur.getRole_id());
				if(roleModuleList!=null&&roleModuleList.size()>0){
					for(RoleModule m:roleModuleList){
						for(Module om : allList){
							if(m.getModule_id()==om.getId()){
								om.setChecked(true);
							}
						}
					}
				}
			}
		}
		return allList;
	}
	public List<Module> filterModuleList(List<Module> list){
		List<Module> rst = null;
		if(list!=null&&list.size()>0){
			rst = new ArrayList<Module>();
			for(Module m:list){
				if(m.getUrl()!=null&&!"".equals(m.getUrl())){
					rst.add(m);
				}
			}
		}
		return rst;
	}

	public List<UserRole> getUserRoleByRoleId(int role_id) {
		return userRoleDAO.getUserRoleByRoleId(role_id);
	}

	public boolean deleteUserRoleByRoleId(int role_id) {
		int flag = userRoleDAO.deleteUserRoleByRoleId(role_id);
		if(flag>=0){
			return true;
		}else{
			return false;
		}
	}
}