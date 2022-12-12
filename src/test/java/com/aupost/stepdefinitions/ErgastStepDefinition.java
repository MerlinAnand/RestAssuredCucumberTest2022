package com.aupost.stepdefinitions;

import io.restassured.RestAssured;
import com.aupost.util.Constants;
import com.aupost.util.Utilities;
import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

/**
 * This class is used to glue the steps mentioned in the feature file
 * 
 * @author Merlin
 *
 */

public class ErgastStepDefinition {
	private static Logger log = LogManager.getLogger(ErgastStepDefinition.class.getName());
	private ArrayList circuitID;
	private int round;

	@Given("^I hit f1 for the \"(.*)\"$")
	public void getCiruit(String year) {
		this.circuitID = getCircuitID(year);
	}

	/**
	 * This api call returns the first post code as sometimes it returns multiple
	 * post codes for a given suburb and state
	 *
	 * @param year
	 *
	 *  */

	private ArrayList getCircuitID(String year) {

		RestAssured.baseURI = Constants.BASE_URI;
		round = 1;
		log.info("Before calling the API to retrieve the first post code ... getPostCode()");
		Response resp = given()
				.accept(ContentType.JSON)
				.contentType("application/json")
				.when()
				.get(year+"/"+round+".json").then().assertThat().statusCode(HttpStatus.SC_OK).extract().response();
		log.info("Response : " + resp.asString());
		JsonPath js = Utilities.rawToJson(resp);

		if (null != js && null != js.get("MRData.RaceTable.Races.Circuit.circuitId")) {
			log.info("circuit ID : " + js.get("MRData.RaceTable.Races.Circuit.circuitId"));
			circuitID = js.get("MRData.RaceTable.Races.Circuit.circuitId");
			log.info("MRData.url : " + js.get("MRData.url"));
			Assert.assertTrue(js.get("MRData.url").equals("http://ergast.com/api/f1/2017/1.json"));
			return circuitID;
		} else {
			log.info("No response from the api. Please check");
		}
		return circuitID;
	}
}
