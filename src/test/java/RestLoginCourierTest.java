import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName; // импорт DisplayName
import io.qameta.allure.Description; // импорт Description
import requests.BaseTest;
import requests.CourierApi;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RestLoginCourierTest extends BaseTest {


    @Before
    public void createTestData() {

        //Создаём курьера для тестов
            CourierApi.createCourier("AutoTestCourier","1234", "Иван");
    }


    @Test
    @DisplayName("Логин курьера (курьер может авторизоваться)") // имя теста
    @Description("успешный запрос возвращает id") // описание теста
    public void restLoginSuccess() {
        Response response = CourierApi.loginCourier("AutoTestCourier","1234");
        response.then().assertThat()
                .body("id", notNullValue())
                .and()
                .statusCode(SC_OK);
    }

    @Test
    @DisplayName("Логин курьера (для авторизации нужно передать все обязательные поля)") // имя теста
    @Description("если какого-то поля нет, запрос возвращает ошибку") // описание теста
    public void restAllFieldRequired() {
        Response response = CourierApi.loginCourierPass("1234");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Логин курьера (система вернёт ошибку, если неправильно указать логин или пароль;)") // имя теста
    @Description("если авторизоваться под несуществующим пользователем, запрос возвращает ошибку") // описание теста
    public void restErrorPasswordFailLogin() {
        Response response = CourierApi.loginCourier("AutoTestCourier","qwerty");
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }

   @After
    public void deleteTestData() {
        CourierApi.deleteCourier("AutoTestCourier", "1234");
    }


}
