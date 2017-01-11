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
import cn.capitek.entity.admin.RadPostAuth;
import cn.capitek.web.dao.RadPostAuthDao;

@Service
public class RadPostAuthService {
	
	@Autowired
	protected RadPostAuthDao radPostAuthDao;
	
	/**
	 * 获取RadPostAuth分页信息
	 * @param username
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<RadPostAuth> queryPage(RadCheckSearchModel v) {
		Criteria countCriteria = radPostAuthDao.getCriteria();	
		Criteria listCriteria = radPostAuthDao.getCriteria();
		String username = v.getUsername();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_username = (username!=null && !username.equals(""))?Restrictions.like("username", "%"+username+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_username);
		listCriteria.add(cri_username);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<RadPostAuth> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 新增信息
	 * @param radPostAuth
	 */
	public void addRadPostAuth(RadPostAuth radPostAuth) {
		radPostAuthDao.save(radPostAuth);
	}
	
	/**
	 * 更改信息
	 * @param radPostAuth
	 */
	public void updateRadPostAuth(RadPostAuth radPostAuth) {
		radPostAuthDao.update(radPostAuth);
	}
	
	/**
	 * 删除信息
	 * @param id
	 */
	public void deleteRadPostAuth(int id) {	
		RadPostAuth RadPostAuth = radPostAuthDao.load(id);
		radPostAuthDao.remove(RadPostAuth);
	}

	/**
	 * 获取某个radPostAuth
	 * @param id
	 * @return
	 */
	public RadPostAuth getRadPostAuthById(Integer id) {
		return radPostAuthDao.get(id);
	}
}
