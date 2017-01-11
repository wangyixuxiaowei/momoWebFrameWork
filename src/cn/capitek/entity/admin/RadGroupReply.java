package cn.capitek.entity.admin;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the radgroupreply database table.
 * 
 */
@Entity
@NamedQuery(name="RadGroupReply.findAll", query="SELECT r FROM RadGroupReply r")
public class RadGroupReply implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@NotNull
	@Column(length=64)
	private String attribute;

	@NotNull
	@Column(length=64)
	private String groupname;

	@NotNull
	@Column(length=2)
	private String op;

	@NotNull
	@Column(length=253)
	private String value;

	public RadGroupReply() {
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

	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getOp() {
		return this.op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}