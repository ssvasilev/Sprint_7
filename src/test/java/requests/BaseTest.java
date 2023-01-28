package requests;

import io.restassured.RestAssured;
import org.junit.Before;

import java.net.URI;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @Before
    public void setUp() {
        //Устанавливаем адрес сайта
        RestAssured.baseURI= "http://qa-scooter.praktikum-services.ru";
    }

}
