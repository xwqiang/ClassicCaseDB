package com.test.controller.remark;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.filter.SystemSession;
import com.test.model.Remark;
import com.test.model.UserInfo;
import com.test.service.SolutionService;
import com.test.service.remark.RemarkService;
import com.test.utils.JSONUtil;
@Controller
public class RemarkController {
	@Autowired
	private RemarkService remarkService;
	@Autowired
	private SolutionService solutionService;
	
	/**
	 * 查看问题的评论
	 * @param solution_id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/showRemarkList")
	public String showRemarkList(int solution_id,HttpServletResponse response,ModelMap modelMap) {
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Remark> remarkList = remarkService.getRemarkListBySolutionId(solution_id);
		modelMap.addAttribute("remarkList",remarkList);
		String remark_json = JSONUtil.changeJSON(remarkList);
		out.write(remark_json);
		return null;
	}
	
	/**
	 * 提交评论  ajax方式
	 * @param solution_id
	 * @param remark
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="insertRemark")
	public String insertRemark(int solution_id,String remark,HttpServletResponse response,ModelMap modelMap) {
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Remark myremark = new Remark();
		myremark.setSolution_id(solution_id);
		myremark.setRemark(remark);
		myremark.setUser_id(((UserInfo) SystemSession.getSession().getAttribute("USER")).getUser_id());
		boolean flag = remarkService.insertRemark(myremark);
		Remark rtnRemark = remarkService.getRemarkById(myremark.getId());
		if(flag){
			out.write(JSONUtil.changeJSON(rtnRemark));
		}else{
			out.write("fail");
		}
		return null;
	}
}