package api;

import com.aventstack.extentreports.gherkin.model.Given;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import common.JsonSchemaUtils;
import common.Util;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.ValidatableResponse;
import model.DataVO;
import org.hamcrest.Matchers.*;
import net.sf.json.JSONObject;
import io.restassured.module.jsv.JsonSchemaValidator.*;
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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.github.fge.jsonschema.SchemaVersion.DRAFTV3;
import static com.github.fge.jsonschema.SchemaVersion.DRAFTV4;
import static io.restassured.config.HeaderConfig.headerConfig;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;

import io.restassured.module.jsv.JsonSchemaValidator.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidatorSettings.settings;
//import static org.junit.Assert.assertThat;

public class ForeignExchangeApiTest {
    JsonSchemaFactory jsonschemaemaFactory = null;

    DataVO data = new DataVO();

    ForeignExchangeApiTest() {
        data.setToken("eyJhbGciOiJIUzUxMiIsInppcCI6IkRFRiJ9.eNo8y00OwiAQhuG7zNoFkBLUpbqwadI7ADNWEn4aWozGeHchNs7yme99A9KDfJop9xc4gkTaG6XtTQrRCWEPhMKojhnDLVdKwg5sKnHNr3NCqsF1aORJZxenzRhjvKrJOtr73xr5NLk46tBkdqGlZVlToDyWYCj_hmy7Viw64ik9e6wv-HwBAAD__w.alc0ibAbJotnPxSQL2wtt9Qo8h0YYzl4WkxOK65PnGy1fK4SDmNRRVEohqOya_K7qOXJOt5Cjdm10cejK3PViA");
    }

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://3.130.122.199:8086/foreignexchange/";
        jsonschemaemaFactory = JsonSchemaFactory.newBuilder().setValidationConfiguration(ValidationConfiguration.newBuilder().setDefaultVersion(DRAFTV4).freeze()).freeze();
    }


    @Test(groups = {"all", "foreign-exchange"})
    public void depositAccountEnquiryAccountDetailsGet() throws IOException {
        given()
                .headers(Util.setHeader(data))
                .body(Util.getObject("/request/foreignExchange/foreignExchange.txt"))
                .post("foreignExchange/foreignExchange").then().statusCode(200)
                .body(matchesJsonSchemaInClasspath("response/foreignExchange/foreignExchange").using(jsonschemaemaFactory));
    }
}
