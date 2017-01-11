package cn.capitek.web.service;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.capitek.common.util.PageList;
import cn.capitek.common.util.PageListUtil;
import cn.capitek.entity.admin.Module;
import cn.capitek.entity.admin.Role;
import cn.capitek.entity.admin.RolePermission;
import cn.capitek.entity.admin.RoleSearchModel;
import cn.capitek.web.auth.AuthTools;
import cn.capitek.web.dao.RoleDao;

@Service
public class RoleService {
	
	@Autowired
	protected RoleDao roleDao;
	
	/**
	 * 获取角色分页信息
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<Role> queryUserPage(RoleSearchModel v) {
		Criteria countCriteria = roleDao.getCriteria();	
		Criteria listCriteria = roleDao.getCriteria();
		String name = v.getName();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_name = (name!=null && !name.isEmpty())?Restrictions.like("name", "%"+name+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_name);
		listCriteria.add(cri_name);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Role> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 新增一个role信息
	 * @param user
	 */
	public void addRole(Role role) {
		roleDao.save(role);
	}
	
	/**
	 * 更改role信息
	 * @param role
	 */
	public void updateRole(Role role) {
		roleDao.update(role);
	}
	
	/**
	 * 删除一个role信息
	 * @param roleId
	 */
	public void deleteUser(int roleId) {	
		Role role = roleDao.load(roleId);
		roleDao.remove(role);
	}
	
	/**
	 * 获得全部角色列表
	 * @return
	 */
	public List<Role> getAllRoles(){
		return roleDao.loadAll();
	}
	
	/**
	 * 根据id获取某个角色
	 * @param roleId
	 * @return
	 */
	public Role getSingleRole(int roleId){
		return roleDao.load(roleId);
	}
	
}
