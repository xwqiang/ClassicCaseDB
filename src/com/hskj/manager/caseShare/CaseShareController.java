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
		String type_id = request.getParameter("type_id");//��������
		String case_code = request.getParameter("case_code");//���
		String user_id = request.getParameter("user_id");//������
		String server_id = request.getParameter("server_id");//������id
		String service_code = request.getParameter("service_code");//�����
		String insert_time = request.getParameter("insert_time");//����ʱ��
		if(type_id==null||"".equals(type_id)){
			type_id ="0";
		}
		if(server_id==null||"".equals(server_id)){
			server_id ="0";
		}
		String title = request.getParameter("title");
		
		//��ȡ�������б�
		List<ServerType> serverTypeList = serverTypeService.getServerTypeList();
		//��ȡ�����������б�
		List<CaseType> caseTypeList = caseTypeService.getCaseTypeList();
		//��ȡ�����б�
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
		//���ɰ������
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		String dateString = formatter.format(currentTime);
		String case_code = caseShareService.generateCaseCode(dateString);
		//���ɵ�ǰʱ��(����ʱ��)
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String insert_time = formatter1.format(currentTime);
		//��ȡ�������б�
		List<ServerType> serverTypeList = serverTypeService.getServerTypeList();
		//��ȡ�����������б�
		List<CaseType> caseTypeList = caseTypeService.getCaseTypeList();
		
		CaseShare caseShare = new CaseShare();
		caseShare.setCase_code(case_code);
		caseShare.setInsert_time(insert_time);
		//��ȡ����������
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
			modelMap.addAttribute("message","����ɹ�").addAttribute("url","listCaseShare.action");
		}
		return "forward";
	}
	
	
	@RequestMapping(value="deleteCaseShare")
	public String deleteCaseShare(int id,ModelMap modelMap){
		boolean flag  = caseShareService.deleteCaseShare(id);
		if(flag){
			modelMap.addAttribute("message","ɾ���ɹ�").addAttribute("url","listCaseShare.action");
		}else{
			modelMap.addAttribute("message","����ʧ��");
		}
		return "forward";
	}
	
	@RequestMapping(value="showUpdateCaseShare")
	public String showUpdateCaseShare(int id,ModelMap modelMap){
		//��ȡ�������б�
		List<ServerType> serverTypeList = serverTypeService.getServerTypeList();
		//��ȡ�����������б�
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
			modelMap.addAttribute("message","ɾ���ɹ�").addAttribute("url","listCaseShare.action");
		}else{
			modelMap.addAttribute("message","����ʧ��");
		}
		return "forward";
	}
	
	
}