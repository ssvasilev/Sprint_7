package requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {

    private final static String ORDER_ENDPOINT = "/api/v1/orders";
@Step("Отправляем POST-запрос создания заказа")
    public static Response createOrder(Order body) {
        Response response =
                given().log().all()
                        .header("Content-type", "application/json")
                        .body(body)
                        .post(ORDER_ENDPOINT);
        return response;
    }
    @Step("Отправляем GET-запрос получения списка заказов")
    public static Response getOrder() {
        Response response =
                given()
                        .get(ORDER_ENDPOINT);
        return response;
    }

}
