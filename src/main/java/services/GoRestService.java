package services;

import io.restassured.response.Response;
import models.CreateUserModel;


public class GoRestService extends BaseService {


    public static Response createUser(final CreateUserModel createUserModel) {
        return defaultRequestSpecification()
                .body(createUserModel)
                .when()
                .post("/public/v1/users");
    }

    public static Response updateUser(int userID, final CreateUserModel updateUserModel) {
        return defaultRequestSpecification()
                .body(updateUserModel)
                .when()
                .put("/public/v1/users/" + userID);
    }

    public static Response getUser(int userId) {
        return defaultRequestSpecification()
                .when()
                .get("/public/v1/users/" + userId);
    }


    public static Response deleteUser(int userId) {
        return defaultRequestSpecification()
                .when()
                .delete("/public/v1/users/" + userId);
    }

}
