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
import cn.capitek.entity.admin.Nas;
import cn.capitek.entity.admin.NasSearchModel;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.PermissionService;
import cn.capitek.web.service.NasService;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/radius/nas")
public class NasController extends BaseController {
	
	@Autowired
	private NasService nasService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private OperLogManager op;
	
	private final String[] actionArr = new String[]{"add","edit","delete"};
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showNasList(){
		return "radius/nasList";
	}
	
	//根据名称 --非ResponseBody方式
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showUserLists(@RequestBody NasSearchModel nsm,HttpServletRequest request,HttpServletResponse response) {
		PageList<Nas> plist = nasService.queryPage(nsm);
		List<RolePermission> rpList = (List<RolePermission>) request.getSession().getAttribute("currentRolePermission");
		Map<String, Boolean> m = permissionService.getActionPermsMap(nsm.getPermcode(), rpList, actionArr);
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
	public String addToNasPage(Model model){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new Nas());
		}
		return "radius/nasAdd";
	}
	
	//添加记录
	@RequestMapping(value = "/save", method=RequestMethod.PUT)  
	public String addUser(Model model, @Valid @ModelAttribute("contentModel") Nas nas, BindingResult result) {
		if(result.hasErrors()){
			 return addToNasPage(model);
		}
		nasService.addNas(nas);
		op.log(Constants.OPERATION_TYPE_ADD, Constants.MODULE_NAS_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
        return "redirect:/radius/nas/list";
	}
	
	//指向修改页面
	@RequestMapping(value="/{id}/edit")  
	public String editToUserPage(@PathVariable Integer id,HttpServletRequest request) {
		Nas nas = nasService.getNasById(id);
		request.setAttribute("nas", nas);
		return "radius/nasUpdate";
	}
	
	//更改信息
	@RequestMapping(value="/{id}/update",method=RequestMethod.PUT)  
	public String updateViewSpace(@PathVariable Integer id,HttpServletRequest request, Nas nas) {
		nas.setId(id);
		nasService.updateNas(nas);
		op.log(Constants.OPERATION_TYPE_EDIT, Constants.MODULE_NAS_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/nas/list";
	}
	
	// 删除信息
	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)  
	public String deleteNas(@PathVariable Integer id) {
		nasService.deleteNas(id);
		op.log(Constants.OPERATION_TYPE_DEL, Constants.MODULE_NAS_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		return "redirect:/radius/nas/list";
	}
}
