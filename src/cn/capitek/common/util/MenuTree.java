package cn.capitek.common.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import cn.capitek.entity.admin.Module;
import cn.capitek.entity.admin.PermValue;
import cn.capitek.entity.admin.PermValueId;
/**
 * 
 * 角色管理中的树组装util 
 * 
 *
 */
public class MenuTree {   
    /**
     * 
     */
    public static int PERM_VALUE_ID_START = 1000000;
    /**
     * 
     * 整合权限值到模块的子节点
     * 
     * @param modules
     * @return
     */
	 public static Set<Menu> parseMenuTree(Set<Module> modules){
		Iterator<Module> moduleItr = modules.iterator();
		Set<Menu> menuHs = new HashSet<Menu>();
		while(moduleItr.hasNext()){	        
		    Module module = moduleItr.next();
		    if(!module.isIsenable()) {
		    	continue;
		    }
		    Menu menu = new Menu();
		    //拷贝Module对象
		    Attributes att = new Attributes(module.getCode());
		    menu.setAttributes(att);
		    menu.setCode(module.getCode());
		    menu.setText(module.getText());
		    menu.setId(module.getId());
		    menu.setLeaf(module.getLeaf());
		    menu.setChecked(false);
		    //递归显示子节点
		    if(module.getChildren().size() > 0){
		    	menu.setChildren(parseMenuTree(module.getChildren()));
		    }else if(module.getPermValues().size() > 0){//将权限值加入子节点
				Set<Menu> PvSet = new HashSet<Menu>();
				Iterator<PermValue> permValueItr = module.getPermValues().iterator();
				while(permValueItr.hasNext()){
				    PermValueId pv = permValueItr.next().getId();
				    Menu menuPv = new Menu();//防止串号
				    menuPv.setText(pv.getPeName());
				    Attributes attributes = new Attributes(pv.getPeCode());
				    menuPv.setAttributes(attributes);
				    //menuPv.setCode(pv.getPeCode());
				    menuPv.setId(pv.getPeId() + PERM_VALUE_ID_START);//防止串号
				    menuPv.setChecked(false);//未选择
				    menuPv.setLeaf(true);
				    PvSet.add(menuPv);
				}
				menu.setLeaf(false);
				menu.setChildren(PvSet);
		    }
		    module.setChecked(false);
		    menuHs.add(menu);
		}
		return menuHs;
	}
}
