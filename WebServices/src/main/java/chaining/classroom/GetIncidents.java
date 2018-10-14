package chaining.classroom;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetIncidents extends BaseRequests{

	@Test
	public void getIncidents() {
		

		// Get all the incidents		
		Response response = RestAssured
			.given()
			.param("impact", "2")
			.get("incident");
		
		//response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		List<String> incidentNumbers = json.getList("result.sys_id");
		System.out.println(incidentNumbers.size());
		
		int val = (int)Math.random()*incidentNumbers.size();
		System.out.println(val);
		System.out.println(incidentNumbers.get(val));
		
		sys_id = incidentNumbers.get(val);
	}

}
