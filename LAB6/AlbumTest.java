import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AlbumTest {

    private Album<Figurinha> albumFigurinhas;
    private Repositorio<Figurinha> repositorioFigurinhas;

    //novos atributos, da parte 5
    private Album<Selo> albumSelos;
    private Repositorio<Selo> repositorioSelos;

    private static final int TAMANHO_DO_ALBUM = 200;
    private static final int ITENS_POR_PACOTE = 3;

    @Before  // roda antes de cada teste
    public void setUp() {
        this.repositorioFigurinhas = new Repositorio("album_copa2014", TAMANHO_DO_ALBUM, new Figurinha(0, "fig"));
        this.albumFigurinhas = new Album(repositorioFigurinhas, ITENS_POR_PACOTE);
    }

    private void popularAlbum(int[] posicoesDesejadas) {
        Pacotinho<Figurinha> pacote = new Pacotinho<>(this.repositorioFigurinhas, posicoesDesejadas);
        this.albumFigurinhas.receberNovoPacotinho(pacote);
    }

    @Test
    public void testarPossuiFigurinhaParaFigurinhasExistentes() {
        popularAlbum(new int[] {1, 2, 3});

        for (int i = 1; i <= 3; i++) {
            assertTrue("Figurinhas já inseridas devem ser encontradas",
                    this.albumFigurinhas.possuiItemColado(i));
        }
    }

    @Test
    public void testarPossuiFigurinhaParaFigurinhasAusentes() {
        popularAlbum(new int[] {1, 2, 3});

        assertFalse("Não devemos encontrar no álbum figurinhas nunca inseridas",
                this.albumFigurinhas.possuiItemColado(4));
        assertFalse("Não devemos encontrar figurinhas de posições não-positivas",
                this.albumFigurinhas.possuiItemColado(-390));
        assertFalse("Não devemos encontrar figurinhas maiores do que o tamanho",
                this.albumFigurinhas.possuiItemColado(TAMANHO_DO_ALBUM + 1));
    }

    @Test
    public void testarPossuiRepetidaParaFigurinhaRepetida() {
        popularAlbum(new int[] {1, 2, 3});
        assertFalse(this.albumFigurinhas.possuiItemRepetido(2));  // sanity check

        popularAlbum(new int[] {2, 8, 9});
        assertTrue(this.albumFigurinhas.possuiItemRepetido(2));
    }

    @Test
    public void testarGetTamanhoAlbum() {
        assertEquals(TAMANHO_DO_ALBUM, this.albumFigurinhas.getTamanho());
    }

    @Test
    public void testarGetContFigurinhas() {
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(3, this.albumFigurinhas.getQuantItensColados());

        // vou agora abrir outro pacotinho com as mesmas figurinhas
        // e verificar que o álbum terá ainda 3 figurinhas

        popularAlbum(new int[] {1, 2, 3});
        assertEquals(3, this.albumFigurinhas.getQuantItensColados());
    }

    @Test
    public void testarGetQuantasFaltam() {
        popularAlbum(new int[] {1, 2, 3});
        assertEquals(TAMANHO_DO_ALBUM - 3,
                this.albumFigurinhas.getQuantItensFaltantes());
    }

    @Test
    public void testarAutoCompletar() {
        albumFigurinhas.autoCompletar();
        assertEquals("Não deve ser possível auto-completar um álbum que esteja vazio",
                TAMANHO_DO_ALBUM, albumFigurinhas.getQuantItensFaltantes());

        // agora vamos adicionar pacotinhos aleatórios até o álbum ficar quase cheio

        int minimoFigurinhasColadasParaAutoCompletar =
                (int) (TAMANHO_DO_ALBUM * Album.PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR / 100f);

        while (albumFigurinhas.getQuantItensColados() < minimoFigurinhasColadasParaAutoCompletar) {
            Pacotinho novoPacotinho = new Pacotinho(
                    this.repositorioFigurinhas, ITENS_POR_PACOTE);  // aleatório
            albumFigurinhas.receberNovoPacotinho(novoPacotinho);
        }

        // sanity check
        assertTrue(albumFigurinhas.getQuantItensFaltantes() > 0);

        albumFigurinhas.autoCompletar();

        assertEquals("O álbum deve estar completo após uma chamada válida ao auto-completar " +
                        "(isto é, após o percentual mínimo de figurinhas já ter sido obtido)",
                0, albumFigurinhas.getQuantItensFaltantes());  // álbum completo!
    }

    @Test
    public void testarGetItemColado() {
        popularAlbum(new int[] {1, 2, 3});
        Figurinha figurinha = (Figurinha) albumFigurinhas.getItemColado(2);

        assertNotNull(figurinha);

        assertEquals(2, figurinha.getPosicao());

        assertNull(albumFigurinhas.getItemColado(4));
    }

    @Test
    public void testarRejeicaoPacotinhosDeTamanhoErrado() {
        popularAlbum(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 8, 8, 8});

        assertEquals("Pacotes de tamanho distinto do informado na construção " +
                "do álbum devem ser rejeitados",
                0, albumFigurinhas.getQuantItensColados());
    }

   @Test
    public void testarInstanciaUnica(){
        repositorioSelos = new Repositorio<>("albumSelos", TAMANHO_DO_ALBUM, new Selo(0, "selo", 20));
        albumSelos = new Album<>(repositorioSelos, ITENS_POR_PACOTE);

        popularAlbum(new int[]{4, 8, 15});

        int[] posicoesSelos = {17, 45, 59};
        Pacotinho<Selo> pacoteSelo = new Pacotinho<>(this.repositorioSelos, posicoesSelos);
        this.albumSelos.receberNovoPacotinho(pacoteSelo);

        Selo selo = albumSelos.getItemColado(17);
        assertNotNull("Selos já inseridas devem ser encontradas",
                selo);

        Figurinha figurinha = albumFigurinhas.getItemColado(15);
        assertNotNull("Figurinhas já inseridas devem ser encontradas",
                figurinha);

        //Não deve compilar
/*
        Pacotinho <Figurinha> pacoteFigurinhas = new Pacotinho<>(this.repositorioFigurinhas, new int[] {2,4,6});
        this.albumSelos.receberNovoPacotinho(pacoteSelos);

*/
    }
}