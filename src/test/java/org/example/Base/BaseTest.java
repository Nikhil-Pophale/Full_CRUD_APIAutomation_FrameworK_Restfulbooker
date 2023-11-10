package org.example.Base;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.example.COMMON.APIConstants;
import org.example.Resources.PayloadManager;
import org.example.Action.AssertActions;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    public static RequestSpecification requestSpecification;
    public static AssertActions actions;
    public static PayloadManager payloadManager;
    public static JsonPath jsonPath;
    public static Response response;
    public static ValidatableResponse validatableResponse;

    @BeforeMethod(alwaysRun = true)
    public void SetUp() {
        actions = new AssertActions();
        payloadManager = new PayloadManager();


        requestSpecification = RestAssured.given()
                .baseUri(APIConstants.Base_Url)
                .contentType(ContentType.JSON);
    }

    public String getToken() throws JsonProcessingException {
        String load = payloadManager.setToken();
        requestSpecification = RestAssured.given().baseUri(APIConstants.Base_Url).basePath("/auth");
        response = requestSpecification.contentType(ContentType.JSON).when().body(load).post();
        jsonPath = new JsonPath(response.asString());
        return jsonPath.getString("token");
    }

}

