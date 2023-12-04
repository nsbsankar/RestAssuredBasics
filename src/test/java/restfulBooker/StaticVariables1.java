package restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class StaticVariables1 {

    @BeforeTest
    public void setUp(){
        //static variables
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
        RestAssured.basePath = "booking";
        RestAssured.requestSpecification = RestAssured.given().log().all();
        RestAssured.responseSpecification = RestAssured.expect().log().all().statusCode(200);
    }

    @Test
    public void createBooking1(){
        RestAssured.given()
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
                .post();
    }

    @Test
    public void createBooking2(){
        RestAssured.given()
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
                .post();
    }
}
