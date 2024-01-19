package api.utils;

import org.testng.Assert;

import io.restassured.response.Response;

public class StatusCodeHandler {
	
	public static void handleStatusCode(Response response, int expectedStatusCode) {
        if (response.statusCode() == expectedStatusCode) {
            Assert.assertEquals(response.statusCode(), expectedStatusCode);
        } else if (response.statusCode() == 208) {
            Assert.fail("Existing Record");
        } else if (response.statusCode() == 400) {
            Assert.fail("Invalid | Empty Input Parameters in the Request");
        } else if (response.statusCode() == 401) {
            Assert.fail("Received 401 Unauthorized error");
        } else if (response.statusCode() == 403) {
            Assert.fail("Received 403 Forbidden error");
        } else {
            Assert.fail("Received an unexpected status code: " + response.statusCode());
        }
    }
}
