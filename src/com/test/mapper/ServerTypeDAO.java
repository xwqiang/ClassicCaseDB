package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.model.ServerType;

public interface ServerTypeDAO {

	List<ServerType> getServerTypeList(@Param(value="start")int start, @Param(value="end")int end);

}
