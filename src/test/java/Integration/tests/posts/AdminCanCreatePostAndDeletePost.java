package Integration.tests.posts;

import Integration.authApi.AuthApi;
import Integration.post.CreatePost;
import Integration.post.DeletePost;
import Integration.post.GetPostByPostId;
import Integration.uploadPhoto.UploadPhoto;
import Integration.schemas.PostCreateReq;
import Integration.user.GetUser;
import Integration.user.UpdateUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.*;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class AdminCanCreatePostAndDeletePost {
    AuthApi authApi;
    GetUser getUser;
    UploadPhoto uploadPhoto;
    UpdateUser updateUser;
    CreatePost createPost;
    GetPostByPostId getPostByPostId;
    DeletePost deletePost;
    @Epic(value = "Create post")
    @Feature(value= "Creating post")
    @Story(value = "User can create post with role admin")
    @Description(value = "User can create post")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "User Can create post")
    public void adminCanCreatePost() throws JsonProcessingException {
        String email = "alewwx@gmail.com";
        String password = "Gazmanov1234";
        String newName = "Alex";
        String newSurname = "Rashevchenko";
        File filePath = new File("src/test/java/Integration/Photo/Man-PNG-Photo.png");

        String title = "hallo world";
        String description = "djfjdfj";
        String body = "djfjdjfjd";

        authApi = new AuthApi();
        String token = authApi.login(email, password, 200);

        getUser = new GetUser(token);
        String userJson = getUser.getUser(200);
        JsonPath object = new JsonPath(userJson);
        String userId = object.getString("id");

        uploadPhoto = new UploadPhoto(token);
        String imageURL = uploadPhoto.uploadImage(filePath, 201);

        PostCreateReq postCreateReq = new PostCreateReq();
        postCreateReq.setTitle(title);
        postCreateReq.setDescription(description);
        postCreateReq.setBody(body);
        postCreateReq.setImageUrl(imageURL);

        createPost = new CreatePost(token);
        String response = createPost.createPost(postCreateReq,201);
        JsonPath jsonPath = new JsonPath(response);
        String postId = jsonPath.getString("id");

        getPostByPostId = new GetPostByPostId(token);
        String postResponse=getPostByPostId.getPostByPostId(postId,200);
        JsonPath postJson = new JsonPath(postResponse);
        String postTitle = jsonPath.getString("title");
        String postDescription = jsonPath.getString("description");
        String postBody = jsonPath.getString("body");
        String postImageUrl = jsonPath.getString("imageUrl");

        Assert.assertEquals(title,postTitle);
        Assert.assertEquals(description,postDescription);
        Assert.assertEquals(body,postBody);
        Assert.assertEquals(imageURL,postImageUrl);

        deletePost = new DeletePost(token);
        deletePost.deletePost(postId,204);

        getPostByPostId.getPostByPostId(postId,404);

    }
}
