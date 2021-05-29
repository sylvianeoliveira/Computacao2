public class Dimensoes {

    private int comprimento;
    private int largura;
    private int altura;
    private int volume;

    /**
     * @return atributo comprimento em centímetros
     */
    public int getComprimentoEmCentimetros() {
        return this.comprimento;
    }

    /**
     * @return atributo largura em centímetros
     */
    public int getLarguraEmCentimetros() {
        return this.largura;
    }

    /**
     * @return atributo altura em centímetros
     */
    public int getAlturaEmCentimetros() {
        return this.altura;
    }

    /**
     * @return atributo volume em centímetros
     */
    public int getVolumeEmCentimetrosCubicos() {
        volume = comprimento * largura * altura;
        return this.volume;
    }
}