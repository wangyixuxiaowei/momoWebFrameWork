package client;

import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import AuthLogQuery.AuthLogQueryInf;
import AuthLogQuery.AuthLogQueryInfHelper;
import AuthLogQuery.AuthLogQueryResults;

/**
 * 
 *Module:         AuthLogQueryClient.java
 *Description:    客户端的初始化以及调用的代码
 *Company:       
 *Version:        1.0.0
 *Author:         pantp
 *Date:           Jul 8, 2012
 */
public class AuthLogQueryClient {

	static AuthLogQueryInf authLogQueryInfImpl;

	static {
		System.out.println("客户端的初始化配置开始......." + System.currentTimeMillis());

		// -ORBInitialHost 127.0.0.1 -ORBInitialPort 1050
		String args[] = new String[4];
		args[0] = "-ORBInitialHost";
		args[1] = "127.0.0.1";// 服务端的IP地址
		args[2] = "-ORBInitialPort";
		args[3] = "1050";// 服务端的端口

		// 创建一个ORB实例
		ORB orb = ORB.init(args, null);

		// 获取根名称上下文
		org.omg.CORBA.Object objRef = null;
		try {
			objRef = orb.resolve_initial_references("NameService");
		} catch (InvalidName e) {
			e.printStackTrace();
		}
		NamingContextExt neRef = NamingContextExtHelper.narrow(objRef);

		String name = "AuthLogQueryInf";
		try {
			authLogQueryInfImpl = AuthLogQueryInfHelper.narrow(neRef.resolve_str(name));
		} catch (NotFound e) {
			e.printStackTrace();
		} catch (CannotProceed e) {
			e.printStackTrace();
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName e) {
			e.printStackTrace();
		}

		System.out.println("客户端的初始化配置结束......." + System.currentTimeMillis());
	}
	
	public static void main(String args[]) throws Exception {
		getResult();
	}

	// 调用corba服务的方法
	public static void getResult() {
		AuthLogQueryResults[] strArr = authLogQueryInfImpl.getResult("momo");
		for (AuthLogQueryResults eachStr : strArr) {
			System.out.println(eachStr.toString());
		}
	}
}
