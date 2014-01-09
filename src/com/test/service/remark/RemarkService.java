package com.test.service.remark;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.filter.SystemSession;
import com.test.mapper.RemarkDAO;
import com.test.model.Remark;
import com.test.utils.IPageService;
import com.test.utils.Page;
@Service
public class RemarkService extends IPageService{
	@Autowired
	private RemarkDAO remarkDAO;
	private Page page;
	
	public List<Remark> getRemarkListBySolutionId(int solution_id) {
		if(page!=null){
			return remarkDAO.getRemarkListBySolutionId(solution_id,page.getStart(),page.getEnd());
		}else{
			return remarkDAO.getRemarkListBySolutionId(solution_id,0,0);
		}
	}

	public boolean insertRemark(Remark remark) {
		int flag = remarkDAO.insertRemark(remark);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public Remark getRemarkById(int id) {
		return remarkDAO.getRemarkById(id);
	}

	@Override
	public int getTatal(Map<String, Object> args) {
		return remarkDAO.countRemarkListBySolutionId(args);
	}

	@Override
	public RemarkService pagination(int pageSize) {
		HttpServletRequest request = SystemSession.getRequest();
		page = initPage(request,pageSize);
		return this;
	}

	public boolean deleteRemarkByRolutionId(int id) {
		int flag = remarkDAO.deleteRemarkByRolutionId(id);
		if(flag>=0){
			return true;
		}else{
			return false;
		}
	}
}