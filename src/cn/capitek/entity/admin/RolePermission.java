package cn.capitek.entity.admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "SM_PERMISSION")
@BatchSize(size=120)
public class RolePermission implements java.io.Serializable {

	private int id;
	private String recipient;
	private String action;
	private String target;
	private String discriminator;
	
	
	public RolePermission() {
	}

	public RolePermission(int id) {
		this.id = id;
	}
	public RolePermission(int id, String recipient, String action,
			String target) {
		this.id = id;
		this.recipient = recipient;
		this.action = action;
		this.target = target;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 8, scale = 0)
	@NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "RECIPIENT_ID", length = 100)
	@Length(max = 100)
	public String getRecipient() {
		return this.recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	@Column(name = "ACTION")
	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Column(name = "TARGET", length = 100)
	@Length(max = 100)
	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(name = "DISCRIMINATOR", length = 20)
	@Length(max = 20)
	public String getDiscriminator()
	{
	    return discriminator;
	}
	public void setDiscriminator(String discriminator)
	{
	    this.discriminator = discriminator;
	}
}
