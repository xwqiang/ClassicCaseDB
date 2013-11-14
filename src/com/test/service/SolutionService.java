package com.test.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.filter.SystemSession;
import com.test.mapper.SolutionDAO;
import com.test.model.Solution;
import com.test.model.UserInfo;
import com.test.utils.IPageService;
import com.test.utils.Page;
@Service
public class SolutionService extends IPageService{

	@Autowired
	private SolutionDAO solutionDAO;
	private Page page;
	public List<Solution> getSolutionListByMenuId(int menu_id) {
		if(page!=null && page.isUsing()){
			return solutionDAO.getSolutionListByMenuId(menu_id,page.getStart(),page.getEnd());
		}else{
			return solutionDAO.getSolutionListByMenuId(menu_id,0,0);
		}
	}

	public boolean insertSolution(Solution solution) {
		UserInfo user = (UserInfo) SystemSession.getSession().getAttribute("USER");
		solution.setUser_id(user.getUser_id());
		int flag = solutionDAO.insertSolution(solution);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updateSolutionById(Solution solution) {
		int flag = solutionDAO.updateSolutionById(solution);
		if(flag> 0){
			return true;
		}else{
			return false;
		}
	}

	public boolean updateSolutionTitleById(int id,String title) {
		Solution solution = solutionDAO.getsolutionById(id);
		solution.setTitle(title);
		int flag = solutionDAO.updateSolutionById(solution);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
		
	}

	public boolean deleteSolutionById(int id) {
		int flag = solutionDAO.deleteSolutionById(id);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}

	public List<Solution> getHighRankingsolutions(int top) {
		List<Solution> rankinglist =solutionDAO.getHighRankingsolutions(top);
		return rankinglist;
	}

	public Solution getsolutionById(int id) {
		return solutionDAO.getsolutionById(id);
	}

	/**
	 * @param pageSize 每页显示多少条
	 * @return
	 */
	@Override
	public SolutionService pagination(int pageSize,String...args){
		HttpServletRequest request = SystemSession.getRequest();
		page = initPage(request,pageSize,args);
		return this;
	}

	@Override
	public int getTatal(Map<String,Object> args) {
		return solutionDAO.countSolutionListByMenuId(args);
	}
}