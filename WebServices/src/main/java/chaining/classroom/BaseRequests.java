package chaining.classroom;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseRequests {
	
	public static String sys_id;
	
	@BeforeSuite
	public void setUp() {
		RestAssured.authentication = 
				RestAssured.basic("admin", "Tuna@123");

		RestAssured.baseURI = "https://dev69962.service-now.com/api/now/table/";

	}

}
