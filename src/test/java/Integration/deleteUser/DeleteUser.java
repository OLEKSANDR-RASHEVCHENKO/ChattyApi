package Integration.deleteUser;

import Integration.ApiBase;
import io.restassured.response.Response;

public class DeleteUser extends ApiBase {
    public DeleteUser(String token) {
        super(token);
    }
    public String deleteUser(String userId, int expectedStatusCode) {
        String endpoint = "/api/users/" + userId;
        Response response = deleteRequest(endpoint, expectedStatusCode, userId);
        return response.asString();
    }
}
