package org.example.Full_Crud;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import org.example.Base.BaseTest;
import org.example.COMMON.APIConstants;
import org.example.Payload.BookingResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Integration extends BaseTest {
    String bookingid;
    String token;
    public static JsonPath jsonPath;

    @Test
    public void CreateBooking() throws JsonProcessingException {
        token = getToken();

        requestSpecification.basePath(APIConstants.Create_Booking);
        response = requestSpecification.body(payloadManager.CreateBooking()).when().post();
        System.out.println(response.asString());
        jsonPath = JsonPath.from(response.asString());
        bookingid = jsonPath.getString("bookingid");

//        AssertActions For Verification > >

        Gson gson = new Gson();
        BookingResponse bookingResponse = gson.fromJson(response.asString(), BookingResponse.class);
//        Assert.assertEquals(bookingResponse.getBooking().getFirstname(),"Simran");
//        Assert.assertEquals(bookingResponse.getBooking().getLastname(),"Kapoor");
        Assert.assertEquals(bookingResponse.getBooking().getDepositpaid(), true);
//        Assert.assertEquals(bookingResponse.getBooking().getTotalprice(),799);

    }

    @Test(dependsOnMethods = "CreateBooking")
    public void UpdateBooking() throws JsonProcessingException {
        token = getToken();
        requestSpecification.basePath(APIConstants.Update_Booking + "/" + bookingid);
        response = requestSpecification.cookie("token", token).body(payloadManager.UpdateBooking()).when().put();
        System.out.println(response.asString());

    }

    @Test(dependsOnMethods = "UpdateBooking")
    public void PatchBooking() throws JsonProcessingException {
        token = getToken();
        requestSpecification.basePath(APIConstants.Update_Booking + "/" + bookingid);
        response = requestSpecification.cookie("token", token).body(payloadManager.PatchBooking()).when().patch();
        System.out.println(response.asString());


    }

    @Test(dependsOnMethods = "PatchBooking")
    public void DeleteBooking() throws JsonProcessingException {
        token = getToken();
        requestSpecification.basePath(APIConstants.Update_Booking + "/" + bookingid).cookie("token", token);
        response = requestSpecification.auth().basic("admin", "password123").when().delete();
        Assert.assertEquals(response.asString(), "Created");
        System.out.println(response.asString());

    }

    @Test(dependsOnMethods = "DeleteBooking")
    public void GetBooking() throws JsonProcessingException {
        requestSpecification.basePath(APIConstants.Update_Booking + "/" + bookingid);
        response = requestSpecification.when().get();
        ValidatableResponse validatableResponse1 = response.then().statusCode(404);

        Assert.assertEquals(response.asString(), "Not Found");
        System.out.println(response.asString());

    }
}
