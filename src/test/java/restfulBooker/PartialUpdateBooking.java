package restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PartialUpdateBooking {

    public static void main(String[] args) {

        RestAssured.given().log().all()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/{id}")
                .pathParam("id", 2)
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body("""
                        {
                            "firstname" : "James",
                            "lastname" : "sankar"
                        }""")
                .when().patch()
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
