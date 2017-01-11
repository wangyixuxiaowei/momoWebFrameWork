package cn.capitek.entity.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "SM_OPERATION_LOG")
public class OperationLog implements java.io.Serializable {

	private Long id;
	private String operator;
	private String operationType;
	private String module;
	private Date operationTime;
	private String operatorIP;
	private String operationResult;
	
	private String operationTimeStr;

	public OperationLog() {
	}

	public OperationLog(Long id) {
		this.id = id;
	}
	public OperationLog(Long id, String operator, String operationType,
			String module, Date operationTime, String operationResult) {
		this.id = id;
		this.operator = operator;
		this.operationType = operationType;
		this.module = module;
		this.operationTime = operationTime;
		this.operationResult = operationResult;
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false, precision = 12, scale = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
	//@NotNull
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "OPERATOR", length = 20)
	@Length(max = 20)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "OPERATION_TYPE", length = 20)
	@Length(max = 20)
	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	@Column(name = "MODULE", length = 20)
	@Length(max = 20)
	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OPERATION_TIME", length = 20)
	public Date getOperationTime() {
		return this.operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}
	
	@Column(name = "OPERATOR_IP", length = 20)
	@Length(max = 20)
	public String getOperatorIP()
    {
    	return operatorIP;
    }

	public void setOperatorIP(String operatorIP)
    {
    	this.operatorIP = operatorIP;
    }

	@Column(name = "OPERATION_RESULT", length = 20)
	@Length(max = 20)
	public String getOperationResult() {
		return this.operationResult;
	}

	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}

	@Transient
	public String getOperationTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(this.operationTime);
	}

	public void setOperationTimeStr(String operationTimeStr) {
		this.operationTimeStr = operationTimeStr;
	}

}
