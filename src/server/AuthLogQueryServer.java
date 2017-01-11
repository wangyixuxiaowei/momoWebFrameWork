package server;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import AuthLogQuery.AuthLogQueryInf;
import AuthLogQuery.AuthLogQueryInfHelper;

import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NameComponent;

/**
 * Module: AuthLogQueryServer.java 
 * Description: 启动服务端的服务
 */
public class AuthLogQueryServer {
	public static void main(String[] args) throws Exception {
		System.out.println("server---> begin to program ……");
		args = new String[2];
		args[0] = "-ORBInitialPort";
		args[1] = "1050";
		// 创建一个ORB实例
		ORB orb = ORB.init(args, null);
		// 得到一个RootPOA的引用，并激活POAManager
		org.omg.CORBA.Object obj = orb.resolve_initial_references("RootPOA");
		POA rootpoa = POAHelper.narrow(obj);
		rootpoa.the_POAManager().activate();
		// 创建一个AuthLogQueryImpl实例
		AuthLogQueryImpl authLogQueryImpl = new AuthLogQueryImpl();
		// 从服务中得到对象的引用
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(authLogQueryImpl);
		AuthLogQueryInf href = AuthLogQueryInfHelper.narrow(ref);
		// 得到一个根名称的上下文
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		// 在命名上下文中绑定这个对象
		String name = "AuthLogQueryInf";
		NameComponent path[] = ncRef.to_name(name);
		ncRef.rebind(path, href);
		// 启动线程服务，等待客户端调用
		System.out.println("server--->orb server is running. "+ args[0] + " is " + args[1] + "; NameService is " + name);
		orb.run();
	}
}