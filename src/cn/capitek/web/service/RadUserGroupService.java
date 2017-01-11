package cn.capitek.web.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.capitek.common.util.PageList;
import cn.capitek.common.util.PageListUtil;
import cn.capitek.entity.admin.RadUserGroup;
import cn.capitek.entity.admin.RadUserGroupSearchModel;
import cn.capitek.web.dao.RadUserGroupDao;

@Service
public class RadUserGroupService {
	
	@Autowired
	protected RadUserGroupDao radUserGroupDao;
	
	/**
	 * 获取RadUserGroup分页信息
	 * @param RadUserGroupSearchModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<RadUserGroup> queryPage(RadUserGroupSearchModel v) {
		Criteria countCriteria = radUserGroupDao.getCriteria();	
		Criteria listCriteria = radUserGroupDao.getCriteria();
		String username = v.getUsername();
		String groupname = v.getGroupname();
		Integer priority = v.getPriority();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_username = (username!=null && !username.equals(""))?Restrictions.like("username", "%"+username+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_groupname = (groupname!=null && !groupname.equals(""))?Restrictions.like("groupname", "%"+username+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_priority = (priority!=null)?Restrictions.eq("priority", priority):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_username).add(cri_groupname).add(cri_priority);
		listCriteria.add(cri_username).add(cri_groupname).add(cri_priority);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<RadUserGroup> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 获取RadUserGroup list信息by username
	 * @param username
	 * @return
	 */
	public List<RadUserGroup> queryByUsername(String hql,String username) {
        return radUserGroupDao.find(hql, new Object[]{username});
	}
	
	/**
	 * 新增一个radUserGroup信息
	 * @param radUserGroup
	 */
	public void addRadUserGroup(RadUserGroup radUserGroup) {
		radUserGroupDao.save(radUserGroup);
	}
	
	/**
	 * 更改radUserGroup信息
	 * @param radUserGroup
	 */
	public void updateRadUserGroup(RadUserGroup radUserGroup) {
		radUserGroupDao.update(radUserGroup);
	}
	
	/**
	 * 删除一个radUserGroup信息
	 * @param id
	 */
	public void deleteRadUserGroup(int id) {	
		RadUserGroup RadUserGroup = radUserGroupDao.load(id);
		radUserGroupDao.remove(RadUserGroup);
	}

	/**
	 * 获取某个radUserGroup
	 * @param id
	 * @return
	 */
	public RadUserGroup getRadUserGroupById(Integer id) {
		return radUserGroupDao.get(id);
	}
	
	public void batchSave(List<RadUserGroup> list){
		radUserGroupDao.batchSave(list);
	}
	
	public void batchUpdate(List<RadUserGroup> list){
		radUserGroupDao.batchUpdate(list);
	}
	public void batchDel(List<RadUserGroup> list){
		radUserGroupDao.batchDelete(list);
	}
}
