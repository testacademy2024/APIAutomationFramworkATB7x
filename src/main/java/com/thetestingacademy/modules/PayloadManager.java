package com.thetestingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.thetestingacademy.pojos.*;

public class PayloadManager {
    Gson gson;
    //Serialization and De-Serialization

    // Converting the JAVA object to the String
    // {}
public String CreatePayloadBookingAsString()
{
    Booking booking = new Booking();
    booking.setFirstname("James");
    booking.setLastname("Brown");
    booking.setTotalprice(111);
    booking.setDepositpaid(true);

    BookingDates bookingdates = new BookingDates();
    bookingdates.setCheckin("2024-02-01");
    bookingdates.setCheckout("2024-02-01");

    booking.setBookingdates(bookingdates);
    booking.setAdditionalneeds("Breakfast");

    System.out.println(booking);
    // Java Object -> JSON String (byteStream) - Serlization
    Gson gson = new Gson();
    String jsonStringpayload = gson.toJson(booking);
    System.out.println(jsonStringpayload);
    return  jsonStringpayload;
}
    public String CreatePayloadBookingAsStringFaker()
    {
        Faker faker=new Faker();
        Booking booking = new Booking();
        booking.setFirstname(faker.name().firstName());
        booking.setTotalprice(faker.random().nextInt(1000));
        booking.setDepositpaid(true);
        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-01");

        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        System.out.println(booking);
        // Java Object -> JSON String (byteStream) - Serlization
        Gson gson = new Gson();
        String jsonStringpayload = gson.toJson(booking);
        System.out.println(jsonStringpayload);
        return jsonStringpayload;


    }

    public BookingResponse bookingResponseJava(String responseString) {
        gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(responseString, BookingResponse.class);
        return bookingResponse;
    }

    public String setAuthPayload(){
    //Auth Object to JSON String conversion
    Auth auth=new Auth();
    auth.setUsername("admin");
    auth.setPassword("password123");
    gson=new Gson();
    String jsonPayloadString=gson.toJson(auth);
        System.out.println("Payload set to the->"+ jsonPayloadString);
        return jsonPayloadString;
    }
    public String getTokenFromJSON(String tokenResponse)
    {
        gson=new Gson();
        TokenResponse tokenResponse1=gson.fromJson(tokenResponse,TokenResponse.class);
        return tokenResponse1.getToken();
    }
    public Booking getResponseFromJSON(String getResponse)

    {
        gson=new Gson();
        //Response JSON->>>Object Token Response
        //Deserialization
        Booking booking=gson.fromJson(getResponse,Booking.class);
        return booking;
    }
    public String fullUpdatePayloadAsString() {
        Booking booking = new Booking();
        booking.setFirstname("Pramod");
        booking.setLastname("Dutta");
        booking.setTotalprice(112);
        booking.setDepositpaid(true);

        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2024-02-05");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");
        return gson.toJson(booking);
    }

}
