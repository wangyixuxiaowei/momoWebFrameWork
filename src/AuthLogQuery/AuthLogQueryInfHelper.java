package AuthLogQuery;


/**
* AuthLogQuery/AuthLogQueryInfHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从H:/myproj/AuthLogQuery.idl
* 2016年11月22日 星期二 下午04时24分59秒 CST
*/

abstract public class AuthLogQueryInfHelper
{
  private static String  _id = "IDL:AuthLogQuery/AuthLogQueryInf:1.0";

  public static void insert (org.omg.CORBA.Any a, AuthLogQuery.AuthLogQueryInf that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static AuthLogQuery.AuthLogQueryInf extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (AuthLogQuery.AuthLogQueryInfHelper.id (), "AuthLogQueryInf");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static AuthLogQuery.AuthLogQueryInf read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_AuthLogQueryInfStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, AuthLogQuery.AuthLogQueryInf value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static AuthLogQuery.AuthLogQueryInf narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof AuthLogQuery.AuthLogQueryInf)
      return (AuthLogQuery.AuthLogQueryInf)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      AuthLogQuery._AuthLogQueryInfStub stub = new AuthLogQuery._AuthLogQueryInfStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static AuthLogQuery.AuthLogQueryInf unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof AuthLogQuery.AuthLogQueryInf)
      return (AuthLogQuery.AuthLogQueryInf)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      AuthLogQuery._AuthLogQueryInfStub stub = new AuthLogQuery._AuthLogQueryInfStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
