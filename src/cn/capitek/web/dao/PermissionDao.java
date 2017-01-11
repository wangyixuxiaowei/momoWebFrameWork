package cn.capitek.web.dao;

import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.capitek.entity.admin.RolePermission;

/**
 * 用户权限Dao
 */
@Repository
public class PermissionDao extends BaseDao<RolePermission> {
	
    @Autowired
    private HibernateTemplate hibernateTemplate;
	
	@SuppressWarnings("unchecked")
	public void batchDelete(final String hql, final String code) throws Exception {
        hibernateTemplate.execute(new HibernateCallback() {
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
            	session = getSession();
                Query query = session.createQuery(hql);
                query.setParameterList("code", new Object[]{code});
                return query.executeUpdate();
            }
        });
	}

}
