package cn.capitek.entity.admin;

public class RadCheckSearchModel {
	private String username;
	private int pageNo;
	private int pageSize;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	//权限值
	private String permcode;
	
	public String getPermcode() {
		return permcode;
	}
	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}
	
}
