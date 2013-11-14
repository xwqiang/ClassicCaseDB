package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.model.UserRole;

public interface UserRoleDAO {

	List<UserRole> getUserRoleByUserId(int user_id);

	int deleteUserRoleByUserId(int user_id);

	int insertUserRole(@Param(value="user_id")int user_id, @Param(value="role_id")int role_id);

	List<UserRole> getUserRoleByRoleId(int role_id);

	int deleteUserRoleByRoleId(int role_id);

}
