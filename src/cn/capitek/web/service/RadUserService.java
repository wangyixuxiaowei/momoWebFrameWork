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
import cn.capitek.entity.admin.RadCheckSearchModel;
import cn.capitek.entity.admin.RadUser;
import cn.capitek.web.dao.RadUserDao;

@Service
public class RadUserService {
	
	@Autowired
	protected RadUserDao radUserDao;
	
	/**
	 * 获取RadUser分页信息
	 * @param username
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<RadUser> queryPage(RadCheckSearchModel v) {
		Criteria countCriteria = radUserDao.getCriteria();	
		Criteria listCriteria = radUserDao.getCriteria();
		String username = v.getUsername();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_username = (username!=null && !username.equals(""))?Restrictions.like("username", "%"+username+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_username);
		listCriteria.add(cri_username);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<RadUser> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 新增一个radUser信息
	 * @param radUser
	 */
	public void addRadUser(RadUser radUser) {
		radUserDao.save(radUser);
	}
	
	/**
	 * 更改radUser信息
	 * @param radUser
	 */
	public void updateRadUser(RadUser radUser) {
		radUserDao.update(radUser);
	}
	
	/**
	 * 删除一个radUser信息
	 * @param id
	 */
	public void deleteRadUser(int id) {	
		RadUser RadUser = radUserDao.load(id);
		radUserDao.remove(RadUser);
	}

	/**
	 * 获取某个radUser
	 * @param id
	 * @return
	 */
	public RadUser getRadUserById(Integer id) {
		return radUserDao.get(id);
	}
}
