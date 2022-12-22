package cydeo.spartan.editor;


import io.restassured.http.ContentType;
import net.serenitybdd.junit5.SerenityTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utilities.SpartanTestBase;
import utilities.SpartanUtil;

import java.util.Map;

import static net.serenitybdd.rest.SerenityRest.given;

@SerenityTest
public class SpartanEditorTest extends SpartanTestBase {


    @DisplayName("POST Spartan as editor")
    @Test
    public void postSpartan(){

        Map<String, Object> spartanMap = SpartanUtil.getRandomSpartanMap();

        given().auth().basic("editor","editor")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(spartanMap). // SERIALIZATION
                when().post("/spartans").prettyPeek();
    }



}
