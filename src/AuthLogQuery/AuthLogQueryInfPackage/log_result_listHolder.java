package AuthLogQuery.AuthLogQueryInfPackage;


/**
* AuthLogQuery/AuthLogQueryInfPackage/log_result_listHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��H:/myproj/AuthLogQuery.idl
* 2016��11��22�� ���ڶ� ����04ʱ24��59�� CST
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
