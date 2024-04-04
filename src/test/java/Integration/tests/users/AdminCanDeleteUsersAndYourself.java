package Integration.tests.users;

import Integration.authApi.AuthApi;
import Integration.user.DeleteUser;
import Integration.user.GetUser;
import com.github.javafaker.Faker;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminCanDeleteUsersAndYourself {
    AuthApi authApi;
    GetUser getUser;
    DeleteUser deleteUser;

    Faker faker = new Faker();
    @Test

    public void adminCanDeleteUsersAndYourself() {
        String userEmail = faker.internet().emailAddress();
        String userPassword = "Gazmanov1234";
        String confirmUserPassword = "Gazmanov1234";
        String role = "user";

        String adminEmail = faker.internet().emailAddress();;
        String adminPassword = "Gazmanov1234";
        String confirmAdminPassword = "Gazmanov1234";
        String adminRole = "admin";
        authApi = new AuthApi();
        authApi.registration(userEmail, userPassword, confirmUserPassword, role, 201);
        String token = authApi.login(userEmail, userPassword, 200);
        getUser = new GetUser(token);
        String userJson = getUser.getUser(200);
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");


        authApi.registration(adminEmail, adminPassword, confirmAdminPassword, adminRole, 201);
        String adminToken = authApi.login(adminEmail, adminPassword, 200);
        getUser = new GetUser(adminToken);
        String adminJson = getUser.getUser(200);
        JsonPath object1 = new JsonPath(adminJson);
        String adminId = object1.getString("id");

        deleteUser = new DeleteUser(adminToken);
        deleteUser.deleteUser(userId, 204);

        try {
            getUser = new GetUser(token);
            String userJson1 = getUser.getUser(404);
            JsonPath object2 = new JsonPath(userJson1);
            String userId1 = object2.getString("id");
            Assert.fail("User should not be found after deletion, but user data was retrieved.");
        } catch (AssertionError e) {
            if (e.getMessage().contains("Expected status code <200> but was <404>")) {
                System.out.println("User not found as expected after deletion.");
            } else {
                throw e;
            }
        }
        deleteUser.deleteUser(adminId,204);
    }
}
