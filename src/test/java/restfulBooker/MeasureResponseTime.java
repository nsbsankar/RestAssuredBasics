package restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.concurrent.TimeUnit;

public class MeasureResponseTime {

    public static void main(String[] args) {

        Response response = RestAssured.given().log().all()
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
                .post();

        long responseTimeInMS = response.time();
        System.out.println("Response Time in MS = " + responseTimeInMS);

        long responseTimeInSeconds = response.timeIn(TimeUnit.SECONDS);
        System.out.println("Response Time in Seconds = " + responseTimeInSeconds);

        //Better readable methods for response time

        long responseTimeInMS1 = response.getTime();
        System.out.println("Response Time in MS 1 = " + responseTimeInMS1);

        long responseTimeInSeconds1 = response.getTimeIn(TimeUnit.SECONDS);
        System.out.println("Response Time in Seconds 1 = " + responseTimeInSeconds1);

        //Assertions using hamcrest assertions
        response.then().time(Matchers.lessThan(5000L));
        response.then().time(Matchers.greaterThan(2000L));
        response.then().time(Matchers.both(Matchers.lessThan(5000L)).and(Matchers.greaterThan(2000L)));

        response.then().time(Matchers.lessThan(5L),TimeUnit.SECONDS);


    }
}
