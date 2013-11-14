package com.test.mapper;

import java.util.List;

import com.test.model.Module;

public interface ModuleDAO {

	List<Module> getModuleList();

	int insertModule(Module module);

	Module getModuleById(int id);

	int updateModule(Module module);

	int deleteModuleById(int id);


	int deleteModuleListByParentId(List<Integer> delList);
	
}
