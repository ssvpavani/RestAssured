package Utilities;

import static io.restassured.RestAssured.baseURI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	static RequestSpecification requestspecification;

	public RequestSpecification requestSpecification() throws IOException {
		if (requestspecification == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			requestspecification = new RequestSpecBuilder().setBaseUri(ReadConfig.getbaseURL())
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();
			return requestspecification;
		}
		return requestspecification;
	}
	public String getJsonPath(Response response,String key)
	{
		String responsebody = response.asString();
		JsonPath js = new JsonPath(responsebody);
		return js.get(key).toString();
	}
}
