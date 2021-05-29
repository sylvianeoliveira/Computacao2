import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements Comparable<Usuario> {

    private int id;

    private List<Usuario> amigos;

    private CalculadorIntersecao calculadorIntersecao;

    public Usuario(int id, CalculadorIntersecao calculador) {
        this.id = id;
        this.calculadorIntersecao = calculador;
        this.amigos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Usuario> getAmigos() {
        return this.amigos;
    }

    /**
     * Retorna a quantidade de amigos em comum entre este Usuario e o
     * outro Usuario informado.
     *
     * @param outro outro Usuario da rede social
     * @return o tamanho da interseção dos conjuntos de amigos
     */
    public int obterQuantidadeDeAmigosEmComum(Usuario outro) {
        return calculadorIntersecao.obterIntersecao(amigos, outro.getAmigos()).size();
    }

    public void adicionarAmigo(Usuario amigo){
        if (this.amigos.contains(amigo)) return;
        this.amigos.add(amigo);
    }

    @Override
    public int compareTo(Usuario o) {
        return this.id - o.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return getId() == usuario.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
