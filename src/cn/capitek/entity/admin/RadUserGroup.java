package cn.capitek.entity.admin;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * The persistent class for the radusergroup database table.
 * 
 */
@Entity
@NamedQuery(name="RadUserGroup.findAll", query="SELECT r FROM RadUserGroup r")
public class RadUserGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(length=64,nullable=false,unique=true)
	@NotEmpty(message="{property.not.empty}")
	@Length(max=64)
	private String username;
	
	@Column(length=64,nullable=false)
	@NotEmpty(message="{property.not.empty}")
	@Length(max=64)
	private String groupname;

	@Column(precision=11,nullable=false)
	@NotNull(message="{property.not.empty}")
	private Integer priority;

	public RadUserGroup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}