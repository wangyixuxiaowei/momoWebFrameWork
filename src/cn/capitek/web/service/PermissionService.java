package cn.capitek.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.capitek.entity.admin.RolePermission;
import cn.capitek.web.auth.AuthTools;
import cn.capitek.web.dao.PermissionDao;

@Service
public class PermissionService {
	
	@Autowired
	protected PermissionDao permissionDao;

	public void addPermission(RolePermission rp) {
		permissionDao.save(rp);
	}

	public void deleteByRole(String code) {
		String hql = "delete RolePermission where recipient = :code";
		try {
			permissionDao.batchDelete(hql,code);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, Boolean> getActionPermsMap(String a, List<RolePermission> b, String[] c) {
		return AuthTools.actionPermsMap(a, b, c);
	}
}
