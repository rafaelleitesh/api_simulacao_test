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
        ValidatableResponse response = restricoesClient.retornaRestricaoCPF(cpf);
        response.statusCode(is(HttpStatus.SC_OK));
        response.body("mensagem", is("O CPF "+cpf+" possui restrição"));
    }

    @Test
    //Há uma chance muito pequena do CPF gerado coincidir com um CPF restrito, em caso de falha comparar o gerado a lista de restritos
    public void consultarCPFSemRestricao() throws Exception {
        String cpf = CPFRandom.geraCPFRandom();
        ValidatableResponse response = restricoesClient.retornaRestricaoCPF(cpf);
        response.statusCode(is(HttpStatus.SC_NO_CONTENT));
    }

    @Test
    public void consultarCPFVazio() {
        boolean error = false;
        try {
            restricoesClient.retornaRestricaoCPF(null);
        } catch (IllegalArgumentException exception) {
            error = true;
        }
        assertTrue(error);
    }

    @Test
    public void consultarCPFInvalido() {
        ValidatableResponse response = restricoesClient.retornaRestricaoCPF("xxxxxxxxxxx");
        response.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }
}
