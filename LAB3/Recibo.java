public class Recibo {

    private Produto produto;
    private int quantidadeDesejada;
    private Usuario comprador;
    private float valorTotal;

    /**
     *
     * @param produto
     * @param quantidadeDesejada
     * @param comprador
     */
    public Recibo(Produto produto, int quantidadeDesejada, Usuario comprador){
        this.produto = produto;
        this.quantidadeDesejada = quantidadeDesejada;
        this.comprador = comprador;
        this.valorTotal = this.produto.precoEmReais() * this.quantidadeDesejada;
    }

    /**
     *
     * @return valor total da compra
     */
    public float getValorTotalDaCompra() {
        return this.valorTotal;
    }

    /**
     *
     * @return comprador
     */
    public Usuario getUsuario() {
        return comprador;
    }

    @Override
    public String toString(){
        String mensagem = String.format("Recibo no valor de R$%.2f para %s referente à compra de %d unidades de %s", this.valorTotal, this.comprador.getNome(), this.quantidadeDesejada, this.produto.getDescricao());
        return mensagem;
    }
}