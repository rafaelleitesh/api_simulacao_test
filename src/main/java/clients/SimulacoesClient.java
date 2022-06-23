package clients;

import commons.Base;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SimulacoesClient {

    public SimulacoesClient() {
        Base.configInicial();
    }

}
