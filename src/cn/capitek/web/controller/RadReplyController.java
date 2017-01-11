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
import cn.capitek.entity.admin.RadReply;
import cn.capitek.entity.admin.RadUserModel;
import cn.capitek.entity.admin.RadUserMergeModel;
import cn.capitek.entity.admin.RadCheckSearchModel;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.RadReplyService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/radius/checks/reply")
public class RadReplyController extends BaseController {
	
	@Autowired
	private RadReplyService radReplyService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showRadReplyList(){
		return "radius/radReplyList";
	}
	
	//根据名称 --非ResponseBody方式
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showUserLists(@RequestBody RadCheckSearchModel rsm,HttpServletRequest request,HttpServletResponse response) {
		PageList<RadReply> plist = radReplyService.queryUserPage(rsm);
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
	public void showReplyDatas(@RequestBody RadUserModel rcs,HttpServletRequest request,HttpServletResponse response) {
		System.out.println(rcs.getUsername());
		List<RadReply> checkList = radReplyService.queryByUsername("select rc from RadReply rc where username like ?", rcs.getUsername());
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
	
	private List<RadReply> deserTools(String prop) throws Exception{
		return new JSONDeserializer<List<RadReply>>().use("values", RadReply.class).deserialize(prop);
	}
	
	@RequestMapping(value = "/merge", method = RequestMethod.POST)
	public void mergeDatas(@RequestBody RadUserMergeModel rcmm,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(!"[]".equals(rcmm.getAddList())){
				List<RadReply> addList  = deserTools(rcmm.getAddList());
				radReplyService.batchSave(addList);
				System.out.println("批量添加完成！");
			}
			if(!"[]".equals(rcmm.getEditList())){
				List<RadReply> editList  = deserTools(rcmm.getEditList());
				radReplyService.batchUpdate(editList);
				System.out.println("批量更新完成");
			}
			if(!"[]".equals(rcmm.getDeleteList())){
				List<RadReply> deleteList  = deserTools(rcmm.getDeleteList());
				radReplyService.batchDel(deleteList);
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
	public String addToRadReplyPage(Model model){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new RadReply());
		}
		return "radius/radReplyAdd";
	}
	
	//添加记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addUser(Model model, @Valid @ModelAttribute("contentModel") RadReply radReply, BindingResult result) {
		if(result.hasErrors()){
			 return addToRadReplyPage(model);
		}
		radReplyService.addRadReply(radReply);
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_RADREPLY_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/radius/checks/reply/list";
	}
	
	//指向修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToUserPage(@PathVariable Integer id,HttpServletRequest request) {
		RadReply radReply = radReplyService.getRadReplyById(id);
		request.setAttribute("radReply", radReply);
		return "radius/radReplyUpdate";
	}
	
	//更改信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, RadReply radReply) {
		radReply.setId(id);
		radReplyService.updateRadReply(radReply);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_RADREPLY_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/checks/reply/list";
	}
	
	// 删除信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteRadReply(@PathVariable Integer id) {
		radReplyService.deleteRadReply(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_RADREPLY_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/checks/reply/list";
	}
}
