package simulacoes;


import clients.SimulacoesClient;
import io.restassured.response.ValidatableResponse;
import model.Simulacao;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;

public class CriarSimulacoesTest {

    SimulacoesClient simulacoesClient = new SimulacoesClient();

    @Before
    public void inicializaSimulacoesClient() {
        simulacoesClient = new SimulacoesClient();
    }

    @Test
    public void criarNovaSimulacaoValidaValorMinETrue() throws Exception {
        Simulacao simulacao = new Simulacao
                (new BigDecimal(1000), 2, true);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_CREATED));
        resposta.body("nome", is(simulacao.getNome()));
        resposta.body("cpf", is(simulacao.getCpf()));
        resposta.body("email", is(simulacao.getEmail()));
        resposta.body("valor", is(1000));
        resposta.body("parcelas", is(simulacao.getParcelas()));
        resposta.body("seguro", is(simulacao.getSeguro()));

        simulacoesClient.removeSimulacao(resposta.extract().jsonPath().getLong("id"));
    }

    @Test
    public void criarNovaSimulacaoValidaValorMaxEFalse() throws Exception {
        Simulacao simulacao = new Simulacao
                (new BigDecimal(40000), 48, false);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_CREATED));
        resposta.body("nome", is(simulacao.getNome()));
        resposta.body("cpf", is(simulacao.getCpf()));
        resposta.body("email", is(simulacao.getEmail()));
        resposta.body("valor", is(40000));
        resposta.body("parcelas", is(simulacao.getParcelas()));
        resposta.body("seguro", is(simulacao.getSeguro()));

        simulacoesClient.removeSimulacao(resposta.extract().jsonPath().getLong("id"));
    }

    @Test
    public void criarNovaSimulacaoComCPFJaCadastrado() throws Exception {
        Simulacao simulacao = new Simulacao();
        ValidatableResponse resposta1 =simulacoesClient.criarSimulacao(simulacao);
        ValidatableResponse resposta2 = simulacoesClient.criarSimulacao(simulacao);
        resposta2.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta2.body("mensagem", is("CPF duplicado"));

        simulacoesClient.removeSimulacao(resposta1.extract().jsonPath().getLong("id"));
    }

    @Test
    public void criarNovaSimulacaoComCPFVazio() {
        Simulacao simulacao = new Simulacao
                ("Vazio", null, "vazio@email.com");
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.cpf", is("CPF não pode ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComCPFInvalido() {
        Simulacao simulacao = new Simulacao
                ("Invalido", "XXXXX12345", "invalido@email.com");
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }

    @Test
    public void criarNovaSimulacaoComNomeVazio() {
        Simulacao simulacao = new Simulacao
                (null, "99999999999", "nomevazio@email.com");
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.nome", is("Nome não pode ser vazio"));
    }

    @Test
    @Ignore
    public void criarNovaSimulacaoComNomeInvalido() {
        Simulacao simulacao = new Simulacao
                ("123154335 1235235", "99999999999", "nomeinvalido@email.com");
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }

    @Test
    public void criarNovaSimulacaoComEmailVazio() {
        Simulacao simulacao = new Simulacao
                ("Email Vazio", "99999999999", null);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.email", is("E-mail não deve ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComEmailInvalido() {
        Simulacao simulacao = new Simulacao
                ("Email Invalido", "99999999999", "emailinvalido");
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.email", is("E-mail deve ser um e-mail válido"));
    }

    @Test
    public void criarNovaSimulacaoComValorVazio() throws Exception {
        Simulacao simulacao = new Simulacao
                (null, 26, true);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.valor", is("Valor não pode ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComValorMenor() throws Exception {
        Simulacao simulacao = new Simulacao
                (new BigDecimal(999), 26, true);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.valor", is("Valor deve ser maior ou igual a R$ 1.000"));
    }

    @Test
    public void criarNovaSimulacaoComValorMaior() throws Exception {
        Simulacao simulacao = new Simulacao
                (new BigDecimal(40000.01), 26, true);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.valor", is("Valor deve ser menor ou igual a R$ 40.000"));
    }

    @Test
    public void criarNovaSimulacaoComParcelaVazio() throws Exception {
        Simulacao simulacao = new Simulacao
                (new BigDecimal(20000), null, true);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.parcelas", is("Parcelas não pode ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComParcelaMenor() throws Exception {
        Simulacao simulacao = new Simulacao
                (new BigDecimal(20000), 1, true);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"));
    }

    @Test
    public void criarNovaSimulacaoComParcelaMaior() throws Exception {
        Simulacao simulacao = new Simulacao
                (new BigDecimal(20000), 49, false);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.parcelas", is("Parcelas deve ser menor ou igual a 48"));
    }

    @Test
    @Ignore
    public void criarNovaSimulacaoComSeguroVazio() throws Exception {
        Simulacao simulacao = new Simulacao
                (new BigDecimal(20000), 26, null);
        ValidatableResponse resposta = simulacoesClient.criarSimulacao(simulacao);
        resposta.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        resposta.body("erros.seguro", is("Uma das opções de Seguro devem ser selecionadas"));
    }

}
