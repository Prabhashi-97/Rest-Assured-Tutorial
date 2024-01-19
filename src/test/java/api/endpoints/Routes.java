package api.endpoints;

public class Routes {
	
//	public static String base_url = "http://localhost:7081/api";
	public static String base_url = "http://127.0.0.1:7081/api";
	
	public static String post_url = base_url+"/books";
	public static String get = base_url+"/books";
	public static String update_url = base_url+"/books/{id}";
	public static String delete_url = base_url+"/books/{id}";
}
