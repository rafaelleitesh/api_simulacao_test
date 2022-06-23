package restricoes;

import clients.RestricoesClient;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class RestricoesTest {

    RestricoesClient restricoesClient;

    @Before
    public void inicializaClient() {
        restricoesClient = new RestricoesClient();
    }

    @Test
    public void consultarCPFComRestricao() {
        ValidatableResponse response = restricoesClient.retornaRestricaoCPF("97093236014");
        response.statusCode(is(HttpStatus.SC_OK));
        response.body("mensagem", is("O CPF 97093236014 possui restrição"));
    }

    @Test
    public void consultarCPFSemRestricao() {
        ValidatableResponse response = restricoesClient.retornaRestricaoCPF("99999999999");
        response.statusCode(is(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarCPFVazio() {
        ValidatableResponse response = restricoesClient.retornaRestricaoCPF("");
        response.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }

    @Test
    public void consultarCPFInvalido() {
        ValidatableResponse response = restricoesClient.retornaRestricaoCPF("xxxxxxxxxxx");
        response.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }
}
