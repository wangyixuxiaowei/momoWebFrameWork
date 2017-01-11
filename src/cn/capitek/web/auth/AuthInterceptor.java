package cn.capitek.web.auth;

import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cn.capitek.common.exception.PermissionException;
import cn.capitek.entity.admin.RolePermission;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(handler.getClass().isAssignableFrom(HandlerMethod.class)){     	
        	AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);//拿到验证注解的对象      	 
       	 	if(authPassport == null || authPassport.validate() == false) {//没有声明需要权限,或者声明不验证权限
       	 		return true;//返回true，验证通过或者不验证，直接往下走业务流程
       	 	}else{//从session中取到“当前用户的权限”的内容
       	 		HttpSession session = request.getSession(true);
       	 		@SuppressWarnings("unchecked")
				List<RolePermission> rpList = (List<RolePermission>) session.getAttribute("currentRolePermission");
            	if(rpList!=null) {//用户已经登录，开始验证权限！
            		boolean hasPermission=false;
            		String requestServletPath=request.getServletPath();//比如：/home/index
            		System.out.println("requestServletPath:"+requestServletPath);
            		hasPermission = AuthTools.hasPermission(requestServletPath, rpList, "view");
            		System.out.println("hasPermission:"+hasPermission);
            		if(hasPermission){
            			return true;
            		}else{
            			throw new PermissionException("没有执行权限.");
            		}
            	}else{//当前用户没有登录——去登录，记住用户重定向之前的需求页面，等登录后，继续自动重定向访问
					StringBuilder urlBuilder = new StringBuilder(request.getContextPath());
            		urlBuilder.append("/auth/login");
            		if(request.getServletPath()!=null && !request.getServletPath().isEmpty()){
            			urlBuilder.append("?returnUrl=");
            			StringBuilder pathAndQuery = new StringBuilder(request.getServletPath());
            			if(request.getQueryString()!=null && !request.getQueryString().isEmpty()){
                			pathAndQuery.append("?");
                			pathAndQuery.append(request.getQueryString());
                		}
            			urlBuilder.append(URLEncoder.encode(pathAndQuery.toString(), "UTF-8"));
            		}
            		response.sendRedirect(urlBuilder.toString());
            		return false;
            	}
            }
        }else{
        	return true;
        }
	 }
}