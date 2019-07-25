package api;

import common.JsonSchemaUtils;
import model.DataVO;
import common.Util;
import net.sf.json.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLEncoder;

public class DepositApiTest {
    String baseURL = "http://134.175.161.212:8086/deposit/";
//    String baseURL = "http://3.130.122.199:8086/deposit/";

    DataVO data = new DataVO();

    DepositApiTest() {
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
//        setGetHeader(get, data);
        get.setHeader("content-type", "application/json");
        get.setHeader("token", data.getToken());
        get.setHeader("messageid", data.getMessageid());
        get.setHeader("clientid", data.getClientid());
    }

    /***
     * @account-enquiry
     */
    @Test(groups = {"all","account-enquiry"})
    public void depositAccountEnquiryAccountDetailsGet() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String accountNumber = "HK720001001000000001001";

        String url = baseURL + "deposit/account/accountDetails/" + accountNumber;

        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet get = new HttpGet(uriBuilder.build());
            setGetHeader(get, data);
            CloseableHttpResponse response = httpclient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/accountDetails", str);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","account-enquiry"})
    public void depositAccountEnquiryPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        JSONObject paramASE = new JSONObject();
        paramASE.put("accountnumber", "HK720001001000000001001");
        JSONObject params = new JSONObject();
        params.put("ase", paramASE);
        HttpPost post = new HttpPost(baseURL + "deposit/account/accountNumberValidation");
        setPostHeader(post, data);
        try {
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/accountNumberValidation", res);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","account-enquiry"})
    public void depositAccountEnquiryAllAccountGet() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String customerNumber = "001000000001";
        String url = baseURL + "deposit/account/allAccounts/" + customerNumber;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
//            uriBuilder.setParameters(params);
            HttpGet get = new HttpGet(uriBuilder.build());
            setGetHeader(get, data);
            CloseableHttpResponse response = httpclient.execute(get);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/allAccounts", res);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * @account-maintenance
     */
    @Test(groups = {"all","account-maintenance"})
    public void depositAccountMaintenanceAccountClosureGet() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        String accountNumber = "HK920001001000006239002";
        String url = baseURL + "deposit/account/accountClosure/" + accountNumber;
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
//            uriBuilder.setParameters(params);
            HttpGet get = new HttpGet(uriBuilder.build());
            setGetHeader(get, data);
            CloseableHttpResponse response = httpclient.execute(get);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/accountClosure", res);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    /**
     * @account-opening
     */
    @Test(groups = {"all","account-opening"})
    public void depositAccountOpeningAccountCreationPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/accountCreation");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/accountCreation.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/accountCreation", "res");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","account-opening"})
    public void depositCurrentAccountOpeningPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/currentAccountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/currentAccountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/currentAccountOpening", "res");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","account-opening"})
    public void depositFeAccountOpeningPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/feAccountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/feAccountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String reps = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/feAccountOpening", reps);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","account-opening"})
    public void depositMetAccountOpeningPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/metAccountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/metAccountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/metAccountOpening", res);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","account-opening"})
    public void depositSavingAccountOpeningPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/savingAccountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/savingAccountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/savingAccountOpening", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","account-opening"})
    public void depositTdAccountOpeningPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/tdAccountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/tdAccountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/tdAccountOpening", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @customer-maintenance
     */
    @Test(groups = {"all","customer-maintenance"})
    public void depositCusMainCustContactInfoUpdatePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/custContactInfoUpdate");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/custContactInfoUpdate.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/custContactInfoUpdate", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","customer-maintenance"})
    public void depositCusMainCustomerCreationPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/customerCreation");
        setPostHeader(post, data);
        try {
            JSONObject custObj = new JSONObject();
            JSONObject requestObj = Util.getObject("/request/deposit/customerCreation.txt");
            requestObj.put("customerID", "U" + Util.getRandomCustomerId() + "(1)");
            post.setEntity(new StringEntity(requestObj.toString(), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/customerCreation", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Term Deposit API*
     *
     * @TermDeposit
     */
    @Test(groups = {"all","TermDeposit"})
    public void depositTermDepositAllTermDepositGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String customerNumber = "001000000001";
        HttpGet get = new HttpGet(baseURL + "deposit/term/allTermDeposit/" + customerNumber);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/allTermDeposit", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","TermDeposit"})
    public void depositTermDepositAllEnquiryCustomerNumberGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String accountNumber = "HK760001001000000005100";
        HttpGet get = new HttpGet(baseURL + "deposit/term/termDeposit/" + accountNumber);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/termDeposit", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","TermDeposit"})
    public void depositTermDepositTermDepositApplicationPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/term/termDepositApplication");
        setPostHeader(post, data);

        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/termDepositApplication.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/termDepositApplication", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","TermDeposit"})
    public void depositTermDepositTermDepositDrawDownPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/term/termDepositDrawDown");
        setPostHeader(post, data);

        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/termDepositDrawDown.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/termDepositDrawDown", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","TermDeposit"})
    public void depositTermDepositTermDepositEnquiryPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/term/termDepositEnquiry");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/termDepositEnquiry.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/termDepositEnquiry", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","TermDeposit"})
    public void depositTermDepositTermDepositRenewalPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/term/termDepositRenewal");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/termDepositRenewal.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/termDepositRenewal", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Transaction
     */
    @Test(groups = {"all","Transaction"})
    public void depositTransactionChequeBookCreationPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/chequeBookCreation");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/chequeBookCreation.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/chequeBookCreation", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Transaction"})
    public void depositTransactionDepositPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/deposit");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/deposit.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/deposit", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Transaction"})
    public void depositTransactionTransferPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/transfer");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/transfer.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/transfer", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Transaction"})
    public void depositTransactionWithdrawalPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/account/withdrawal");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/withdrawal.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/withdrawal", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Transaction History
     */

    @Test(groups = {"all","Transaction History"})
    public void depositTransactionLogEnquiryPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/transactionLog/enquiry");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/enquiry.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/enquiry", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Vaccount
     */
    @Test(groups = {"all","Vaccount"})
    public void depositVaccountAccountBalancePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/accountBalance");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/accountBalance.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/accountBalance", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountAccountNumberExistsPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String accountNumber = "HK720001001000000001001";
        HttpPost post = new HttpPost(baseURL + "deposit/validate/accountNumberExists/" + accountNumber);
        setPostHeader(post, data);
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/accountNumberExists", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountAmountFormatPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/amountFormat");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/amountFormat.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/amountFormat", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountAssociatedAccountsPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String accountNumber = "HK720001001000000001001";
        HttpPost post = new HttpPost(baseURL + "deposit/validate/associatedAccounts/" + accountNumber);
        setPostHeader(post, data);
        try {
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/associatedAccounts", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountCurAccountTypePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/curAccountType");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/curAccountType.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/curAccountType", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountCurrencyGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String currency = "HKD";
        HttpGet get = new HttpGet(baseURL + "deposit/validate/currency/" + currency);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/currency", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountFexAccountTypePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/fexAccountType");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/fexAccountType.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/fexAccountType", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountFundAccountTypePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/fundAccountType");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/fundAccountType.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/fundAccountType", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountMetAccountTypePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/metAccountType");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/metAccountType.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/metAccountType", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountSavOrCurTypePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/savOrCurType");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/savOrCurType.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/savOrCurType", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountStockAccountTypePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/stockAccountType");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/stockAccountType.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/stockAccountType", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vaccount"})
    public void depositVaccountTdAccountTypePost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/tdAccountType");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/tdAccountType.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/tdAccountType", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Vcustomer
     */
    @Test(groups = {"all","Vcustomer"})
    public void depositVcustomerEmailFormatPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "deposit/validate/emailFormat");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/emailFormat.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/emailFormat", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vcustomer"})
    public void depositVcustomerExistingCustomerGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String customerID = "U735535(9)";
        HttpGet get = new HttpGet(baseURL + "deposit/validate/existingCustomer/" + customerID);
        setGetHeader(get, data);

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/existingCustomer", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vcustomer"})
    public void depositVcustomerIdFormatGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String customerID = "U735535(9)";
        HttpGet get = new HttpGet(baseURL + "deposit/validate/idFormat/" + customerID);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/idFormat", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vcustomer"})
    public void depositVcustomerPhoneNumberFormatGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String phone = "64657884";
        HttpGet get = new HttpGet(baseURL + "deposit/validate/phoneNumberFormat/" + phone);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/phoneNumberFormat", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vcustomer"})
    public void depositVtermDepositContractPeriodGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String period = "1day";
        HttpGet get = new HttpGet(baseURL + "deposit/validate/contractPeriod/" + period);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/contractPeriod", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vcustomer"})
    public void depositVtermDepositTdNumberExistsGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String tdnumber = "000000001";
        HttpGet get = new HttpGet(baseURL + "deposit/validate/tdNumberExists/" + tdnumber);
        setGetHeader(get, data);

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/tdNumberExists", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all","Vcustomer"})
    public void depositVtransactionDateFormatGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String date = "2019-03-12";
        HttpGet get = new HttpGet(baseURL + "deposit/validate/dateFormat/" + date);
        setGetHeader(get, data);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/dateFormat", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Test(enabled = true)
//    public void depositVtransactionDateRangePost() {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost post = new HttpPost(baseURL + "deposit/validate/dateRange");
//        setPostHeader(post, data);
//
//        try {
//            post.setEntity(new StringEntity(Util.getStringValue("/request/deposit/dateRange.txt"), "UTF-8"));
//            CloseableHttpResponse response = httpClient.execute(post);
//            String str = EntityUtils.toString(response.getEntity());
//            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
//            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/dateRange", str);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//    @Test(enabled = false)
//    public void depositVtransactionTimeFormatGet() {
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        String time = "2019-04-23 15:52:43";
//        try {
//            HttpGet get = new HttpGet(baseURL + "deposit/validate/timeFormat/" + URLEncoder.encode(time).replace("+", "%20"));
//            setGetHeader(get, data);
//            CloseableHttpResponse response = httpClient.execute(get);
//            String str = EntityUtils.toString(response.getEntity());
//            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/timeFormat", str);
//            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Test(groups = {"all","Vcustomer"})
    public void depositVtransactionTransTypeGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String transtype = "0001";
        try {
            HttpGet get = new HttpGet(baseURL + "deposit/validate/transType/" + transtype);
            setGetHeader(get, data);
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/deposit/transType", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
