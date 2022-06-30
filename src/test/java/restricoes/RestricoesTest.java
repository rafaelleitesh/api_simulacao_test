package restricoes;

import clients.RestricoesClient;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import utils.CPFRandom;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

public class RestricoesTest {

    RestricoesClient restricoesClient;

    @Before
    public void inicializaRestricoesClient() {
        restricoesClient = new RestricoesClient();
    }

    @Test
    public void consultarCPFComRestricao() {
        String cpf = CPFRandom.geraCPFComRestricaoRandom();
        ValidatableResponse resposta = restricoesClient.retornaRestricaoCPF(cpf);
        resposta.statusCode(is(HttpStatus.SC_OK));
        resposta.body("mensagem", is("O CPF " + cpf + " possui restrição"));
    }

    @Test
    public void consultarCPFSemRestricao() throws Exception {
        String cpf = CPFRandom.geraCPFRandom();
        ValidatableResponse resposta = restricoesClient.retornaRestricaoCPF(cpf);
        resposta.statusCode(is(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarCPFVazio() {
        boolean erro = false;
        try {
            restricoesClient.retornaRestricaoCPF(null);
        } catch (IllegalArgumentException exception) {
            erro = true;
        }
        assertTrue(erro);
    }

    @Test
    public void consultarCPFInvalido() {
        ValidatableResponse response = restricoesClient.retornaRestricaoCPF("xxxxxxxxxxx");
        response.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }
}
