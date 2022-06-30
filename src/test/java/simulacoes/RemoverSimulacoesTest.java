package simulacoes;

import clients.SimulacoesClient;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class RemoverSimulacoesTest {

    SimulacoesClient simulacoesClient = new SimulacoesClient();

    @Before
    public void inicializaSimulacoesClient() {
        simulacoesClient = new SimulacoesClient();
    }

    @Test
    public void removeSimulacaoValido() {
        ValidatableResponse resposta = simulacoesClient.removeSimulacao(17);
        resposta.statusCode(is(HttpStatus.SC_OK));
    }

    @Test
    @Ignore
    public void removeSimulacaoNaoExistente() {
        ValidatableResponse resposta = simulacoesClient.removeSimulacao(10);
        resposta.statusCode(is(HttpStatus.SC_NOT_FOUND));
    }

}
