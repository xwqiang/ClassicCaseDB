package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.model.RoleModule;



public interface RoleModuleDAO {

	List<RoleModule> getPriorityByRoleId(int role_id);

	int insertPriority(@Param(value="role_id") int role_id, @Param(value="module_id")int module_id);

	int deletePriorityByRoleId(int role_id);
}
