package cn.capitek.web.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.capitek.common.util.Constants;
import cn.capitek.common.util.Menu;
import cn.capitek.common.util.MenuTree;
import cn.capitek.common.util.PageList;
import cn.capitek.entity.admin.Module;
import cn.capitek.entity.admin.Role;
import cn.capitek.entity.admin.RoleIdQuery;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.entity.admin.RoleSearchModel;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.auth.AuthTools;
import cn.capitek.web.service.ModuleService;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.RoleService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/system/perms/role")
public class RoleController extends BaseController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showRoleList(){
		return "system/roleList";
	}
	
	//根据名称或者卡号模糊查询role会员信息 --非ResponseBody方式
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showRoleLists(@RequestBody RoleSearchModel rsm, HttpServletRequest request, HttpServletResponse response) {
		PageList<Role> plist = roleService.queryUserPage(rsm);
		List<RolePermission> rpList = (List<RolePermission>) request.getSession().getAttribute("currentRolePermission");
		Map<String, Boolean> m = permissionService.getActionPermsMap(rsm.getPermcode(), rpList, actionArr);
		plist.setActionPermsMap(m);
		response.setCharacterEncoding("utf-8");
		JSONSerializer serializer = new JSONSerializer();
		String res_data = serializer.include("items").include("actionPermsMap").exclude("*.class").exclude("*.permValues").exclude("*.roletype").serialize(plist);
		try {
			response.getWriter().write(res_data);
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//指向role添加页面
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addToRolePage(HttpServletRequest request){
		return "system/roleAdd";
	}

	//获取全部的权限列表
	@RequestMapping(value = "/permtree", method = RequestMethod.POST)
	public void permtree(HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		List<Module> moduleTreeList = moduleService.getModuleTreeList();
		Set<Module> moduleTreeTmpSet = new HashSet<Module>();
		for(Module module : moduleTreeList){
			if (!moduleTreeTmpSet.contains(module)){
			     moduleTreeTmpSet.add(module);
			}
		}
		Set<Menu> menuSet = MenuTree.parseMenuTree(moduleTreeTmpSet);
		JSONSerializer serializer = new JSONSerializer();
		String moduleListJson = serializer.exclude("parent")
										  .exclude("*.class")
										  .exclude("module")
										  .include("children")
										  .deepSerialize(menuSet);
		try {
			response.getWriter().write(moduleListJson);
			response.getWriter().flush();
			op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_ROLE_MANAGER+"-"+Constants.MODULE_ROLE_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		} catch (Exception e) {
			op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_ROLE_MANAGER+"-"+Constants.MODULE_ROLE_MANAGER, Constants.OPERATION_RESULT_FAIL);
			e.printStackTrace();
		}
	}
	
	//获取当前角色的全部的权限列表
	@RequestMapping(value = "/rolepermtree", method = RequestMethod.POST)
	public void currentRolePerms(@RequestBody RoleIdQuery roleIdQuery, HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");
		List<Module> moduleTreeList = moduleService.getModuleTreeList();
		Set<Module> moduleTreeTmpSet = new HashSet<Module>();
		for(Module module : moduleTreeList){
			if (!moduleTreeTmpSet.contains(module)){
			     moduleTreeTmpSet.add(module);
			}
		}
		Set<Menu> menuSet = MenuTree.parseMenuTree(moduleTreeTmpSet);
		JSONSerializer serializer = new JSONSerializer();
		String moduleListJson = serializer.exclude("parent")
										  .exclude("*.class")
										  .exclude("module")
										  .include("children")
										  .deepSerialize(menuSet);
		String roleid = roleIdQuery.getRoleId();
		List<Module> moduleCheckedTreeList = moduleService.getModuleCTreeList(roleid);
		String moduleCheckedJson = serializer.exclude("*.class").deepSerialize(moduleCheckedTreeList);
		StringBuffer sbf = new StringBuffer();
		sbf.append("{\"allPList\":")
			.append(moduleListJson)
			.append(",\"checkedPList\":")
			.append(moduleCheckedJson)
			.append("}");
		try {
			response.getWriter().write(sbf.toString());
			response.getWriter().flush();
			op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_ROLE_MANAGER+"-"+Constants.MODULE_ROLE_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		} catch (Exception e) {
			op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_ROLE_MANAGER+"-"+Constants.MODULE_ROLE_MANAGER, Constants.OPERATION_RESULT_FAIL);
			e.printStackTrace();
		}
	}
	 
	//添加role记录，级联添加权限记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addRole(HttpServletRequest request,Role role) {
		roleService.addRole(role);
		if(role.getId() > 0){
			String code = String.valueOf(role.getId());
			role.setCode(code);
			JSONDeserializer<List<HashMap<String, String>>> deserializer = new JSONDeserializer<List<HashMap<String, String>>>();
			List<HashMap<String, String>> pvList = (List<HashMap<String, String>>) deserializer
                    .use("values", HashMap.class).deserialize(role.getPermValues());
			for (HashMap<String, String> pvEach : pvList) {
				RolePermission rp = new RolePermission();
				rp.setAction(pvEach.get("action"));
				rp.setDiscriminator("role");
				rp.setRecipient(code);
				rp.setTarget(pvEach.get("code"));
				permissionService.addPermission(rp);
			}
			roleService.updateRole(role);
		}
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_ROLE_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/system/perms/role/list";
	}
	
	//指向role修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToRolePage(@PathVariable Integer id,HttpServletRequest request) {
		Role role = roleService.getSingleRole(id);
		request.setAttribute("role", role);
		return "system/roleUpdate";
	}
	
	//更改role信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, Role role) {
		role.setId(id);
		role.setCode(String.valueOf(id));
		permissionService.deleteByRole(role.getCode());
		JSONDeserializer<List<HashMap<String, String>>> deserializer = new JSONDeserializer<List<HashMap<String, String>>>();
		List<HashMap<String, String>> pvList = (List<HashMap<String, String>>) deserializer
                .use("values", HashMap.class).deserialize(role.getPermValues());
		for (HashMap<String, String> pvEach : pvList) {
			RolePermission rp = new RolePermission();
			rp.setAction(pvEach.get("action"));
			rp.setDiscriminator("role");
			rp.setRecipient(role.getCode());
			rp.setTarget(pvEach.get("code"));
			permissionService.addPermission(rp);
		}
		roleService.updateRole(role);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_ROLE_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/system/perms/role/list";
	}
	
	// 删除role信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteViewSpace(@PathVariable Integer id) {
		roleService.deleteUser(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_ROLE_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/system/perms/role/list";
	}
}
