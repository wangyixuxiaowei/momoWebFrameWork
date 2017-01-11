package cn.capitek.web.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.capitek.common.util.StringHelper;
import cn.capitek.entity.admin.Module;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.entity.admin.User;
import cn.capitek.web.dao.ModuleDao;
import cn.capitek.web.dao.PermissionDao;
import cn.capitek.web.dao.UserDao;
import flexjson.JSONSerializer;

@Service
public class LoginService {
	
	@Autowired
	protected UserDao userDao;
	
	@Autowired
	protected PermissionDao permDao;
	
	@Autowired
	protected ModuleDao moduleDao;
	
	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	@SuppressWarnings("unchecked")
	public User login(String username, String password) throws NoSuchAlgorithmException{
		Criteria criteria = userDao.getCriteria();
		criteria.add(Restrictions.eq("username", username));
		//criteria.add(Restrictions.eq("password", StringHelper.md5(username + password)));	
		criteria.add(Restrictions.eq("password", password));
		List<User> userList = criteria.list();
		User current = null;
		if(userList != null && !userList.isEmpty()){
			current = userList.get(0);
		}
		return current;
	}

	/**
	 * 通过角色code获取角色的权限列表
	 * @param hsql
	 * @param code
	 * @return
	 */
	public List<RolePermission> getLoginRolePerms(String hsql, String code) {
		return permDao.find(hsql, new Object[]{code});
	}

	/**
	 * 获取当前角色的全部权限值
	 */
	public String getMenuJson(String roleId) {
		final String hsql = "from Module m where m.parent.cid=0 and m.isenable=true order by m.orderid asc";
		//List<Module> moduleList = moduleDao.find(hsql);
		List<Module> moduleList =  moduleDao.getRoleMPerms(hsql, roleId);
		JSONSerializer serializer = new JSONSerializer();
		String menuData = serializer.exclude("*.childs").exclude("parent").exclude("*.checked")
                .exclude("*.class").include("children").exclude("*.permValues").exclude("*.opcheked")
                .deepSerialize(moduleList);
		return menuData;
		
	}
}
