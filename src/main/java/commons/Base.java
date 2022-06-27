package commons;

import static io.restassured.RestAssured.*;

public class Base {
    public static void configInicial() {
        baseURI = "http://localhost";
        basePath = "/api";
        port = 8080;
    }
}
