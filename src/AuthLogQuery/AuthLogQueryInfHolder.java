package AuthLogQuery;

/**
* AuthLogQuery/AuthLogQueryInfHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从H:/myproj/AuthLogQuery.idl
* 2016年11月22日 星期二 下午04时24分59秒 CST
*/

public final class AuthLogQueryInfHolder implements org.omg.CORBA.portable.Streamable
{
  public AuthLogQuery.AuthLogQueryInf value = null;

  public AuthLogQueryInfHolder ()
  {
  }

  public AuthLogQueryInfHolder (AuthLogQuery.AuthLogQueryInf initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AuthLogQuery.AuthLogQueryInfHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AuthLogQuery.AuthLogQueryInfHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AuthLogQuery.AuthLogQueryInfHelper.type ();
  }

}
