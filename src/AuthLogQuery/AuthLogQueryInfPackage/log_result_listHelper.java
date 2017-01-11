package AuthLogQuery.AuthLogQueryInfPackage;


/**
* AuthLogQuery/AuthLogQueryInfPackage/log_result_listHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从H:/myproj/AuthLogQuery.idl
* 2016年11月22日 星期二 下午04时24分59秒 CST
*/

abstract public class log_result_listHelper
{
  private static String  _id = "IDL:AuthLogQuery/AuthLogQueryInf/log_result_list:1.0";

  public static void insert (org.omg.CORBA.Any a, AuthLogQuery.AuthLogQueryResults[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static AuthLogQuery.AuthLogQueryResults[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = AuthLogQuery.AuthLogQueryResultsHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (AuthLogQuery.AuthLogQueryInfPackage.log_result_listHelper.id (), "log_result_list", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static AuthLogQuery.AuthLogQueryResults[] read (org.omg.CORBA.portable.InputStream istream)
  {
    AuthLogQuery.AuthLogQueryResults value[] = null;
    int _len0 = istream.read_long ();
    value = new AuthLogQuery.AuthLogQueryResults[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = AuthLogQuery.AuthLogQueryResultsHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, AuthLogQuery.AuthLogQueryResults[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      AuthLogQuery.AuthLogQueryResultsHelper.write (ostream, value[_i0]);
  }

}
