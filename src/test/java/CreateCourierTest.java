import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;
import requests.BaseTest;
import requests.CourierApi;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;




public class CreateCourierTest extends BaseTest {


    @Test
    @DisplayName("Создание курьера (курьера можно создать)") // имя теста
    @Description("успешный запрос возвращает ok: true") // описание теста
    public void restCreateCourier() {
    Response response = CourierApi.createCourier("AutoTestCourier","1234","Иван");
        response.then().assertThat().body("ok", equalTo(true))
                .and()
               .statusCode(SC_CREATED);

    }

    @Test
    @DisplayName("Создание курьера (нельзя создать двух одинаковых курьеров)") // имя теста
    @Description("Негативный") // описание теста
    public void restDoubleCreateCourierFail() {
        CourierApi.createCourier("AutoTestCourier","1234","Иван");
        Response responseSecondCourier = CourierApi.createCourier("AutoTestCourier","1234","Иван");
        responseSecondCourier.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(SC_CONFLICT);
    }

    @Test
    @DisplayName("Создание курьера (чтобы создать курьера, нужно передать в ручку все обязательные поля)") // имя теста
    @Description("если одного из полей нет, запрос возвращает ошибку") // описание теста
    public void restAllFieldsAreRequired() {
        Response response = CourierApi.createCourier("Иван");
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @Test
    @DisplayName("Создание курьера (если создать пользователя с логином, который уже есть, возвращается ошибка.)") // имя теста
    @Description("Негативный") // описание теста
    public void restFailCreateDoubleLoginCourier() {
        CourierApi.createCourier("AutoTestCourier","1234","Иван");
        Response response = CourierApi.createCourier("AutoTestCourier","qwerty","Владимир");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(SC_CONFLICT);
    }


    @After
    public void deleteTestData() {
        CourierApi.deleteCourier("AutoTestCourier", "1234");
    }
}
