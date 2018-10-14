package requests;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class DeleteIncident {

	public static void main(String[] args) {

	/*	RestAssured.authentication = 
				RestAssured.basic("admin", "Tuna@123");*/

		RestAssured.baseURI = "https://dev69962.service-now.com/api/now/table/";

		Cookie session = new Cookie
		.Builder("JSESSIONID", "05E5E48719D6419E22E6AFC097D0A301")
		.build();



		Response response = RestAssured
				.given()
				.cookie(session)
				.queryParam("sysparm_fields", "name,sys_id")
				.log().all()
				.contentType(ContentType.JSON)
				.body(new File("./post.json"))
				.post("incident");
		
		response.prettyPrint();
		
		String sys_id = response.jsonPath().get("result.sys_id");
		System.out.println(sys_id);
		/*Headers headers = response.getHeaders();
		for (Header header : headers) {
			System.out.println(header);
		}*/
		
		Map<String, String> cookies = response.getCookies();
		for (Entry<String,String> eachCookie : cookies.entrySet()) {
			System.out.println(eachCookie);
		}
		
		// Get all the incidents		
		Response response1 = RestAssured
				.given()
				.cookie(session)
				.pathParam("sys_id", sys_id)
				.delete("incident/{sys_id}");

		
		if(response1.getStatusCode() == 204) {
			System.out.println("Delete Success");
		}else {
			System.out.println("Failed, Check response for errors");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		



	}

}
