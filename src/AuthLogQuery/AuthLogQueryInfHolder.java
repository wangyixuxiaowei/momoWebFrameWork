package AuthLogQuery;

/**
* AuthLogQuery/AuthLogQueryInfHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��H:/myproj/AuthLogQuery.idl
* 2016��11��22�� ���ڶ� ����04ʱ24��59�� CST
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
