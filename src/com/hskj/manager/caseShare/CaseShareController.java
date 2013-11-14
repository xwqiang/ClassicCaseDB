package com.hskj.manager.caseShare;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.CaseShare;
import com.test.model.CaseType;
import com.test.model.ServerType;
import com.test.model.UserInfo;
import com.test.service.CaseShareService;
import com.test.service.CaseTypeService;
import com.test.service.ServerTypeService;
@Controller
public class CaseShareController {
	@Autowired
	private CaseShareService caseShareService;
	@Autowired
	private CaseTypeService caseTypeService;
	@Autowired
	private ServerTypeService serverTypeService;
	
	@RequestMapping(value="/listCaseShare")
	public String listCaseShare(HttpServletRequest request,ModelMap modelMap){
		String type_id = request.getParameter("type_id");//案例类型
		String case_code = request.getParameter("case_code");//编号
		String user_id = request.getParameter("user_id");//分享者
		String server_id = request.getParameter("server_id");//服务器id
		String service_code = request.getParameter("service_code");//接入号
		String insert_time = request.getParameter("insert_time");//分享时间
		if(type_id==null||"".equals(type_id)){
			type_id ="0";
		}
		if(server_id==null||"".equals(server_id)){
			server_id ="0";
		}
		String title = request.getParameter("title");
		
		//获取服务器列表
		List<ServerType> serverTypeList = serverTypeService.getServerTypeList();
		//获取问题里类型列表
		List<CaseType> caseTypeList = caseTypeService.getCaseTypeList();
		//获取案例列表
		List<CaseShare> caseShareList =caseShareService
				.pagination(5, "type_id","case_code","user_id","service_code","server_id","insert_time","title")
				.getCaseShareList(Integer.parseInt(type_id),case_code,user_id,service_code,Integer.parseInt(server_id),insert_time,title);
		modelMap.addAttribute("caseShareList",caseShareList).addAttribute("serverTypeList",serverTypeList)
		.addAttribute("caseTypeList",caseTypeList);
		return "manager/case_share/listCaseShare";
	}
	
	
	@RequestMapping(value="/getCaseShareById")
	public String getCaseShareById(int id,ModelMap modelMap){
		CaseShare caseShare = caseShareService.getCaseShareById(id);
		modelMap.addAttribute("caseShare",caseShare);
		return "manager/case_share/caseShareDetail";
	}
	
	@RequestMapping(value="showInsertCaseShare")
	public String showInsertCaseShare(HttpSession session,ModelMap modelMap){
		//生成案例编号
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		String case_code = caseShareService.generateCaseCode(dateString);
		//生成当前时间(分享时间)
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String insert_time = formatter1.format(currentTime);
		//获取服务器列表
		List<ServerType> serverTypeList = serverTypeService.getServerTypeList();
		//获取问题里类型列表
		List<CaseType> caseTypeList = caseTypeService.getCaseTypeList();
		
		CaseShare caseShare = new CaseShare();
		caseShare.setCase_code(case_code);
		caseShare.setInsert_time(insert_time);
		//获取分享者姓名
		caseShare.setUser_name(((UserInfo) session.getAttribute("USER")).getUser_name());
		caseShare.setUser_id(((UserInfo) session.getAttribute("USER")).getUser_id());
		modelMap.addAttribute("caseShare",caseShare).addAttribute("serverTypeList",serverTypeList)
		.addAttribute("caseTypeList",caseTypeList);
		return "manager/case_share/addCaseShare";
	}
	
	@RequestMapping(value="insertCaseShare")
	public String insertCaseShare(CaseShare caseShare,ModelMap modelMap){
		String msg = caseShareService.insertCaseShare(caseShare);
		if(!msg.equals("OK")){
			modelMap.addAttribute("message",msg);
		}else{
			modelMap.addAttribute("message","插入成功").addAttribute("url","listCaseShare.action");
		}
		return "forward";
	}
	
	
	@RequestMapping(value="deleteCaseShare")
	public String deleteCaseShare(int id,ModelMap modelMap){
		boolean flag  = caseShareService.deleteCaseShare(id);
		if(flag){
			modelMap.addAttribute("message","删除成功").addAttribute("url","listCaseShare.action");
		}else{
			modelMap.addAttribute("message","插入失败");
		}
		return "forward";
	}
	
	@RequestMapping(value="showUpdateCaseShare")
	public String showUpdateCaseShare(int id,ModelMap modelMap){
		//获取服务器列表
		List<ServerType> serverTypeList = serverTypeService.getServerTypeList();
		//获取问题里类型列表
		List<CaseType> caseTypeList = caseTypeService.getCaseTypeList();
		CaseShare caseShare = caseShareService.getCaseShareById(id);
		modelMap.addAttribute("caseShare",caseShare).addAttribute("serverTypeList",serverTypeList)
		.addAttribute("caseTypeList",caseTypeList);
		return "manager/case_share/modCaseShare";
	}
	@RequestMapping(value="updateCaseShare")
	public String updateCaseShare(CaseShare caseShare,ModelMap modelMap){
		boolean flag = caseShareService.updateCaseShareById(caseShare);
		if(flag){
			modelMap.addAttribute("message","删除成功").addAttribute("url","listCaseShare.action");
		}else{
			modelMap.addAttribute("message","插入失败");
		}
		return "forward";
	}
	
	
}