import java.util.HashMap;
import java.util.Map;

public class Turma {

    private Disciplina disciplina;
    private Periodo periodo;
    private Professor professor;

    private Map<Long, Aluno> alunobyDre;  // Map<Chave, Valor> valorByChave

    public Turma(Disciplina disciplina, Periodo periodo, Professor professor){
        this.disciplina = disciplina;
        this.periodo = periodo;
        this.professor = professor;
        this.alunobyDre = new HashMap<>();
    }

    /**
     * Verifica se um aluno está inscrito, ou não, em uma turma
     * @param aluno
     * @return 1 se o aluno está inscrito na turma, 0 se não.
     */
    public boolean verficarAlunoInscrito(Aluno aluno){
        long dre = aluno.getDre();
        Aluno alunoJaCadastradoTurma = this.alunobyDre.get(dre);
        return (alunoJaCadastradoTurma != null);
    }

    /**
     * Inscreve um aluno em uma disciplina. Se o aluno já está inscrito, não faz nada.
     * @param aluno
     */
    public void inscreverAluno(Aluno aluno){
        long dre = aluno.getDre();
        if (verficarAlunoInscrito(aluno)) return;
        this.alunobyDre.put(dre, aluno);
    }

    /**
     * Atribui uma nota final a um aluno que cursou a disciplina
     * @param dre
     * @param nota
     */
    public void atribuirMediaFinal(long dre, float nota){
        Aluno aluno = this.alunobyDre.get(dre);
        if (aluno == null || !verficarAlunoInscrito(aluno)) return;
        aluno.inserirItemHistorico(this.disciplina, nota, this.periodo);
    }

    /**
     * Retorna a média final de um aluno na disciplina
     * @param dre
     * @return mediafinal, -1 se o aluno não está inscrito na turma.
     */
    public float obterMediaFinal(long dre){
        Aluno aluno = this.alunobyDre.get(dre);
        if (aluno == null || !verficarAlunoInscrito(aluno)) return -1;
        return aluno.obterMedia(this.disciplina, this.periodo);
    }

    /**
     * Retorna uma string contendo o nome dos alunos inscritos na turma e seus respectivos DRE's.
     * @return listaAlunos
     */
    public String listarAlunos(){
        String listaAlunos = "";

        for (Aluno aluno : this.alunobyDre.values()) {
            listaAlunos += aluno.getNome() + ": " + aluno.getDre() + "\n";
        }
        return listaAlunos;
    }
}