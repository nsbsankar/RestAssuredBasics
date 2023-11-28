package restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UpdateBooking {
    public static void main(String[] args) {

        RestAssured.given()
                .log().all()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking/1")
                .contentType(ContentType.JSON)
                .header("Authorization","Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .body("""
                        {
                            "firstname" : "James",
                            "lastname" : "Brown",
                            "totalprice" : 111,
                            "depositpaid" : true,
                            "bookingdates" : {
                                "checkin" : "2018-01-01",
                                "checkout" : "2019-01-01"
                            },
                            "additionalneeds" : "Breakfast"
                        }""")
                .when().put()
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
