package exchange_domain;


/**
* exchange_domain/iExchangePOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from stockexchange/exchange.idl
* Monday, June 29, 2015 7:42:52 PM EDT
*/

public abstract class iExchangePOA extends org.omg.PortableServer.Servant
 implements exchange_domain.iExchangeOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("updateSharePrice", new java.lang.Integer (0));
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

  // update share price
       case 0:  // exchange_domain/iExchange/updateSharePrice
       {
         String symbol = in.read_string ();
         float PRICE = in.read_float ();
         boolean $result = false;
         $result = this.updateSharePrice (symbol, PRICE);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:exchange_domain/iExchange:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public iExchange _this() 
  {
    return iExchangeHelper.narrow(
    super._this_object());
  }

  public iExchange _this(org.omg.CORBA.ORB orb) 
  {
    return iExchangeHelper.narrow(
    super._this_object(orb));
  }


} // class iExchangePOA
