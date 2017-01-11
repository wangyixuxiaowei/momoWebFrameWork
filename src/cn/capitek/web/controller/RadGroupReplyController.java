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
import cn.capitek.entity.admin.RadGroupCheckSearchModel;
import cn.capitek.entity.admin.RadGroupReply;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.RadGroupReplyService;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/radius/group/radgroupreply")
public class RadGroupReplyController extends BaseController {
	
	@Autowired
	private RadGroupReplyService radGroupReplyService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showRadGroupReplyList(){
		return "radius/radGroupReplyList";
	}
	
	//根据名称 --非ResponseBody方式
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showUserLists(@RequestBody RadGroupCheckSearchModel rsm,HttpServletRequest request,HttpServletResponse response) {
		PageList<RadGroupReply> plist = radGroupReplyService.queryPage(rsm);
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
	public String addToRadGroupReplyPage(Model model){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new RadGroupReply());
		}
		return "radius/radGroupReplyAdd";
	}
	
	//添加记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addUser(Model model, @Valid @ModelAttribute("contentModel") RadGroupReply radGroupReply, BindingResult result) {
		if(result.hasErrors()){
			 return addToRadGroupReplyPage(model);
		}
		radGroupReplyService.addRadGroupReply(radGroupReply);
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_RADGROUPREPLY_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/radius/group/radgroupreply/list";
	}
	
	//指向修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToUserPage(@PathVariable Integer id,HttpServletRequest request) {
		RadGroupReply radGroupReply = radGroupReplyService.getRadGroupReplyById(id);
		request.setAttribute("radGroupReply", radGroupReply);
		return "radius/radGroupReplyUpdate";
	}
	
	//更改信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, RadGroupReply radGroupReply) {
		radGroupReply.setId(id);
		radGroupReplyService.updateRadGroupReply(radGroupReply);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_RADGROUPREPLY_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/group/radgroupreply/list";
	}
	
	// 删除信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteRadGroupReply(@PathVariable Integer id) {
		radGroupReplyService.deleteRadGroupReply(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_RADGROUPREPLY_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/group/radgroupreply/list";
	}
}
