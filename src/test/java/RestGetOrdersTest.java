import io.restassured.response.Response;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName; // импорт DisplayName
import io.qameta.allure.Description; // импорт Description
import requests.BaseTest;
import requests.OrderApi;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RestGetOrdersTest extends BaseTest {


        @Test
    @DisplayName("Список заказов (Проверь, что в тело ответа возвращается список заказов.)") // имя теста
    @Description("Используется без параметров") // описание теста
    public void restGetOrder() {
        Response response = OrderApi.getOrder();
        //Проверяем, что в ответе содержится orders и оно не пустое.
        response
                .then().assertThat().body("orders", notNullValue())
                .and()
                .statusCode(SC_OK); //Проверяем, что код ответа = 200
    }

}
