package logger;

import util.Config;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Servver used to receive log message
 * Threads are created each time a message is sreceived
 * Thread handles writing to the log file
 */
public class LoggerServer {

    /**
     * Constructor
     * Listens for any incoming log request and delegates it to a new thread
     */
    public LoggerServer() {
        try {
            // Log start message
            LoggerThread.log("");
            LoggerThread.log("Log Server Started");


            int port = Integer.parseInt(Config.getInstance().getAttr("logServerPort"));

            DatagramSocket serverSocket = new DatagramSocket(port);

            byte[] receiveData = new byte[1024];
            while (true) {

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String msg = new String(receivePacket.getData(), 0, receivePacket.getLength());

                LoggerThread logThread = new LoggerThread(msg);
                Thread logger = new Thread(logThread);
                logger.start();

                serverSocket.send(receivePacket);
                serverSocket.close();
            }
        } catch(IOException ioe) {
            System.out.println("IO Exception in host: " + ioe.getMessage());
        }
    }


    public static void main(String args[]) {

        LoggerServer loggerServer = new LoggerServer();
    }

}
