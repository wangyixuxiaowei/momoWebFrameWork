package cn.capitek.entity.admin;

public class RadUserGroupSearchModel {
	private String username;
	private String groupname;
	private Integer priority;
	private int pageNo;
	private int pageSize;
	//权限值
	private String permcode;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
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
	public String getPermcode() {
		return permcode;
	}
	public void setPermcode(String permcode) {
		this.permcode = permcode;
	}
	
}
