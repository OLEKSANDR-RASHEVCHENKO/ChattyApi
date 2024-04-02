package Integration;

import Integration.AuthApi.AuthApi;
import Integration.getUser.GetUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

public class UserCanLogin {
    AuthApi authApi;
    GetUser getUser;

    @Test
    public void userCanRegister(){
        String email = "rashevc88495f@gmail.com";
        String password = "Gazmanov1234";
        String confirmPassword = "Gazmanov1234";
        String role = "user";
        authApi = new AuthApi();
//        authApi.registration(email,password,confirmPassword,role,201);
        String token= authApi.login(email,password,200);
        getUser = new GetUser(token);
        String userJson=getUser.getUser();
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");
        System.out.println(userId);

    }
}
