public class Professor extends Pessoa{

    private int anoContratacao;

    public Professor(String nome, int anoNascimento, int anoContratacao){
        super(nome, anoNascimento);
        this.anoContratacao = anoContratacao;
    }

    public int getAnoContratacao() {
        return this.anoContratacao;
    }
}
