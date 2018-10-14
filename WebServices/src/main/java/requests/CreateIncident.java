package requests;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIncident {

	public static void main(String[] args) {

		RestAssured.authentication = 
				RestAssured.basic("admin", "Tuna@123");

		RestAssured.baseURI = "https://dev69962.service-now.com/api/now/table/";

		// Get all the incidents		
		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(new File("./post.json"))
				/*.body("{\r\n" + 
						"    \"number\" : \"INC0010100\",\r\n" + 
						"    \"description\": \"This is automated using PostMan\",\r\n" + 
						"    \"severity\" : 2\r\n" + 
						"}")*/
				.post("incident");

		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		String description = json.get("result.description");
		System.out.println(description);
		if(description.equals("This is automated using PostMan")) {
			System.out.println("Expected Description matches the actual");
		}
		
		if(response.getStatusCode() == 201) {
			System.out.println("Success");
		}else {
			System.out.println("Failed, Check response for errors");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		



	}

}
