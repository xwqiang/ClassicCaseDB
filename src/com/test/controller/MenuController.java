package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.model.Menu;
import com.test.service.MenuService;
import com.test.service.SolutionService;
@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private SolutionService solutionService;
	
	@RequestMapping(value="updateMenuName.do")
	@ResponseBody
	public String updateMenuName(int id,String name){
		Menu menu = menuService.getMenuById(id);
		menu.setName(name);
		boolean flag = menuService.updateMenuById(menu);
		if(flag){
			return "success";
		}else{
			return "fail";
		}
	}
	
	/**
	 * 移动菜单节点
	 * @param id
	 * @param parent_id
	 * @return
	 */
	@RequestMapping(value="updateMenuParent")
	@ResponseBody
	public String updateMenuParent(int id,int parent_id){
		Menu menu = menuService.getMenuById(id);
		menu.setParent_id(parent_id);
		boolean flag =menuService.updateMenuById(menu);
		if(flag){
			return "success";
		}else{
			return "fail";
		}
	}
	@RequestMapping(value="copyMenuToAnother.do")
	@ResponseBody
	public String copyMenuToAnother(int id,int parent_id){
		boolean bool = menuService.copyMenuToAnother(id,parent_id);
		if(bool){
			return "success";
		}else{
			return "fail";
		}
	}
	/**
	 * 新建节点
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="insertNewMenu")
	@ResponseBody
	public String insertNewMenu(Menu menu){
		boolean flag = menuService.insertMenu(menu);
		if(flag){
			return menu.getId()+"";
		}else{
			return "fail";
		}
	}
	@RequestMapping(value="deleteMenuById")
	@ResponseBody
	public String deleteMenuById(int id){
		boolean flag = menuService.deleteMenuByIdCascade(id);
		if(flag){
			menuService.deleteSolutionByMenuIdCascade(id);
			return "success";
		}else{
			return "fail";
		}
	}
}