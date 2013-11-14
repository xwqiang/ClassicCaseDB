package com.test.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.test.model.CaseShare;

public interface CaseShareDAO {

	List<CaseShare> getCaseShareList(@Param(value = "type_id") int type_id,
			@Param(value = "case_code") String case_code,
			@Param(value = "user_id") String user_id,
			@Param(value = "service_code") String service_code,
			@Param(value = "server_id") int server_id,
			@Param(value = "insert_time") String insert_time,
			@Param(value = "title") String title,
			@Param(value = "start") int start,
			@Param(value = "end") int end);

	int countCaseShareList(@Param(value="args")Map<String, Object> args);

	CaseShare getCaseShareById(int id);

	String generateCaseCode(String dateString);

	int insertCaseShare(CaseShare caseShare);

	int deleteCaseShare(int id);

	int updateCaseShareById(CaseShare caseShare);
}
