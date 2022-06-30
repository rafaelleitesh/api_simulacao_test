package simulacoes;

import clients.SimulacoesClient;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import utils.CPFRandom;


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
            assertThat().body("size()", is(2));
    }

    @Test
    public void retornaSimulacaoPeloCPFValido() {
        ValidatableResponse resposta = simulacoesClient.retornaSimulacao("17822386034");
        resposta.statusCode(is(HttpStatus.SC_OK));
        resposta.body("nome", is("Deltrano"));
        resposta.body("cpf", is("17822386034"));
        resposta.body("email", is("deltrano@gmail.com"));
        resposta.body("valor", is(20000.0F));
        resposta.body("parcelas", is(5));
        resposta.body("seguro", is(false));
    }

    @Test
    public void retornaSimulacaoPeloCPFNaoCadastrado() throws Exception {
        String cpf = CPFRandom.geraCPFRandom();
        ValidatableResponse resposta = simulacoesClient.retornaSimulacao(cpf);
        resposta.statusCode(is(HttpStatus.SC_NOT_FOUND));
        resposta.body("mensagem", is("CPF "+cpf+" não encontrado"));
    }

    @Test
    public void retornaSimulacaoPeloCPFVazio() {
        boolean erro = false;
        try {
            simulacoesClient.retornaSimulacao(null);
        } catch (IllegalArgumentException exception) {
            erro = true;
        }
        assertTrue(erro);
    }

    @Test
    public void retornaSimulacaoPeloCPFInvalido() {
        ValidatableResponse resposta = simulacoesClient.retornaSimulacao("XXXXXXXXXXX");
        resposta.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }

}
