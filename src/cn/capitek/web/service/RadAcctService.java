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
import cn.capitek.entity.admin.RadAcct;
import cn.capitek.entity.admin.RadCheckSearchModel;
import cn.capitek.web.dao.RadAcctDao;

@Service
public class RadAcctService {
	
	@Autowired
	protected RadAcctDao radAcctDao;
	
	/**
	 * 获取RadAcct分页信息
	 * @param username
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<RadAcct> queryPage(RadCheckSearchModel v) {
		Criteria countCriteria = radAcctDao.getCriteria();	
		Criteria listCriteria = radAcctDao.getCriteria();
		String username = v.getUsername();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_username = (username!=null && !username.equals(""))?Restrictions.like("username", "%"+username+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_username);
		listCriteria.add(cri_username);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<RadAcct> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 新增一个radAcct信息
	 * @param radAcct
	 */
	public void addRadAcct(RadAcct radAcct) {
		radAcctDao.save(radAcct);
	}
	
	/**
	 * 更改radAcct信息
	 * @param radAcct
	 */
	public void updateRadAcct(RadAcct radAcct) {
		radAcctDao.update(radAcct);
	}
	
	/**
	 * 删除一个radAcct信息
	 * @param id
	 */
	public void deleteRadAcct(int id) {	
		RadAcct RadAcct = radAcctDao.load(id);
		radAcctDao.remove(RadAcct);
	}

	/**
	 * 获取某个radAcct
	 * @param id
	 * @return
	 */
	public RadAcct getRadAcctById(Integer id) {
		return radAcctDao.get(id);
	}
}
