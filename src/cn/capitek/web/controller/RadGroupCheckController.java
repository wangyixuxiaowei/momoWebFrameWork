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
import cn.capitek.entity.admin.RadGroupCheck;
import cn.capitek.entity.admin.RadGroupCheckSearchModel;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.RadGroupCheckService;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/radius/group/radgroupcheck")
public class RadGroupCheckController extends BaseController {
	
	@Autowired
	private RadGroupCheckService radGroupCheckService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showRadGroupCheckList(){
		return "radius/radGroupCheckList";
	}
	
	//根据名称 --非ResponseBody方式
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showUserLists(@RequestBody RadGroupCheckSearchModel rsm,HttpServletRequest request,HttpServletResponse response) {
		PageList<RadGroupCheck> plist = radGroupCheckService.queryPage(rsm);
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
	public String addToRadGroupCheckPage(Model model){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new RadGroupCheck());
		}
		return "radius/radGroupCheckAdd";
	}
	
	//添加记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addUser(Model model, @Valid @ModelAttribute("contentModel") RadGroupCheck radGroupCheck, BindingResult result) {
		if(result.hasErrors()){
			 return addToRadGroupCheckPage(model);
		}
		radGroupCheckService.addRadGroupCheck(radGroupCheck);
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_RADGROUPCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/radius/group/radgroupcheck/list";
	}
	
	//指向修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToUserPage(@PathVariable Integer id,HttpServletRequest request) {
		RadGroupCheck radGroupCheck = radGroupCheckService.getRadGroupCheckById(id);
		request.setAttribute("radGroupCheck", radGroupCheck);
		return "radius/radGroupCheckUpdate";
	}
	
	//更改信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, RadGroupCheck radGroupCheck) {
		radGroupCheck.setId(id);
		radGroupCheckService.updateRadGroupCheck(radGroupCheck);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_RADGROUPCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/group/radgroupcheck/list";
	}
	
	// 删除信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteRadGroupCheck(@PathVariable Integer id) {
		radGroupCheckService.deleteRadGroupCheck(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_RADGROUPCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/group/radgroupcheck/list";
	}
}
