package cydeo.spartan.admin;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import net.serenitybdd.rest.Ensure;
import net.serenitybdd.rest.SerenityRest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

@SerenityTest
public class SpartanAdminTest {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://34.204.7.162:7000";
    }


    @DisplayName("GET /spartans with PURE REST ASSURED")
    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when().get("/api/spartans").prettyPeek()
                .then().statusCode(200);

    }

    @DisplayName("GET /spartans with SERENITY REST")
    @Test
    public void test2(){

        SerenityRest.given()
        .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .pathParam("id", 45)
                .when().get("/api/spartans/{id}").prettyPeek();

        lastResponse();

        System.out.println("lastResponse().statusCode() = " + lastResponse().statusCode());

        Ensure.that("Status code is 200",vRes -> vRes.statusCode(200));
        Ensure.that("ContentType is JSON", vRes -> vRes.contentType(ContentType.JSON));
        Ensure.that("id is 45", vRes -> vRes.extract().jsonPath().getInt("id"));
        Ensure.that("Ensure that Expires header  is 0", vRes -> vRes.header("Expires", "0"));

    }






}
