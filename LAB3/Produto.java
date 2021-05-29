import java.util.Objects;

public class Produto {

    private String descricao;
    private int peso;
    private String urlDaImagem;
    private float preco;
    private Dimensoes dimensoes;

    /**
     *
     * @param descricao
     * @param urlDaImagem
     */
    public Produto(String descricao, String urlDaImagem) {
       this.descricao = descricao;
       this.urlDaImagem = urlDaImagem;
    }

    /**
     * @return uma descrição textual do produto
     */
    public String getDescricao() {
        return this.descricao;
    }

    /**
     *
     * @return peso em gramas
     */
    public int getPesoEmGramas() {
        return this.peso;
    }

    /**
     *
     * @return dimensoes do produto
     */
    public Dimensoes getDimensoes() {
        return this.dimensoes;
    }

    /**
     *
     * @return preço do produto
     */
    public float precoEmReais() {

        return this.preco;
    }

    /**
     * Recebe o preço do produto
     * @param preco
     */
    public void setPrecoEmReais(float preco) {
        this.preco = preco;
    }

    /**
     *
     * @return url da imagem do produto
     */
    public String getUrlDaImagem() {

        return this.urlDaImagem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return Objects.equals(getDescricao(), produto.getDescricao());
    }
}