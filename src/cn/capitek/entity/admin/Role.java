package cn.capitek.entity.admin;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "SM_ROLE")
public class Role implements java.io.Serializable
{

    private int id;
    private String code = "99";
    private String name;
    private String memo;
    private Set<User> users;
    
    private int roletype=11;
    
    @Column(name = "ROLETYPE", precision = 2, scale = 0)
    public int getRoletype()
    {
    	return roletype;
    }

	public void setRoletype(int roletype)
    {
    	this.roletype = roletype;
    }

	//权限值临时属性
    private String permValues;

    public Role()
    {
    }

    public Role(int id)
    {
        this.id = id;
    }

    public Role(int id, String code, String name, String memo)
    {
        this.id = id;
        this.code = code;
        this.name = name;
        this.memo = memo;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    public int getId()
    {
        return this.id;
    }

    public void setId(int id)
    {
        //this.code = String.valueOf(id);
        this.id = id;
    }

    @Column(name = "CODE", length = 50, unique = true, nullable = false)
    @Length(max = 50)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    @Column(name = "NAME", length = 100, unique = true, nullable = false)
    @Length(max = 100)
    @NotNull
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "MEMO", length = 100)
    @Length(max = 100)
    public String getMemo()
    {
        return this.memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    /**
     * @param permValues
     *            the permValues to set
     */
    public void setPermValues(String permValues)
    {
        this.permValues = permValues;
    }

    /**
     * @return the permValues
     */
    @Transient
    public String getPermValues()
    {
        return permValues;
    }

    /**
     * @param users
     *            the users to set
     */
    public void setUsers(Set<User> users)
    {
        this.users = users;
    }

    @ManyToMany(targetEntity = User.class, mappedBy = "roles", fetch = FetchType.LAZY)
    @Cascade(
    {
        org.hibernate.annotations.CascadeType.DELETE_ORPHAN
    })
    public Set<User> getUsers()
    {
        return users;
    }

    /*
     * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
     * "role") public Set<RoleUser> getRoleUsers() { return this.roleUsers; }
     * 
     * public void setRoleUsers(Set<RoleUser> roleUsers) { this.roleUsers =
     * roleUsers; }
     */
    
    /**
     * 
     * 功能:是否显示在界面上
     *
     * @return TODO
     * @return boolean    TODO
     */
    public boolean displayIs()
    {
    	if(roletype % 2 == 0)
    	{
    		return false;
    	}
    	return true;
    }
    /**
     * 
     * 功能:是否可修改
     *
     * @return TODO
     * @return boolean    TODO
     */
    public boolean modifyIs()
    {
    	if(roletype / 10 % 2 == 0)
    	{
    		return false;
    	}
    	return true;
    }
    /**
     * 
     * 功能:是否可删除
     *
     * @return TODO
     * @return boolean    TODO
     */
    public boolean deleteIs()
    {
    	if(roletype / 10 > 1)
    	{
    		return false;
    	}
    	return true;
    }
}
