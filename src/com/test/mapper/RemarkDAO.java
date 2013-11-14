package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.model.Remark;



public interface RemarkDAO {

	List<Remark> getRemarkListBySolutionId(@Param(value="solution_id")int solution_id,@Param(value="start")int start ,@Param(value="end")int end);

	int insertRemark(Remark remark);

	Remark getRemarkById(int id);

	int countRemarkListBySolutionId(@Param(value="args")Map<String, Object> args);

	int deleteRemarkByRolutionId(@Param(value="solution_id")int solution_id);
	
}
