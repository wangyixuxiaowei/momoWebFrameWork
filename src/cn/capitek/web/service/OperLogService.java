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
import cn.capitek.entity.admin.OperLogSearchModel;
import cn.capitek.entity.admin.OperationLog;
import cn.capitek.web.dao.OperLogDao;

@Service
public class OperLogService {
	
	@Autowired
	protected OperLogDao operLogDao;
	
	/**
	 * 新增一个log信息
	 * @param log
	 */
	public void addLog(OperationLog log) {
		operLogDao.save(log);
	}
	
	/**
	 * 获取日志分页信息
	 * @param v
	 * @return
	 */
	public PageList<OperationLog> queryOperLogPage(OperLogSearchModel v) {
		Criteria countCriteria = operLogDao.getCriteria();	
		Criteria listCriteria = operLogDao.getCriteria();
		String operator = v.getOperator();
		String operationType = v.getOperationType();
		String module = v.getModule();
		String operationResult = v.getOperationResult();
		int pageNo = v.getPageNo();
		int pageSize = v.getPageSize();
		Criterion cri_operator = (operator!=null && !operator.isEmpty())?Restrictions.like("operator", "%"+operator+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_operationType = (operationType!=null && !operationType.isEmpty())?Restrictions.like("operationType", "%"+operationType+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_module = (module!=null && !module.isEmpty())?Restrictions.like("module", "%"+module+"%"):Restrictions.sqlRestriction("1=1");
		Criterion cri_operationResult = (operationResult!=null && !operationResult.isEmpty())?Restrictions.like("operationResult", "%"+operationResult+"%"):Restrictions.sqlRestriction("1=1");
		countCriteria.add(cri_operator).add(cri_operationType).add(cri_module).add(cri_operationResult);
		listCriteria.add(cri_operator).add(cri_operationType).add(cri_module).add(cri_operationResult);
        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<OperationLog> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        return PageListUtil.getPageList(count, pageNo, pageSize, items);
	}

}
