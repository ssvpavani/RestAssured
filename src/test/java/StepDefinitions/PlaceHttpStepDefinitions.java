package StepDefinitions;

import static io.restassured.RestAssured.baseURI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Utilities.APIResources;
import Utilities.TestDataBuild;
import Utilities.Utils;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoClasses.AddPlace;
import pojoClasses.Location;

public class PlaceHttpStepDefinitions extends Utils {
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	static String place_id;
	TestDataBuild data = new TestDataBuild();

	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload(String name,String language,String address) throws IOException {

		res = RestAssured.given().spec(requestSpecification()).body(data.AddPlacePayload(name,language,address));
	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource,String method) 
	{
		//constructor will be called with value of resource which you pass
		APIResources resourceAPI=APIResources.valueOf(resource);
		System.out.println(resourceAPI.getresource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
			
		response = res.request(Method.POST,resourceAPI.getresource());
	
		else if(method.equalsIgnoreCase("GET"))
		
		response = res.request(Method.GET,resourceAPI.getresource());
		
	}

	@Then("the API call is success with status code {int}")
	public void the_api_call_is_success_with_status_code(Integer int1) {
		int statuscode = response.getStatusCode();
		assertEquals(statuscode, 200);
	}

	@Then("{string} in response body in {string}")
	public void in_response_body_in(String keyvalue, String Expectedvalue) {
		
		assertEquals(getJsonPath(response,keyvalue), Expectedvalue);

	}
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws IOException {
		 place_id=getJsonPath(response, "place_id");
		res = RestAssured.given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource,"GET" ) ;
		String actualname=getJsonPath(response,"name");
		assertEquals(actualname,expectedname);
	}
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		res=RestAssured.given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
	}
}
