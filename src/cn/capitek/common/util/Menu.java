package cn.capitek.common.util;

import java.util.Set;
/**
 * 
 * 菜单树类. 
 * 
 *
 */
public class Menu
{
    public Menu(){}
    private Set<Menu> children;
    /**
     * 文本
     */
    private String text;
    /**
     * 已选
     */
    private Object checked;
    /**
     * id
     */
    private int id;
    /**
     * 是否是叶子节点
     */
    private boolean leaf;
    /**
     * 模块代码
     */
    private String code;
    
    
    private Attributes attributes;
    
    //add for op ↓
    private String name;
    private Integer pid;
    private String href;
    private Object opcheked = null;
    private Set<Menu> childs;
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Set<Menu> getChilds() {
		return childs;
	}
	public void setChilds(Set<Menu> childs) {
		this.childs = childs;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Object getOpcheked() {
		return opcheked;
	}
	public void setOpcheked(Object opcheked) {
		this.opcheked = opcheked;
	}
	
	public Attributes getAttributes() {
		return attributes;
	}
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}
	
	//add for op ↑

	/**
     * @return the children
     */
    public Set<Menu> getChildren()
    {
        return children;
    }
    /**
     * @param children the children to set
     */
    public void setChildren(Set<Menu> children)
    {
        this.children = children;
    }
    /**
     * @return the text
     */
    public String getText()
    {
        return text;
    }
    /**
     * @param text the text to set
     */
    public void setText(String text)
    {
        this.text = text;
    }
    /**
     * @return the checked
     */
    public Object getChecked()
    {
        return checked;
    }
    /**
     * @param checked the checked to set
     */
    public void setChecked(Object checked)
    {
        this.checked = checked;
    }
    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }
    /**
     * @return the leaf
     */
    public boolean getLeaf()
    {
        return leaf;
    }
    /**
     * @param leaf the leaf to set
     */
    public void setLeaf(boolean leaf)
    {
        this.leaf = leaf;
    }
    /**
     * @param code the code to set
     */
    public void setCode(String code)
    {
	this.code = code;
    }
    /**
     * @return the code
     */
    public String getCode()
    {
	return code;
    }
}
