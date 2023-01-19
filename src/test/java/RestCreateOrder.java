import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class RestCreateOrder {
    //Тестируемый эндпойнт
    public String endpoint = "/api/v1/orders";

        private final String firstNameSet;
        private final String lastNameSet;
        private final String addressSet;
        private final String metroStationSet;
        private final String phoneSet;
        private final String rentTimeSet;
        private final String deliveryDateSet;
        private final String commentSet;
        private final String colorSet;

        public RestCreateOrder(String firstNameSet, String lastNameSet, String addressSet, String metroStationSet, String phoneSet, String rentTimeSet, String deliveryDateSet, String commentSet, String colorSet) {
            this.firstNameSet = firstNameSet;
            this.lastNameSet = lastNameSet;
            this.addressSet = addressSet;
            this.metroStationSet = metroStationSet;
            this.phoneSet = phoneSet;
            this.rentTimeSet = rentTimeSet;
            this.deliveryDateSet = deliveryDateSet;
            this.commentSet = commentSet;
            this.colorSet = colorSet;
        }

        // Тестовые данные
        @Parameterized.Parameters
        public static Object[][] getDataOrder() {
            return new Object[][]{
                    {"Никита", "Комбаров", "Комсомольская ул., д. 6 кв.152", "Черкизовская", "89888678901", "1", "30.11.2022", "Прикрепить бантик на руль", "[\"black\"]"},
                    {"Пётр", "Петрович", "Чкалова ул., д. 15 кв.55", "Китай-город", "89881235897", "2", "22.08.2023", "Оставить у двери", "[\"grey\"]"},
                    {"Леонид", "Ксенофонтов", "Вокзальная ул., д. 19 кв.170", "Речной вокзал", "89882836886", "3", "27.05.2024", " ", "[\"black\",\"grey\"]"},
                    {"Александр", "Пушкин", "Мира ул., д. 20 кв.35", "Комсомольская", "89889783152", "4", "13.05.2024", "Ничего не делать", "[]"},

            };
        }

    @Before
    public void setUp() {
        //Устанавливаем адрес сайта
        RestAssured.baseURI= "http://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Создание заказа (Параметризованный)") // имя теста
    @Description("Параметры учитывают все варианты из задания") // описание теста
    public void restCreateCourier() {
        //Собираем из тестовых данных JSON, который послужит телом для запроса.
        String json = "{" +
                "\"firstName\":\"" + firstNameSet + "\"," +
                "\"lastName\":\"" + lastNameSet + "\"," +
                "\"address\":\"" + addressSet + "\"," +
                "\"metroStation\":\"" + metroStationSet + "\"," +
                "\"phone\":" + phoneSet + "," +
                "\"rentTime\":" + rentTimeSet + "," +
                "\"deliveryDate\":\"" + deliveryDateSet + "\"," +
                "\"comment\":\"" + commentSet + "\"," +
                "\"color\":" + colorSet +
                "}";
        Response response =
                //Отправляем запрос
                given()
                        .header("Content-type", "text/plain")
                        .body(json)
                        .post(endpoint);
        //Проверяем, что в ответе содержится track и оно не пустое.
        response
                .then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(201); //Проверяем, что код ответа = 201
    }

}
