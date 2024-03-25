package org.rest.ass;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class Beginning {

	String id;
	
	//Get All Users
	@Test(priority = 4)
	 void getUsers()
	{
		given()
		
		.when()
		.get("https://murugesan-crud-app.onrender.com/api/user/")
		
		.then()
		.statusCode(200)
		//.body("page",equalTo(1))
		.log().all()
		;
	}
	
	 @Test(priority = 1)
	void createUser() {
		
		HashMap<String,String> h = new HashMap<String,String>();
		h.put("name", "Sukum32rwar");
		h.put("email", "sukumar1werfweq2345@gmail.com");
		h.put("address","Mumbai");
		
	id=	given()
		.contentType("application/json")
		.body(h)
		
		
		.when()
		.post("https://murugesan-crud-app.onrender.com/api/user/create")
		.jsonPath().getString("_id")
		
	//	.then()
		//.statusCode(200)
	//	.log().all()
		;
	}
	 
	 @Test(priority = 2, dependsOnMethods = {"createUser"})
		void updateUser() {
			
			HashMap<String,String> h = new HashMap<String,String>();
			h.put("name", "sukumark");
			h.put("email", "sukumark123@gmail.com");
			h.put("address","Mumbai-567890");
			
			given()
			.contentType("application/json")
			.body(h)
			
			
			.when()
			.put("https://murugesan-crud-app.onrender.com/api/user/update/"+id)
			
			.then()
			.statusCode(201)
			.log().all()
			;
		}
	 
	 @Test(priority = 3, dependsOnMethods = {"updateUser"})
		void deleteUser() {
			
			
			given()
			
			
			
			.when()
			.delete("https://murugesan-crud-app.onrender.com/api/user/delete/"+id)
			
			.then()
			.statusCode(201)
			.log().all()
			;
		} 
}
