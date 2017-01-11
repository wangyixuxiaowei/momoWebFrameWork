package cn.capitek.entity.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "SM_USER", uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
public class User implements java.io.Serializable{
    private int id;
    private String username;
    private String password;
    private String name;
    private String memo;
    private boolean enable;
    private int usertype = 11;
    private Date pwdUpdateTime;
    private int selectRoleId;
    
    private String pwdUpdateTimeStr;
    
	@Column(name = "USERTYPE", precision = 2, scale = 0)
    public int getUsertype(){
    	return usertype;
    }

    @Transient
	public int getSelectRoleId() {
		return selectRoleId;
	}

	public void setSelectRoleId(int selectRoleId) {
		this.selectRoleId = selectRoleId;
	}

	public void setUsertype(int usertype)
    {
    	this.usertype = usertype;
    }

	//private Set<RoleUser> roleUsers = new HashSet<RoleUser>(0);
    private Set<Role> roles;

    //单个Role
    private Role singleRole;

    //单个Role值
    private String singleRoleValue;

    //重复密码
    private String confirmPassword;

    public User(){
    }

    public User(int id){
        this.id = id;
    }

    public User(int id, String username, String password, String name,
            String memo, boolean enable, Set<Role> roles){
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.memo = memo;
        this.enable = enable;
        this.roles = roles;
    }

    @Id
    @Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    @Column(name = "USERNAME", length = 50, unique = true, nullable = false)
    @Length(max = 25, min = 4)
    @NotEmpty(message="{property.not.empty}")
    //@Pattern(regex = "^[a-zA-Z][a-zA-Z0-9]{3,25}$",message = "{validator.addon.acount}")
    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    @Column(name = "PASSWORD", length = 50)
    @NotEmpty(message="{property.not.empty}")
    //@Pattern(regex="^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[\\W,_]).*$",message="{validator.addon.pwdsrong}")
    //@NotNull
    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    @Column(name = "NAME", length = 100)
    @Length(max = 100)
    @NotEmpty(message="{property.not.empty}")
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "MEMO", length = 200)
    @Length(max = 200)
    public String getMemo()
    {
        return this.memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    @Column(name = "ENABLE", precision = 1, scale = 0)
    public boolean isEnable()
    {
        return this.enable;
    }

    public void setEnable(boolean enable)
    {
        this.enable = enable;
    }

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER, cascade =
    {
        CascadeType.REFRESH
    })
    @JoinTable(name = "SM_ROLE_USER", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    public Set<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    @Transient
    public Role getSingleRole()
    {
        if (this.roles != null && this.roles.size() > 0)
        {
            return (Role) this.roles.toArray()[0];
        }
        else
        {
            return null;
        }
    }

    public void setSingleRole(Role role)
    {
        this.singleRole = role;
    }

    /*
     * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy =
     * "user") public Set<RoleUser> getRoleUsers() { return this.roleUsers; }
     * 
     * public void setRoleUsers(Set<RoleUser> roleUsers) { this.roleUsers =
     * roleUsers; }
     */

    /**
     * @param singleRoleValue
     *            the singleRoleValue to set
     */
    public void setSingleRoleValue(String singleRoleValue)
    {
        this.singleRoleValue = singleRoleValue;
    }

    /**
     * @return the singleRoleValue
     */
    @Transient
    public String getSingleRoleValue()
    {
        if (this.singleRoleValue != null)
        {
            return this.singleRoleValue;
        }
        else
        {
            if (this.getSingleRole() != null)
            {
                return this.getSingleRole().getCode();
            }
            else
            {
                return "";
            }
        }
    }

    /**
     * @param confirmPassword
     *            the confirmPassword to set
     */
    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return the confirmPassword
     */
    @Transient
    @NotEmpty(message="{property.not.empty}")
    public String getConfirmPassword()
    {
        if (this.confirmPassword != null)
        {
            return this.confirmPassword;
        }
        else
        {
            return this.password;
        }
    }
    /**
     * 
     * 功能:是否显示在界面上
     *
     * @return TODO
     * @return boolean    TODO
     */
    public boolean displayIs()
    {
    	if(usertype % 2 == 0)
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
    	if(usertype / 10 % 2 == 0)
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
    	if(usertype / 10 > 1)
    	{
    		return false;
    	}
    	return true;
    }   
    
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PWD_UPDATE_TIME")
    public Date getPwdUpdateTime() {
		return pwdUpdateTime;
	}

	public void setPwdUpdateTime(Date pwdUpdateTime) {
		this.pwdUpdateTime = pwdUpdateTime;
	}

	@Transient
	public String getPwdUpdateTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.pwdUpdateTime);
	}

	public void setPwdUpdateTimeStr(String pwdUpdateTimeStr) {
		this.pwdUpdateTimeStr = pwdUpdateTimeStr;
	}
	
	
	
}
