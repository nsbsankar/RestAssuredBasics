package restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MultipleBookingsWithResponseSpec {

    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeClass
    public void setRequestSpec(){
       requestSpecification =  RestAssured.given().log().all()
               .baseUri("https://restful-booker.herokuapp.com/")
               .contentType(ContentType.JSON);

        responseSpecification = RestAssured.expect().log().all()
               .statusCode(200)
               .contentType(ContentType.JSON)
               .time(Matchers.lessThan(5000L));
    }

    @Test
    public void createBooking1(){
        RestAssured.given()
                .spec(requestSpecification)
                .basePath("booking")
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
                .spec(responseSpecification);
    }
    
    @Test
    public void createBooking2(){
        RestAssured.given()
                .spec(requestSpecification)
                .basePath("booking/1")
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
                .then()
                .spec(responseSpecification);
    }
}
