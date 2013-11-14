package com.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.filter.SystemSession;
import com.test.mapper.CaseTypeDAO;
import com.test.model.CaseType;
import com.test.utils.IPageService;
import com.test.utils.Page;
@Service
public class CaseTypeService extends IPageService {
	@Autowired
	private CaseTypeDAO caseTypeDAO;
	private Page page;
	public List<CaseType> getCaseTypeList(){
		if(page!=null){
			return caseTypeDAO.getCaseTypeList(page.getStart(),page.getEnd());
		}else{
			return caseTypeDAO.getCaseTypeList(0,0);
		}
	}
	@Override
	public int getTatal(Map<String, Object> args) {
		return 0;
	}

	@Override
	public CaseTypeService pagination(int pageSize, String... args) {
		HttpServletRequest request = SystemSession.getRequest();
		page = initPage(request,pageSize,args);
		return this;
	}
}