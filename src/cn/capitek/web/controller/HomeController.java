package cn.capitek.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.capitek.web.auth.AuthPassport;

@Controller
@RequestMapping(value="/home")
public class HomeController extends BaseController {
	
	@AuthPassport
	@RequestMapping(value = "/index")  
	public String testIndex(HttpServletRequest request){
		request.setAttribute("yep", "许墨安然");
		return "home/index";
	}
	
}
