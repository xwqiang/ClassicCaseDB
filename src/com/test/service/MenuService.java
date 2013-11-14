package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.mapper.MenuDAO;
import com.test.mapper.SolutionDAO;
import com.test.model.Menu;
import com.test.model.Solution;
@Service
public class MenuService {
	@Autowired
	private MenuDAO menuDAO;
	@Autowired
	private SolutionDAO solutionDAO;
	private List<Integer> getMenuListByParentId(List<Menu> menulist,int parent_id, List<Integer> showList) {
		for (Menu g : menulist) {
			if (g.getParent_id() == parent_id) {
				showList.add(g.getId());
				getMenuListByParentId(menulist, g.getId(), showList);
			}
		}
		return showList;
	}
	public boolean deleteMenuByIdCascade(int id){
		List<Menu> list = menuDAO.getMenuList();
		List<Integer> showList = new ArrayList<Integer>();
		getMenuListByParentId(list,id,showList);
		showList.add(id);
		int flag = menuDAO.deleteMenuCascade(showList);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}
	public boolean deleteSolutionByMenuIdCascade(int menu_id){
		List<Menu> list = menuDAO.getMenuList();
		List<Integer> showList = new ArrayList<Integer>();
		getMenuListByParentId(list,menu_id,showList);
		showList.add(menu_id);
		int flag = menuDAO.deleteSolutionByMenuId(showList);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}
	public List<Menu> getMenuList() {
		return menuDAO.getMenuList();
	}
	
	public Menu getMenuById(int id){
		Menu menu = menuDAO.getMenuById(id);
		return menu;
	}
	public boolean updateMenuById(Menu menu){
		int flag = menuDAO.updateMenuById(menu);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}
	public boolean insertMenu(Menu menu){
		int flag = menuDAO.insertMenu(menu);
		if(flag > 0){
			return true;
		}else{
			return false;
		}
	}
	public boolean copyMenuToAnother(int id, int parent_id) {
		//获取该menu
		Menu menu = menuDAO.getMenuById(id);
		menu.setParent_id(parent_id);
//		int flag =menuDAO.updateMenuById(menu);
		//复制menu 及其solution (solution并不同步更新)
		int flag = menuDAO.insertMenu(menu);
		List<Solution> solutionList = solutionDAO.getSolutionListByMenuId(id,0,0);
		if(solutionList!=null && solutionList.size()>0){
			for(Solution solution:solutionList){
				solution.setMenu_id(menu.getId());
				solutionDAO.insertSolution(solution);
			}
		}
		if(flag> 0){
			return true;
		}else{
			return false;
		}
	}
}