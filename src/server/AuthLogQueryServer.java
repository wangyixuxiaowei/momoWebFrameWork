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
 * Description: ��������˵ķ���
 */
public class AuthLogQueryServer {
	public static void main(String[] args) throws Exception {
		System.out.println("server---> begin to program ����");
		args = new String[2];
		args[0] = "-ORBInitialPort";
		args[1] = "1050";
		// ����һ��ORBʵ��
		ORB orb = ORB.init(args, null);
		// �õ�һ��RootPOA�����ã�������POAManager
		org.omg.CORBA.Object obj = orb.resolve_initial_references("RootPOA");
		POA rootpoa = POAHelper.narrow(obj);
		rootpoa.the_POAManager().activate();
		// ����һ��AuthLogQueryImplʵ��
		AuthLogQueryImpl authLogQueryImpl = new AuthLogQueryImpl();
		// �ӷ����еõ����������
		org.omg.CORBA.Object ref = rootpoa.servant_to_reference(authLogQueryImpl);
		AuthLogQueryInf href = AuthLogQueryInfHelper.narrow(ref);
		// �õ�һ�������Ƶ�������
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		// �������������а��������
		String name = "AuthLogQueryInf";
		NameComponent path[] = ncRef.to_name(name);
		ncRef.rebind(path, href);
		// �����̷߳��񣬵ȴ��ͻ��˵���
		System.out.println("server--->orb server is running. "+ args[0] + " is " + args[1] + "; NameService is " + name);
		orb.run();
	}
}