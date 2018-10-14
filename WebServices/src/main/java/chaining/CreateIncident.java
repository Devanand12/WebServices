package chaining;

import java.io.File;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIncident extends BaseRequests{

	@Test(dataProvider="Create")
	public void createIncident(String number, String sev) {
		
		// Get all the incidents		
		Response response = RestAssured
				.given()
				.contentType(ContentType.JSON)
				.body(new File("./post.json"))
				.body("{\r\n" + 
						"    \"number\" : \""+number+"\",\r\n" + 
						"    \"description\": \"This is automated using PostMan\",\r\n" + 
						"    \"severity\" : "+sev+"\r\n" + 
						"}")
				.post("incident");

		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		sys_id = json.get("result.sys_id");
		
		
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
	//,parallel=true, indices= {1}
	@DataProvider(name="Create")
	public Object[][] getData(){
		
		Object[][] data = new Object[2][2];
		data[0][0] = "INC0010102";
		data[0][1] = "2";
		
		data[1][0] = "INC0010103";
		data[1][1] = "1";
		
		return data;
	}
	
	
	
	
	
	
	

}
