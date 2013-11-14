package com.hskj.manager.priority;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.Module;
import com.test.service.ModuleService;
import com.test.utils.JSONUtil;
@Controller
public class ModuleController {
	@Autowired
	private ModuleService moduleService;
	
	
	@RequestMapping(value="listModule")
	public String listModule(String user_id,String user_name,ModelMap modelMap){
		List<Module> moduleList = moduleService.getModuleList();
		String roleList = JSONUtil.changeJSON(moduleList);
		modelMap.addAttribute("module_json",roleList);
		return "manager/module/listModule";
	}
	
	@RequestMapping(value="insertModule")
	public String insertModule(String name,int parent_id,String url,HttpServletResponse response,ModelMap modelMap){
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		}catch(Exception e){
			e.printStackTrace();
		}
		Module module = new Module();
		module.setName(name);
		module.setParent_id(parent_id);
		module.setUrl(url);
		boolean flag = moduleService.insertModule(module);
		if(flag){
			out.write(module.getId()+"");
		}else{
			out.write("fail");
		}
		return null;
	}
	
	@RequestMapping(value="updateModule")
	public String updateModule(int id,String name,String url,HttpServletResponse response,ModelMap modelMap){
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		}catch(Exception e){
			e.printStackTrace();
		}
		Module module = moduleService.getModuleById(id);
		module.setName(name);
		module.setUrl(url);
		boolean flag = moduleService.updateModule(module);
		if(flag){
			out.write("success");
		}else{
			out.write("fail");
		}
		return null;
	}
	
	@RequestMapping(value="deleteModule")
	public String deleteModule(int id,HttpServletResponse response,ModelMap modelMap){
		response.setContentType("application/text; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		}catch(Exception e){
			e.printStackTrace();
		}
		boolean flag = moduleService.deleteModuleById(id);
		if(flag){
			boolean b = moduleService.deleteModuleListByParentId(id,moduleService.getModuleList());
			if(!b){
				flag=false;
			}
		}
		if(flag){
			out.write("success");
		}else{
			out.write("fail");
		}
		return null;
	}
}