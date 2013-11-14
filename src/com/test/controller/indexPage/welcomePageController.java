package com.test.controller.indexPage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.core.filter.SystemSession;
import com.test.model.Menu;
import com.test.model.Module;
import com.test.model.Notice;
import com.test.model.Solution;
import com.test.model.UploadFile;
import com.test.service.MenuService;
import com.test.service.NoticeService;
import com.test.service.SolutionService;
import com.test.service.UploadFileService;
import com.test.service.UserRoleService;
import com.test.utils.JSONUtil;
@Controller
public class welcomePageController {
	@Autowired
	private SolutionService solutionService;
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private UploadFileService uploadFileService;
	@Autowired
	private UserRoleService userRoleService;
	/**
	 * 主页展示，展示菜单和管理模块的菜单
	 * @param modelMap
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="showWelcomePage")
	public String showWelcomePage(ModelMap modelMap){
		List<Module> moduleList =  (List<Module>) SystemSession.getSession().getAttribute("modulelist");
		List<Module> menuList = null;
		if(moduleList!=null && moduleList.size()>0){
			menuList = new ArrayList<Module>();
			for(Module m: moduleList){
				if(m.isChecked()&&m.getUrl()!=null&&!m.getUrl().equals("")&&m.getUrl().endsWith(".action")){
					menuList.add(m);
				}
			}
		}
		modelMap.addAttribute("menuList",menuList);
		
		List<Menu> list = menuService.getMenuList();
		String json = JSONUtil.changeJSON(list);
		modelMap.addAttribute("json",json);
		
		return "index";
	}
	
	/**
	 * iframe中的主页显示，包括排行最高30的解决方案，文件展示，公告展示。
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="/getHighRankingsolutions")
	public String getHighRankingsolutions(ModelMap modelMap){
		int top = 10;
		List<Solution> rankinglist =solutionService.getHighRankingsolutions(top);
		
		List<UploadFile> uploadFileList = uploadFileService.getUploadFileList();
		
		List<Notice> noticeList = noticeService.getNoticeList();
		modelMap.addAttribute("rankingList",rankinglist).addAttribute("noticeList",noticeList)
		.addAttribute("uploadFileList",uploadFileList);
		return "welcome";
	}
	
}