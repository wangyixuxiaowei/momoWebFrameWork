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
import cn.capitek.entity.admin.RadGroupCheck;
import cn.capitek.entity.admin.RadGroupCheckSearchModel;
import cn.capitek.web.dao.RadGroupCheckDao;

@Service
public class RadGroupCheckService {
	
	@Autowired
	protected RadGroupCheckDao radGroupCheckDao;
	
	/**
	 * 获取RadGroupCheck分页信息
	 * @param RadGroupCheckSearchModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<RadGroupCheck> queryPage(RadGroupCheckSearchModel v) {
		Criteria countCriteria = radGroupCheckDao.getCriteria();	
		Criteria listCriteria = radGroupCheckDao.getCriteria();
		String groupname = v.getGroupname();
		String attribute = v.getAttribute();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_groupname = (groupname!=null && !groupname.equals(""))?Restrictions.like("groupname", "%"+groupname+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_attribute = (attribute!=null && !attribute.equals(""))?Restrictions.like("attribute", "%"+attribute+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_groupname).add(cri_attribute);
		listCriteria.add(cri_groupname).add(cri_attribute);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<RadGroupCheck> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 新增一个radGroupCheck信息
	 * @param radGroupCheck
	 */
	public void addRadGroupCheck(RadGroupCheck radGroupCheck) {
		radGroupCheckDao.save(radGroupCheck);
	}
	
	/**
	 * 更改radGroupCheck信息
	 * @param radGroupCheck
	 */
	public void updateRadGroupCheck(RadGroupCheck radGroupCheck) {
		radGroupCheckDao.update(radGroupCheck);
	}
	
	/**
	 * 删除一个radGroupCheck信息
	 * @param id
	 */
	public void deleteRadGroupCheck(int id) {	
		RadGroupCheck RadGroupCheck = radGroupCheckDao.load(id);
		radGroupCheckDao.remove(RadGroupCheck);
	}

	/**
	 * 获取某个radGroupCheck
	 * @param id
	 * @return
	 */
	public RadGroupCheck getRadGroupCheckById(Integer id) {
		return radGroupCheckDao.get(id);
	}
}
