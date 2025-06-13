package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import request.CreateBookingRequest;
import request.CreateTokenRequest;
import response.CreateBookingResponse;
import response.CreateTokenResponse;
import response.GetBookingIdsResponse;
import response.GetBookingResponse;
import response.Helper.BookingDates;
import utils.DataProvider;
import java.util.Random;

import static org.testng.Assert.assertNotNull;

public class ApiTests {


    public static final Logger logger = LogManager.getLogger(ApiTests.class);


    @Test(priority = 1)
    void CreateToken(){
        logger.info("CreateToken------------");
        //request
        CreateTokenRequest createTokenRequest = new CreateTokenRequest();
        createTokenRequest.setUsername("admin");
        createTokenRequest.setPassword("password123");

        logger.info("Api request body is ready");
        Response response = RestAssured
                .given().log().body()
                .header("Content-Type", "application/json")
                .body(createTokenRequest)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then().log().body()
                .statusCode(200)
                .extract()
                .response();

        DataProvider.createTokenResponse =response.as(CreateTokenResponse.class);

        assertNotNull(DataProvider.createTokenResponse.getToken());
        logger.info("Test completed");
    }

    @Test(priority = 2)
    void GetBookingIds(){
        logger.info("GetBookingIds------------");

        logger.info("Api request is ready");
        Response response = RestAssured
                .given().log().all()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then().log().body()
                .statusCode(200)
                .extract()
                .response();

        DataProvider.bookingIdList = response.jsonPath().getList("", GetBookingIdsResponse.class);
        assertNotNull(DataProvider.bookingIdList);
        logger.info("Test completed");
    }

    @Test(priority = 3)
    void GetBooking(){
        logger.info("GetBooking------------");

        int bookingNumber = DataProvider.bookingIdList.get(new Random().nextInt(DataProvider.bookingIdList.size())).getBookingid();

        logger.info("Api request body is ready");
        Response response = RestAssured
                .given().log().all()
                .header("Accept","application/json")
                .when()
                .get("https://restful-booker.herokuapp.com/booking/"+bookingNumber)
                .then().log().body()
                .statusCode(200)
                .extract()
                .response();
        DataProvider.bookingResponse = response.as(GetBookingResponse.class);
        assertNotNull( DataProvider.bookingResponse.getFirstname());
        assertNotNull( DataProvider.bookingResponse.getLastname());
        assertNotNull( DataProvider.bookingResponse.getBookingdates().getCheckin());
        assertNotNull( DataProvider.bookingResponse.getBookingdates().getCheckout());

        logger.info("Test completed");

    }

    @Test(priority = 4)
    void CreateBooking(){
        logger.info("CreateBooking------------");

        //request
        CreateBookingRequest request = new CreateBookingRequest();
        request.setFirstname("James");
        request.setLastname("Brown");
        request.setTotalprice(150);
        request.setDepositpaid(true);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-06-15");
        bookingDates.setCheckout("2025-06-16");
        request.setBookingdates(bookingDates);
        request.setAdditionalneeds("Breakfast");

        DataProvider.createBookingRequest = request;

        logger.info("Api request body is ready");
        Response response = RestAssured
                .given().log().all()
                .headers(
                        "Content-Type", "application/json",
                        "Accept", "application/json")
                .body(request)
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();

        DataProvider.createBookingResponse = response.as(CreateBookingResponse.class);
        assertNotNull( DataProvider.createBookingResponse.getBooking());

        logger.info("Test completed");

    }

    @Test(priority = 5)
    void UpdateBooking(){
        logger.info("UpdateBooking------------");

        //request
        int bookingID= DataProvider.createBookingResponse.getBookingid();
        CreateBookingRequest request = DataProvider.createBookingRequest;
        request.setFirstname("Jamess");
        request.setLastname("Brownn");
        request.setTotalprice(160);
        request.setDepositpaid(true);
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2025-06-16");
        bookingDates.setCheckout("2025-06-17");
        request.setBookingdates(bookingDates);
        request.setAdditionalneeds("Breakfast");

        logger.info("Api request body is ready");
        Response response = RestAssured
                .given().log().all()
                .headers(
                        "Content-Type", "application/json",
                        "Accept", "application/json")
                .header("Cookie", "token="+DataProvider.createTokenResponse.getToken())
                .body(request)
                .when()
                .put("https://restful-booker.herokuapp.com/booking/"+ bookingID)
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();


        DataProvider.uptadateBooking =response.as(GetBookingResponse.class);
        assertNotNull( DataProvider.uptadateBooking);
        logger.info("Test completed");
    }

    @Test(priority = 6)
    void PartialUpdateBooking(){
        logger.info("PartialUpdateBooking------------");

        //request
        int bookingID= DataProvider.createBookingResponse.getBookingid();
        CreateBookingRequest request = new CreateBookingRequest();
        request.setFirstname("Jamsss");
        request.setLastname("Brown");

        logger.info("Api request body is ready");
        Response response = RestAssured
                .given().log().all()
                .headers(
                        "Content-Type", "application/json",
                        "Accept", "application/json",
                        "Cookie","token="+DataProvider.createTokenResponse.getToken())
                .body(request)
                .when()
                .patch("https://restful-booker.herokuapp.com/booking/"+bookingID)
                .then().log().all()
                .statusCode(200)
                .extract()
                .response();


        DataProvider.particalUpdateBooking = response.as(GetBookingResponse.class);
        assertNotNull( DataProvider.particalUpdateBooking);
        logger.info("Test completed");
    }

    @Test(priority = 7)
    void DeleteBooking(){
        logger.info("DeleteBooking------------");
        int bookingID= DataProvider.createBookingResponse.getBookingid();
        logger.info("Api request is ready");
        Response response = RestAssured
                .given().log().all()
                .headers(
                        "Content-Type", "application/json",
                        "Cookie","token="+DataProvider.createTokenResponse.getToken())
                .when()
                .delete("https://restful-booker.herokuapp.com/booking/"+bookingID)
                .then().log().all()
                .statusCode(201)
                .extract()
                .response();

        logger.info("Test completed");

    }

    @Test(priority = 8)
    void HealthCheck(){
        logger.info("HealthCheck------------");

        logger.info("DeleteBooking------------");
        Response response = RestAssured
                .given().log().all()
                .when()
                .get("https://restful-booker.herokuapp.com/ping")
                .then().log().all()
                .statusCode(201)
                .extract()
                .response();
    }
}
