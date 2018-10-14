package requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GetIncidentsAsXml {

	public static void main(String[] args) {
		
	/*	BasicAuthScheme basic = new BasicAuthScheme();
		basic.setUserName("admin");
		basic.setPassword("Tuna@123");
		
		RestAssured.authentication = basic;*/
		
		RestAssured.authentication = 
				RestAssured.basic("admin", "Tuna@123");
		
		RestAssured.baseURI = "https://dev69962.service-now.com/api/now/table/";

		
		Map<String,String> parametersMap = 
				new HashMap<String,String>();
		
		parametersMap.put("impact", "2");
		parametersMap.put("sysparm_fields", "sys_id");

		
		// Get all the incidents		
		Response response = RestAssured
			.given()
			/*.param("impact", "2")
			.param("sysparm_fields", "sys_id")*/
			.params(parametersMap)
			.accept(ContentType.XML)
		/*	.auth()
			.basic("admin", "Tuna@123")*/
			.get("incident");
		
		//response.prettyPrint();
		
		String contentType = response.getContentType();
		System.out.println(contentType);
		
		
		XmlPath xml = response.xmlPath();
		List<Object> allNumbers = xml.getList("response.result.sys_id");
		System.out.println(allNumbers.get(0));
		
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		
		long time = response.getTime();
		System.out.println(time);
		
		/*JsonPath json = response.jsonPath();
		List<String> incidentNumbers = json.getList("result.number");
		System.out.println(incidentNumbers.size());
		
		for (String eachNumber : incidentNumbers) {
			System.out.println(eachNumber);
		}*/
		
		
		
	}

}
