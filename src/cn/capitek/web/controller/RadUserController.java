package cn.capitek.web.controller;

import java.util.List;
import java.util.Map;

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
import cn.capitek.entity.admin.RadCheckSearchModel;
import cn.capitek.entity.admin.RadUser;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.RadCheckService;
import cn.capitek.web.service.RadUserService;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/radius/raduser")
public class RadUserController extends BaseController {
	
	@Autowired
	private RadUserService radUserService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete","drill"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showRadUserList(){
		return "radius/radUserList";
	}
	
	//根据名称 --非ResponseBody方式
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showUserLists(@RequestBody RadCheckSearchModel rsm,HttpServletRequest request,HttpServletResponse response) {
		PageList<RadUser> plist = radUserService.queryPage(rsm);
		List<RolePermission> rpList = (List<RolePermission>) request.getSession().getAttribute("currentRolePermission");
		Map<String, Boolean> m = permissionService.getActionPermsMap(rsm.getPermcode(), rpList, actionArr);
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
	
	//指向添加页面
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addToRadUserPage(Model model){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new RadUser());
		}
		return "radius/radUserAdd";
	}
	
	//添加记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addUser(Model model, @Valid @ModelAttribute("contentModel") RadUser radUser, BindingResult result) {
		if(result.hasErrors()){
			 return addToRadUserPage(model);
		}
		radUserService.addRadUser(radUser);
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_RADCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/radius/raduser/list";
	}
	
	//指向修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToUserPage(@PathVariable Integer id,HttpServletRequest request) {
		RadUser radUser = radUserService.getRadUserById(id);
		request.setAttribute("radUser", radUser);
		return "radius/radUserUpdate";
	}
	
	//更改信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, RadUser radUser) {
		radUser.setId(id);
		radUserService.updateRadUser(radUser);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_RADCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/raduser/list";
	}
	
	// 删除信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteRadUser(@PathVariable Integer id) {
		radUserService.deleteRadUser(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_RADCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/raduser/list";
	}
	
	//指向钻取页面
	@RequestMapping(value="/{id}/drill")  
	public String drillFromUserPage(@PathVariable Integer id,HttpServletRequest request) {
		String name = radUserService.getRadUserById(id).getUsername();
		request.setAttribute("username", name);
		return "radius/radUserDetail";
	}
	
}
