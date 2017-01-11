package AuthLogQuery;


/**
* AuthLogQuery/AuthLogQueryInfPOA.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从H:/myproj/AuthLogQuery.idl
* 2016年11月22日 星期二 下午04时24分59秒 CST
*/

public abstract class AuthLogQueryInfPOA extends org.omg.PortableServer.Servant
 implements AuthLogQuery.AuthLogQueryInfOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("getResult", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // AuthLogQuery/AuthLogQueryInf/getResult
       {
         String username = in.read_string ();
         AuthLogQuery.AuthLogQueryResults $result[] = null;
         $result = this.getResult (username);
         out = $rh.createReply();
         AuthLogQuery.AuthLogQueryInfPackage.log_result_listHelper.write (out, $result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:AuthLogQuery/AuthLogQueryInf:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public AuthLogQueryInf _this() 
  {
    return AuthLogQueryInfHelper.narrow(
    super._this_object());
  }

  public AuthLogQueryInf _this(org.omg.CORBA.ORB orb) 
  {
    return AuthLogQueryInfHelper.narrow(
    super._this_object(orb));
  }


} // class AuthLogQueryInfPOA
