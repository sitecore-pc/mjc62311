package stockexchange.broker;

import common.Customer;
import common.share.ShareType;
import exchangeServer.CORBACustomer;
import exchangeServer.CORBAShareType;
import mock.MockBroker;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BrokerTest {

    MockBroker broker;
    ArrayList<String> shares;
    CORBACustomer customer;

    @Before
    public void setUp() throws Exception {
        broker = new MockBroker();
        shares = new ArrayList<String>(){{
            add("GOOG");
            add("MSFT");
            add("YHOO");
        }};
        customer = new CORBACustomer(1,"Ross", "Ross Street", "Ross Street 2", "Ross town", "Ross Province", "ROS SSMI", "Ross County");

    }

    @Test
    public void getTickerListingTest() throws RemoteException{
        // 3 businesses * 3
        assertEquals(broker.getTickerListing().length, 9);
    }

    @Test
    public void sellSharesTest(){
        assertTrue(broker.sellShares(shares.toArray(new String[shares.size()]), CORBAShareType.COMMON.COMMON, 100, customer));
    }

    @Test
    public void buySharesTest() throws RemoteException{
        //    assertTrue(broker.buyShares(shares, ShareType.PREFERRED, 100, customer));

    }

}