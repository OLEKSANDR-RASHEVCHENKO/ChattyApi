package Integration.UploadPhoto;

import Integration.ApiBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadPhoto extends ApiBase {
    public UploadPhoto(String token) {
        super(token);
    }

    public String uploadImage(File imageFile, int expectedStatusCode) {
        String endPoint = "/api/images";

        Response response = uploadImageRequest(endPoint, imageFile, expectedStatusCode);

        if (response.statusCode() == expectedStatusCode) {
            return response.jsonPath().getString("imageUrl");
        } else {
            String errorMessage = response.jsonPath().getString("message");
            return "Failed to upload image: " + errorMessage;
        }
    }
}