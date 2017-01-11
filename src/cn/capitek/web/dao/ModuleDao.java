package cn.capitek.web.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import cn.capitek.entity.admin.Module;

/**
 * 用户Dao
 */
@Repository
public class ModuleDao extends BaseDao<Module> {
	@SuppressWarnings("unchecked")
	public List<Module> getRoleMPerms(String hsql,String roleId){
		Session session = getSession();
		session.enableFilter("roleModuleFilter").setParameter("roleId", roleId);
		session.enableFilter("roleModuleChildFilter").setParameter("roleId", roleId);
		List<Module> resList = session.createQuery(hsql).list();
		session.disableFilter("roleModuleFilter");
		session.disableFilter("roleModuleChildFilter");
		return resList;
	}
}
