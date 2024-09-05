package userinfo;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.util.HashMap;

/**
 * Created by Manish
 */
public class UserSteps {

    @Step("Getting all the User")
    public ValidatableResponse getAllUsers(){
        return SerenityRest.given()
                .when()
                .get("/public/v1/users")
                .then().log().all();

    }

    @Step("Getting all the User by Name")
    public HashMap<String, Object> getAllUserByName(String name){
        String s1 = "data.pots.findAll{it.name == '";
        String s2 = "'}.get(0)";
        return SerenityRest.given().log().all()
                .when()
                .get("/public/v1/users")
                .then().statusCode(200)
                .extract()
                .path(s1 + name + s2);

    }

//    @Step("Insert multiple users")
//    public  ValidatableResponse multipleUserInsert(){
//
//    }
}
