package AuthLogQuery;

/**
 * AuthLogQuery/AuthLogQueryResults.java . 
 * 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
 * 从H:/myproj/AuthLogQuery.idl 
 * 2016年11月22日 星期二 下午04时24分59秒 CST
 */

public final class AuthLogQueryResults implements org.omg.CORBA.portable.IDLEntity {

	private static final long serialVersionUID = 1L;
	
	public String user_name = null;
	public String group_name = null;
	public String calling_id = null;
	public String called_id = null;
	public String framed_ipaddr = null;
	public String request_loc = null;
	public String request_time = null;
	public String nas_name = null;
	public String nas_addr = null;
	public short result_type = (short) 0;
	public String reply_msg = null;

	public AuthLogQueryResults() {
	}

	public AuthLogQueryResults(String _user_name, String _group_name, String _calling_id, String _called_id,
			String _framed_ipaddr, String _request_loc, String _request_time, String _nas_name, String _nas_addr,
			short _result_type, String _reply_msg) {
		user_name = _user_name;
		group_name = _group_name;
		calling_id = _calling_id;
		called_id = _called_id;
		framed_ipaddr = _framed_ipaddr;
		request_loc = _request_loc;
		request_time = _request_time;
		nas_name = _nas_name;
		nas_addr = _nas_addr;
		result_type = _result_type;
		reply_msg = _reply_msg;
	}

	@Override
	public String toString() {
		return "AuthLogQueryResults [user_name=" + user_name + ", group_name=" + group_name + ", calling_id="
				+ calling_id + ", called_id=" + called_id + ", framed_ipaddr=" + framed_ipaddr + ", request_loc="
				+ request_loc + ", request_time=" + request_time + ", nas_name=" + nas_name + ", nas_addr=" + nas_addr
				+ ", result_type=" + result_type + ", reply_msg=" + reply_msg + "]";
	}

}
