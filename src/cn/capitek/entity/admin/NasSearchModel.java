package cn.capitek.entity.admin;

public class NasSearchModel {
	private String nasname;
	private String shortname;
	private String type;
	private int pageNo;
	private int pageSize;
	private String permcode;
	
	public NasSearchModel(){
	}
	
	public String getNasname() {
		return nasname;
	}
	public void setNasname(String nasname) {
		this.nasname = nasname;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
