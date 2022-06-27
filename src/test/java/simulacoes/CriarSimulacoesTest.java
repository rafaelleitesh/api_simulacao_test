package simulacoes;


import clients.SimulacoesClient;
import io.restassured.response.ValidatableResponse;
import model.Simulacao;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class CriarSimulacoesTest {

    SimulacoesClient simulacoesClient = new SimulacoesClient();

    @Before
    public void inicializaSimulacoesClient() {
        simulacoesClient = new SimulacoesClient();
    }

    @Test
    public void criarNovaSimulacaoValida() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678906", "rafael@email.com", 1000, 2, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_CREATED));
        response.body("nome", is("Rafael"));
        response.body("cpf", is("12345678906"));
        response.body("email", is("rafael@email.com"));
        response.body("valor", is(1000));
        response.body("parcelas", is(2));
        response.body("seguro", is(true));
    }

    @Test
    public void criarNovaSimulacaoComCPFJaCadastrado() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678904", "rafael@email.com", 20000, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("mensagem", is("CPF duplicado"));
    }

    @Test
    public void criarNovaSimulacaoComCPFVazio() {
        Simulacao simulacao = new Simulacao
                ("Rafael", null, "rafael@email.com", 20000, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.cpf", is("CPF não pode ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComCPFInvalido() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "XXXXX12345", "rafael@email.com", 20000, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }

    @Test
    public void criarNovaSimulacaoComNomeVazio() {
        Simulacao simulacao = new Simulacao
                (null, "12345678907", "rafael@email.com", 20000, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.nome", is("Nome não pode ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComNomeInvalido() {
        Simulacao simulacao = new Simulacao
                ("123154335 1235235", "12345678908", "rafael@email.com", 20000, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_NOT_ACCEPTABLE));
    }

    @Test
    public void criarNovaSimulacaoComEmailVazio() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678907", null, 20000, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.email", is("E-mail não deve ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComEmailInvalido() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678907", "emailinvalido", 20000, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.email", is("E-mail deve ser um e-mail válido"));
    }

    @Test
    public void criarNovaSimulacaoComValorVazio() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678907", "rafael@email.com", null, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.valor", is("Valor não pode ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComValorMenor() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678908", "rafael@email.com", 999, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.valor", is("Valor deve ser maior ou igual a R$ 1.000"));
    }

    @Test
    public void criarNovaSimulacaoComValorMaior() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678908", "rafael@email.com", 40001, 26, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.valor", is("Valor deve ser menor ou igual a R$ 40.000"));
    }

    @Test
    public void criarNovaSimulacaoComParcelaVazio() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678908", "rafael@email.com", 20000, null, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.parcelas", is("Parcelas não pode ser vazio"));
    }

    @Test
    public void criarNovaSimulacaoComParcelaMenor() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678908", "rafael@email.com", 20000, 1, true);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.parcelas", is("Parcelas deve ser igual ou maior que 2"));
    }

    @Test
    public void criarNovaSimulacaoComParcelaMaior() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678909", "rafael@email.com", 20000, 49, false);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.parcelas", is("Parcelas deve ser menor ou igual a 48"));
    }

    @Test

    // Não sei como enviar null para a entidade sem que ela reconheça o parâmetro como false!
    // Tentei criar um construtor sem o parâmetro seguro (deixando-o vazio) e também reconheceu como false!

    public void criarNovaSimulacaoComSeguroVazio() {
        Simulacao simulacao = new Simulacao
                ("Rafael", "12345678912", "rafael@email.com", 20000, 48, null);
        ValidatableResponse response = simulacoesClient.criarSimulacao(simulacao);
        response.statusCode(is(HttpStatus.SC_BAD_REQUEST));
        response.body("erros.seguro", is("Uma das opções de Seguro devem ser selecionadas"));
    }

}
