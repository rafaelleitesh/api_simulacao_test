package clients;

import commons.Base;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

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
                then();
    }

}
