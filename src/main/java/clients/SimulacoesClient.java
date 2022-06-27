package clients;

import commons.Base;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Simulacao;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SimulacoesClient {

    public SimulacoesClient() {
        Base.configInicial();
    }

    public ValidatableResponse criarSimulacao(Simulacao simulacao) {
        return
            given().
                contentType(ContentType.JSON).
                body(simulacao).
            when().
                post("/v1/simulacoes").
            then().log().all();
    }

    public Simulacao retornaSimulacao(String cpf) {
        return
            given().
                pathParam("cpf", cpf).
            when().
                get("/v1/simulacoes/{cpf}").
            then().
                extract().body().as(Simulacao.class);
    }

}
