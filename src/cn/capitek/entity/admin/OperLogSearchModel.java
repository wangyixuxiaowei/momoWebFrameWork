package cn.capitek.entity.admin;

public class OperLogSearchModel {
	
	private String operator;
	private String operationType;
	private String module;
	private String operationResult;
	
	private int pageNo;
	private int pageSize;
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getOperationResult() {
		return operationResult;
	}
	public void setOperationResult(String operationResult) {
		this.operationResult = operationResult;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
