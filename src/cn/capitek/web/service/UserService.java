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
import cn.capitek.entity.admin.User;
import cn.capitek.entity.admin.UserSearchModel;
import cn.capitek.web.dao.UserDao;

@Service
public class UserService {
	
	@Autowired
	protected UserDao userDao;
	
	/**
	 * 获取用户分页信息
	 * @param username
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageList<User> queryUserPage(UserSearchModel v) {
		Criteria countCriteria = userDao.getCriteria();	
		Criteria listCriteria = userDao.getCriteria();
		String username = v.getUsername();
		String name = v.getName();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_username = (username!=null && !username.equals(""))?Restrictions.like("username", "%"+username+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_name = (name!=null && !name.isEmpty())?Restrictions.like("name", "%"+name+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_username).add(cri_name);
		listCriteria.add(cri_username).add(cri_name);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<User> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}
	
	/**
	 * 新增一个user信息
	 * @param user
	 */
	public void addUser(User user) {
		userDao.save(user);
	}
	
	/**
	 * 更改user信息
	 * @param user
	 */
	public void updateUser(User user) {
		userDao.update(user);
	}
	
	/**
	 * 删除一个user信息
	 * @param userId
	 */
	public void deleteUser(int userId) {	
		User user = userDao.load(userId);
		userDao.remove(user);
	}

	/**
	 * 获取某个user
	 * @param userId
	 * @return
	 */
	public User getUserById(Integer userId) {
		return userDao.get(userId);
	}
}
