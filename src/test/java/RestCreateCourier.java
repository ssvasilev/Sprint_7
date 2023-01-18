import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName; // импорт DisplayName
import io.qameta.allure.Description; // импорт Description

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RestCreateCourier {

    String bearerToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI2Mzc3NzQwMjJkMTM2ZDAwM2RkODBkNTciLCJpYXQiOjE2NzM3ODE3NDcsImV4cCI6MTY3NDM4NjU0N30.ODkAuVhkGSLxdtW5_NiBvXbJiDLDUseIHp7rfZ-6XgA";

    @Before
    public void setUp() {

        RestAssured.baseURI= "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание курьера (курьера можно создать)") // имя теста
    @Description("Позитивный") // описание теста
    public void getMyInfoStatusCode() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\", \"firstName\": \"Иван\"}";
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier")
                .then().statusCode(201);
    }


    public class CourierId {
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

    }

    @After
    public void deleteCourier() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\"}";
        CourierId courierResponse =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier/login")
                        .as(CourierId.class);

        given()
                //.queryParam("id", "144671")
                .delete("/api/v1/courier/"+courierResponse.id)
                .then().statusCode(200);
    }
}
