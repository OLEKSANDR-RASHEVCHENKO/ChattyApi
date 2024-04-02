package Integration.AuthApi;

import Integration.ApiBase;
import io.restassured.response.Response;

import java.util.LinkedHashMap;

public class AuthApi extends ApiBase {
    Response response;
    public String registration(String email, String password, String confirmPassword, String role, int expectedStatusCode) {
        String endPoint = "/api/auth/register";
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);
        body.put("confirmPassword", confirmPassword);
        body.put("role", role);

        Response response = postRequest(endPoint, expectedStatusCode, body);
        int statusCode = response.statusCode();

        if (statusCode == expectedStatusCode) {
            return "User registered successfully";
        } else {
            String errorMessage = response.jsonPath().getString("message");
            return "Failed to register user: " + errorMessage;
        }
    }

    public String login(String email, String password, int expectedStatusCode) {
        String endPoint = "/api/auth/login";
        LinkedHashMap<String, String> body = new LinkedHashMap<>();
        body.put("email", email);
        body.put("password", password);

        response = postRequest(endPoint, expectedStatusCode, body);
        int statusCode = response.statusCode();

        if (statusCode == expectedStatusCode) {
            String refreshToken = response.jsonPath().getString("refreshToken");
            return refreshToken;
        } else {
            String errorMessage = response.jsonPath().getString("message");
            return "Failed to login: " + errorMessage;
        }
    }
}
