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
import cn.capitek.entity.admin.RadUserMergeModel;
import cn.capitek.entity.admin.RadUserGroup;
import cn.capitek.entity.admin.RadUserGroupSearchModel;
import cn.capitek.entity.admin.RadUserModel;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.RadUserGroupService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/radius/group/radusergoup")
public class RadUserGroupController extends BaseController {
	
	@Autowired
	private RadUserGroupService radUserGroupService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showRadUserGroupList(){
		return "radius/radUserGroupList";
	}
	
	//根据名称 --非ResponseBody方式
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showUserLists(@RequestBody RadUserGroupSearchModel rsm,HttpServletRequest request,HttpServletResponse response) {
		PageList<RadUserGroup> plist = radUserGroupService.queryPage(rsm);
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
	
	@RequestMapping(value = "/drill", method = RequestMethod.POST)
	public void showUserGroupDatas(@RequestBody RadUserModel rcs,HttpServletRequest request,HttpServletResponse response) {
		List<RadUserGroup> checkList = radUserGroupService.queryByUsername("select rc from RadUserGroup rc where username like ?", rcs.getUsername());
		response.setCharacterEncoding("utf-8");
		JSONSerializer serializer = new JSONSerializer();
		String res_data = serializer.exclude("*.class").serialize(checkList);
		try {
			response.getWriter().write(res_data);
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<RadUserGroup> deserTools(String prop) throws Exception{
		return new JSONDeserializer<List<RadUserGroup>>().use("values", RadUserGroup.class).deserialize(prop);
	}
	
	@RequestMapping(value = "/merge", method = RequestMethod.POST)
	public void mergeDatas(@RequestBody RadUserMergeModel rcmm,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(!"[]".equals(rcmm.getAddList())){
				List<RadUserGroup> addList  = deserTools(rcmm.getAddList());
				radUserGroupService.batchSave(addList);
				System.out.println("批量添加完成！");
			}
			if(!"[]".equals(rcmm.getEditList())){
				List<RadUserGroup> editList  = deserTools(rcmm.getEditList());
				radUserGroupService.batchUpdate(editList);
				System.out.println("批量更新完成");
			}
			if(!"[]".equals(rcmm.getDeleteList())){
				List<RadUserGroup> deleteList  = deserTools(rcmm.getDeleteList());
				radUserGroupService.batchDel(deleteList);
				System.out.println("批量删除完成");
			}
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("{\"rs\":\"ok\"}");
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//指向添加页面
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String addToRadUserGroupPage(Model model){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new RadUserGroup());
		}
		return "radius/radUserGroupAdd";
	}
	
	//添加记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addUser(Model model, @Valid @ModelAttribute("contentModel") RadUserGroup radUserGroup, BindingResult result) {
		if(result.hasErrors()){
			 return addToRadUserGroupPage(model);
		}
		radUserGroupService.addRadUserGroup(radUserGroup);
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_RADUSERGROUP_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/radius/group/radusergoup/list";
	}
	
	//指向修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToUserPage(@PathVariable Integer id,HttpServletRequest request) {
		RadUserGroup radUserGroup = radUserGroupService.getRadUserGroupById(id);
		request.setAttribute("radUserGroup", radUserGroup);
		return "radius/radUserGroupUpdate";
	}
	
	//更改信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, RadUserGroup radUserGroup) {
		radUserGroup.setId(id);
		radUserGroupService.updateRadUserGroup(radUserGroup);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_RADUSERGROUP_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/group/radusergoup/list";
	}
	
	// 删除信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteRadUserGroup(@PathVariable Integer id) {
		radUserGroupService.deleteRadUserGroup(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_RADUSERGROUP_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/group/radusergoup/list";
	}
}
