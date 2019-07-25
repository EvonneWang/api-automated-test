package api;

import common.JsonSchemaUtils;
import common.Util;
import model.DataVO;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class InvestmentApiTest {
    String baseURL = "http://134.175.161.212:8086/investment/";
    DataVO data = new DataVO();

    InvestmentApiTest() {
        data.setToken("eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNo8y00OwiAQhuG7zNoFkBLUpbqwadI7ADNWEn4aWozGeHchNs7yme99A9KDfJop9xc4gkTaG6XtTQrRCWEPhMKojhnDLVdKwg5sKnHNr3NCqsF1aORJZxenzRhjvKrJOtr73xr5NLk46tBkdqGlZVlToDyWYCj_hmy7Viw64ik9e6wv-HwBAAD__w.alc0ibAbJotnPxSQL2wtt9Qo8h0YYzl4WkxOK65PnGy1fK4SDmNRRVEohqOya_K7qOXJOt5Cjdm10cejK3PViA");
        data.setClientid("devin");
        data.setMessageid("006f7113e5fa48559549c4dfe74e2cd6");
    }

    private void setPostHeader(HttpPost post, DataVO data) {
        post.setHeader("content-type", "application/json");
        post.setHeader("token", data.getToken());
        post.setHeader("messageid", data.getMessageid());
        post.setHeader("clientid", data.getClientid());
    }

    private void setGetHeader(HttpGet get, DataVO data) {
        get.setHeader("content-type", "application/json");
        get.setHeader("token", data.getToken());
        get.setHeader("messageid", data.getMessageid());
        get.setHeader("clientid", data.getClientid());
    }


    /**
     * @mutual-fund
     **/

    @Test(groups = {"all", "mutual-fund"})
    public void investmentFundAccountOpeningPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "fund/fundAccountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/fundAccountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/fundAccountOpening", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mutual-fund"})
    public void investmentHoldingEnquiryPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "fund/holdingEnquiry");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/holdingEnquiry.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/holdingEnquiry", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mutual-fund"})
    public void investmentRedemptionPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "fund/redemption");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/redemption.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/redemption", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"all", "mutual-fund"})
    public void investmentSubscriptionPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "fund/subscription");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/subscription.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/subscription", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @order
     */

    @Test(groups = {"all", "order", "error"})
    public void investmentCancellationPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "fund/order/cancellation");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/cancellation.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/cancellation", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "order", "error"})
    public void investmentOrderDetailRetrievalsByIdPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "investment/order/orderDetailRetrievalsById");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/orderDetailRetrievalsById.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/orderDetailRetrievalsById", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"all", "order", "error"})
    public void investmentOrderInfoUpdatePost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "investment/order/orderInfoUpdate");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/orderInfoUpdate.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/orderInfoUpdate", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "order"})
    public void investmentOrderRetrievalPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "investment/order/orderRetrieval");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/orderRetrieval.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/orderRetrieval", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"all", "order", "error"})
    public void investmentOrderStatusUpdatePost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "investment/order/orderStatusUpdate");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/orderStatusUpdate.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/orderStatusUpdate", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @stock Stock
     */
    @Test(groups = {"all", "stock", "error"})
    public void investmentOrderPlacingPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "investment/stock/orderPlacing");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/orderPlacing.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/orderPlacing", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "stock"})
    public void investmentSettlementAccountUpdatePost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "investment/stock/settlementAccountUpdate");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/settlementAccountUpdate.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/settlementAccountUpdate", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "stock"})
    public void investmentStkAccountOpeningPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "investment/stock/stkAccountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/stkAccountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/stkAccountOpening", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "stock"})
    public void investmentStockListGet() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(baseURL + "investment/stock/stockList");
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpclient.execute(get);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/stockList", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @v-mutual
     */
    @Test(groups = {"all", "v-mutual"})
    public void investmentFundQuotationGet() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String fundcode = "U000001";
        HttpGet get = new HttpGet(baseURL + "investment/fund/fundQuotation/" + fundcode);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpclient.execute(get);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/fundQuotation", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "v-mutual"})
    public void investmentStockQuotationPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "investment/stock/stockQuotation");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/investment/stockQuotation.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/investment/stockQuotation", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

