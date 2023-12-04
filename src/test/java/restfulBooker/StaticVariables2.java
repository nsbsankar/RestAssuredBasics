package restfulBooker;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class StaticVariables2 {

    @Test
    public void createToken(){

        RestAssured.given()
                .basePath("auth")
                .body("""
                        {
                            "username" : "admin",
                            "password" : "password123"
                        }""")
                .when().post();
    }
}
