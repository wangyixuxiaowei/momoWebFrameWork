package cn.capitek.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.capitek.entity.admin.Module;
import cn.capitek.web.dao.ModuleDao;

@Service
public class ModuleService {
	
	@Autowired
	protected ModuleDao moduleDao;
	
	public List<Module> getModuleTreeList() {
		String hsql = "select m from Module m where m.parent.cid=0 and m.isenable=true order by m.orderid asc";
		return moduleDao.find(hsql);
	}

	public List<Module> getModuleCTreeList(String roleId) {
		String hsql = "select pv.id.peId from Module m,RolePermission rp,PermValue pv where m.cid = pv.id.moduleId and m.isenable=true and rp.recipient = ? and m.code = rp.target and instr(rp.action||',',pv.id.peCode||',') > 0 ";
		return moduleDao.find(hsql, roleId);
	}
	
}
