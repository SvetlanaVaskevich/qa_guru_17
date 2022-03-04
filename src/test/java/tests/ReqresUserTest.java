package tests;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ReqresUserTest {

    @Test
    public void userFirstNameTest() {
        List<Map<String, Object>> userName = given().
                when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .extract().path("data");

        for (int i=0;i<userName.size();i++){
            System.out.println(userName.get(i).get("first_name"));
            assertThat(userName.get(i).get("first_name")).isNotNull();
        }

    }
}
