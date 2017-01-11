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
import cn.capitek.entity.admin.RadGroupCheckSearchModel;
import cn.capitek.entity.admin.RadGroupReply;
import cn.capitek.web.dao.RadGroupReplyDao;

@Service
public class RadGroupReplyService {
	
	@Autowired
	protected RadGroupReplyDao radGroupReplyDao;
	
	/**
	 * 获取RadGroupReply分页信息
	 * @param RadGroupCheckSearchModel
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<RadGroupReply> queryPage(RadGroupCheckSearchModel v) {
		Criteria countCriteria = radGroupReplyDao.getCriteria();	
		Criteria listCriteria = radGroupReplyDao.getCriteria();
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
        List<RadGroupReply> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 新增一个radGroupReply信息
	 * @param radGroupReply
	 */
	public void addRadGroupReply(RadGroupReply radGroupReply) {
		radGroupReplyDao.save(radGroupReply);
	}
	
	/**
	 * 更改radGroupReply信息
	 * @param radGroupReply
	 */
	public void updateRadGroupReply(RadGroupReply radGroupReply) {
		radGroupReplyDao.update(radGroupReply);
	}
	
	/**
	 * 删除一个radGroupReply信息
	 * @param id
	 */
	public void deleteRadGroupReply(int id) {	
		RadGroupReply RadGroupReply = radGroupReplyDao.load(id);
		radGroupReplyDao.remove(RadGroupReply);
	}

	/**
	 * 获取某个radGroupReply
	 * @param id
	 * @return
	 */
	public RadGroupReply getRadGroupReplyById(Integer id) {
		return radGroupReplyDao.get(id);
	}
}
