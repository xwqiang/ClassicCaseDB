package com.test.mapper;

import java.util.List;

import com.test.model.Role;

public interface RoleDAO {

	List<Role> getRoleList();

	int insertRole(Role role);

	int deleteRoleById(int id);

	Role getRoleById(int id);

	int updateRole(Role role);
}
