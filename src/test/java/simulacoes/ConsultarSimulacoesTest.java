package simulacoes;

import clients.SimulacoesClient;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

public class ConsultarSimulacoesTest {

    SimulacoesClient simulacoesClient = new SimulacoesClient();

    @Before
    public void inicializaSimulacoesClient() {
        simulacoesClient = new SimulacoesClient();
    }

    @Test
    public void retornaTodasAsSimulacoes() {
        given().
        when().
            get("/v1/simulacoes").
        then().log().all().
            assertThat().body("size()", is(19));
    }

    @Test
    public void retornaSimulacaoPeloCPFValido() {
        ValidatableResponse response = simulacoesClient.retornaSimulacao("17822386034");
        response.statusCode(is(HttpStatus.SC_OK));
        response.body("nome", is("Deltrano"));
        response.body("cpf", is("17822386034"));
        response.body("email", is("deltrano@gmail.com"));
        response.body("valor", is(20000.0F));
        response.body("parcelas", is(5));
        response.body("seguro", is(false));
    }

    @Test
    public void retornaSimulacaoPeloCPFNaoCadastrado() {
        ValidatableResponse response = simulacoesClient.retornaSimulacao("60094146012");
        response.statusCode(is(HttpStatus.SC_NOT_FOUND));
        response.body("mensagem", is("CPF 60094146012 não encontrado"));
    }

    @Test
    public void retornaSimulacaoPeloCPFVazio() {
        boolean error = false;
        try {
            simulacoesClient.retornaSimulacao(null);
        } catch (IllegalArgumentException exception) {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void retornaSimulacaoPeloCPFInvalido() {
        ValidatableResponse response = simulacoesClient.retornaSimulacao("XXXXXXXXXXX");
        response.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }

}
