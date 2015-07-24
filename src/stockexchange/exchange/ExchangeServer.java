package stockexchange.exchange;

import common.logger.LoggerClient;

public class ExchangeServer implements Runnable {
	private Exchange exchange = new Exchange();

	public void run(){
		try {
//			// Set up ORB properties
//			Properties p = new Properties();
//			p.put("org.omg.CORBA.ORBInitialPort",
//					Config.getInstance().getAttr("namingServicePort"));
//			p.put("org.omg.CORBA.ORBInitialHost",
//					Config.getInstance().getAttr("namingServiceAddr"));
//
//			// Create a new object request broker
//			ORB orb = ORB.init(new String[0], p);
//			POA rootpoa = POAHelper.narrow(orb
//					.resolve_initial_references("RootPOA"));
//			rootpoa.the_POAManager().activate();
//
//			// get object reference from the servant
//			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(exchange);
//			iExchange href = iExchangeHelper.narrow(ref);
//
//			org.omg.CORBA.Object objRef = orb
//					.resolve_initial_references("NameService");
//			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
//
//			NameComponent path[] = ncRef.to_name("exchange");
//			ncRef.rebind(path, href);
//
//			LoggerClient.log("Exchange Server ready and waiting....");
//
//			while (true) {
//				// loop forever keeping the server alive
//			}
		} catch (Exception e) {
			LoggerClient.log("ExchangeServerException in run: " + e.getMessage());
		}
	}

	/**
	 * Launches a new exchange server within a new thread
	 *
	 * @return The thread that was launched
	 */
	public static Thread launch() {
		ExchangeServer es = new ExchangeServer();

		Thread thread = new Thread(() -> es.run());
		thread.start();

		return thread;
	}
}
