package AuthLogQuery;

/**
* AuthLogQuery/AuthLogQueryResultsHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��H:/myproj/AuthLogQuery.idl
* 2016��11��22�� ���ڶ� ����04ʱ24��59�� CST
*/

public final class AuthLogQueryResultsHolder implements org.omg.CORBA.portable.Streamable
{
  public AuthLogQuery.AuthLogQueryResults value = null;

  public AuthLogQueryResultsHolder ()
  {
  }

  public AuthLogQueryResultsHolder (AuthLogQuery.AuthLogQueryResults initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = AuthLogQuery.AuthLogQueryResultsHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    AuthLogQuery.AuthLogQueryResultsHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return AuthLogQuery.AuthLogQueryResultsHelper.type ();
  }

}
