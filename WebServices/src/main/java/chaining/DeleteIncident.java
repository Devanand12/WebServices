package chaining;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteIncident extends BaseRequests {

	@Test(dependsOnMethods="chaining.classroom.GetIncidents.getIncidents")
	public void deleteIncident() {

		// Get all the incidents		
		Response response1 = RestAssured
				.given()
				.pathParam("sys_id", sys_id)
				.delete("incident/{sys_id}");

		
		if(response1.getStatusCode() == 204) {
			System.out.println("Delete Success");
		}else {
			System.out.println("Failed, Check response for errors");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		



	}

}
