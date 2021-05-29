public class Pessoa {


    private String nome;
    private int anoNascimento;

    public final static int TAMANHO_MAXIMO_DO_NOME = 30;

    public Pessoa(String nome, int anoNascimento){
        this.nome = nome;
        this.anoNascimento = anoNascimento;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        if (nome.length() > TAMANHO_MAXIMO_DO_NOME) {
            // ToDo: lançar uma exceção!!!
            return;
        }

        this.nome = nome;
    }

    public void setAnoNascimento(int anoNascimento) {
        if(anoNascimento > Siguinha.obterAnoCorrente()){
            return;
        }
        this.anoNascimento = anoNascimento;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public int getIdade() {
        return Siguinha.obterAnoCorrente() - anoNascimento;
    }

}