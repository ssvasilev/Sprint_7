import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName; // импорт DisplayName
import io.qameta.allure.Description; // импорт Description
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class RestCreateCourier {


    @Before
    public void setUp() {

        RestAssured.baseURI= "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание курьера (курьера можно создать)") // имя теста
    @Description("успешный запрос возвращает ok: true") // описание теста
    public void restCreateCourier() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\", \"firstName\": \"Иван\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @Test
    @DisplayName("Создание курьера (нельзя создать двух одинаковых курьеров)") // имя теста
    @Description("Негативный") // описание теста
    public void restDoubleCreateCourierFail() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\", \"firstName\": \"Иван\"}";
        Response responseFirstCourier =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier");
        responseFirstCourier.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
        Response responseSecondCourier =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier");
        responseSecondCourier.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @Test
    @DisplayName("Создание курьера (чтобы создать курьера, нужно передать в ручку все обязательные поля)") // имя теста
    @Description("если одного из полей нет, запрос возвращает ошибку") // описание теста
    public void restAllFieldsAreRequired() {
        String json = "{\"login\": \"AutoTestCourier\", \"firstName\": \"Иван\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Создание курьера (если создать пользователя с логином, который уже есть, возвращается ошибка.)") // имя теста
    @Description("Негативный") // описание теста
    public void restFailCreateDoubleLoginCourier() {
        String jsonFirstCourier = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\", \"firstName\": \"Иван\"}";
                given()
                        .header("Content-type", "application/json")
                        .body(jsonFirstCourier)
                        .post("/api/v1/courier");

        String jsonSecondCourier = "{\"login\": \"AutoTestCourier\", \"password\": \"qwerty\", \"firstName\": \"Владимир\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(jsonSecondCourier)
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @After
    public void deleteCourier() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\"}";
        CourierId courierResponse =
                //Логин курьером, что бы получить его id
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier/login")
                        .as(CourierId.class);
        //Удаление курьера через его id
        given()
                .delete("/api/v1/courier/" + courierResponse.id);
    }

    public static class CourierId {
        private int id;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

    }
}
