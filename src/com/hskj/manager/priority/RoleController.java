package com.hskj.manager.priority;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.model.Role;
import com.test.model.UserInfo;
import com.test.model.UserRole;
import com.test.service.RoleService;
import com.test.service.UserRoleService;
import com.test.service.UserService;
@Controller
public class RoleController {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	
	@RequestMapping(value="listRole")
	public String listRole(String user_id,String user_name,ModelMap modelMap){
		List<Role> roleList = roleService.getRoleList();
		modelMap.addAttribute("roleList",roleList);
		return "manager/role/listRole";
	}
	
	@RequestMapping(value="showInsertRole")
	public String showInsertRole(String user_id,String user_name,ModelMap modelMap){
		return "manager/role/addRole";
	}
	
	@RequestMapping(value="insertRole")
	public String insertRole(String role_name,ModelMap modelMap){
		Role role = new Role();
		role.setRole_name(role_name);
		boolean flag = roleService.insertRole(role);
		if(flag){
			modelMap.addAttribute("message","创建成功").addAttribute("url","listRole.do");
		}else{
			modelMap.addAttribute("message","创建失败");
		}
		return "forward";
	}
	
	@RequestMapping(value="deleteRole")
	public String deleteRole(int id,ModelMap modelMap){
		List<UserRole> userRoleList = userRoleService.getUserRoleByRoleId(id);
		if(userRoleList!=null&&userRoleList.size()>0){
			modelMap.addAttribute("message","尚有用户绑定了该角色，禁止删除");
			return "forward";
		}
		boolean flag = roleService.deleteRoleById(id);
		if(flag){
			modelMap.addAttribute("message","删除成功").addAttribute("url","listRole.do");
		}else{
			modelMap.addAttribute("message","删除失败");
		}
		return "forward";
	}
	
	
	@RequestMapping(value="showUpdateRole")
	public String showUpdateRole(int id,ModelMap modelMap){
		Role role = roleService.getRoleById(id);
		modelMap.addAttribute("role",role);
		return "manager/role/modRole";
	}
	
	@RequestMapping(value="updateRole")
	public String updateRole(int id,String role_name,ModelMap modelMap){
		Role role = roleService.getRoleById(id);
		role.setRole_name(role_name);
		boolean flag = roleService.updateRole(role);
		if(flag){
			modelMap.addAttribute("message","修改成功").addAttribute("url","listRole.do");
		}else{
			modelMap.addAttribute("message","修改失败");
		}
		return "forward";
	}
	
	/**
	 * 给用户分配角色 UI
	 * @param id
	 * @param role_name
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="grantPriorityToUserByRoleId")
	public String grantPriorityToUserByRoleId(int role_id,String role_name,ModelMap modelMap){
		Role role = roleService.getRoleById(role_id);
		//获取拥有该角色的所有用户
		List<UserRole> userRoleList = userRoleService.getUserRoleByRoleId(role_id);
		List<UserInfo> userList = userService.getUserInfoList(null, null);
		for(UserRole userRole: userRoleList){
			for(UserInfo user:userList){
				if(user.getId()==userRole.getUser_id()){
					user.setChecked(true);
					break;
				}
			}
		}
		modelMap.addAttribute("userlist",userList).addAttribute("role",role);
		return "manager/role_user/listRoleUser";
	}
	
	@RequestMapping(value="grantPriorityToUserByRoleIdSubmit")
	public String grantPriorityToUserByRoleIdSubmit(HttpServletRequest request,ModelMap modelMap){
		String[] id = request.getParameterValues("id");//用户id
		String role_id = request.getParameter("role_id");//角色id
		boolean flag = userRoleService.deleteUserRoleByRoleId(Integer.parseInt(role_id));
		boolean flag_insert = true;
		if(flag){
			if(id!=null && id.length>=0){
				for(String s: id){
					flag_insert = userRoleService.insertUserRole(Integer.parseInt(s), Integer.parseInt(role_id));
				}
			}
		}
		if(flag&&flag_insert){
			modelMap.addAttribute("message","配置成功").addAttribute("grantPriorityToUserByRoleId.do");
		}else{
			modelMap.addAttribute("message","配置失败");
		}
		return "forward";
	}
	
}