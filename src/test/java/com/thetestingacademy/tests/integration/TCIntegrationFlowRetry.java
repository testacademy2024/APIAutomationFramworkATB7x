package com.thetestingacademy.tests.integration;

import com.thetestingacademy.base.BaseTest;
import com.thetestingacademy.endpoints.APIConstants;
import com.thetestingacademy.listeners.RetryAnalyzer;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingResponse;
import com.thetestingacademy.utils.PropertyReader;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
@Test(retryAnalyzer = RetryAnalyzer.class)
public class TCIntegrationFlowRetry extends BaseTest {
    /**
     * Create a booking
     * Create a token
     * Update the booking
     * Delete the booking
     */
    @Test(groups="integration",priority=1)
    @Owner("Sanket")
    @Description("TC#INT1 - Step 1. Verify that booking can be created")
    public void testCreateBooking(ITestContext iTestContext){
        iTestContext.setAttribute("token",getToken()); ///SetAttribute used here for API chaining
requestSpecification.basePath(APIConstants.CREATE_UPDATE_BOOKING_URL);
response= RestAssured.given(requestSpecification).when().body(payloadManager.CreatePayloadBookingAsString()).post();
validatableResponse=response.then().log().all();
//Validatable Assertions
        validatableResponse.statusCode(200);

        //DeSerialization
        BookingResponse bookingResponse= payloadManager.bookingResponseJava(response.asString());
        //AssertJ
        assertThat(bookingResponse.getBookingid()).isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotNull().isNotBlank();
        assertThat(bookingResponse.getBooking().getFirstname()).isEqualTo(PropertyReader.readKey("booking.post.firstname"));
        
//Set the Booking Id
        iTestContext.setAttribute("bookingId",bookingResponse.getBookingid());


    }
    @Test(groups="integration",priority=2)
    @Owner("Sanket")
    @Description("TC#INT1 - Step 2. Verify Booking by Id")
    public void testVerifyBookingId(ITestContext iTestContext){

        //Get Booking Id
        Integer bookingId= (Integer) iTestContext.getAttribute("bookingId");
        //Get Req
        String basePathGET=APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+bookingId;
        System.out.println(basePathGET);
        requestSpecification.basePath(basePathGET);
        response=RestAssured
                .given(requestSpecification)
                .when().get();
        validatableResponse =response.then().log().all();
        //validateable assertions
        validatableResponse.statusCode(200);
        Booking booking=payloadManager.getResponseFromJSON(response.asString());
assertThat(booking.getFirstname()).isNotNull().isNotBlank();
assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.get.firstname"));



    }
    @Test(groups="integration",priority=3)
    @Owner("Sanket")
    @Description("TC#INT1 - Step 3. Verify Updated Booking By Id")
    public void testUpdateBookingById(ITestContext iTestContext){
        System.out.println("Token is ->"+iTestContext.getAttribute("token"));
      String token= (String) iTestContext.getAttribute("token");
      Integer bookingId=(Integer) iTestContext.getAttribute("bookingId");
String basePathPUTPATCH=APIConstants.CREATE_UPDATE_BOOKING_URL+"/"+ bookingId;
        System.out.println(basePathPUTPATCH);
        requestSpecification.basePath(basePathPUTPATCH);
        response=RestAssured
                .given(requestSpecification).cookie("token",token)
                .when().body(payloadManager.fullUpdatePayloadAsString()).put();
        validatableResponse=response.then().log().all();
        //Validatable Assertions
        validatableResponse.statusCode(200);

        Booking booking = payloadManager.getResponseFromJSON(response.asString());
        assertThat(booking.getFirstname()).isNotNull().isNotBlank();
        assertThat(booking.getFirstname()).isEqualTo(PropertyReader.readKey("booking.put.firstname"));
        assertThat(booking.getLastname()).isEqualTo(PropertyReader.readKey("booking.put.lastname"));


    }
    @Test(groups="integration",priority=4)
    @Owner("Sanket")
    @Description("TC#INT1 - Step 4. Delete the  booking by Id")
    public void testDeleteBookingById(ITestContext iTestContext){
        String token = (String) iTestContext.getAttribute("token");
        Assert.assertTrue(true);

        Integer bookingid = (Integer) iTestContext.getAttribute("bookingid");
        String basePathDELETE = APIConstants.CREATE_UPDATE_BOOKING_URL + "/" + bookingid;
        System.out.println(basePathDELETE);

        requestSpecification.basePath(basePathDELETE).cookie("token", token);
        validatableResponse = RestAssured.given().spec(requestSpecification)
                .when().delete().then().log().all();
        validatableResponse.statusCode(200);

    }

}
