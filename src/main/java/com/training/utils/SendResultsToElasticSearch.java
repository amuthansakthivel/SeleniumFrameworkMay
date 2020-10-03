package com.training.utils;

import static io.restassured.RestAssured.given;

import com.training.reports.TestStatus;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SendResultsToElasticSearch {
	
	public static void sendResults(TestStatus testStatus) {
		
	Response response=	given()
		.header("Content-Type","application/json").log().all()
		.body(testStatus).post("http://localhost:9200/app/suite");
		
		response.prettyPrint();
	}

}
