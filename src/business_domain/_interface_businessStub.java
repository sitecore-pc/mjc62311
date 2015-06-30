package business_domain;


/**
* business_domain/_interface_businessStub.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from business/business.idl
* Monday, June 29, 2015 7:52:38 PM EDT
*/

public class _interface_businessStub extends org.omg.CORBA.portable.ObjectImpl implements business_domain.interface_business
{

  public String getTicker ()
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("getTicker", true);
                $in = _invoke ($out);
                String $result = $in.read_string ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return getTicker (        );
            } finally {
                _releaseReply ($in);
            }
  } // getTicker

  public boolean issueShares (String orderNum, String brokerRef, String businessSymbol, int shareType, float unitPrice, int quantity, float unitPriceOrder)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("issueShares", true);
                $out.write_string (orderNum);
                $out.write_string (brokerRef);
                $out.write_string (businessSymbol);
                $out.write_long (shareType);
                $out.write_float (unitPrice);
                $out.write_long (quantity);
                $out.write_float (unitPriceOrder);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return issueShares (orderNum, brokerRef, businessSymbol, shareType, unitPrice, quantity, unitPriceOrder        );
            } finally {
                _releaseReply ($in);
            }
  } // issueShares

  public boolean recievePayment (String orderNum, float totalPrice)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("recievePayment", true);
                $out.write_string (orderNum);
                $out.write_float (totalPrice);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return recievePayment (orderNum, totalPrice        );
            } finally {
                _releaseReply ($in);
            }
  } // recievePayment

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:business_domain/interface_business:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _interface_businessStub
