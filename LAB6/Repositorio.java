import java.util.ArrayList;
import java.util.List;

public class Repositorio {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<Colecionavel> todosOsItens;

    public Repositorio(String sufixoUrlImagens, int quantItens) {
        todosOsItens = new ArrayList<>(quantItens);
        todosOsItens.add(null);
        for (int i = 1; i <= quantItens; i++) {
            Colecionavel item = new Figurinha(i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosOsItens.add(item);
        }
    }

    public Colecionavel getItem(int posicao){
        Colecionavel item = todosOsItens.get(posicao);
        return item;
    }
    public int getTotalItens() {
        return this.todosOsItens.size() -1;
    }
}
