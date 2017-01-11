package cn.capitek.web.controller;

import java.util.List;

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
import cn.capitek.entity.admin.RadCheck;
import cn.capitek.entity.admin.RadUserMergeModel;
import cn.capitek.entity.admin.RadUserModel;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.RadCheckService;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/radius/checks/check")
public class RadCheckController extends BaseController {
	
	@Autowired
	private RadCheckService radCheckService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showRadCheckList(){
		return "radius/radCheckList";
	}
	
	/*//根据名称 --非ResponseBody方式
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showUserLists(@RequestBody RadCheckSearchModel rsm,HttpServletRequest request,HttpServletResponse response) {
		PageList<RadCheck> plist = radCheckService.queryPage(rsm);
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
	}*/
	
	@RequestMapping(value = "/drill", method = RequestMethod.POST)
	public void showCheckDatas(@RequestBody RadUserModel rcs,HttpServletRequest request,HttpServletResponse response) {
		System.out.println(rcs.getUsername());
		List<RadCheck> checkList = radCheckService.queryByUsername("select rc from RadCheck rc where username like ?", rcs.getUsername());
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
	
	private List<RadCheck> deserTools(String prop) throws Exception{
		return new JSONDeserializer<List<RadCheck>>().use("values", RadCheck.class).deserialize(prop);
	}
	
	@RequestMapping(value = "/merge", method = RequestMethod.POST)
	public void mergeDatas(@RequestBody RadUserMergeModel rcmm,HttpServletRequest request,HttpServletResponse response) {
		try {
			if(!"[]".equals(rcmm.getAddList())){
				List<RadCheck> addList  = deserTools(rcmm.getAddList());
				radCheckService.batchSave(addList);
				System.out.println("批量添加完成！");
			}
			if(!"[]".equals(rcmm.getEditList())){
				List<RadCheck> editList  = deserTools(rcmm.getEditList());
				radCheckService.batchUpdate(editList);
				System.out.println("批量更新完成");
			}
			if(!"[]".equals(rcmm.getDeleteList())){
				List<RadCheck> deleteList  = deserTools(rcmm.getDeleteList());
				radCheckService.batchDel(deleteList);
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
	public String addToRadCheckPage(Model model){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new RadCheck());
		}
		return "radius/radCheckAdd";
	}
	
	//添加记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addUser(Model model, @Valid @ModelAttribute("contentModel") RadCheck radCheck, BindingResult result) {
		if(result.hasErrors()){
			 return addToRadCheckPage(model);
		}
		radCheckService.addRadCheck(radCheck);
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_RADCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/radius/checks/check/list";
	}
	
	//指向修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToUserPage(@PathVariable Integer id,HttpServletRequest request) {
		RadCheck radCheck = radCheckService.getRadCheckById(id);
		request.setAttribute("radCheck", radCheck);
		return "radius/radCheckUpdate";
	}
	
	//更改信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, RadCheck radCheck) {
		radCheck.setId(id);
		radCheckService.updateRadCheck(radCheck);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_RADCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/checks/check/list";
	}
	
	// 删除信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteRadCheck(@PathVariable Integer id) {
		radCheckService.deleteRadCheck(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_RADCHECK_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/checks/check/list";
	}
}
