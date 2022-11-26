package videoGameAPI.TestScript;

import java.io.File;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import videoGameAPI.Base.BaseClass;
import videoGameAPI.Utilities.AllureListener;
import videoGameAPI.Utilities.HttpMethods;
import videoGameAPI.Utilities.ReadConfig;
import videoGameAPI.Utilities.Reusable;

@Listeners({AllureListener.class})
public class TC_5_PUT_Request extends BaseClass {
	
	static Response httpResponse;

	public static String id = ReadConfig.idData();
	public static String name = Reusable.randomString(5);
	public static String URI = ReadConfig.read("BaseURL");
	public static String pathParameter = "/app/videogames/" + id;
	public static String queryPath = null;
	
	HttpMethods hm;
	
	@BeforeClass
	public void post_request() {
		//Specify Base URI
		RestAssured.baseURI = URI;
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority = 3)
	@Description("Update an existing video game in the DB by specifying a new body")
	@Epic("Epic 001")
	//@Feature("feature one")
	@Story("Story 001")
	@Step("Update a single game by ID")
	@Severity(SeverityLevel.BLOCKER)
	public void update_existingVideoGameByID() {
		
		hm = new HttpMethods();
		
		httpResponse = hm.method_Type(Method.GET, pathParameter , queryPath);
		
		String name = httpResponse.xmlPath().get("/videoGame.name");
		
		// Request Payload
		JSONObject data = new JSONObject();
		
		data.put("id", TC_5_PUT_Request.id);
		data.put("name", "Updated " + TC_5_PUT_Request.name);
		data.put("releaseDate", "2022-11-25T20:11:29.772Z");
		data.put("reviewScore", "0");
		data.put("category", "Updated string");
		data.put("rating", "Updated string");

		hm.addJsonParamsToRequest(data);
		
		httpResponse = hm.method_Type(Method.PUT, pathParameter , queryPath);
		
		String responseName = httpResponse.xmlPath().get("/videoGame.name");
		
		if (name != responseName) {
			Assert.assertTrue(true);
			logger.info("Response Body is Updated");
		}
		else {
			Assert.assertTrue(false);
			logger.info("Response Body is not Updated");
		}
		
		int statusCode = httpResponse.getStatusCode();
		
		if (statusCode == 200) {
			Assert.assertTrue(true);
			logger.info("Stusts Code is: " + statusCode);
		}
		else {
			Assert.assertTrue(false);
			logger.warn("Stusts Code is: " + statusCode);
		}
		
		{
			Headers hd = httpResponse.getHeaders();

			Header contentType = hd.get("content-type");
			String contentTypeValue = contentType.getValue();

			if (contentType != null && contentTypeValue.equalsIgnoreCase("application/xml")) {
				Assert.assertTrue(true);
				logger.info("content-type header is present and value is: " + contentTypeValue);
			} else {
				Assert.assertTrue(false);
				logger.warn("content-type header is not present");
			}
		}
		
		File datafile = new File("./data.id");
		datafile.delete();

	}

}
