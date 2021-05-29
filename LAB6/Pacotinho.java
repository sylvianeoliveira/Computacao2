import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pacotinho<T extends Colecionavel>{

    private Repositorio repo;
    private int[] posicoesDesejadas;
    private List<T> pacote;

    public Pacotinho(Repositorio repo, int[] posicoesDesejadas) {
        if(posicoesDesejadas.length != 3){
            return;
        }
        this.posicoesDesejadas = new int[3];
        this.posicoesDesejadas = posicoesDesejadas;
        this.repo = repo;
        this.pacote = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            this.pacote.add(null);
        }
        for(int i=0; i < 3; i++){
             this.pacote.set(i, (T) this.repo.getItem(this.posicoesDesejadas[i]));
        }
    }

    /**
     * Produz um pacotinho com quantFigurinhas sorteadas aleatoriamente
     * dentre aqueles presentes no repositório informado.
     *
     * @param repo o repositório desejado
     * @param quantFigurinhas a quantidade de figurinhas a constar no pacotinho
     */
    public Pacotinho(Repositorio repo, int quantFigurinhas) {
        if(quantFigurinhas != 3){
            return;
        }
        this.posicoesDesejadas = new int[quantFigurinhas];
        this.repo = repo;
        this.pacote = new ArrayList<>(3);
        for (int i = 0; i < 3; i++) {
            this.pacote.add(null);
        }
        for(int i=0; i < 3; i++){
            Random r = new Random();
            this.posicoesDesejadas[i] = r.nextInt(200) + 1;
            this.pacote.set(i, (T) this.repo.getItem(this.posicoesDesejadas[i]));
        }
    }

    public List<T> getItens() {
        return pacote;
    }
}
