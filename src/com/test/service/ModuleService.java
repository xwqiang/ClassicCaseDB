package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.ModuleDAO;
import com.test.model.Module;
import com.test.utils.RecursionUtil;
@Service
public class ModuleService  {
	@Autowired
	private ModuleDAO moduleDAO;

	public List<Module> getModuleList() {
		return moduleDAO.getModuleList();
	}

	public boolean insertModule(Module module) {
		int flag = moduleDAO.insertModule(module);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public Module getModuleById(int id) {
		return moduleDAO.getModuleById(id);
	}

	public boolean updateModule(Module module) {
		int flag = moduleDAO.updateModule(module);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteModuleById(int id) {
		int flag = moduleDAO.deleteModuleById(id);
		if(flag>0){
			return true;
		}else{
			return false;
		}
	}

	public boolean deleteModuleListByParentId(int id,List<Module> list) {
		List<Integer> delList = new RecursionUtil(list).recursion(id).getResult();
		int flag = -1;
		if(delList!=null&&delList.size()>0){
			flag = moduleDAO.deleteModuleListByParentId(delList);
		}else{
			flag = 100;
		}
		if(flag>=0){
			return true;
		}else{
			return false;
		}
	}
}