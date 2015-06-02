package business;

import org.junit.Before;
import org.junit.Test;

import share.Share;
import share.ShareOrder;
import share.ShareType;
import util.Config;

import java.rmi.RemoteException;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Ross on 2015-05-26.
 */
public class BusinessTest {
    Business google;

    @Before
    public void setUp() {
        google = new Business(Config.getInstance().getAttr("google"));
    }

    @Test
    public void testIssueShares() {
    	ShareOrder aSO = new ShareOrder("a00", "broker1", "GOOG", ShareType.PREFERRED, 0, 150, (float) 1000.0);
    	assertTrue(google.issueShares(aSO));
    }

    @Test
    public void testShareTypeExists() {
    	assertTrue(google.getShareInfo(ShareType.PREFERRED) != null);
    }

    @Test
    public void testCompanyTicker() throws RemoteException {
        assertTrue(google.getTicker().equals("GOOG"));
    }
}