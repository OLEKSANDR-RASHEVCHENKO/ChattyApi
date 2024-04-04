package Integration.user;

import Integration.ApiBase;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class GetPostsByUser extends ApiBase {
    public GetPostsByUser(String token) {
        super(token);
    }
    @Step("Get post by postID: {0}")
    public String getPostsByUserId(String userId,int expectedStatusCode){
        String endpoint = "/api/users/"+userId+"/posts";
        Response response = getRequest(endpoint,expectedStatusCode);
        return response.asString();
    }
}
