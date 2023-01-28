package requests;

import io.qameta.allure.Step;
import io.restassured.response.Response;


import static io.restassured.RestAssured.given;

public class CourierApi {

    private final static String COURIER_ENDPOINT = "/api/v1/courier/";


    //Создание курьера
    @Step("Отправляем POST-запрос создания курьера")
    public static Response createCourier(String login, String password, String firstName) {
        Courier courier = new Courier(login,password,firstName);
                Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .post(COURIER_ENDPOINT);
        return  response;
    }

    //Создание без обязательного поля
    @Step("Отправляем POST-запрос создания без обязательных полей")
    public static Response createCourier(String password) {
        Courier courier = new Courier(password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .post(COURIER_ENDPOINT);
        return  response;
    }

    //Логин курьера для получения Id
    @Step("Отправляем POST-запрос авторизации пользователя для получения ID")
    public static int loginCourierId(String login, String password) {
        Courier courier = new Courier(login, password);
                CourierId courierResponse =
               //Логин курьером, что бы получить его id
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .post(COURIER_ENDPOINT + "login")
                        .as(CourierId.class);
                return courierResponse.id;
    }

    //Логин курьера
    @Step("Отправляем POST-авторизации курьера")
    public static Response loginCourier(String login, String password) {
        Courier courier = new Courier(login, password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .post(COURIER_ENDPOINT + "login");
        return response;
    }

    //Логин курьера без одного из обязательных полей
    @Step("Отправляем POST-запрос авторизации курьера без одного поля")
    public static Response loginCourierPass(String password) {
        Courier courier = new Courier(password);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(courier)
                        .post(COURIER_ENDPOINT + "login");
        return response;
    }

    //Удаление курьера
    @Step("Отправляем DELETE-запрос удаления курьера")
    public static void deleteCourier(String login, String password) {
        int courierId = loginCourierId(login, password);
        given()
                .delete(COURIER_ENDPOINT + courierId);

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
