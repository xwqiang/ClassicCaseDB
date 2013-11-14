package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.RoleDAO;
import com.test.model.Role;
@Service
public class RoleService {
	@Autowired
	private RoleDAO roleDAO;

	public List<Role> getRoleList() {
		return roleDAO.getRoleList();
	}

	public boolean insertRole(Role role) {
		int flag = roleDAO.insertRole(role);
		if(flag> 0 ){
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteRoleById(int id) {
		int flag = roleDAO.deleteRoleById(id);
		if(flag> 0 ){
			return true;
		}else{
			return false;
		}
	}

	public Role getRoleById(int id) {
		return roleDAO.getRoleById(id);
	}

	public boolean updateRole(Role role) {
		int flag =  roleDAO.updateRole(role);
		if(flag> 0 ){
			return true;
		}else{
			return false;
		}
	}
	
}