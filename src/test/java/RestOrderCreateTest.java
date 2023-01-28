import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import requests.BaseTest;
import requests.OrderApi;

@RunWith(Parameterized.class)
public class RestOrderCreateTest extends BaseTest {

        private final String firstNameSet;
        private final String lastNameSet;
        private final String addressSet;
        private final String metroStationSet;
        private final String phoneSet;
        private final String rentTimeSet;
        private final String deliveryDateSet;
        private final String commentSet;
        private final String colorSet;

        public RestOrderCreateTest(String firstNameSet, String lastNameSet, String addressSet, String metroStationSet, String phoneSet, String rentTimeSet, String deliveryDateSet, String commentSet, String colorSet) {
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

    @Test
    @DisplayName("Создание заказа (Параметризованный)") // имя теста
    @Description("Параметры учитывают все варианты из задания") // описание теста
    public void restCreateOrder() {
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

        //Отправляем запрос
                Response response = OrderApi.createOrder(json);
        //Проверяем, что в ответе содержится track и оно не пустое.
        response
                .then().assertThat().body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED); //Проверяем, что код ответа = 201

    }



}
