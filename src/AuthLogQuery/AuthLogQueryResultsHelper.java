package AuthLogQuery;


/**
* AuthLogQuery/AuthLogQueryResultsHelper.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从H:/myproj/AuthLogQuery.idl
* 2016年11月22日 星期二 下午04时24分59秒 CST
*/

abstract public class AuthLogQueryResultsHelper
{
  private static String  _id = "IDL:AuthLogQuery/AuthLogQueryResults:1.0";

  public static void insert (org.omg.CORBA.Any a, AuthLogQuery.AuthLogQueryResults that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static AuthLogQuery.AuthLogQueryResults extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [11];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[0] = new org.omg.CORBA.StructMember (
            "user_name",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "group_name",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[2] = new org.omg.CORBA.StructMember (
            "calling_id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[3] = new org.omg.CORBA.StructMember (
            "called_id",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[4] = new org.omg.CORBA.StructMember (
            "framed_ipaddr",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[5] = new org.omg.CORBA.StructMember (
            "request_loc",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[6] = new org.omg.CORBA.StructMember (
            "request_time",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[7] = new org.omg.CORBA.StructMember (
            "nas_name",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[8] = new org.omg.CORBA.StructMember (
            "nas_addr",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_short);
          _members0[9] = new org.omg.CORBA.StructMember (
            "result_type",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_string_tc (0);
          _members0[10] = new org.omg.CORBA.StructMember (
            "reply_msg",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (AuthLogQuery.AuthLogQueryResultsHelper.id (), "AuthLogQueryResults", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static AuthLogQuery.AuthLogQueryResults read (org.omg.CORBA.portable.InputStream istream)
  {
    AuthLogQuery.AuthLogQueryResults value = new AuthLogQuery.AuthLogQueryResults ();
    value.user_name = istream.read_string ();
    value.group_name = istream.read_string ();
    value.calling_id = istream.read_string ();
    value.called_id = istream.read_string ();
    value.framed_ipaddr = istream.read_string ();
    value.request_loc = istream.read_string ();
    value.request_time = istream.read_string ();
    value.nas_name = istream.read_string ();
    value.nas_addr = istream.read_string ();
    value.result_type = istream.read_short ();
    value.reply_msg = istream.read_string ();
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, AuthLogQuery.AuthLogQueryResults value)
  {
    ostream.write_string (value.user_name);
    ostream.write_string (value.group_name);
    ostream.write_string (value.calling_id);
    ostream.write_string (value.called_id);
    ostream.write_string (value.framed_ipaddr);
    ostream.write_string (value.request_loc);
    ostream.write_string (value.request_time);
    ostream.write_string (value.nas_name);
    ostream.write_string (value.nas_addr);
    ostream.write_short (value.result_type);
    ostream.write_string (value.reply_msg);
  }

}
