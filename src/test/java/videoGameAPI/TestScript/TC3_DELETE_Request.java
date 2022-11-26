package videoGameAPI.TestScript;

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

@Listeners({AllureListener.class})
public class TC3_DELETE_Request extends BaseClass {
	
	static Response httpResponse;

	public static String id = ReadConfig.idData();
	public static String URI = ReadConfig.read("BaseURL");
	public static String pathParameter = "/app/videogames/" + id;
	public String queryPath = null;
	
	HttpMethods hm;
	
	@BeforeClass
	public void post_request() {
		//Specify Base URI
		RestAssured.baseURI = URI;
	}
	
	@Test(priority = 4)
	@Description("Deletes a video game from the DB by ID")
	@Epic("Epic 001")
	//@Feature("feature one")
	@Story("Story 001")
	@Step("Record Deleted Successfully")
	@Severity(SeverityLevel.CRITICAL)
	public void delete_existinVideoGame() {
		
		hm = new HttpMethods();

		httpResponse = hm.method_Type(Method.DELETE, pathParameter , queryPath); 
		
		// Response
		String responseBody = httpResponse.getBody().asString();
		
		if (responseBody != null ) {
			Assert.assertTrue(true);
			logger.info("Response Body is not null");	
			Assert.assertEquals(httpResponse.jsonPath().get("status"), "Record Deleted Successfully");
		}
		else {
			Assert.assertTrue(false);
			logger.warn("Response Body null");
		}
		
		int statusCode = httpResponse.getStatusCode();

		if (statusCode == 200) {
			Assert.assertTrue(true);
			logger.info("Stusts Code is: " + statusCode);
		} else {
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
		
	}

}
