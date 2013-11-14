package com.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.filter.SystemSession;
import com.test.mapper.SolutionDAO;
import com.test.model.Solution;
import com.test.utils.IPageService;
import com.test.utils.Page;
@Service
public class SolutionSearchService extends IPageService{

	@Autowired
	private SolutionDAO solutionDAO;
	private Page page;
	List<Solution> list = null;
	public List<Solution> searchSolutions(String key) {
		key = key.replaceAll("\\s{1,}", " ");
		String[] keyarr = key.split(" ");
		List<String> keylist = new ArrayList<String>();
		for(String str:keyarr){
			keylist.add(str);
		}
		list = solutionDAO.searchSolutions(keylist);
		return list;
	}
	/**
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@Override
	public Page pagination(int pageSize,String...args){
		HttpServletRequest request = SystemSession.getRequest();
		page = initPage(request,pageSize,args);
		return page;
	}

	@Override
	public int getTatal(Map<String,Object> argsmap) {
		return list.size();
	}
}