package videoGameAPI.Utilities;

import org.json.simple.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpMethods {

	private static RequestSpecification httpRequest = RestAssured.given();
	private static Response httpResponse;
	public static String queryPath;

	// Response Object
	public Response method_Type(Method httpRequestType, String PathParameter, String queryPath) {

		if (httpRequestType == Method.GET) {

			httpResponse(httpRequestType, PathParameter, queryPath);

		} else if (httpRequestType == Method.POST) {

			httpResponse(httpRequestType, PathParameter, queryPath);

		}
		else if (httpRequestType == Method.PUT) {
			
			httpResponse(httpRequestType, PathParameter, queryPath);
			
		}
		else if (httpRequestType == Method.DELETE){
			
			httpResponse(httpRequestType, PathParameter, queryPath);
			
		}

		return httpResponse;
	}

	private Response httpResponse(Method httpRequestType, String PathParameter, String queryPath) {
		if (queryPath == null) {
			httpResponse = httpRequest.request(httpRequestType, PathParameter);
		} else if (queryPath != null) {
			httpResponse = httpRequest.request(httpRequestType, PathParameter + queryPath);
		}
		return httpResponse;
	}

	// Add Request Parameters to the POST Request
	public void addJsonParamsToRequest(JSONObject requestParameter) {
		httpRequest.contentType("application/json");
		httpRequest.body(requestParameter.toJSONString());
	}

}
