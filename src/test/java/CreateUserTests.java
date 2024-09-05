import io.restassured.response.Response;
import models.CreateUserModel;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import services.GoRestService;

import java.util.Random;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class CreateUserTests {

    static int userId;

    public static String getRandomValue() {
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }


    @Test
    public void A_Users_CreateUsers_Success() {
        final String uniqueEmail = "qatest" + getRandomValue() + "@test.com";
        final CreateUserModel createUserModel = new CreateUserModel("Gino Paloma", uniqueEmail, "Male", "Active");

        Response response = GoRestService.createUser(createUserModel);
        response
                .then()
                .statusCode(SC_CREATED)
                .body("data.id", notNullValue())
                .body("data.name", equalTo(createUserModel.getName()));

        userId = response.jsonPath().getInt("data.id");
    }

    @Test
    public void B_Users_UpdateUser_Success() {
        final String updatedName = "Updated Gino";
        final String updatedEmail = "updated" + getRandomValue() + "@test.com";
        final CreateUserModel updateUserModel = new CreateUserModel(updatedName, updatedEmail, "Male", "Inactive");
        Response updateResponse = GoRestService.updateUser(userId, updateUserModel);

        updateResponse.then()
                .statusCode(SC_OK)
                .body("data.name", equalTo(updatedName))
                .body("data.email", equalTo(updatedEmail));

    }
    @Test
    public void C_Users_GetUser_Success() {
        GoRestService.getUser(userId)
                .then()
                .statusCode(SC_OK);
    }

//    @Test
//    public void D_Users_DeleteUser_Success() {
//        Response deleteResponse = GoRestService.deleteUser(userId);
//        deleteResponse
//                .then()
//                .statusCode(SC_NO_CONTENT);
//        Response getResponse = GoRestService.getUser(userId);
//
//        getResponse.then()
//                .statusCode(SC_NOT_FOUND);
//    }
}
