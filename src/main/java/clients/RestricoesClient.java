package clients;

import commons.Base;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;

public class RestricoesClient {

    public RestricoesClient() {
        Base.configInicial();
    }

    public ValidatableResponse retornaRestricaoCPF (String cpf) {
        return
                given().
                    pathParam("cpf", cpf).
                when().
                    get("/v1/restricoes/{cpf}").
                then().log().all();
    }

}
