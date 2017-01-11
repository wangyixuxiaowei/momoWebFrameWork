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
import cn.capitek.entity.admin.RadCheck;
import cn.capitek.entity.admin.RadCheckSearchModel;
import cn.capitek.web.dao.RadCheckDao;

@Service
public class RadCheckService {
	
	@Autowired
	protected RadCheckDao radCheckDao;
	
	/**
	 * 获取RadCheck分页信息
	 * @param username
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<RadCheck> queryPage(RadCheckSearchModel v) {
		Criteria countCriteria = radCheckDao.getCriteria();	
		Criteria listCriteria = radCheckDao.getCriteria();
		String username = v.getUsername();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_username = (username!=null && !username.equals(""))?Restrictions.like("username", "%"+username+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_username);
		listCriteria.add(cri_username);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<RadCheck> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 获取RadCheck list信息
	 * @return
	 */
	public List<RadCheck> queryAll() {
        return radCheckDao.loadAll();
	}
	
	/**
	 * 获取RadCheck list信息by username
	 * @param username
	 * @return
	 */
	public List<RadCheck> queryByUsername(String hql,String username) {
        return radCheckDao.find(hql, new Object[]{username});
	}
	
	/**
	 * 新增一个radCheck信息
	 * @param radCheck
	 */
	public void addRadCheck(RadCheck radCheck) {
		radCheckDao.save(radCheck);
	}
	
	/**
	 * 更改radCheck信息
	 * @param radCheck
	 */
	public void updateRadCheck(RadCheck radCheck) {
		radCheckDao.update(radCheck);
	}
	
	/**
	 * 删除一个radCheck信息
	 * @param id
	 */
	public void deleteRadCheck(int id) {	
		RadCheck RadCheck = radCheckDao.load(id);
		radCheckDao.remove(RadCheck);
	}

	/**
	 * 获取某个radCheck
	 * @param id
	 * @return
	 */
	public RadCheck getRadCheckById(Integer id) {
		return radCheckDao.get(id);
	}
	
	public void batchSave(List<RadCheck> list){
		radCheckDao.batchSave(list);
	}
	
	public void batchUpdate(List<RadCheck> list){
		radCheckDao.batchUpdate(list);
	}
	public void batchDel(List<RadCheck> list){
		radCheckDao.batchDelete(list);
	}
}
