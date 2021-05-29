import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.*;

public class AlunoTest {

    private static final float MAX_FLOAT_DIFF = 0.000001f;

    private Aluno fulana;
    private Aluno fulano;
    private Aluno sicrano;
    private Disciplina algGraf;
    private Disciplina calculo1;
    private Periodo periodo20201;
    private Periodo periodo20202;

    private DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getInstance();
    private char decimalSeparator = decimalFormat.getDecimalFormatSymbols().getDecimalSeparator();

    @Before
    public void setUp() {
        fulana = new Aluno(1234, "Fulana de Tal");
        fulano = new Aluno(5678, "Fulaninho");
        sicrano = new Aluno(9090, "Sicraninho");

        algGraf = new Disciplina("Algoritmos e Grafos", 4, "MAB704");
        calculo1 = new Disciplina("Calculo 1", 4, "MAB704");
        periodo20201 = new Periodo(2020, 1);
        periodo20202 = new Periodo(2020, 2);
    }

    @Test
    public void testarAtualizacaoCraComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        assertEquals("O CRA deve refletir a média ponderada das notas finais já obtidas",
                10, fulana.getCra(), MAX_FLOAT_DIFF);
    }

    @Test
    public void testarAtualizacaoCreditosAcumuladosComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        assertEquals("A quantidade de créditos acumulados deve refletir o somátorio " +
                        "dos créditos das disciplinas em que houve aprovação",
                4, fulana.getCreditosAcumulados());
    }

    @Test
    public void testarAtualizacaoHistoricoComAprovacoes() {
        fulana.inserirItemHistorico(algGraf, 10, periodo20201);
        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Algoritmos e Grafos - 10" + decimalSeparator + "0";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarAtualizacaoCraComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        assertEquals("O CRA deve refletir a média ponderada das notas finais já obtidas",
                3, fulana.getCra(), MAX_FLOAT_DIFF);
    }

    @Test
    public void testarAtualizacaoCreditosAcumuladosComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        assertEquals("A quantidade de créditos acumulados deve refletir o somátorio " +
                        "dos créditos APENAS das disciplinas em que houve aprovação",
                0, fulana.getCreditosAcumulados());
    }

    @Test
    public void testarAtualizacaoHistoricoComReprovacoes() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 3" + decimalSeparator + "0";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarDisciplinasRepetidasNoMesmoPeriodo() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        fulana.inserirItemHistorico(calculo1, 4, periodo20201);
        fulana.inserirItemHistorico(calculo1, 8.5f, periodo20201);

        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 8" + decimalSeparator + "5";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());
    }

    @Test
    public void testarDisciplinasRepetidasEmPeriodosDistintos() {
        fulana.inserirItemHistorico(calculo1, 3, periodo20201);
        fulana.inserirItemHistorico(calculo1, 8.5f, periodo20202);

        String historicoDesejado = "Aluno(a): Fulana de Tal (DRE: 1234)\n" +
                "2020.1 - Calculo 1 - 3" + decimalSeparator + "0\n" +
                "2020.2 - Calculo 1 - 8" + decimalSeparator + "5";

        assertEquals("O histórico deve conter todas as disciplinas cursadas",
                historicoDesejado, fulana.getHistoricoParaImpressao());

    }

    @Test
    public void testarInsersacoDeUmNumeroMuitoGrandeDeDisciplinas() {
        for (int i = 0; i < 10_000; i++) {
            Disciplina disciplina = new Disciplina("blah" + i, 4, "MAB" + i);
            fulana.inserirItemHistorico(disciplina, 6, periodo20201);
        }
    }

    @Test
    public void cadastrarAlunoNoSiga(){
        Siguinha siga = new Siguinha();

        siga.cadastrarAluno(6543, "Maria");
        assertEquals("O aluno cadastrado neste dre deve ser Maria", new Aluno (6543, "Maria"), siga.obterAluno(6543));
    }

    @Test
    public void obterAlunoNãoCadastrado(){
        Siguinha siga = new Siguinha();
        assertEquals("Não pode haver aluno cadastrado", null, siga.obterAluno(1234));
    }

    @Test
    public void inscreverAlunoNaTurma(){
        Professor prof = new Professor("Stefanella", 1960, 2000);
        Turma turminha = new Turma(calculo1, periodo20201, prof);

        turminha.inscreverAluno(fulana);
        assertEquals("O aluno deve ser cadastrado", true, turminha.verficarAlunoInscrito(fulana));
    }

    @Test
    public void verificarAlunoNãoInscrito(){
        Professor prof = new Professor("Stefanella", 1960, 2000);
        Turma turminha = new Turma(calculo1, periodo20201, prof);
        assertEquals("O aluno não pode estar cadastrado", false, turminha.verficarAlunoInscrito(fulana));
    }

    @Test
    public void atribuirMediaFinal() {
        Professor prof = new Professor("Stefanella", 1960, 2000);
        Turma turminha = new Turma(calculo1, periodo20201, prof);
        turminha.inscreverAluno(fulana);

        turminha.atribuirMediaFinal(1234, 7.6f);
        assertEquals("O aluno deve ter a nota esperada", 7.6f, turminha.obterMediaFinal(1234), MAX_FLOAT_DIFF);
    }

    @Test
    public void obterMediaFinalInexistente(){
        Professor prof = new Professor("Stefanella", 1960, 2000);
        Turma turminha = new Turma(calculo1, periodo20201, prof);

        assertEquals("O aluno deve não deve possuir média final", -1, turminha.obterMediaFinal(1234),  MAX_FLOAT_DIFF);
    }

    @Test
    public void atribuirMediaAlunoNãoCadastrado(){
        Professor prof = new Professor("Stefanella", 1960, 2000);
        Turma turminha = new Turma(calculo1, periodo20201, prof);

        turminha.atribuirMediaFinal(1234, 7.6f);
        assertEquals("O aluno deve estar cadastrado na turma", -1, turminha.obterMediaFinal(1234), MAX_FLOAT_DIFF);
    }

    /**
     * Este teste só funciona caso você tenha feito sua lista no seguinte formato:
     * Aluno: dre
     * Aluno2: dre
     * Aluno3: dre
     * E ainda sim pode dar erro porque o map organiza os alunos de forma aleatória,
     * então é só ler o que aconteceu pra saber se sua lista ta certinha
     */
    @Test
    public void retornarListadeAlunos(){
        Professor prof = new Professor("Stefanella", 1960, 2000);
        Turma turminha = new Turma(calculo1, periodo20201, prof);

        turminha.inscreverAluno(fulana);
        turminha.inscreverAluno(fulano);
        turminha.inscreverAluno(sicrano);

        assertEquals("A lista de alunos inscritos na turma deve ser a mesma", "Fulana de Tal: 1234\nSicraninho: 9090\nFulaninho: 5678\n", turminha.listarAlunos());
    }

}


