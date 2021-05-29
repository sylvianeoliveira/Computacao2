import java.awt.*;

public class Selo implements Colecionavel{

    private float valorNominal;
    private String pais;

    public Selo(float valorNominal, String pais) {
        this.valorNominal = valorNominal;
        this.pais = pais;
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
        return 0;
    }
}
