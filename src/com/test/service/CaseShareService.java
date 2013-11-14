package com.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.filter.SystemSession;
import com.test.mapper.CaseShareDAO;
import com.test.model.CaseShare;
import com.test.utils.IPageService;
import com.test.utils.Page;
@Service
public class CaseShareService extends IPageService {
	@Autowired
	private CaseShareDAO caseShareDAO;
	private Page page;
	public List<CaseShare> getCaseShareList(int type_id,String case_code,String user_id,String service_code,int server_id,String insert_time,String title){
		if(page!=null){
			return caseShareDAO.getCaseShareList(type_id,case_code,user_id,service_code,server_id,insert_time,title,page.getStart(),page.getEnd());
		}else{
			return caseShareDAO.getCaseShareList(type_id,case_code,user_id,service_code,server_id,insert_time,title,0,0);
		}
	}
	@Override
	public int getTatal(Map<String, Object> args) {
		return caseShareDAO.countCaseShareList(args);
	}

	@Override
	public CaseShareService pagination(int pageSize, String... args) {
		HttpServletRequest request = SystemSession.getRequest();
		page = initPage(request,pageSize,args);
		return this;
	}
	public CaseShare getCaseShareById(int id) {
		return caseShareDAO.getCaseShareById(id);
	}
	public String generateCaseCode(String dateString) {
		String case_code = caseShareDAO.generateCaseCode(dateString);
		if(case_code==null){
			case_code = dateString+"001";
		}else{
			int case_code_int = Integer.parseInt(case_code.substring(8, 10));
			
			case_code = case_code+(case_code_int+1);
		}
		return case_code;
	}
	public String insertCaseShare(CaseShare caseShare) {
		String msg = "OK";
		if(caseShare.getSumma()==null||"".equals(caseShare.getSumma())){
			msg="内容不能为空";
		}
		int flag = caseShareDAO.insertCaseShare(caseShare);
		if(flag <0){
			msg = "插入失败";
		}
		return msg;
	}
	public boolean deleteCaseShare(int id) {
		int flag = caseShareDAO.deleteCaseShare(id);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}
	public boolean updateCaseShareById(CaseShare caseShare) {
		int flag = caseShareDAO.updateCaseShareById(caseShare);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}
}