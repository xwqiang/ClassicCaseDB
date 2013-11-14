package com.test.mapper;

import java.util.List;

import com.test.model.Menu;



public interface MenuDAO {
	public List<Menu> getMenuList();

	public int deleteMenuById(int id);

	public int deleteMenuCascade(List<Integer> showList);

	public int insertMenu(Menu menu);

	public int deleteSolutionByMenuId(List<Integer> showList);

	public Menu getMenuById(int id);

	public int updateMenuById(Menu menu);


}
