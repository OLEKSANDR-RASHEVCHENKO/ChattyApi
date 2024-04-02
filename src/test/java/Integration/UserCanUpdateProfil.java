package Integration;

import Integration.AuthApi.AuthApi;
import Integration.UpdateUser.UpdateUser;
import Integration.getUser.GetUser;
import Integration.schemas.UserUpdateReq;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class UserCanUpdateProfil {


    AuthApi authApi;
    GetUser getUser;
    UpdateUser updateUser;

    @Test
    public void userCanUpdateProfile() throws JsonProcessingException {
        String email = "rashevc88495f@gmail.com";
        String password = "Gazmanov1234";
        String newName = "Alex";
        String newSurname = "Rashevchenko";
        String newBirthDate = ""; // Предполагается, что пустая строка допустима для birthDate
        String newPhone = "+123456789";
        String newGender = "FEMALE";
        String newAvatarUrl = "http://example.com/new-avatar.jpg";
        String newBackgroundUrl = "http://example.com/new-background.jpg";
        boolean newBlockedStatus = false;
        authApi = new AuthApi();
        String token = authApi.login(email, password, 200);
        getUser = new GetUser(token);
        String userJson = getUser.getUser();
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");
        System.out.println(userId);
        UserUpdateReq userUpdateReq = new UserUpdateReq();
        userUpdateReq.setName(newName);
        userUpdateReq.setSurname(newSurname);
        userUpdateReq.setBirthDate(newBirthDate);
        userUpdateReq.setPhone(newPhone);
        userUpdateReq.setGender(newGender);
        userUpdateReq.setAvatarUrl(newAvatarUrl);
        userUpdateReq.setBackgroundUrl(newBackgroundUrl);
        userUpdateReq.setBlocked(newBlockedStatus);

        updateUser = new UpdateUser(token);
        updateUser.updateUser(userId, userUpdateReq, 200);
        String updatedUserJson = getUser.getUser();
        JsonPath updatedUser = new JsonPath(updatedUserJson);
        assertEquals(updatedUser.getString("name"), newName);
        assertEquals(updatedUser.getString("surname"), newSurname);
        assertEquals(updatedUser.getString("phone"), newPhone);
        assertEquals(updatedUser.getString("gender"), newGender);
        assertEquals(updatedUser.getString("avatarUrl"), newAvatarUrl);

    }
}
