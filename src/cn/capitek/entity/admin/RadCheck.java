package cn.capitek.entity.admin;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


/**
 * The persistent class for the radcheck database table.
 * 
 */
@Entity
@NamedQuery(name="RadCheck.findAll", query="SELECT r FROM RadCheck r")
public class RadCheck implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@NotNull
	@Length(max = 64)
	private String attribute;
	
	@NotNull
	@Length(min=1,max=2)
	private String op;
	
	@NotNull
	@Length(max = 64)
	private String username;
	
	@NotNull
	@Length(max = 253)
	private String value;

	public RadCheck() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAttribute() {
		return this.attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getOp() {
		return this.op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}