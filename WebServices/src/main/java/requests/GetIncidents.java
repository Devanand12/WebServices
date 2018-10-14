package requests;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetIncidents {

	public static void main(String[] args) {
		
	/*	BasicAuthScheme basic = new BasicAuthScheme();
		basic.setUserName("admin");
		basic.setPassword("Tuna@123");
		
		RestAssured.authentication = basic;*/
		
		RestAssured.authentication = 
				RestAssured.basic("admin", "Tuna@123");
		
		RestAssured.baseURI = "https://dev69962.service-now.com/api/now/table/";

		// Get all the incidents		
		Response response = RestAssured
			.given()
			.param("impact", "2")
		/*	.auth()
			.basic("admin", "Tuna@123")*/
			.get("incident");
		
		//response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		List<String> incidentNumbers = json.getList("result.number");
		System.out.println(incidentNumbers.size());
		
		for (String eachNumber : incidentNumbers) {
			System.out.println(eachNumber);
		}
		
		
		
	}

}
