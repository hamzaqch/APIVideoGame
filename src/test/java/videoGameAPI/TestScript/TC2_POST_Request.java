package videoGameAPI.TestScript;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
public class TC2_POST_Request extends BaseClass{
	
	static Response httpResponse;
	
	public static String URI = ReadConfig.read("BaseURL");
	public static String pathParameter = "/app/videogames";
	public static String queryPath = null;
	
	HttpMethods hm; 

	public String idnum = Reusable.randomNumber(3);
	
	// Creat a File
	{
	Path file = Paths.get("./data.id");
	try {
		Files.writeString(file, "id = " + idnum, StandardCharsets.UTF_8);
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	
	@BeforeClass
	public void post_request() {
		//Specify Base URI
		RestAssured.baseURI = URI;
	}
	
	@SuppressWarnings("unchecked")
	@Test(priority = 1)
	@Description("Add a new video game to the DB")
	@Epic("Epic 001")
	//@Feature("feature one")
	@Story("Story 001")
	@Step("Record Added Successfully")
	@Severity(SeverityLevel.BLOCKER)
	public void post_aNewVideoGame() throws IOException {
		
		hm = new HttpMethods();

		// Request Payload
		JSONObject data = new JSONObject();
		
		data.put("id", idnum);
		data.put("name", Reusable.randomString(5));
		data.put("releaseDate", "2022-11-25T20:11:29.772Z");
		data.put("reviewScore", "0");
		data.put("category", "string");
		data.put("rating", "string");

		hm.addJsonParamsToRequest(data);
		
		httpResponse = hm.method_Type(Method.POST, pathParameter , queryPath);
		
		
		int statusCode = httpResponse.getStatusCode();
		
		if (statusCode == 200) {
			Assert.assertTrue(true);
			logger.info("Stusts Code is: " + statusCode);
		}
		else {
			Assert.assertTrue(false);
			logger.warn("Stusts Code is: " + statusCode);
		}
		
		// Response
		String responseBody = httpResponse.getBody().asString();
		
		if (responseBody != null ) {
			Assert.assertTrue(true);
			logger.info("Response Body is not null");	
			Assert.assertEquals(httpResponse.jsonPath().get("status"), "Record Added Successfully");
		}
		else {
			Assert.assertTrue(false);
			logger.warn("Response Body null");
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
	}

}
