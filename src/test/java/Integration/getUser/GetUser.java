package Integration.getUser;

import Integration.ApiBase;
import io.restassured.response.Response;

public class GetUser extends ApiBase {

    public GetUser(String token) {
        super(token);
    }
    public String getUser() {
        String endPoint = "/api/me";
        Response response = getRequest(endPoint, 200);
        return response.asString();
    }
}
