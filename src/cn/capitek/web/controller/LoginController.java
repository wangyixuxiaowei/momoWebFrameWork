package cn.capitek.web.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.capitek.common.exception.EntityOperateException;
import cn.capitek.common.exception.ValidatException;
import cn.capitek.common.util.Constants;
import cn.capitek.entity.admin.LoginModel;
import cn.capitek.entity.admin.Role;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.entity.admin.User;
import cn.capitek.web.service.LoginService;

@Controller
@RequestMapping(value = "/auth")
public class LoginController extends BaseController {  
	
	@Autowired
    private LoginService loginService;
	
	@Autowired
	private OperLogManager op;
	
	@RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login(Model model){
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new LoginModel());
		}
        return "login";
    }
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST})
	public String login(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") LoginModel accountLoginModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
        if(result.hasErrors()){
        	op.log(Constants.OPERATION_TYPE_LOGIN, Constants.MODULE_SYSTEM_MANAGER, Constants.OPERATION_RESULT_FAIL);
        	return login(model);
        }
        User account = loginService.login(accountLoginModel.getUsername().trim(), accountLoginModel.getPassword().trim());
        if(account==null || account.isEnable() == false || account.getRoles() == null){
        	if(account==null){
	        	result.addError(new FieldError("contentModel", "username", "用户名或密码错误"));
	        	result.addError(new FieldError("contentModel", "password", "用户名或密码错误"));
        	}else if(account.isEnable() == false){
        		result.addError(new FieldError("contentModel", "username", "此用户被禁用，不能登录"));
        	}else{
        		result.addError(new FieldError("contentModel", "username", "此用户当前未被授权，不能登录"));
        	}
        	op.log(Constants.OPERATION_TYPE_LOGIN, Constants.MODULE_SYSTEM_MANAGER, Constants.OPERATION_RESULT_FAIL);
            return login(model);
        }else{
        	request.getSession().setAttribute("currentUser", account);
        	request.getSession().setAttribute("currentUserIP", getIpAddr(request));
        	request.getSession().setAttribute("currentAgent", request.getHeader("USER-AGENT"));
    		if(account.getRoles().size() > 0){
    			Role role = (Role) Arrays.asList(account.getRoles().toArray()).get(0);
    			final String hsql = "select p from RolePermission p where p.recipient = ?";
    			List<RolePermission> rplist = loginService.getLoginRolePerms(hsql, role.getCode());
    			request.getSession().setAttribute("currentRole", role);
    			request.getSession().setAttribute("currentRoleId", String.valueOf(role.getId()));
    			request.getSession().setAttribute("currentRolePermission", rplist);
    			//获取全部的权限及动作json
    			String permsMenuJson = loginService.getMenuJson(String.valueOf(role.getId()));
    			request.getSession().setAttribute("currentPermsMenuJson", permsMenuJson);
    		}
        }
        //获取之前登录失败时使用的地址，如果没有（即用户是直接输入的登录地址，直接跳转到主页；否则跳转到之前想去的页面 
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl == null){
        	returnUrl = "/home/index";
        }
        op.log(Constants.OPERATION_TYPE_LOGIN, Constants.MODULE_SYSTEM_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
    	return "redirect:" + returnUrl; 	
	}
	
	@RequestMapping(value = "/menu", method = {RequestMethod.POST})
    public void getMenuJson(HttpServletRequest request, HttpServletResponse response){
		String  permsMenuJson = (String) request.getSession().getAttribute("currentPermsMenuJson");
		if(permsMenuJson != null){
			try {
				response.getWriter().write(permsMenuJson);
				response.getWriter().flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }
	
	@RequestMapping(value = "/logout", method = {RequestMethod.GET})
    public String logout(HttpServletRequest request){
		op.log(Constants.OPERATION_TYPE_LOGOUT, Constants.MODULE_SYSTEM_MANAGER, Constants.OPERATION_RESULT_SUCCESS);
		request.getSession().invalidate();
		return "redirect:/auth/login";
    }
	
	private String getIpAddr(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if( ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) ){
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}  
