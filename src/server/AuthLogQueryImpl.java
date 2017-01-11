package server;

import AuthLogQuery.AuthLogQueryInfPOA;
import AuthLogQuery.AuthLogQueryResults;
/**
 * @Module: AuthLogQueryImpl.java 
 * @Description: 服务端功能的业务实现类
 * @author xuxw
 */
public class AuthLogQueryImpl extends AuthLogQueryInfPOA {

	@Override
	public AuthLogQueryResults[] getResult(String username) {
		AuthLogQueryResults[] rrarry = new AuthLogQueryResults[1];
		AuthLogQueryResults rra = new AuthLogQueryResults(
				"_result_id_1", 
				"_user_id_1", 
				"momo", 
				"_group_id_1", 
				"_group_name_a",
				"_calling_id_1", 
				"_request_type_1", 
				"_nas_name_x",
				"_calling_id_1",
				(short) 100, 
				"_nas_name_x");
		rrarry[0] = rra;
		return rrarry;
	}

}
