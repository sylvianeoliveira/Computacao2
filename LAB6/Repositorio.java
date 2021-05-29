import java.util.ArrayList;
import java.util.List;

public class Repositorio<T extends Colecionavel> {

    private static final String PREFIXO_URL_IMAGENS = "http://www.nossoalbum.com.br/imagens/";

    private List<T> todosOsItens;

    public Repositorio(String sufixoUrlImagens, int quantItens, T objetoReferencia) {
        todosOsItens = new ArrayList<>(quantItens);
        todosOsItens.add(null);
        for (int i = 1; i <= quantItens; i++) {
            T item = (T) ColecionavelFactory.create(objetoReferencia.getClass().getName(), i, PREFIXO_URL_IMAGENS + sufixoUrlImagens);
            todosOsItens.add(item);
        }
    }

    public T getItem ( int posicao){
        return todosOsItens.get(posicao);
    }

    public int getTotalItens () {
        return this.todosOsItens.size() - 1;
    }
}
