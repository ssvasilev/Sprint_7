import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName; // импорт DisplayName
import io.qameta.allure.Description; // импорт Description
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class RestGetOrdersTest {
    private String endpoint = "/api/v1/orders";

    @Before
    public void setUp() {
        //Устанавливаем адрес сайта
        RestAssured.baseURI= "http://qa-scooter.praktikum-services.ru";
}

        @Test
    @DisplayName("Список заказов (Проверь, что в тело ответа возвращается список заказов.)") // имя теста
    @Description("Используется без параметров") // описание теста
    public void restGetOrder() {
        Response response =
                //Отправляем запрос
                given()
                        .get(endpoint);
        //Проверяем, что в ответе содержится orders и оно не пустое.
        response
                .then().assertThat().body("orders", notNullValue())
                .and()
                .statusCode(200); //Проверяем, что код ответа = 200
    }

}
