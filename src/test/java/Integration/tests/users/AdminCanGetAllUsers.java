package Integration.tests.users;

import Integration.authApi.AuthApi;
import Integration.user.GetAllUsersAsAdmin;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.testng.annotations.Test;

public class AdminCanGetAllUsers {
    AuthApi authApi;
    GetAllUsersAsAdmin getAllUsersAsAdmin;

    @Test
    public void adminCanGetAllUsers() throws JsonProcessingException {
        String email = "alewwx@gmail.com";
        String password = "Gazmanov1234";
        authApi = new AuthApi();
        String token = authApi.login(email, password, 200);
        getAllUsersAsAdmin = new GetAllUsersAsAdmin(token);
        getAllUsersAsAdmin.getAllUsersAsAdmin(200,0,10);
    }

}
