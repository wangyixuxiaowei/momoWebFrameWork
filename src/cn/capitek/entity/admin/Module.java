package cn.capitek.entity.admin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.FilterDefs;
import org.hibernate.annotations.ParamDef;
import org.hibernate.validator.constraints.Length;

import cn.capitek.common.util.Attributes;
/**
 * 系统模块类
 */
@Entity
@Table(name = "SM_MODULE")
@NamedQueries({
        @NamedQuery(name = "modulea.level", query = "select m from Module m where m.parent.cid=:parent and m.isenable=true order by m.orderid asc")
})
//过滤不是本角色的Module.
@FilterDefs({
        @FilterDef(name = "roleModuleFilter", parameters ={
            @ParamDef(name = "roleId", type = "string")
        }), @FilterDef(name = "roleModuleChildFilter", parameters ={
            @ParamDef(name = "roleId", type = "string")
        })
})
@Filter(name = "roleModuleFilter", condition = "isenable !='0' and code in (select SM_PERMISSION.TARGET from SM_PERMISSION where SM_PERMISSION.RECIPIENT_ID=:roleId )")
@BatchSize(size = 100)
public class Module implements java.io.Serializable{
	private static final long serialVersionUID = 7819078398104433162L;
	
	private int cid;
    private Integer id;
    private String text;
    private String name;
    private Integer pid;
    private String href;
    private boolean isenable;
    private Integer orderid;
    private String code;
    private int isdefault;
    private String topclass;
    private String menuclass;
    private String hrefTarget;
    private Module parent;
    private Set<Module> children;
    private Set<Module> childs;
    private Set<PermValue> permValues = new HashSet<PermValue>(0);
    private String memo;

    private Attributes attributes;
    
    @Transient
	public Attributes getAttributes() {
		return attributes;
	}
    
	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Transient
    public Integer getPid() {
		return this.parent.getCid();
	}



    
	@Transient
    public String getName() {
		String m = this.getMemo()==null?"":"["+this.getMemo()+"]";
		return this.text+m;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	public Set<Module> getChilds() {
		return this.children;
	}

	public void setChilds(Set<Module> childs) {
		this.childs = childs;
	}

	/**
     * 是否是叶子节点
     */
    private boolean leaf = true;

    /**
     * 是否已选择
     */
    private Object checked = null;
    private Object opcheked = null;
    
    public Module(){
    }

    public Module(int cid){
        this.cid = cid;
    }

    public Module(int cid, Integer id,String text, Integer pid, String href,
            boolean isenable, Integer orderid, String code, int isdefault,
            String topclass, String menuclass, Set<PermValue> permValues,Attributes attr){
        this.cid = cid;
        this.id = id;
        this.text = text;
        this.pid = pid;
        this.href = href;
        this.isenable = isenable;
        this.orderid = orderid;
        this.code = code;
        this.isdefault = isdefault;
        this.topclass = topclass;
        this.menuclass = menuclass;
        this.permValues = permValues;
        this.attributes = attr;
    }

    @Id
    @Column(name = "CID", unique = true, nullable = false, precision = 8, scale = 0)
    @NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
    public int getCid(){
        return this.cid;
    }

    public void setCid(int cid){
        this.cid = cid;
    }

    @Column(name = "NAME", length = 30)
    @Length(max = 30)
    public String getText(){
        return this.text;
    }

    public void setText(String text){
        this.text = text;
    }

    /*
     * @Column(name = "PID", precision = 8, scale = 0) public Integer getPid() {
     * return this.pid; }
     * referencedColumnName="ID",
     * public void setPid(Integer pid) { this.pid = pid; }
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PID",nullable = false)
    @NotNull
    @OrderBy(value = "orderid")
    public Module getParent(){
        return parent;
    }

    public void setParent(Module parent){
        this.parent = parent;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "parent")
    @OrderBy("orderid asc")
    //过滤不是本角色的Module.
    @Filter(name = "roleModuleChildFilter", condition = "isenable!=0 and code in (select SM_PERMISSION.TARGET from SM_PERMISSION where SM_PERMISSION.RECIPIENT_ID=:roleId)")
    @BatchSize(size = 100)
    public Set<Module> getChildren(){
        if (this.children != null)
            return children;
        else
            return null;
    }

    public void setChildren(Set<Module> children){
        this.children = children;
    }

    @Column(name = "LINKURL", length = 100)
    @Length(max = 100)
    public String getHref(){
        return this.href;
    }

    public void setHref(String href){
        this.href = href;
    }

    @Column(name = "ISENABLE", precision = 1, scale = 0)
    public boolean isIsenable(){
        return this.isenable;
    }

    public void setIsenable(boolean isenable)
    {
        this.isenable = isenable;
    }

    @Column(name = "ORDERID", precision = 8, scale = 0)
    public Integer getOrderid(){
        return this.orderid;
    }

    public void setOrderid(Integer orderid){
        this.orderid = orderid;
    }

    @Column(name = "CODE", length = 100)
    @Length(max = 100)
    public String getCode(){
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Column(name = "ISDEFAULT", precision = 2, scale = 0)
    public int getIsdefault(){
        return this.isdefault;
    }

    public void setIsdefault(int isdefault){
        this.isdefault = isdefault;
    }

    @Column(name = "TOPCLASS", length = 30)
    @Length(max = 30)
    public String getTopclass(){
        return this.topclass;
    }

    public void setTopclass(String topclass){
        this.topclass = topclass;
    }

    @Column(name = "MENUCLASS", length = 30)
    @Length(max = 30)
    public String getMenuclass(){
        return this.menuclass;
    }

    public void setMenuclass(String menuclass){
        this.menuclass = menuclass;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "module")
    @BatchSize(size = 10)
    public Set<PermValue> getPermValues(){
        return this.permValues;
    }

    public void setPermValues(Set<PermValue> permValues){
        this.permValues = permValues;
    }

    @Column(name = "TARGET", length = 30)
    @Length(max = 30)
    public String getHrefTarget(){
        return hrefTarget;
    }

    public void setHrefTarget(String hrefTarget){
        this.hrefTarget = hrefTarget;
    }

    @Transient
    public boolean getLeaf(){
        if (this.children != null && this.children.size() > 0){
            return false;
        }
        return true;
    }

    public void setLeaf(boolean leaf){
        this.leaf = leaf;
    }

    /**
     * @return the checked
     */
    @Transient
    public Object getChecked(){
    	return checked;
    }

    /**
     * @param checked
     *            the checked to set
     */
    public void setChecked(Object checked){
        this.checked = checked;
    }

    @Transient
	public Object getOpcheked() {
		return this.isenable;
	}

	public void setOpcheked(Object opcheked) {
		this.opcheked = opcheked;
	}

    @Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    @Column(name = "MEMO")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
}
