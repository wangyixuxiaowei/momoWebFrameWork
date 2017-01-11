package cn.capitek.web.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.capitek.common.util.PageList;
import cn.capitek.entity.admin.OperLogSearchModel;
import cn.capitek.entity.admin.OperationLog;
import cn.capitek.web.auth.AuthPassport;
import cn.capitek.web.service.OperLogService;
import flexjson.JSONSerializer;

@Controller
@RequestMapping(value="/system/oplog")
public class OperLogController extends BaseController {
	
	@Autowired
	private OperLogService operLogService;
	
	@AuthPassport
	@RequestMapping(value = "/list", method = RequestMethod.GET)  
	public String showUserList(){
		return "system/operlog";
	}
	
	//根据查询参数 --非ResponseBody方式
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public void showLogLists(@RequestBody OperLogSearchModel olm, HttpServletResponse response) {
		PageList<OperationLog> plist = operLogService.queryOperLogPage(olm);
		response.setCharacterEncoding("utf-8");
		JSONSerializer serializer = new JSONSerializer();
		String res_data = serializer.include("items").exclude("*.class").serialize(plist);
		try {
			response.getWriter().write(res_data);
			response.getWriter().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
