package exchange_domain.iExchangePackage;


/**
* exchange_domain/iExchangePackage/lstCorShareItemHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from stockexchange/exchange.idl
* Wednesday, July 1, 2015 11:19:20 PM EDT
*/

abstract public class lstCorShareItemHelper
{
  private static String  _id = "IDL:exchange_domain/iExchange/lstCorShareItem:1.0";

  public static void insert (org.omg.CORBA.Any a, exchange_domain.iExchangePackage.corShareItem[] that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static exchange_domain.iExchangePackage.corShareItem[] extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = exchange_domain.iExchangePackage.corShareItemHelper.type ();
      __typeCode = org.omg.CORBA.ORB.init ().create_sequence_tc (0, __typeCode);
      __typeCode = org.omg.CORBA.ORB.init ().create_alias_tc (exchange_domain.iExchangePackage.lstCorShareItemHelper.id (), "lstCorShareItem", __typeCode);
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static exchange_domain.iExchangePackage.corShareItem[] read (org.omg.CORBA.portable.InputStream istream)
  {
    exchange_domain.iExchangePackage.corShareItem value[] = null;
    int _len0 = istream.read_long ();
    value = new exchange_domain.iExchangePackage.corShareItem[_len0];
    for (int _o1 = 0;_o1 < value.length; ++_o1)
      value[_o1] = exchange_domain.iExchangePackage.corShareItemHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, exchange_domain.iExchangePackage.corShareItem[] value)
  {
    ostream.write_long (value.length);
    for (int _i0 = 0;_i0 < value.length; ++_i0)
      exchange_domain.iExchangePackage.corShareItemHelper.write (ostream, value[_i0]);
  }

}