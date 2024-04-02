package Integration.passwordUpdate;

import Integration.ApiBase;
import Integration.schemas.PasswordReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class PasswordUpdate extends ApiBase {
    public PasswordUpdate(String token) {
        super(token);
    }
    public String changePassword(String userId, PasswordReq passwordReq,int expectedStatusCode) throws JsonProcessingException {
        String endpoint = "/api/user/password/update";
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(passwordReq);
        Response response = putRequest(endpoint,expectedStatusCode,jsonRequest);
        return response.asString();
    }
}
