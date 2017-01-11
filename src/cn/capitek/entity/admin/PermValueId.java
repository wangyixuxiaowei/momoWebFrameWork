package cn.capitek.entity.admin;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Length;

@Embeddable
public class PermValueId implements java.io.Serializable {

	private Integer peId;
	private Integer moduleId;
	private String peName;
	private String peCode;

	public PermValueId() {
	}

	public PermValueId(Integer peId, Integer moduleId, String peName,
			String peCode) {
		this.peId = peId;
		this.moduleId = moduleId;
		this.peName = peName;
		this.peCode = peCode;
	}

	@Column(name = "PE_ID", precision = 8, scale = 0)
	public Integer getPeId() {
		return this.peId;
	}

	public void setPeId(Integer peId) {
		this.peId = peId;
	}

	@Column(name = "MODULE_ID", precision = 8, scale = 0)
	public Integer getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(Integer moduleId) {
		this.moduleId = moduleId;
	}

	@Column(name = "PE_NAME", length = 100)
	@Length(max = 100)
	public String getPeName() {
		return this.peName;
	}

	public void setPeName(String peName) {
		this.peName = peName;
	}

	@Column(name = "PE_CODE", length = 100)
	@Length(max = 100)
	public String getPeCode() {
		return this.peCode;
	}

	public void setPeCode(String peCode) {
		this.peCode = peCode;
	}

	@Override
	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PermValueId))
			return false;
		PermValueId castOther = (PermValueId) other;

		return ((this.getPeId() == castOther.getPeId()) || (this.getPeId() != null
				&& castOther.getPeId() != null && this.getPeId().equals(
				castOther.getPeId())))
				&& ((this.getModuleId() == castOther.getModuleId()) || (this
						.getModuleId() != null
						&& castOther.getModuleId() != null && this
						.getModuleId().equals(castOther.getModuleId())))
				&& ((this.getPeName() == castOther.getPeName()) || (this
						.getPeName() != null
						&& castOther.getPeName() != null && this.getPeName()
						.equals(castOther.getPeName())))
				&& ((this.getPeCode() == castOther.getPeCode()) || (this
						.getPeCode() != null
						&& castOther.getPeCode() != null && this.getPeCode()
						.equals(castOther.getPeCode())));
	}

	@Override
	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getPeId() == null ? 0 : this.getPeId().hashCode());
		result = 37 * result
				+ (getModuleId() == null ? 0 : this.getModuleId().hashCode());
		result = 37 * result
				+ (getPeName() == null ? 0 : this.getPeName().hashCode());
		result = 37 * result
				+ (getPeCode() == null ? 0 : this.getPeCode().hashCode());
		return result;
	}

}
