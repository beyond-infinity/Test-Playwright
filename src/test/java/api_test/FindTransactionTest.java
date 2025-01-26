package api_test;

import api_helper.FindTransactionHelper;
import base.Base;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.PropertyReader;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class FindTransactionTest extends Base {
    FindTransactionHelper helper = new FindTransactionHelper();
    PropertyReader reader;
    @BeforeClass
    public void readProperties() throws IOException {
        reader = new PropertyReader("properties/systemProperties.properties");
    }

    @Test
    public void FindTransactionAPITest()
    {
        Response response = given().baseUri(reader.getProperty("APIendpoint"))
                .basePath(helper.endpoint.replace("{$accountNumber}",previousAccountNumber))
                .headers(helper.setHeaders(authCookie))
                .when()
                .get();
        System.out.println(response.prettyPrint());
        io.restassured.path.json.JsonPath jsonPath = new io.restassured.path.json.JsonPath(response.print());
        response.then().statusCode(200);
        assertThat(jsonPath.getInt("[0].accountId")+"", equalTo(previousAccountNumber));
        assertThat(jsonPath.getString("[0].type"), equalTo("Debit"));
        assertThat(jsonPath.getFloat("[0].amount"), equalTo(1.00f)); // Validate 'amount'
        assertThat(jsonPath.getString("[0].description"), equalTo("Bill Payment to Ankit"));
    }
}
