import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Siguinha {
    public final static float MEDIA_MINIMA_PARA_APROVACAO = 5.0f;

    private static Periodo periodoCorrente = null;

    private Map<Long, Aluno> alunobyDre;  // Map<Chave, Valor> valorByChave

    String instituicaoDeEnsino;

    public Siguinha(){
        this.alunobyDre = new HashMap<>();
    }

    /**
     * Cadastra um aluno no Siguinha
     * @param dre
     * @param nome
     */
    public void cadastrarAluno(long dre, String nome){
        Aluno alunoJaCadastrado = obterAluno(dre);
        if (alunoJaCadastrado != null){
            return;
        }
        Aluno aluno = new Aluno(dre, nome);
        this.alunobyDre.put(dre, aluno);
    }

    /**
     * Dado o DRE, retorna se um aluno está cadastrado no Siga.
     * @param dre
     * @return Aluno, se cadastrado, null se não.
     */
    public Aluno obterAluno(long dre){
        return this.alunobyDre.get(dre);
    }

    public static int obterAnoCorrente() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    private static int obterSemestreCorrente() {
        return obterMesCorrente() <= 6 ? 1 : 2;
    }

    public static int obterMesCorrente() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public static Periodo obterPeriodoCorrente() {

        if (periodoCorrente != null) {
            if (periodoCorrente.getAno() != obterAnoCorrente() ||
                    periodoCorrente.getSemestre() != obterSemestreCorrente()) {
                periodoCorrente = null;  // invalida o cache
            }
        }

        if (periodoCorrente == null) {  // verifica o memo ("cache")
            // atualiza o cache
            periodoCorrente = new Periodo(obterAnoCorrente(), obterSemestreCorrente());
        }

        return periodoCorrente;
    }

    // apenas para escrever testes rápidos, por ora
    public static void main(String[] args) {

        HashMap<Integer, String> numerosPorExtenso = new HashMap<>();
        numerosPorExtenso.put(1, "um");
        numerosPorExtenso.put(2, "dois");
        numerosPorExtenso.put(3, "três");
        numerosPorExtenso.put(4, "quatro");

        System.out.println(numerosPorExtenso.get(2));
        System.out.println(numerosPorExtenso.get(600));

        // formar de iterar pelo mapa
        for (Integer chave : numerosPorExtenso.keySet()) {
            System.out.println(chave);
        }

        // formar de iterar pelo mapa
        for (String valor : numerosPorExtenso.values()) {
            System.out.println(valor);
        }

        for (Map.Entry<Integer, String> parChaveValor : numerosPorExtenso.entrySet()) {
            Integer chave = parChaveValor.getKey();
            String valor = parChaveValor.getValue();
            System.out.println(chave + " ---> " + valor);
        }

    }

}