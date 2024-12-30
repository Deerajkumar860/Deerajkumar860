package stepDefination;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.RequestSpecBuilder.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resource.APIresource;
import resource.TestDataBuild;
import resource.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class MyStepdefs extends Utils{

    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;

    TestDataBuild data = new TestDataBuild();

    static String place_id;
    @Given("Add Place payload with {string} {string} {string}")
    public void add_place_payload_with(String Accurancy, String address, String language) throws IOException {
        res = given().spec(requestSpecification())
                .body(data.addPlacePayload(Accurancy, address, language));
    }

    @When("user calls {string} with {string} http request")
    public void userCallsWithPostHttpRequest(String param, String method) {

        APIresource resourceApi=APIresource.valueOf(param);

        resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        if (method.equalsIgnoreCase("POST"))
        response = res.when().post(resourceApi.getResource()).
                then().spec(resspec).extract().response();
        else if (method.equalsIgnoreCase("GET")) {
            response = res.when().get(resourceApi.getResource()).
                    then().spec(resspec).extract().response();
        }
    }

    @Then("the API call got success with status code {int}")
    public void theAPICallGotSuccessWithStatusCode(int arg0) {
        assertEquals("Status code is not matching :", response.getStatusCode(), arg0);
    }

    @And("{string} in response body is {string}")
    public void inResponseBodyIs(String arg0, String arg1) {
        assertEquals(getJsonPath(response,arg0), arg1);
    }

    @And("verify place_id created maps to {string} with {string}")
    public void verifyPlace_idCreatedMapsToWith(String arg0, String arg1) throws IOException {
        place_id= getJsonPath(response,"place_id");
        res=given().spec(requestSpecification().queryParam("place_id",place_id));
        userCallsWithPostHttpRequest(arg1,"GET");
        String name = getJsonPath(response,"language");
        assertEquals(name,arg0);
    }

    @Given("deletePlace payload")
    public void deletePlacePayload() throws IOException {
        res = given().spec(requestSpecification().body(data.deletePlace(place_id)));
    }
}
