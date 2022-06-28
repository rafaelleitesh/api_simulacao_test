package clients;

import commons.Base;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import model.Simulacao;

import static io.restassured.RestAssured.*;


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

    public ValidatableResponse alterarSimulacao(String cpf, Simulacao simulacao) {
        return
            given().
                pathParam("cpf", cpf).
                contentType(ContentType.JSON).
                body(simulacao).
            when().
                put("/v1/simulacoes/{cpf}").
            then().log().all();
    }

    public ValidatableResponse retornaSimulacao(String cpf) {
        return
            given().
                pathParam("cpf", cpf).
            when().
                get("/v1/simulacoes/{cpf}").
            then().log().all();
    }

    public ValidatableResponse removeSimulacao(long id) {
        return
            given().
                pathParam("id", id).
            when().
                delete("/v1/simulacoes/{id}").
            then().log().all();
    }

}
