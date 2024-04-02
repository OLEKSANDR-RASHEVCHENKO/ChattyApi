package Integration;

import Integration.AuthApi.AuthApi;
import Integration.UploadPhoto.UploadPhoto;
import Integration.getUser.GetUser;
import Integration.schemas.UploadFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

public class UserCanUploadFile {
    AuthApi authApi;
    GetUser getUser;
    UploadPhoto uploadPhoto;
    @Test
    public void userCanUploadFile() throws JsonProcessingException {
        String email = "rashevc88495f@gmail.com";
        String password = "Gazmanov1234";
        File filePath = new File("src/test/java/Integration/Photo/Man-PNG-Photo.png");
        authApi = new AuthApi();
        String token= authApi.login(email,password,200);
        getUser = new GetUser(token);
        String userJson=getUser.getUser();
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");
        uploadPhoto = new UploadPhoto(token);
        uploadPhoto.uploadImage(filePath,201);

    }
}
