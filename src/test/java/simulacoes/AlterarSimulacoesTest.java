package simulacoes;

import clients.SimulacoesClient;
import io.restassured.response.ValidatableResponse;
import model.Simulacao;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;

public class AlterarSimulacoesTest {

    SimulacoesClient simulacoesClient = new SimulacoesClient();

    @Before
    public void inicializaSimulacoesClient() {
        simulacoesClient = new SimulacoesClient();
    }

    @Test
    public void alterarSimulacaoNaoExistente() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678904", "rafael@email.com", new BigDecimal(1000), 26, true);
        ValidatableResponse response = simulacoesClient.alterarSimulacao("12345678999",simulacao);
        response.statusCode(is(HttpStatus.SC_NOT_FOUND));
        response.body("mensagem", is("CPF 12345678999 não encontrado"));
    }

    @Test
    public void alterarSimulacaoCPFComRestricao() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "97093236014", "rafael@email.com", new BigDecimal(1000), 26, true);
        ValidatableResponse response = simulacoesClient.alterarSimulacao("97093236014",simulacao);
        response.statusCode(is(HttpStatus.SC_CONFLICT));
        response.body("mensagem", is("O CPF 97093236014 possui restrição"));
    }

    @Test
    public void alterarSimulacaoCPFValido() {
        Simulacao simulacao = new Simulacao
                ("Jose", "12345678906", "jose@email.com", new BigDecimal(5000), 6, true);
        ValidatableResponse response = simulacoesClient.alterarSimulacao("12345678906", simulacao);
        response.statusCode(is(HttpStatus.SC_OK));
        response.body("nome", is("Jose"));
        response.body("cpf", is("12345678906"));
        response.body("email", is("jose@email.com"));
        response.body("valor", is(5000));
        response.body("parcelas", is(6));
        response.body("seguro", is(true));
    }

}
