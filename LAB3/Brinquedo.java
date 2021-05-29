public class Brinquedo extends Produto {

    private String marca;
    private int idadeMinima;

    /**
     *
     * @param descricao descrição do brinquedo
     */
    public Brinquedo(String descricao) {
        super("Brinquedo: " + descricao, null);
    }

    /**
     *
     * @return atributo marca
     */
    public String getMarca() {
        return this.marca;
    }

    /**
     *
     * @return atributo idade mínima
     */
    public int getIdadeMinimaRecomendada() {
        return this.idadeMinima;
    }
}