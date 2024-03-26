package org.rest.ass;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class CreatePOSTMethod {

	@Test(priority = 1)
	void testPostUsingHashMap() {
		
		HashMap h = new HashMap<Object, Object>();
		
		
		h.put("name", "Murugesan");
		h.put("location", "Karaikudi");
		h.put("mobile", "9095614367");
		String [] coursesArr = {"Java","Selenium"};
		h.put("courses", coursesArr);
		
		given()
		.contentType("application/json")
		.body(h)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Murugesan"))
		.body("location", equalTo("Karaikudi"))
		.body("mobile", equalTo("9095614367"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("Selenium"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all()
		;
	}
	
	// by org.json
	
	// @Test(priority = 1)
	void testPostUsingorgjson() {
		
		JSONObject js = new JSONObject();
		
		
		js.put("name", "Murugesan");
		js.put("location", "Karaikudi");
		js.put("mobile", "9095614367");
		String [] coursesArr = {"Java","Selenium"};
		js.put("courses", coursesArr);
	
		given()
		.contentType("application/json")
		.body(js.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Murugesan"))
		.body("location", equalTo("Karaikudi"))
		.body("mobile", equalTo("9095614367"))
		.body("courses[0]",equalTo("Java"))
		.body("courses[1]",equalTo("Selenium"))
		.header("Content-Type","application/json; charset=utf-8")
		.log().all()
		;
	}

	// by External json file
	
		// @Test(priority = 1)
		void testPostUsingExternalJSON() throws FileNotFoundException {
			
			File f = new File(".\\body.json");
			
			FileReader fr = new FileReader(f);
			
			JSONTokener jt = new JSONTokener(fr);
			
			JSONObject js = new JSONObject(jt);
			
			
			
			given()
			.contentType("application/json")
			.body(js.toString())
			
			.when()
			.post("http://localhost:3000/students")
			
			.then()
			.statusCode(201)
			.body("name", equalTo("Vignesh"))
			.body("location", equalTo("salem"))
			.body("mobile", equalTo("9098877654"))
			.body("courses[0]",equalTo("C"))
			.body("courses[1]",equalTo("C++"))
			.header("Content-Type","application/json; charset=utf-8")
			.log().all()
			;
		}

	// deleting the record
	@Test(priority = 2)
	void deleteRecord() {
		
		
		given()
		
		.when()
		.delete("http://localhost:3000/students/3")
		
		.then()
		.statusCode(200)
		
		;
	}
	
}
