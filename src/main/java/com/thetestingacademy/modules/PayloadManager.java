package com.thetestingacademy.modules;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.thetestingacademy.pojos.Booking;
import com.thetestingacademy.pojos.BookingDates;
import com.thetestingacademy.pojos.BookingResponse;

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
}
