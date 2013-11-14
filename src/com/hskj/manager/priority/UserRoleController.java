package com.hskj.manager.priority;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.model.Role;
import com.test.model.UserInfo;
import com.test.model.UserRole;
import com.test.service.RoleService;
import com.test.service.UserRoleService;
import com.test.service.UserService;
@Controller
public class UserRoleController {
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping(value="listUserRole")
	public String listUserRole(@RequestParam(value="user_id",required=false) String user_id,ModelMap modelMap){
		List<UserInfo> userList = userService.pagination(15,"user_id").getUserInfoList(user_id,null);
		modelMap.addAttribute("userlist",userList);
		return "manager/user_role/listUserRole";
	}
	
	/**
	 * 展示用户角色分配界面
	 * @param user_id 用户主键ID
	 * @param user_name
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="updatePriorityByUserId")
	public String updatePriorityByUserId(int user_id,String user_name,ModelMap modelMap){
		List<Role> roleList = roleService.getRoleList();
		List<UserRole> urList = userRoleService.getUserRoleByUserId(user_id);
		for(UserRole ur: urList){
			for(Role r: roleList){
				if(r.getId()==ur.getRole_id()){
					r.setChecked(true);
				}
			}
		}
		modelMap.addAttribute("roleList",roleList).addAttribute("id",user_id);
		return "manager/user_role/updateUserRole";
	}
	
	@RequestMapping(value="updateUserRoleByUserId")
	public String updateUserRoleByUserId(HttpServletRequest request,ModelMap modelMap){
		String[] roles = request.getParameterValues("role_id");
		String user_id = request.getParameter("id");//user的id
		boolean flag_del = userRoleService.deleteUserRoleByUserId(Integer.parseInt(user_id));
		boolean flag = true;
		if(flag_del){
			if(roles!=null&&!"".equals(roles)){
				for(String role_id:roles){
					flag = userRoleService.insertUserRole(Integer.parseInt(user_id),Integer.parseInt(role_id));
				}
			}
		}
		if(flag&&flag_del){
				modelMap.addAttribute("message","修改成功").addAttribute("url","listUserInfo.do");
		}else{
			modelMap.addAttribute("message","修改失败");
		}
		return "forward";
	}
	
	
	
}