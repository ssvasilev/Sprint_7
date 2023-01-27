import io.restassured.RestAssured;
import org.junit.Before;

import static io.restassured.RestAssured.given;

public class BaseApi {

    @Before
    public void setUp() {
        //Устанавливаем адрес сайта
        RestAssured.baseURI= "http://qa-scooter.praktikum-services.ru";
    }

}
