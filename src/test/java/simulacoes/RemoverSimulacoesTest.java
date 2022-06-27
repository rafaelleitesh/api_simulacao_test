package simulacoes;

import clients.SimulacoesClient;
import io.restassured.response.ValidatableResponse;
import model.Simulacao;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

public class RemoverSimulacoesTest {

    SimulacoesClient simulacoesClient = new SimulacoesClient();

    @Before
    public void inicializaSimulacoesClient() {
        simulacoesClient = new SimulacoesClient();
    }

    @Test
    public void removeSimulacaoValido() {
        ValidatableResponse response = simulacoesClient.removeSimulacao(15);
        response.statusCode(is(HttpStatus.SC_OK));
    }

    @Test
    public void removeSimulacaoNaoExistente() {
        ValidatableResponse response = simulacoesClient.removeSimulacao(15);
        response.statusCode(is(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    public void removeSimulacaoComIDVazio() {
        boolean error = false;
        try {
            simulacoesClient.removeSimulacao(null);
        } catch (IllegalArgumentException exception) {
            error = true;
        }
        assertTrue(error);
    }

}
