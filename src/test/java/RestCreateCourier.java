import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
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
    //Логинимся курьером для получения его id
    public void deleteCourier() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\"}";
        CourierId courierResponse =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier/login")
                        .as(CourierId.class);

        int courId = courierResponse.id;
        given()
                .queryParam("id", courId)
                .then().statusCode(200);
    }







    @Test
    @DisplayName("Создание курьера (курьера можно создать)") // имя теста
    @Description("Позитивный") // описание теста
    public void getMyInfoStatusCode() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\", \"firstName\": \"Иван\"}";
        Response response =
                given()
                        .body(json)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("ok", CoreMatchers.equalTo("true"))
                .and()
                .statusCode(201);
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
}
