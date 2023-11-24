package restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateBookingInBDDFormat {
    public static void main(String[] args) {
        //Build request
        RestAssured.given()
                .log().all()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("booking")
                .contentType(ContentType.JSON)
                .body("""
                        {
                            "firstname" : "Jim",
                            "lastname" : "Brown",
                            "totalprice" : 111,
                            "depositpaid" : true,
                            "bookingdates" : {
                                "checkin" : "2018-01-01",
                                "checkout" : "2019-01-01"
                            },
                            "additionalneeds" : "Breakfast"
                        }""")
                //Hit request and get response
                .when()
                .post()
                //Validate response
                .then()
                .log().all()
                .statusCode(200);
    }
}
