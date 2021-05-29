import java.util.ArrayList;
import java.util.Objects;

public class Aluno extends Pessoa {

    private long dre;
    private float cra;
    private float numeradorCra;
    private float denominadorCra;
    private int creditosAcumulados;
    private Periodo periodoIngresso;
    private ArrayList<ItemHistorico> historico;

    public Aluno() {
        this(0, "Sem Nome");
    }

    public Aluno(long dre, String nome) {
        super(nome, 0);

        this.dre = dre;
        this.historico = new ArrayList<>();
        this.periodoIngresso = Siguinha.obterPeriodoCorrente();

        this.cra = 0;
        this.numeradorCra = 0;
        this.denominadorCra = 0;

        this.creditosAcumulados = 0;
    }

    public float getCra() {
        return cra;
    }

    public int getCreditosAcumulados() {
        return creditosAcumulados;
    }

    public long getDre() {
        return dre;
    }

    public void inserirItemHistorico(Disciplina disciplina, float mediaFinal) {
        Periodo periodoCorrente = Siguinha.obterPeriodoCorrente();
        inserirItemHistorico(disciplina, mediaFinal, periodoCorrente);
    }

    public void inserirItemHistorico(
            Disciplina disciplina, float mediaFinal, Periodo periodo) {

        boolean disciplinaJaExistenteNoPeriodo = false;

        // verifica se já existe no histórico essa disciplina nesse período
        for (ItemHistorico itemHistorico : this.historico) {

            if (itemHistorico == null) {
                break;
            }

            if (itemHistorico.disciplinaCursada == disciplina &&
                    itemHistorico.periodo == periodo) {

                disciplinaJaExistenteNoPeriodo = true;

                int creditosDaDisciplina = itemHistorico.disciplinaCursada.getCreditos();
                this.numeradorCra -= itemHistorico.mediaFinal * creditosDaDisciplina;
                if (itemHistorico.mediaFinal >= Siguinha.MEDIA_MINIMA_PARA_APROVACAO) {
                    this.creditosAcumulados -= creditosDaDisciplina;
                }

                itemHistorico.mediaFinal = mediaFinal;
            }
        }

        if (!disciplinaJaExistenteNoPeriodo) {
            // inserir item no histórico

            ItemHistorico novoItem = new ItemHistorico(
                    disciplina, mediaFinal, periodo);
            this.historico.add(novoItem);
        }

        // atualizar creditos
        if (mediaFinal >= Siguinha.MEDIA_MINIMA_PARA_APROVACAO) {
            this.creditosAcumulados += disciplina.getCreditos();
        }

        // outro jeito de atualizar o CRA (melhor performance)
        this.numeradorCra += mediaFinal * disciplina.getCreditos();
        this.denominadorCra += disciplina.getCreditos();
        this.cra = this.numeradorCra / this.denominadorCra;
    }

    public String getHistoricoParaImpressao() {
        String resultado = "Aluno(a): " + this.getNome() +
                " (DRE: " + this.dre + ")\n";
        for (int i = 0; i < this.historico.size(); i++) {
            ItemHistorico itemHistorico = this.historico.get(i);
            resultado += itemHistorico.periodo.getAno();
            resultado += ".";
            resultado += itemHistorico.periodo.getSemestre();
            resultado += " - ";
            resultado += itemHistorico.disciplinaCursada.getNome();
            resultado += " - ";
            resultado += String.format("%.1f", itemHistorico.mediaFinal);
            if (i != this.historico.size() - 1) {  // se não é o último item...
                resultado += "\n";
            }
        }
        return resultado;
    }

    /**
     * Obtem a média final de um aluno na disciplina e periodo desejado.
     * @param disciplina
     * @param periodo
     * @return mediafinal ou -1 se o aluno não possui media final(Não cursou a disciplina).
     */
    public float obterMedia(Disciplina disciplina, Periodo periodo) {
        for (ItemHistorico itemHistorico : this.historico) {
            if (itemHistorico == null) {
                break;
            }
            if(itemHistorico.disciplinaCursada.equals(disciplina)
                    && itemHistorico.periodo.equals(periodo)){
                return itemHistorico.mediaFinal;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aluno aluno = (Aluno) o;
        return dre == aluno.dre &&
                Objects.equals(this.getNome(), aluno.getNome());
    }

    // inner class (classe auxiliar, visível apenas de dentro da classe Aluno)
    private class ItemHistorico {

        public Disciplina disciplinaCursada;

        float mediaFinal;

        Periodo periodo;

        ItemHistorico(Disciplina disciplinaCursada, float mediaFinal, Periodo periodo) {
            this.disciplinaCursada = disciplinaCursada;
            this.mediaFinal = mediaFinal;
            this.periodo = periodo;
        }
    }
}