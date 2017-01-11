package AuthLogQuery.AuthLogQueryInfPackage;


/**
* AuthLogQuery/AuthLogQueryInfPackage/log_result_listHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从H:/myproj/AuthLogQuery.idl
* 2016年11月22日 星期二 下午04时24分59秒 CST
*/

public final class log_result_listHolder implements org.omg.CORBA.portable.Streamable
{
  public AuthLogQuery.AuthLogQueryResults value[] = null;

  public log_result_listHolder ()
  {
  }

  public log_result_listHolder (AuthLogQuery.AuthLogQueryResults[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AuthLogQuery.AuthLogQueryInfPackage.log_result_listHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AuthLogQuery.AuthLogQueryInfPackage.log_result_listHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AuthLogQuery.AuthLogQueryInfPackage.log_result_listHelper.type ();
  }

}
