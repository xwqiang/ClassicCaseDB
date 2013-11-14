package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.model.UserInfo;



public interface UserDAO {
	public UserInfo queryUserById(UserInfo user);

	public int insertUserInfo(UserInfo userInfo);

	public UserInfo getUserInfoByUserId(String user_id);

	public List<UserInfo> getUserInfoList(@Param(value="user_id")String user_id, @Param(value="user_name")String user_name,
			@Param(value="start")int start, @Param(value="end")int end);

	public int countUserInfoList(@Param(value="argsmap")Map<String, Object> argsmap);

	public UserInfo getUserInfoById(int id);

	public int updateUserInfoById(UserInfo userInfo);

	public int deleteUserInfoById(int id);

	public List<UserInfo> getUserByIdPwd(@Param(value="user_id")String user_id,@Param(value="user_pwd") String user_pwd);

}
