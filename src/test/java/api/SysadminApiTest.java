package api;

import common.JsonSchemaUtils;
import common.Util;
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

public class SysadminApiTest {

    //    String baseURL = "http://134.175.161.212:8086/deposit/";
//    String baseURL = "http://3.130.122.199:8086/deposit/";
    String baseURL = "http://47.112.134.188:8086/sysadmin/";

    /**
     * @admin-controller
     */
    @Test(groups = {"all", "admin-controller"})
    public void sysadminAdminControllerLoginInPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/adminuser/loginIn");
        post.setHeader("content-type", "application/json");
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/sysadmin/loginIn.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/loginIn", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @authority-controller
     */
    @Test(groups = {"all", "authority-controller"})
    public void sysadminAuthorityControllerAppDockerForDeveloperPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/permission/appDockerForDeveloper");
        post.setHeader("content-type", "application/json");

        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/sysadmin/appDockerForDeveloper.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/appDockerForDeveloper", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "authority-controller"})
    public void sysadminAuthorityControllerDeveloperAuthorizationPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/permission/developerAuthorization");
        post.setHeader("content-type", "application/json");

        try {
            JSONObject custObj = new JSONObject();
            JSONObject requestObj = Util.getObject("/request/sysadmin/developerAuthorization.txt");
            requestObj.put("developerID", Util.getRandomCustomerId());
            post.setEntity(new StringEntity(requestObj.toString(), "UTF-8"));
            JSONObject jsonObject = new JSONObject();
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/developerAuthorization", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "authority-controller"})
    public void sysadminAuthorityControllerGetSandBoxPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/permission/getSandBox");
        post.setHeader("content-type", "application/json");

        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/sysadmin/getSandBox.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/getSandBox", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @branch-code-search-controller
     */
    @Test(groups = {"all", "branch-code-search-controller"})
    public void sysadminBranchCodeSearchControllerGetBranchCodeTableGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(baseURL + "sysadmin/branchdata/getBranchCodeTable");
        get.setHeader("content-type", "application/json");

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/getBranchCodeTable", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "branch-code-search-controller", "error"})
    public void sysadminBranchCodeSearchControllerGetBranchCodeTableInfoPost() {
//        "SandBoxId": 9
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/branchdata/getBranchCodeTableInfo");
        post.setHeader("content-type", "application/json");

        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/sysadmin/getBranchCodeTableInfo.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/getBranchCodeTableInfo", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @currency-controller
     */
    @Test(groups = {"all", "currency-controller"})
    public void sysadminCurrencyControllerCurrencyRetrievalGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(baseURL + "sysadmin/currency/currencyRetrieval");
        get.setHeader("content-type", "application/json");
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/currencyRetrieval", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @generate-available-controller
     */
    @Test(groups = {"all", "generate-available-controller"})
    public void sysadminGenerateAvailableControllerlGetNextAvailableNumberGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String item = "NextAvailableCustomerNumber";
        HttpGet get = new HttpGet(baseURL + "sysadmin/generate/getNextAvailableNumber/" + item);
        get.setHeader("content-type", "application/json");
        get.setHeader("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNo8y00OwiAQhuG7zNoFkBLUpbqwadI7ADNWEn4aWozGeHchNs7yme99A9KDfJop9xc4gkTaG6XtTQrRCWEPhMKojhnDLVdKwg5sKnHNr3NCqsF1aORJZxenzRhjvKrJOtr73xr5NLk46tBkdqGlZVlToDyWYCj_hmy7Viw64ik9e6wv-HwBAAD__w.alc0ibAbJotnPxSQL2wtt9Qo8h0YYzl4WkxOK65PnGy1fK4SDmNRRVEohqOya_K7qOXJOt5Cjdm10cejK3PViA");

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/getNextAvailableNumber", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @lbs-sdk-controller
     */
    @Test(groups = {"all", "generate-available-controller", "error"})
    public void sysadminLbsSdkControllerDownloadsdkGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(baseURL + "sysadmin/downloadsdk");
        get.setHeader("content-type", "application/json");
        get.setHeader("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNo8y00OwiAQhuG7zNoFkBLUpbqwadI7ADNWEn4aWozGeHchNs7yme99A9KDfJop9xc4gkTaG6XtTQrRCWEPhMKojhnDLVdKwg5sKnHNr3NCqsF1aORJZxenzRhjvKrJOtr73xr5NLk46tBkdqGlZVlToDyWYCj_hmy7Viw64ik9e6wv-HwBAAD__w.alc0ibAbJotnPxSQL2wtt9Qo8h0YYzl4WkxOK65PnGy1fK4SDmNRRVEohqOya_K7qOXJOt5Cjdm10cejK3PViA");

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/downloadsdk", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @login-controller
     */
    @Test(groups = {"all", "login-controller", "error"})
    public void sysadminLoginControllerAuthorizeGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        String loginPK = "00f7893cfbd64e66aa66ab29bbfbaa05";
        HttpGet get = new HttpGet(baseURL + "sysadmin/login/authorize/" + loginPK);
        get.setHeader("content-type", "application/json");
        get.setHeader("token", "eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNo8y00OwiAQhuG7zNoFkBLUpbqwadI7ADNWEn4aWozGeHchNs7yme99A9KDfJop9xc4gkTaG6XtTQrRCWEPhMKojhnDLVdKwg5sKnHNr3NCqsF1aORJZxenzRhjvKrJOtr73xr5NLk46tBkdqGlZVlToDyWYCj_hmy7Viw64ik9e6wv-HwBAAD__w.alc0ibAbJotnPxSQL2wtt9Qo8h0YYzl4WkxOK65PnGy1fK4SDmNRRVEohqOya_K7qOXJOt5Cjdm10cejK3PViA");

        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/authorize", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test(groups = {"all", "login-controller", "error"})
    public void sysadminLoginControllerLoginInPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/login/loginIn");
        post.setHeader("content-type", "application/json");

        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/sysadmin/loginControllerLoginIn.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "login-controller", "error"})
    public void sysadminLoginControllerUserCreationPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/login/userCreation");
        post.setHeader("content-type", "application/json");

        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/sysadmin/userCreation.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @sand-box-search-controller
     */
    @Test(groups = {"all", "sand-box-search-controller"})
    public void sysadminSandBoxSearchControllerGetSandBoxTableGet() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(baseURL + "sysadmin/sandbox/getSandBoxTable");
        get.setHeader("content-type", "application/json");
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/getSandBoxTable", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"all", "sand-box-search-controller", "error"})
    public void sysadminSandBoxSearchControllerGetSandBoxTableInfoPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/sandbox/getSandBoxTableInfo");
        post.setHeader("content-type", "application/json");

        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/sysadmin/getSandBoxTableInfo.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @sys-config-controller
     */
    @Test(groups = {"all", "sys-config-controller"})
    public void sysadminSysConfigControllerGetSystemParameterPost() {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(baseURL + "sysadmin/sysconfig/getSystemParameter");
        post.setHeader("content-type", "application/json");
        try {
            post.setEntity(new StringEntity(Util.getStringValue("/request/sysadmin/getSystemParameter.txt"), "UTF-8"));
            CloseableHttpResponse response = httpClient.execute(post);
            String str = EntityUtils.toString(response.getEntity());
            Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
            JsonSchemaUtils.assertResponseJsonSchema("/response/sysadmin/getSystemParameter", str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
