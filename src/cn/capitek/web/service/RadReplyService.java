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
import cn.capitek.entity.admin.RadReply;
import cn.capitek.entity.admin.RadCheckSearchModel;
import cn.capitek.web.dao.RadReplyDao;

@Service
public class RadReplyService {
	
	@Autowired
	protected RadReplyDao radReplyDao;
	
	/**
	 * 获取RadCheck分页信息
	 * @param username
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<RadReply> queryUserPage(RadCheckSearchModel v) {
		Criteria countCriteria = radReplyDao.getCriteria();	
		Criteria listCriteria = radReplyDao.getCriteria();
		String username = v.getUsername();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_username = (username!=null && !username.equals(""))?Restrictions.like("username", "%"+username+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_username);
		listCriteria.add(cri_username);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<RadReply> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 获取RadReply list信息by username
	 * @param username
	 * @return
	 */
	public List<RadReply> queryByUsername(String hql,String username) {
        return radReplyDao.find(hql, new Object[]{username});
	}
	
	/**
	 * 新增
	 * @param radReply
	 */
	public void addRadReply(RadReply radReply) {
		radReplyDao.save(radReply);
	}
	
	/**
	 * 更改
	 * @param radReply
	 */
	public void updateRadReply(RadReply radReply) {
		radReplyDao.update(radReply);
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteRadReply(int id) {	
		RadReply RadReply = radReplyDao.load(id);
		radReplyDao.remove(RadReply);
	}

	/**
	 * 获取某个radReply
	 * @param id
	 * @return
	 */
	public RadReply getRadReplyById(Integer id) {
		return radReplyDao.get(id);
	}
	
	public void batchSave(List<RadReply> list){
		radReplyDao.batchSave(list);
	}
	
	public void batchUpdate(List<RadReply> list){
		radReplyDao.batchUpdate(list);
	}
	public void batchDel(List<RadReply> list){
		radReplyDao.batchDelete(list);
	}
}
