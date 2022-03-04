package tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ReqresTest {

    @Test
    public void getUsersTest(){

        given()
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.last_name",is("Weaver"));

        Response response =
                given()
                        .when()
                        .get("https://reqres.in/api/users/2")
                        .then()
                        .extract().response();

        System.out.println("Response " + response.asString());
    }

    @Test
    public void getUserNotFoundTest(){

        given()
                .when()
                .get("https://reqres.in/api/unknown/23")
                .then()
                .statusCode(404);
    }

    @Test
    public void createUserTest(){
        String data = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";
        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name",is("morpheus"));
    }

    @Test
    public void registerSuccesfulTest(){
        String data = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";
        given()
                .contentType(ContentType.JSON)
                .body(data)
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token",is("QpwL5tke4Pnpja7X4"));
    }
}
