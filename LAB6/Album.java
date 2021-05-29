import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Album {

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;

    public static final Image IMAGEM_PADRAO_PARA_POSICAO_VAZIA = null;

    private final Repositorio repositorio;
    private final int quantItensPorPacotinho;

    private List<Colecionavel> itensColados;  // direct addressing
    private int quantItensColados;


    // poderíamos fazer novamente direct addressing para as repetidas,
    // mas vamos usar um HashMap aqui só para treinarmos
    private Map<Integer, Integer> contRepetidosbyPosicao;

    public Album(Repositorio repositorio, int quantItensPorPacotinho) {
        this.repositorio = repositorio;
        this.quantItensPorPacotinho = quantItensPorPacotinho;

        int tamanhoFisicoDaLista = getTamanho() + 1;
        this.itensColados = new ArrayList<>(tamanhoFisicoDaLista);
        // inicializa as posições com nulls, para poder acessá-las diretamente
        for (int i = 0; i < tamanhoFisicoDaLista; i++) {
            this.itensColados.add(null);
        }
        this.quantItensColados = 0;

        this.contRepetidosbyPosicao = new HashMap<>();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        Colecionavel[] itensDoPacotinho = pacotinho.getItens();
        if (itensDoPacotinho == null || itensDoPacotinho.length != this.quantItensPorPacotinho) {
            return;  // melhor ainda: lançaria uma checked exception
        }

        for (Colecionavel item : pacotinho.getItens()) {
            final int posicao = item.getPosicao();
            if (possuiItemColado(posicao)) {
                // adiciona como repetida
                // jeito mais elegante: getOrDefault
                int contRepetidas = this.contRepetidosbyPosicao.getOrDefault(posicao, 0);
                this.contRepetidosbyPosicao.put(posicao, contRepetidas + 1);

            } else {
                // item inédito
                this.itensColados.set(posicao, item);
                this.quantItensColados++;
            }
        }
    }

    public Colecionavel getItemColado(int posicao) {
        if (possuiItemColado(posicao)) {
            return itensColados.get(posicao);
        }
        return null;
    }

    public boolean possuiItemColado(int posicao) {
        if (posicao < 0 || posicao > repositorio.getTotalItens()) {
            return false;
        }
        if (itensColados.get(posicao) != null) {
            return true;
        }
        return false;
    }

    public boolean possuiItemRepetido(int posicao) {
        if (contRepetidosbyPosicao.containsKey(posicao)) {
            return true;
        }
        return false;
    }

    public int getTamanho() {
        return this.repositorio.getTotalItens();
    }

    public int getQuantItensColados() {
        return this.quantItensColados;
    }

    public int getQuantItensFaltantes() {
        return getTamanho() - getQuantItensColados();
    }

    public void autoCompletar() {
        int tamanhoAlbum = repositorio.getTotalItens();
        int valorCompletar = (int) (tamanhoAlbum * PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f);
        if (quantItensColados < valorCompletar) {
            return;
        }
        for (int i = 1; i <= tamanhoAlbum; i++) {
            if (itensColados.get(i) == null) {
                this.itensColados.set(i, repositorio.getItem(i));
                this.quantItensColados++;
            }
        }
    }

    private Image getImagem(int posicao) {
        return possuiItemColado(posicao)
                ? this.getItemColado(posicao).getImagem()
                : IMAGEM_PADRAO_PARA_POSICAO_VAZIA;
    }
}