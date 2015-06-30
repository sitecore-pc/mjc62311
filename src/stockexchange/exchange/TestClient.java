package stockexchange.exchange;

import exchange_domain.*;

import org.omg.CORBA.*;
import org.omg.CosNaming.*;

import common.util.Config;

import java.io.*;
import java.io.DataInputStream;
import java.util.Properties;

/**
 * Created by Gay on 6/29/2015.
 */
public class TestClient {

	public static void main(String args[]) throws IOException {
		try {
			// Set up ORB properties
			// Hi Gay! I had to add this to allow setting of port and IP
			// address. see Config.json. -patrick
			Properties p = new Properties();
			p.put("org.omg.CORBA.ORBInitialPort",
					Config.getInstance().getAttr("namingServicePort"));
			p.put("org.omg.CORBA.ORBInitialHost",
					Config.getInstance().getAttr("namingServiceAddr"));

			ORB orb = ORB.init(args, p);
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			iExchange exchange = (iExchange) iExchangeHelper.narrow(ncRef
					.resolve_str("exchange"));

			boolean r = exchange.updateSharePrice("GOOG", 999);
			System.out.println("Share was updated : " + r);
			System.out.println("-----------------------------------");

		} catch (Exception e) {
			System.out.println("Test Client exception: " + e);
			e.printStackTrace();
		}
	}
}