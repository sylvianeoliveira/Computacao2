import java.util.ArrayList;
import java.util.List;

/**
 * Implementa uma loja virtual para produtos de qualquer tipo,
 * desde que tenham descrição, preço e dimensões.
 *
 * Essa classe será um singleton, isto é, permitiremos apenas
 * uma instância desde objeto no sistema.
 */
public class Loja {

    private static final Loja instanciaUnica = new Loja();
    private List<Produto> estoque;
    private List<Usuario> usuarios;
    private Recibo recibo;

    static {
        System.out.println("Estou subindo a classe Loja...");
    }

    /**
     * Cria uma loja vazia
     */
    private Loja() {
        this.estoque = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    /**
     *
     * @return instanciaUnica
     */
    public static Loja getInstanciaUnica() {
        return instanciaUnica;
    }

    /**
     * Inclui no estoque da loja a quantidade informado do produto.
     *
     * @param produto o produto a ser incluído
     * @param quantidadeAIncluir a quantidade que será acrescentada à quantidade existente.
     */
    public void incluirProduto(Produto produto, int quantidadeAIncluir) {
        for(int i=0; i < quantidadeAIncluir; i++){
            this.estoque.add(produto);
        }
    }

    /**
     * Cadastra o usuário na loja
     * @param usuario
     */
    public void cadastrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    /**
     * Efetua a venda do produto desejado na quantidade especificada.
     *
     * @param produto o produto
     * @param quantidadeDesejada a quantidade
     *
     * @return um Recibo indicando a venda feita, se o produto existia (em quantidade suficiente)
     *         no estoque da loja; null, caso o usuário ou o produto sejam desconhecidos,
     *         ou não haja quantidade suficiente do produto desejado
     */
    public Recibo efetuarVenda(Produto produto, int quantidadeDesejada, Usuario usuario) {
        if(this.usuarios.contains(usuario)){

            if (informarQuantidadeEmEstoque(produto) >= quantidadeDesejada){
                Recibo recibo = new Recibo(produto, quantidadeDesejada, usuario);
                for(int i=0; i < quantidadeDesejada; i++){
                    this.estoque.remove(produto);
                }
                return recibo;
            }
        }
        return null;
    }

    /**
     * @param produto o produto a ser consultado
     *
     * @return a quantidade em estoque;
     *         0 se não houver nenhuma unidade;
     *         -1 se o produto não é sequer vendido pela loja
     */
    public int informarQuantidadeEmEstoque(Produto produto) {
        if (!estoque.contains(produto)) return -1; //Produto não é vendido pela loja
        int cont = 0 ;
        for(Produto prod : this.estoque){
            if(prod.equals(produto)) cont++;
        }
        return cont; //retorna a quantidade em estoque, 0 se não houver nenhuma unidade
    }

    /**
     * Recria os atributos zerados
     */
    public void limparEstado() {
        this.estoque = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }
}