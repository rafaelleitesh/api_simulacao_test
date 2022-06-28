package simulacoes;

import clients.SimulacoesClient;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
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
        ValidatableResponse response = simulacoesClient.removeSimulacao(17);
        response.statusCode(is(HttpStatus.SC_OK));
    }

    @Test
    @Ignore
    public void removeSimulacaoNaoExistente() {
        ValidatableResponse response = simulacoesClient.removeSimulacao(10);
        response.statusCode(is(HttpStatus.SC_NOT_FOUND));
    }

    @Test
    @Ignore
    //Alterar parametro para null quando corrigido id para cpf
    public void removeSimulacaoComIDVazio() {
        boolean error = false;
        try {
            simulacoesClient.removeSimulacao(1);
        } catch (IllegalArgumentException exception) {
            error = true;
        }
        assertTrue(error);
    }

}
