/**
 * Function： TODO 
 *
 *
 * Copyright (c) 2009, Capitek All Rights Reserved.
*/

package cn.capitek.common.util;

public class Constants{
	
	//operation
	public static String OPERATION_TYPE_LOGIN="登录";
	public static String OPERATION_TYPE_LOGOUT="登出";	
	public static String OPERATION_TYPE_ADD="添加";
	public static String OPERATION_TYPE_DEL="删除";
	public static String OPERATION_TYPE_EDIT="修改";
	public static String OPERATION_RESULT_GETTREE="获取权限列表";
	public static String OPERATION_RESULT_GETDATA_FAIL="获取数据失败";
	
	//module
	public static String MODULE_SYSTEM_MANAGER="系统管理";
	public static String MODULE_USER_MANAGER="用户管理";
	public static String MODULE_ROLE_MANAGER="角色管理";
	public static String MODULE_RADCHECK_MANAGER="RADCHECK管理";
	public static String MODULE_RADREPLY_MANAGER="RADREPLY管理";
	public static String MODULE_RADPOSTAUTH_MANAGER="RADPOSTAUTH管理";
	public static String MODULE_RADACCT_MANAGER="RADACCT管理";
	public static String MODULE_RADUSERGROUP_MANAGER="RADUSERGROUP管理";
	public static String MODULE_RADGROUPCHECK_MANAGER="RADGROUPCHECK管理";
	public static String MODULE_RADGROUPREPLY_MANAGER="RADGROUPREPLY管理";
	public static String MODULE_NAS_MANAGER="NAS管理";
	//result
	public static String OPERATION_RESULT_SUCCESS="成功";
	public static String OPERATION_RESULT_FAIL="失败";

	//获取root path
	private static String getRootPath(){		
		return Constants.class.getResource("/").getPath();
	}
}