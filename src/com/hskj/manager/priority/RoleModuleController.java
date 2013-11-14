package com.hskj.manager.priority;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.Module;
import com.test.model.Role;
import com.test.model.RoleModule;
import com.test.service.ModuleService;
import com.test.service.RoleModuleService;
import com.test.service.RoleService;
import com.test.utils.JSONUtil;
@Controller
public class RoleModuleController {
	@Autowired
	private RoleModuleService roleModuleService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping(value="getPriorityByRoleId")
	public String getPriorityByRoleId(int role_id,ModelMap modelMap){
		Role role = roleService.getRoleById(role_id);
		List<RoleModule> list = roleModuleService.getPriorityByRoleId(role_id);
		List<Module> moduleList = moduleService.getModuleList();
		for(RoleModule rm:list){
			for(Module m:moduleList){
				if(m.getId()==rm.getModule_id()){
					m.setChecked(true);
				}
			}
		}
		String module_json = JSONUtil.changeJSON(moduleList);
		modelMap.addAttribute("module_json",module_json).addAttribute("role",role);
		return "manager/role_module/listRoleModule";
	}
	
	
	@RequestMapping(value="updatePriorityByRoleId")
	public String updatePriorityByRoleId(int role_id,String modules,ModelMap modelMap){
		boolean flag = true;
		boolean flag_del = roleModuleService.deletePriorityByRoleId(role_id);
		if(flag_del){
			if(modules!=null&&!"".equals(modules)){
				for(String s : modules.split(",")){
					flag = roleModuleService.insertPriority(role_id,Integer.parseInt(s));
				}
			}
			if(flag){
				modelMap.addAttribute("message","修改成功").addAttribute("url","listRole.do");
			}else{
				modelMap.addAttribute("message","修改失败");
			}
		}else{
			modelMap.addAttribute("message","修改失败");
		}
		return "forward";
	}
	
}