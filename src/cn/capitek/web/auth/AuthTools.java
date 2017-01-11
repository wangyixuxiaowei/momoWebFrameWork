package cn.capitek.web.auth;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.capitek.entity.admin.RolePermission;

public class AuthTools {
	
	/***
	 * 
	 * 功能:遍历权限列表 确认是否有权限
	 *
	 * @param rPerm
	 * @param rpList
	 * @param action
	 * @return TODO
	 * @return boolean    TODO
	 * @throws
	 */
	public static boolean hasPermission(String rPerm, List<RolePermission> rpList, String action) {
		for (RolePermission rp : rpList) {
			if (rp.getTarget().equals(rPerm)) {
				String[] actions = rp.getAction().split(",");
				for (String s : actions) {
					if (s.equals(action)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static Map<String, Boolean> actionPermsMap(String rPerm, List<RolePermission> rpList, String[] actionArr){
		Map<String,Boolean> hasPermsMap = new HashMap<String,Boolean>();
		for (String action : actionArr) {
			boolean b = AuthTools.hasPermission(rPerm, rpList, action);
			hasPermsMap.put(action, b);
		}
		return hasPermsMap;
	}
}
