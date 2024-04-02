package Integration.UpdateUser;

import Integration.ApiBase;
import Integration.schemas.UserUpdateReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.response.Response;

public class UpdateUser extends ApiBase {
    public UpdateUser(String token) {
        super(token); // Инициализация базового класса с токеном
    }

    public String updateUser(String userId, UserUpdateReq userUpdateReq, int expectedStatusCode) throws JsonProcessingException {
        String endpoint = "/api/users/" + userId;
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(userUpdateReq);

        Response response = putRequest(endpoint,expectedStatusCode,jsonRequest);

        return response.asString();
    }
}


