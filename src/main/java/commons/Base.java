package commons;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Base {
    public static void configInicial() {
        baseURI = "http://localhost";
        basePath = "/api";
        port = 8080;
    }
}
