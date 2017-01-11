package cn.capitek.common.util;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import cn.capitek.entity.admin.RolePermission;
import cn.capitek.web.auth.AuthTools;

public class ValiPermission extends BodyTagSupport{
	
	private String code;
	private String action;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public int doAfterBody() throws JspException {
		try {
			if(hassPermission(code, action)){
				if(bodyContent != null){
					JspWriter out = bodyContent.getEnclosingWriter();
					bodyContent.writeOut(out);
				}
			}
		} catch (Exception e) {
			throw new JspException();
		}
		return SKIP_BODY;
	}

	@SuppressWarnings("unchecked")
	private boolean hassPermission(String code, String action){
		boolean r = false;
		HttpSession session = this.pageContext.getSession();
		List<RolePermission> rpList = (List<RolePermission>) session.getAttribute("currentRolePermission");
    	if(rpList!=null) {
    		r = AuthTools.hasPermission(code, rpList, action);
    	}
		return r;
	}

}
