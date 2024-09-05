package cucumber.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.ValidatableResponse;
import models.CreateUserModel;
import net.serenitybdd.annotations.Steps;
import org.junit.Assert;
import services.BaseService;
import services.GoRestService;
import userinfo.UserSteps;
import models.CreateUserModel;
import java.util.HashMap;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.hasValue;

/**
 * Created by Manish
 */
public class MyStepdefs  extends BaseService {

    static ValidatableResponse response;

    @Steps
    UserSteps steps;

    @When("User sends a POST request to add with user endpoint")
    public void userSendsAPOSTRequestToAddWithUserEndpoint() {
        response = steps.getAllUsers();
    }

    @Then("Verify the status code of request")
    public void verifyTheStatusCodeOfRequest() {
        response.statusCode(200);
    }

    @Then("User must get back a valid status code {int} as per the name: {string}")
    public void userMustGetBackAValidStatusCodeAsPerTheName(int statusCode, String name) {
        HashMap<String,Object> userList = steps.getAllUserByName(name);
        Assert.assertThat(userList, hasValue(name));
    }

    @When("Verify the user's name, email, gender, status")
    public void verifyTheUserSNameEmailGenderStatus() {
        response = steps.getAllUsers();
    }

    @Then("User must get back a valid status code {int} as per the data: {string},  {string},  {string},  {string}")
    public void userMustGetBackAValidStatusCodeAsPerTheData(int statusCode, String name, String email, String gender, String status) {

        CreateUserModel createUserModel = new CreateUserModel(name, email, gender, status);
        GoRestService.createUser(createUserModel);

    }
}
