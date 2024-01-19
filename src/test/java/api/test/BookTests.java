package api.test;

import api.endpoints.BookEndPoints;
import api.paylaod.Book;
import api.utils.StatusCodeHandler;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.util.ResourceBundle;

public class BookTests {

	static ResourceBundle getCredentials() {
		ResourceBundle credentials = ResourceBundle.getBundle("config");
		return credentials;
	}
	
	String username = getCredentials().getString("username");
	String password = getCredentials().getString("password");
	
	private Book bookPayload;
	
    @Parameters({"title", "author"})
    public void setUp(String title, String author) {
        bookPayload = new Book();
        bookPayload.setTitle(title);
        bookPayload.setAuthor(author);
    }
    
    
    @Test
    //Not Bug - New record created
    public void testCreateBook() {
        setUp("Madol Duwa", "Martin Wickramasinghe");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 201);
        System.out.println(response.statusCode());
    }
    
    
    @Test
    //Bug - New record created
    public void testCreateBookWithoutTitle() {
        setUp("", "Martin Wickramasinghe");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 400);
        System.out.println(response.statusCode());
    }
    
    @Test
    //Bug- New record created
    public void testCreateBookWithoutAuthor() {
        setUp("Forget me Not", "");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 400);
        System.out.println(response.statusCode());
    }
    
    @Test
    //Bug - New record created
    public void testCreateBookWithAuthorInt() {
        setUp("Lavendar", "12345");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 400);
        System.out.println(response.statusCode());
    }
    
    @Test
    //Bug - New record created
    public void testCreateBookWithDifferentCase() {
        setUp("madol Duwa", "Martin Wickramasinghe");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 208);
        System.out.println(response.statusCode());
    }
    
    @Test
    //Bug - First time we can insert a book without title and author
    public void testCreateBookWithoutAuthorTitle() {
        setUp("", "");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 400);
        System.out.println(response.statusCode());
    }
    
    @Test
    public void testCreateExistingBook() {
        setUp("Madol Duwa", "Martin Wickramasinghe");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 208);
        System.out.println(response.statusCode());
    }
    
    @Test
    public void testCreateExistingBookWithAnotherAuthor() {
        setUp("Madol Duwa", "Milan Withanage");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 208);
        System.out.println(response.statusCode());
    }
    
    @Test
    public void testCreateExistingBookWithoutAuthor() {
        setUp("Madol Duwa", "");
        Response response = BookEndPoints.createBook(bookPayload, username, password);
        StatusCodeHandler.handleStatusCode(response, 208);
        System.out.println(response.statusCode());
    }
    
    
    @Test
    public void testCreateBookWithWrongPassword() {
        setUp("Hunter", "James Andrew");
        Response response = BookEndPoints.createBook(bookPayload, username, "passwrod");
        StatusCodeHandler.handleStatusCode(response, 401);
        System.out.println(response.statusCode());
    }
    
    @Test
    public void testCreateBookWithWrongUsername() {
        setUp("Harry Potter", "James Andrew");
        Response response = BookEndPoints.createBook(bookPayload, "users", password);
        StatusCodeHandler.handleStatusCode(response, 401);
        System.out.println(response.statusCode());
    }
    
    @Test
    public void testCreateBookWithoutUsername() {
        setUp("Harry Potter-II", "James Andrew");
        Response response = BookEndPoints.createBook(bookPayload, "", password);
        StatusCodeHandler.handleStatusCode(response, 401);
        System.out.println(response.statusCode());
    }
    
    @Test
    public void testCreateBookWithoutPassword() {
        setUp("Harry Potter-III", "James Andrew");
        Response response = BookEndPoints.createBook(bookPayload, username, "");
        StatusCodeHandler.handleStatusCode(response, 401);
        System.out.println(response.statusCode());
    }
    
    @Test
    public void testCreateBookWithoutPasswordUsername() {
        setUp("Harry Potter-IV", "James Andrew");
        Response response = BookEndPoints.createBook(bookPayload, "", "");
        StatusCodeHandler.handleStatusCode(response, 401);
        System.out.println(response.statusCode());
    }
    
}
