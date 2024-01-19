package api.endpoints;

import static io.restassured.RestAssured.given;

import api.paylaod.Book;
import io.restassured.response.Response;

public class BookEndPoints {

	public static Response createBook(Book payload, String username, String password) {
		Response response =given()
				.header("Content-type", "application/json")
				.auth().preemptive().basic(username,password)
				.body(payload)
			.when()	
				.post(Routes.post_url);
		
			return response;
	}
}
