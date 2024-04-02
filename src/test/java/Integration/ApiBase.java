package Integration;

import config.Config;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;

public class ApiBase {
    private final Config config = new Config();
    protected final String BASE_URL = config.getProjectUrl();
    protected final RequestSpecification spec;
    public ApiBase(){
        this.spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();

    }
    public ApiBase(String token){
        this.spec = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", "Bearer " + token) // Используем токен в заголовке Authorization
                .build();
    }



    protected Response getRequest(String endpoint, int code){
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }
    protected Response getRequestWhitParam(String endpoint,int code,String paramName,int paramValue){
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam(paramName,paramValue)
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }
    protected Response getRequestWhitParamString(String endpoint,int code,String paramName,String paramValue){
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam(paramName,paramValue)
                .log().all()
                .get(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }
    protected Response postRequest(String endpoint,int code,Object body){
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .post(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }
    protected Response putRequest(String endpoint,int code,Object body){
        Response response = RestAssured.given()
                .spec(spec)
                .body(body)
                .when()
                .log().all()
                .put(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }
    protected Response deleteRequest(String endpoint,int code,String id){
        Response response = RestAssured.given()
                .spec(spec)
                .when()
                .pathParam("id",id)
                .log().all()
                .delete(endpoint)
                .then().log().all()
                .extract().response();
        response.then().assertThat().statusCode(code);
        return response;
    }
    public Response uploadImageRequest(String endpoint, File imageFile, int code) {
        Response response = RestAssured.given()
                .spec(spec)
                .contentType("multipart/form-data")
                .multiPart("multipartFile", imageFile)
                .when()
                .post(endpoint)
                .then().log().all()
                .statusCode(code)
                .extract()
                .response();

        response.then().assertThat().statusCode(code);
        return response;
    }
}

