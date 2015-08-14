package replication;

import WebServices.ExchangeClientServices.ExchangeWSImplService;
import WebServices.ExchangeClientServices.IExchange;
import WebServices.ExchangeClientServices.ShareItem;
import WebServices.ExchangeClientServices.ShareType;
import common.Customer;
import common.UDP;
import common.UdpServer;
import common.logger.LoggerClient;
import common.share.ShareOrder;
import replication.messageObjects.MessageEnvelope;
import replication.messageObjects.OrderMessage;
import replication.messageObjects.OrderResponseMessage;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Gay on 8/9/2015.
 */
public class Replica extends UdpServer{

    private Map<Long, OrderMessage> holdBack;
    private Long curSequence = 0l;
    private static Integer uniqueID = 0;
    private int replicaId;



    /**
     * Replica Constructor
     */
    public Replica() {

        holdBack = new TreeMap<Long, OrderMessage>();
        synchronized (uniqueID){
            this.replicaId = ++uniqueID;
        }




    }



    public Map<Long,OrderMessage> getHoldBack(){

        return this.holdBack;
    }

    /**
     *
     * @param messageEnvelope
     * @return
     */
    public void ToReceive(MessageEnvelope messageEnvelope){

        //Retrieve Order Message for envelope
        OrderMessage orderMessage = messageEnvelope.getOrderMessage();

        int returnPort = orderMessage.getReturnPort();

        LoggerClient.log("Replica FE return port is : " + returnPort);

        //Validate message queue vs current queue
        synchronized (curSequence) {

            if (orderMessage.getSequenceID() == curSequence + 1) {

                //Process request and sent message to front end
                try {
                    if (this.ToDeliver(orderMessage)) {

                        curSequence = orderMessage.getSequenceID();
                        sendConfirmation(true, returnPort);

                        //Process Messages in HolbackQueue
                        processHoldback();

                    }
                } catch (Exception e) {

                    LoggerClient.log("Error in message delivery : " + e.getMessage());
                }
            } else {
                //Add to Hold Back and wait
                this.addToHoldBack(orderMessage);
            }
        }
    }

    /**
     *
     * @param orderMessage
     * @return
     */
    private boolean sendBuyRequest(OrderMessage orderMessage) {

        return false;
    }

    private void processHoldback() {

        for(Map.Entry<Long, OrderMessage> entry : holdBack.entrySet()) {
            Long key = entry.getKey();
            OrderMessage om = entry.getValue();

            synchronized (this.curSequence){

                if (om.getSequenceID() == this.curSequence) {

                    try {
                        if (this.ToDeliver(om)) {
                            curSequence = om.getSequenceID();
                            sendConfirmation(true, om.getReturnPort());
                        }

                    } catch (Exception e) {
                        LoggerClient.log("Error in HoldBack queue message delivery : " + e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Add message to hold back if not in sequence
     * @param orderMessage
     */
    private void addToHoldBack(OrderMessage orderMessage){

        //Do not add duplicate messages to holdback queue
        if (!this.holdBack.containsKey(orderMessage.getSequenceID())){
            this.holdBack.put(orderMessage.getSequenceID(), orderMessage);
        }
    }

    /**
     *
     * @param success
     * @return
     */
    private void sendConfirmation(boolean success, int returnPort){

        synchronized (curSequence) {
            OrderResponseMessage response = new OrderResponseMessage(curSequence,replicaId,success);
            UDP<OrderResponseMessage> client = new UDP<>();
            client.send(response, "localhost", returnPort);
        }
    }

    /**
     *
     * @param orderMessage
     */
    private boolean ToDeliver (OrderMessage orderMessage) throws Exception{

        //TODO: Send Order request to Exchange Server
        ExchangeWSImplService exchangews = new ExchangeWSImplService("TSX");
        IExchange exchange = exchangews.getExchangeWSImplPort();

        ShareOrder sOrder = orderMessage.getShareOrder();
        Customer customer = orderMessage.getCustomer();

        ShareItem sItem = new ShareItem();
        sItem.setOrderNum(sOrder.getOrderNum());
        sItem.setBusinessSymbol(sOrder.getBusinessSymbol());
        sItem.setShareType(ShareType.COMMON);
        sItem.setQuantity(sOrder.getQuantity());
        sItem.setUnitPrice(sOrder.getUnitPrice());

        WebServices.ExchangeClientServices.Customer orderCust = new WebServices.ExchangeClientServices.Customer();
        orderCust.setName(customer.getName());

        System.out.println("REPLICA TO DELIVER");

        return exchange.sellShareService(sItem, orderCust);

    }


    public int getReplicaId() {
        return this.replicaId;
    }

    @Override
    public void incomingMessageHandler(MessageEnvelope me) {

    }

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        Replica myReplica = new Replica();
        Replica myReplica2 = new Replica();
        Replica myReplica3 = new Replica();


       /* OrderMessage testOrder = new OrderMessage(1,new ShareOrder("1","001","GOOG", ShareType.COMMON, 500F,50,500F), new Customer("Gay"));
        OrderMessage testOrder1 = new OrderMessage(2,new ShareOrder("1","001","GOOG", ShareType.COMMON, 500F,50,500F), new Customer("Gay"));
        OrderMessage testOrder2 = new OrderMessage(3,new ShareOrder("1","001","GOOG", ShareType.COMMON, 500F,50,500F), new Customer("Gay"));
        OrderMessage testOrder3 = new OrderMessage(4,new ShareOrder("1","001","GOOG", ShareType.COMMON, 500F,50,500F), new Customer("Gay"));

        myReplica.addToHoldBack(testOrder2);
        myReplica.addToHoldBack(testOrder1);
        myReplica.addToHoldBack(testOrder3);
        myReplica.addToHoldBack(testOrder);*/

        System.out.println(myReplica.getReplicaId());
        System.out.println(myReplica2.getReplicaId());
        System.out.println(myReplica3.getReplicaId());


    }


}
