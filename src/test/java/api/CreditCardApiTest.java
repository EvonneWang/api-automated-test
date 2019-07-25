package api;

import common.JsonSchemaUtils;
import common.Util;
import model.DataVO;
import net.sf.json.JSONObject;
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

public class CreditCardApiTest {
    String baseURL = "http://134.175.161.212:8086/creditcard/";
    //    String baseURL = "http://3.130.122.199:8086/creditcard/";
    DataVO data = new DataVO();

    CreditCardApiTest() {
        data.setToken("eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNo8y00OwiAQhuG7zNoFkBLUpbqwadI7ADNWEn4aWozGeHchNs7yme99A9KDfJop9xc4gkTaG6XtTQrRCWEPhMKojhnDLVdKwg5sKnHNr3NCqsF1aORJZxenzRhjvKrJOtr73xr5NLk46tBkdqGlZVlToDyWYCj_hmy7Viw64ik9e6wv-HwBAAD__w.alc0ibAbJotnPxSQL2wtt9Qo8h0YYzl4WkxOK65PnGy1fK4SDmNRRVEohqOya_K7qOXJOt5Cjdm10cejK3PViA");
        data.setClientid("devin");
        data.setMessageid("006f7113e5fa48559549c4dfe74e2cd6");
    }

    private void setPostHeader(HttpPost post, DataVO data) {
        post.setHeader("content-type", "application/json");
        post.setHeader("token", data.getToken());
    }

    private void setGetHeader(HttpGet get, DataVO data) {
        get.setHeader("content-type", "application/json");
        get.setHeader("token", data.getToken());
    }

    public void getNewCreditCard() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "creditcard/accountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/accountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject().fromObject(str);
            data.setCreditCardNumber(jsonObject.get("creditcardnumber").toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @credit-card
     */
    @Test(groups = {"all", "credit-card"})
    public void creditCardAccountOpeningPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "creditcard/accountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/accountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            JSONObject jsonObject = new JSONObject().fromObject(str);
            data.setCreditCardNumber(jsonObject.get("creditcardnumber").toString());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/accountOpening", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card"})
    public void creditCardCanCellationPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "creditcard/cancellation");
        setPostHeader(post, data);
        try {
            getNewCreditCard();
            JSONObject requestObj = Util.getObject("/request/creditCard/canCellation.txt");
            requestObj.put("creditcardnumber", data.getCreditCardNumber());
            post.setEntity(new StringEntity(requestObj.toString(), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/canCellation", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card"})
    public void creditCardCreditLimitDetailsPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/creditcard/creditLimitDetails");
        setPostHeader(post, data);
        try {
            getNewCreditCard();
            JSONObject requestObj = Util.getObject("/request/creditCard/creditLimitDetails.txt");
            requestObj.put("creditcardnumber", data.getCreditCardNumber());
            post.setEntity(new StringEntity(requestObj.toString(), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/creditLimitDetails", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card"})
    public void creditCardLimitDecreasePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/creditcard/limitDecrease");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/limitDecrease.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/limitDecrease", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card"})
    public void creditCardLimitIncreasePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/creditcard/limitIncrease");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/limitIncrease.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/limitIncrease", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card"})
    public void creditCardLossReportingPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/creditcard/lossReporting");
        setPostHeader(post, data);
        try {
            getNewCreditCard();
            JSONObject requestObj = Util.getObject("/request/creditCard/lossReporting.txt");
            requestObj.put("creditcardnumber", data.getCreditCardNumber());
            post.setEntity(new StringEntity(requestObj.toString(), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/lossReporting", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card"})
    public void creditCardNumberValidationPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/creditcard/numberValidation");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/numberValidation.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/numberValidation", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @credit-card-payment
     */
    @Test(groups = {"all", "credit-card-payment"})
    public void creditCardPaymentCreditCardRepeymentPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "creditcard/creditCardRepayment");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/creditCardRepeyment.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/creditCardRepeyment", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card-payment"})
    public void creditCardPaymentOutstandingPaymentPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/creditcard/outstandingPayment");
        setPostHeader(post, data);
        try {
            getNewCreditCard();
            JSONObject requestObj = Util.getObject("/request/creditCard/outstandingPayment.txt");
            requestObj.put("creditcardnumber", data.getCreditCardNumber());
            post.setEntity(new StringEntity(requestObj.toString(), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/outstandingPayment", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @credit-card-rewards
     */
    @Test(groups = {"all", "credit-card-rewards"})
    public void creditCardPaymentProductEnquiryPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/point/productEnquiry");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/productEnquiry.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/productEnquiry", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card-rewards"})
    public void creditCardPaymentRedemptionPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/point/redemption");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/redemption.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/redemption", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card-rewards"})
    public void creditCardPaymentRedemptionHistoryPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/point/redemptionHistory");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/redemptionHistory.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/redemptionHistory", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card-rewards"})
    public void creditCardPaymentTotalPointPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/point/totalPoint");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/totalPoint.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/totalPoint", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @credit-card-transaction
     */
    @Test(groups = {"all", "credit-card-transaction"})
    public void creditCardTransactiontRansactionDetailsPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/creditcard/transactionDetails");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/transactionDetails.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/transactionDetails", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "credit-card-transaction"})
    public void creditCardTransactiontTransactionPostingPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/creditcard/transactionPosting");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/transactionPosting.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/transactionPosting", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @merchant
     */
    @Test(groups = {"all", "merchant"})
    public void merchantMerchantEnquiryGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String merchantNumber = "HK0001001000009";
        HttpGet get = new HttpGet(baseURL + "/merchant/merchantEnquiry/" + merchantNumber);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/merchantEnquiry", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "merchant"})
    public void merchantTransactionsPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/merchant/transactions");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/transactions.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/transactions", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @vpoint
     */
    @Test(groups = {"all", "vpoint"})
    public void vpointMerchantCategoryPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/validate/merchantCategory");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/merchantCategory.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/merchantCategory", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "vpoint"})
    public void vpointNumberPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "/validate/number");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/creditCard/number.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/creditCard/number", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

 