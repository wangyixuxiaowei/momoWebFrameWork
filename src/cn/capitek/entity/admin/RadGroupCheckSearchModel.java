package cn.capitek.entity.admin;

public class RadGroupCheckSearchModel {
	private String attribute;
	private String groupname;
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
	
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
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
