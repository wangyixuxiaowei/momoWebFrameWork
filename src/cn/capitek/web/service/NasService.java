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
import cn.capitek.entity.admin.Nas;
import cn.capitek.entity.admin.NasSearchModel;
import cn.capitek.web.dao.NasDao;

@Service
public class NasService {
	
	@Autowired
	protected NasDao nasDao;
	
	/**
	 * 获取Nas分页信息
	 * @param NasSearchModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<Nas> queryPage(NasSearchModel v) {
		Criteria countCriteria = nasDao.getCriteria();	
		Criteria listCriteria = nasDao.getCriteria();
		String nasname = v.getNasname();
		String shortname = v.getShortname();
		String type = v.getType();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_nasname = (nasname!=null && !nasname.equals(""))?Restrictions.like("nasname", "%"+nasname+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_shortname = (shortname!=null && !shortname.equals(""))?Restrictions.like("shortname", "%"+shortname+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_type = (type!=null && !type.equals(""))?Restrictions.like("type", "%"+type+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_nasname).add(cri_shortname).add(cri_type);
		listCriteria.add(cri_nasname).add(cri_shortname).add(cri_type);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Nas> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 新增一个nas信息
	 * @param nas
	 */
	public void addNas(Nas nas) {
		nasDao.save(nas);
	}
	
	/**
	 * 更改nas信息
	 * @param nas
	 */
	public void updateNas(Nas nas) {
		nasDao.update(nas);
	}
	
	/**
	 * 删除一个nas信息
	 * @param id
	 */
	public void deleteNas(int id) {	
		Nas Nas = nasDao.load(id);
		nasDao.remove(Nas);
	}

	/**
	 * 获取某个nas
	 * @param id
	 * @return
	 */
	public Nas getNasById(Integer id) {
		return nasDao.get(id);
	}
}
