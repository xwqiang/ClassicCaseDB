package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.Menu;
import com.test.model.Solution;
import com.test.service.MenuService;
import com.test.service.SolutionSearchService;
import com.test.service.SolutionService;
import com.test.service.remark.RemarkService;
import com.test.utils.SearchUtil;
@Controller
public class SolutionController {
	@Autowired
	private SolutionSearchService solutionSearchService;
	@Autowired
	private SolutionService solutionService;
	@Autowired
	private RemarkService remarkService;
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/getSolutionListByMenuId.do")
	public String getSolutionListByMenuId(int menu_id,ModelMap modelMap) {
		List<Solution> list = solutionService.pagination(5).getSolutionListByMenuId(menu_id);
		Menu menu = menuService.getMenuById(menu_id);
		modelMap.addAttribute("menu",menu).addAttribute("solutionlist",list);
		return "solutionlist";
	}
	
	@RequestMapping(value="/showUpdateSolutionById")
	public String showUpdateSolutionById(int id,ModelMap modelMap){
		Solution solution = solutionService.getsolutionById(id);
		Menu menu = menuService.getMenuById(solution.getMenu_id());
		modelMap.addAttribute("solution",solution).addAttribute("menu",menu);
		return "solution/modSolution";
	}
	
	@RequestMapping(value="/updateSolutionById")
	public String updateSolutionById(int id,String title,String content,ModelMap modelMap){
		Solution solution = solutionService.getsolutionById(id);
		solution.setContent(content);
		solution.setTitle(title);
		boolean flag = solutionService.updateSolutionById(solution);
		if(flag){
			modelMap.addAttribute("message","修改成功").addAttribute("url","getSolutionListByMenuId.do?menu_id="+solution.getMenu_id());
		}else{
			modelMap.addAttribute("message","修改失败");
		}
		return "forward";
	}
	
	@RequestMapping(value="/updateSolutionTitleById")
	public String updateSolutionTitleById(int id,String title,HttpServletResponse response){
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean flag = solutionService.updateSolutionTitleById(id,title);
		if(flag){
			out.write("success");
		}else{
			out.write("fail");
		}
		return null;
	}
	
	@RequestMapping(value="/insertSolution")
	public String insertSolution(int menu_id,String title,String content,ModelMap modelMap){
		Solution solution = new Solution();
		solution.setMenu_id(menu_id);
		solution.setTitle(title);
		solution.setContent(content);
		boolean flag = solutionService.insertSolution(solution);
		if(flag){
			modelMap.addAttribute("message","添加成功").addAttribute("url","getSolutionListByMenuId.do?menu_id="+menu_id);
		}
		return "forward";
	}
	
	@RequestMapping(value="/deleteSolutionById")
	public String deleteSolutionById(int id,HttpServletResponse response){
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		boolean flag = solutionService.deleteSolutionById(id);
		if(flag){
			remarkService.deleteRemarkByRolutionId(id);
			out.write("success");
		}else{
			out.write("fail");
		}
		return null;
	}
	
	
	@RequestMapping(value="/getSolutionById")
	public String getSolutionById(int id,ModelMap modelMap){
		Solution solution = solutionService.getsolutionById(id);
		modelMap.addAttribute("solution",solution).addAttribute("content",solution.getContent()).addAttribute("title",solution.getTitle());
		return "solutionDetail";
	}
	
	@RequestMapping(value="/searchSolutions")
	public String searchSolutions(HttpServletRequest request,ModelMap modelMap){
		String key = request.getParameter("key");
		if(key!=null&&key.equals("请输入关键词搜索")){
			modelMap.addAttribute("message","请输入关键字");
			return "forward";
		}
		List<Solution> list = solutionSearchService.searchSolutions(key);
		List<Solution> search_list = new SearchUtil().searchSolution(key,list,solutionSearchService.pagination(10));
		modelMap.addAttribute("list", search_list);
		return "searchList";
	}
	
	/**
	 * 用户点击赞
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="supportIt")
	public String supportIt(int id,HttpServletResponse response){
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Solution solution = solutionService.getsolutionById(id);
		solution.setRanking(solution.getRanking()+1);
		boolean flag = solutionService.updateSolutionById(solution);
		if(flag){
			out.write("success");
		}else{
			out.write("fail");
		}
		return null;
	}
}