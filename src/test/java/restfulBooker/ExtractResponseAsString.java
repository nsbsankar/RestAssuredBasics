package restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ExtractResponseAsString {
    public static void main(String[] args) {

        String responseBody = RestAssured.given()
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

                .post()
                .then()
                .extract()
               // .body() //extract body
               // .asString() // return response as single line
                       .asPrettyString();  // returns response as pretty format


        System.out.println(responseBody);

    }
}
