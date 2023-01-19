import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName; // импорт DisplayName
import io.qameta.allure.Description; // импорт Description
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RestLoginCourierTest {

    //Тестируемый эндпойнт
    private String endpoint = "/api/v1/courier/login/";

    @Before
    public void setUp() {
        //Устанавливаем адрес сайта
        RestAssured.baseURI= "http://qa-scooter.praktikum-services.ru";

        //Создаём курьера для тестов
            String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\", \"firstName\": \"Иван\"}";

                    given()
                            .header("Content-type", "application/json")
                            .body(json)
                            .post("/api/v1/courier")
                            .then()
                    .statusCode(201);

    }


    @Test
    @DisplayName("Логин курьера (курьер может авторизоваться)") // имя теста
    @Description("успешный запрос возвращает id") // описание теста
    public void restLoginSuccess() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post(endpoint);
        response.then().assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @Test
    @DisplayName("Логин курьера (для авторизации нужно передать все обязательные поля)") // имя теста
    @Description("если какого-то поля нет, запрос возвращает ошибку") // описание теста
    public void restAllFieldRequired() {
        String json = "{\"password\": \"1234\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post(endpoint);
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(400);
    }

    @Test
    @DisplayName("Логин курьера (система вернёт ошибку, если неправильно указать логин или пароль;)") // имя теста
    @Description("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку") // описание теста
    public void restErrorPasswordFailLogin() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"qwerty\"}";
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post(endpoint);
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }



    @After
    public void deleteCourier() {
        String json = "{\"login\": \"AutoTestCourier\", \"password\": \"1234\"}";
        RestCreateCourierTest.CourierId courierResponse =
                //Логин курьером, что бы получить его id
                given()
                        .header("Content-type", "application/json")
                        .body(json)
                        .post("/api/v1/courier/login")
                        .as(RestCreateCourierTest.CourierId.class);
        //Удаление курьера через его id
        given()
                .delete("/api/v1/courier/" + courierResponse.id);
    }

    public static class CourierId {
        public int id;
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }

    }
}
