package cn.capitek.entity.admin;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

@Entity
@Table(name = "SM_PERMVALUE")
@BatchSize(size=120)
public class PermValue implements java.io.Serializable {

	private PermValueId id;
	private Module module;

	public PermValue() {
	}

	public PermValue(PermValueId id) {
		this.id = id;
	}
	public PermValue(PermValueId id, Module module) {
		this.id = id;
		this.module = module;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "peId", column = @Column(name = "PE_ID", precision = 8, scale = 0)),
			@AttributeOverride(name = "moduleId", column = @Column(name = "MODULE_ID", precision = 8, scale = 0)),
			@AttributeOverride(name = "peName", column = @Column(name = "PE_NAME", length = 100)),
			@AttributeOverride(name = "peCode", column = @Column(name = "PE_CODE", length = 100))})
	public PermValueId getId() {
		return this.id;
	}

	public void setId(PermValueId id) {
		this.id = id;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MODULE_ID", insertable = false, updatable = false)
	public Module getModule() {
		return this.module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
