package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.RoleModuleDAO;
import com.test.model.RoleModule;
@Service
public class RoleModuleService {
	@Autowired
	private RoleModuleDAO roleModuleDAO;

	public List<RoleModule> getPriorityByRoleId(int role_id) {
		return roleModuleDAO.getPriorityByRoleId(role_id);
	}

	public boolean insertPriority(int role_id, int module_id) {
		int flag = roleModuleDAO.insertPriority(role_id,module_id);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean deletePriorityByRoleId(int role_id) {
		int flag = roleModuleDAO.deletePriorityByRoleId(role_id);
		if(flag>=0){
			return true;
		}else{
			return false;
		}
	}
}