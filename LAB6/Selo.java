import java.awt.*;

public class Selo implements Colecionavel{

    private float valorNominal;
    private String pais;
    private int posicao;
    private String urlDaImagem;

    public Selo(int posicao, String urlDaImagem, float valorNominal) {
        this.valorNominal = valorNominal;
        this.urlDaImagem = urlDaImagem;
        this.posicao = posicao;
    }

    public float getValorNominal(){
        return this.valorNominal;
    }

    public String getPais(){
        return this.pais;
    }

    @Override
    public Image getImagem() {
        return null;
    }

    @Override
    public int getPosicao() {
        return posicao;
    }
}
