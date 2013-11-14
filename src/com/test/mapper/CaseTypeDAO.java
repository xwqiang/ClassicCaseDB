package com.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.test.model.CaseType;

public interface CaseTypeDAO {

	List<CaseType> getCaseTypeList(@Param(value="start")int start, @Param(value="end")int end);

}
