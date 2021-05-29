import java.util.ArrayList;
import java.util.List;

public class RepositorioNovo<T extends Colecionavel> {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<T> todosOsItens;

    @SuppressWarnings("unchecked")
    public RepositorioNovo(String sufixoUrlImagens, int quantFigurinhas, T objetoReferencia) {
        todosOsItens = new ArrayList<>(quantFigurinhas);
        for (int i = 1; i <= quantFigurinhas; i++) {
            T fig = (T) ColecionavelFactory.create(
                    objetoReferencia.getClass().getName(), i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosOsItens.add(fig);
        }
    }
    public T getItem(int posicao){
        return todosOsItens.get(posicao);
    }
    public int getTotalItens() {
        return this.todosOsItens.size() - 1;
    }
}
