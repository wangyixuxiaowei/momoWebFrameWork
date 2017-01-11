package cn.capitek.web.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.capitek.common.util.Constants;
import cn.capitek.common.util.PageList;
import cn.capitek.entity.admin.Role;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.entity.admin.User;
import cn.capitek.entity.admin.UserSearchModel;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.RoleService;
import cn.capitek.web.service.UserService;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/system/perms/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showUserList(){
		return "system/userList";
	}
	
	//根据名称 --非ResponseBody方式
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showUserLists(@RequestBody UserSearchModel usm,HttpServletRequest request,HttpServletResponse response) {
		PageList<User> plist = userService.queryUserPage(usm);
		List<RolePermission> rpList = (List<RolePermission>) request.getSession().getAttribute("currentRolePermission");
		Map<String, Boolean> m = permissionService.getActionPermsMap(usm.getPermcode(), rpList, actionArr);
		plist.setActionPermsMap(m);
		response.setCharacterEncoding("utf-8");
		JSONSerializer serializer = new JSONSerializer();
		String res_data = serializer.include("items").include("actionPermsMap").exclude("*.class").serialize(plist);
		try {
			response.getWriter().write(res_data);
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	//指向user添加页面
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addToUserPage(HttpServletRequest request){
		List<Role> allRoleList = roleService.getAllRoles();
		request.setAttribute("roleList", allRoleList);
		return "system/userAdd";
	}*/
	//指向user添加页面
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addToUserPage(Model model, HttpServletRequest request){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new User());
		}
		List<Role> allRoleList = null;
		if(request.getSession().getAttribute("allrolscache") != null){
			allRoleList = (List<Role>)request.getSession().getAttribute("allrolscache");
		}else{
			allRoleList = roleService.getAllRoles();
			request.getSession().setAttribute("allrolscache", allRoleList);
		}
		request.setAttribute("roleList", allRoleList);
		return "system/userAdd";
	}
	
	//添加user记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addUser(Model model, @Valid @ModelAttribute("contentModel") User user, BindingResult result, HttpServletRequest request) {
		if(result.hasErrors()){
			 return addToUserPage(model,request);
		}
		user.setPwdUpdateTime(new Date());
		Role adminRole = roleService.getSingleRole(user.getSelectRoleId());
		Set adminRoleSet = new HashSet<Role>();
		adminRoleSet.add(adminRole);
		user.setRoles(adminRoleSet);
		user.setEnable(true);
		userService.addUser(user);
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_USER_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/system/perms/user/list";
	}
	
	//指向user修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToUserPage(@PathVariable Integer id,HttpServletRequest request) {
		User user = userService.getUserById(id);
		List<Role> allRoleList = roleService.getAllRoles();
		request.setAttribute("roleList", allRoleList);
		Role myRole = (Role)user.getRoles().toArray()[0];
		request.setAttribute("myRoleId", myRole.getId());
		request.setAttribute("user", user);
		return "system/userUpdate";
	}
	
	//更改user信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, User user) {
		user.setId(id);
		user.setPwdUpdateTime(new Date());
		userService.updateUser(user);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_USER_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/system/perms/user/list";
	}
	
	// 删除user信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteViewSpace(@PathVariable Integer id) {
		userService.deleteUser(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_USER_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/system/perms/user/list";
	}
}
