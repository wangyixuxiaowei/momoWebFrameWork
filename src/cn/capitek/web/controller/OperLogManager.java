package cn.capitek.web.controller;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.capitek.entity.admin.OperationLog;
import cn.capitek.entity.admin.User;
import cn.capitek.web.service.OperLogService;

@Service
public class OperLogManager implements Serializable{
	
	@Autowired
	private OperLogService operLogService;

	/**
	 * 
	 */
	private static final long serialVersionUID = 9209760625060297791L;
	
	/**
	 * 
	 * 功能:log方法,记录日志
	 *
	 * @param opType 操作类型
	 * @param module 操作模块
	 * @param result 操作结果
	 * @return void    TODO
	 * @throws
	 */
	public void log(String opType, String module, String result){
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();  
		HttpServletRequest request = ((ServletRequestAttributes)ra).getRequest();
		User user = (User) request.getSession().getAttribute("currentUser");
		String ip = (String) request.getSession().getAttribute("currentUserIP");
		OperationLog log = new OperationLog();
		log.setOperator(user==null?"":user.getUsername());
		log.setOperatorIP(ip==null?"":ip);
		log.setOperationType(opType);
		log.setModule(module);		
		log.setOperationTime(new Date());
		log.setOperationResult(result);
		operLogService.addLog(log);
	}
}
