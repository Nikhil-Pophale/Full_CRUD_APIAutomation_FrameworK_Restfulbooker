package org.example.Resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Payload.*;
import org.example.Utils.Fakker;

public class PayloadManager {
    ObjectMapper objectMapper;

    public String setToken() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Auth x = new Auth();
        x.setUsername("admin");
        x.setPassword("password123");
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(x);
    }

    public String CreateBooking() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname(Fakker.getFname());
//        booking.setFirstname("dhbvbks");
//        booking.setLastname("lisjfsnv");
        booking.setLastname(Fakker.getLname());
        booking.setTotalprice(Fakker.price());
//        booking.setTotalprice(788);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Lunch");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-01-01");
        bookingdates.setCheckout("2022-01-10");
        booking.setBookingdates(bookingdates);
        String bookingid = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return bookingid;
    }
    public BookingResponse JsonToObject(String jsonString) throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        BookingResponse bookingRespons = objectMapper.readValue(jsonString, BookingResponse.class);
        return bookingRespons;
    }

    public String UpdateBooking() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        Booking booking = new Booking();
        booking.setFirstname(Fakker.getFname());
//        booking.setFirstname("UpdatedFname");
//        booking.setLastname("UpdatedLname");
        booking.setLastname(Fakker.getLname());
        booking.setTotalprice(Fakker.price());
        booking.setTotalprice(788);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Dinner");
        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2022-01-01");
        bookingdates.setCheckout("2022-01-10");
        booking.setBookingdates(bookingdates);
        String bookingid = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(booking);
        return bookingid;
    }

    public String PatchBooking() throws JsonProcessingException {
        objectMapper = new ObjectMapper();
        PatchReq patchReq = new PatchReq();
        patchReq.setFirstname(Fakker.getFname());
        patchReq.setLastname(Fakker.getLname());
        String x=objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(patchReq);
        return x;
    }
}
