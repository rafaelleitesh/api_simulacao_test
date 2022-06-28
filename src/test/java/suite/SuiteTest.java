package suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import restricoes.RestricoesTest;
import simulacoes.AlterarSimulacoesTest;
import simulacoes.ConsultarSimulacoesTest;
import simulacoes.CriarSimulacoesTest;
import simulacoes.RemoverSimulacoesTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({RestricoesTest.class, ConsultarSimulacoesTest.class,
                    CriarSimulacoesTest.class, AlterarSimulacoesTest.class, RemoverSimulacoesTest.class})
public class SuiteTest {
}
