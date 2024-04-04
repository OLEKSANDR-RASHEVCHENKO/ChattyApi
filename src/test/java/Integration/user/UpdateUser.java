package Integration.user;

import Integration.ApiBase;
import Integration.schemas.UserUpdateReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static org.testng.AssertJUnit.assertNotNull;

public class UpdateUser extends ApiBase {
    public UpdateUser(String token) {
        super(token);
    }

    @Step("Update user with ID {0}")
    public String updateUser(String userId, UserUpdateReq userUpdateReq, int expectedStatusCode) throws JsonProcessingException {
        String endpoint = "/api/users/" + userId;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(userUpdateReq);

        Response response = putRequest(endpoint,expectedStatusCode,jsonRequest);


        if (response.getStatusCode() == 200) {
            JsonPath jsonPath = response.jsonPath();
            assertNotNull("ID is missing", jsonPath.get("id"));
            assertNotNull("Name is missing", jsonPath.get("name"));
            assertNotNull("Surname is missing", jsonPath.get("surname"));
            assertNotNull("Phone is missing", jsonPath.get("phone"));
            assertNotNull("Email is missing", jsonPath.get("email"));
            assertNotNull("Gender is missing", jsonPath.get("gender"));
            assertNotNull("AvatarUrl is missing", jsonPath.get("avatarUrl"));
        }
        switch (response.getStatusCode()) {
            case 200:
                return  response.asString();
            case 400:
                return "Bad Request: " + response.jsonPath().getString("message");
            case 401:
                return "Unauthorized: " + response.jsonPath().getString("message");
            default:
                return "Unexpected status code: " + response.getStatusCode() + ". Message: " + response.asString();
        }
    }
}


