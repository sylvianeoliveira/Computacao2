public class Livro extends Produto {

    private int numeroDePaginas;
    private  String trechoDestaque;
    private String autor;
    private int anoPublicacao;
    private String nome;
    private String editora;

    /**
     *
     * @param nome nome do livro
     * @param editora nome da editora
     */
    public Livro(String nome, String editora) {
        super("Livro: " + nome, null);
        this.nome = nome;
        this.editora = editora;
    }

    /**
     *
     * @return atributo numero de páginas do livro
     */
    private int numeroDePaginas() {
        return this.numeroDePaginas;
    }

    /**
     *
     * @return atributo trecho em destaque do livro
     */
    public String getTrechoEmDestaque() {
        return this.trechoDestaque;
    }

    /**
     *
     * @return atributo autor do livro
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     *
     * @return atributo ano de publicação do livro
     */
    public int getAnoDePublicacao() {
        return this.anoPublicacao;
    }
}