package mock;

import business.Business;
import common.share.ShareOrder;
import common.share.ShareType;
import exchangeServer.BusinessInterface;
import exchangeServer.CORBAShareOrder;
import stockexchange.exchange.Exchange;
import stockexchange.exchange.ShareItem;
import stockexchange.exchange.ShareSalesStatusList;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * When testing we don't have the servers running
 * So we use a mockExchange which extends out the
 * server part
 */
public class MockExchange extends Exchange  {


    private final Business google;

    /**
     * Create Exchange object, initializes three businesses
     *
     * @throws RemoteException
     * @throws NotBoundException
     */
    public MockExchange(){
        google = getBusiness1("google");

        //createBusinessDirectory();
        shareStatusSaleList = new ShareSalesStatusList();

        initializeShares();
    }

    /**
     * @param businessName looking for
     * @return
     */
    public Business getBusiness1(String businessName) {
        return new Business("google_data.csv");
    }

    @Override
    public boolean registerBusiness(String symbol, float price){
        priceDirectory.put(symbol, price);
        return true;
    }

    @Override
    protected void initializeShares() {

        List<ShareItem> lstShares = new ArrayList<ShareItem>();

        this.registerBusiness("GOOG", 540.11f);

        //For Testing
        for(Map.Entry<String, Float> entry : this.priceDirectory.entrySet()) {

            lstShares.add(new ShareItem("",entry.getKey(), ShareType.COMMON,entry.getValue(),100));
            lstShares.add(new ShareItem("",entry.getKey(), ShareType.CONVERTIBLE,entry.getValue(),100));
            lstShares.add(new ShareItem("",entry.getKey(), ShareType.PREFERRED,entry.getValue(),100));
        }

        for(ShareItem shareItem : lstShares) {

            ShareItem addShareItem = this.issueSharesRequest(shareItem);

            if (addShareItem != null) {
                shareStatusSaleList.addToNewAvShares(addShareItem);
            }
        }
    }

    protected ShareItem issueSharesRequest(ShareItem sItem) {
        Boolean sharesIssued = false;

        String orderNum = generateOrderNumber();

        synchronized (orderNum) {
            try {
                sharesIssued = google.issueShares(new CORBAShareOrder(orderNum,
                        "not applicable", 500,sItem.getUnitPrice(),sItem.getUnitPrice(), convertShareType(sItem.getShareType()),sItem.getBusinessSymbol()));
            } catch (Exception e) {
                System.out.println(" \n " + e.getMessage());
            }
        }

        if (sharesIssued) {
            ShareItem newShareItem = new ShareItem(orderNum,sItem.getBusinessSymbol(), sItem.getShareType(), sItem.getUnitPrice(), 500);
            return newShareItem;
        }

        return null;
    }

}
