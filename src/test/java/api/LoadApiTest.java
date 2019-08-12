package api;

import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import common.JsonSchemaUtils;
import common.Util;
import model.DataVO;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;

public class LoadApiTest {

    //    String baseURL = "http://47.112.134.188:8086/loan/";
    String baseURL = "http://3.130.122.199:8086/loan/";
    JsonSchemaFactory jsonschemaemaFactory = null;
    DataVO data = new DataVO();

    LoadApiTest() {
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

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://3.130.122.199:8086/foreignexchange/";
        jsonschemaemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();
    }

    @Test(groups = {"all", "mortgage-loan"})
    public void leonMortageAccountDetailEnquiryPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/accountDetailEnquiry");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/leon/accountDetailEnquiry.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/accountDetailEnquiry", res);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"}, dependsOnMethods = "leonMortageMortgageLoanAccountOpeningPost")
    public void leonMortageCancellationPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/cancellation");
        setPostHeader(post, data);
        try {
            JSONObject requestObj = Util.getObject("/request/leon/cancellation.txt");
            requestObj.put("accountnumber", data.getLeonAccount());
            post.setEntity(new StringEntity(requestObj.toString(), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/cancellation", res);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"})
    public void leonMortageLoanCalculaterPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/loanCalculater");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/leon/loanCalculater.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/loanCalculater", res);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"})
    public void leonMortageMortgageLoanAccountOpeningPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/mortgageLoanAccountOpening");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/leon/mortgageLoanAccountOpening.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/mortgageLoanAccountOpening", res);
            data.setLeonAccount(JSONObject.fromObject(res).getString("data"));
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"})
    public void leonMortageMortgageLoanApplicationPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/mortgageLoanApplication");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/leon/mortgageLoanApplication.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/mortgageLoanApplication", res);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"})
    public void leonMortageNextRepaymentEnquiryPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/nextRepaymentEnquiry");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/leon/nextRepaymentEnquiry.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/nextRepaymentEnquiry", res);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"})
    public void leonMortageOverDueRepaymentEnquiryPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/overDueRepaymentEnquiry");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/leon/overDueRepaymentEnquiry.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/overDueRepaymentEnquiry", res);
            Object obj = JSONObject.fromObject(res).getJSONObject("data").get("outstandingrepayment");
            JSONArray jsonArray = JSONArray.fromObject(obj);
            data.setTotalpayment(jsonArray.getJSONObject(0).get("totalpayment").toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"}, dependsOnMethods = "leonMortageOverDueRepaymentEnquiryPost")
    public void leonMortageRepaymentPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/repayment");
        setPostHeader(post, data);
        try {
            JSONObject obj = Util.getObject("/request/leon/repayment.txt");
            obj.put("repaymentamount", data.getTotalpayment());
            post.setEntity(new StringEntity(obj.toString(), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/repayment", res);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"})
    public void leonMortageRepaymentPlanPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/repaymentPlan");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/leon/repaymentPlan.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/repaymentPlan", res);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "mortgage-loan"})
    public void leonMortageTransactionEnquiryPost() {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "mortgage/transactionEnquiry");
        setPostHeader(post, data);
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/leon/transactionEnquiry.txt"), "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(post);
            String res = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/leon/transactionEnquiry", res);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
