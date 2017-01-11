package cn.capitek.entity.admin;

public class UserSearchModel {
	private String username;
	private String name;
	private int pageNo;
	private int pageSize;
	
	//权限值
	private String permcode;
	
	public String getPermcode() {
		return permcode;
	}
	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
