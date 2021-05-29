import java.util.Objects;

public class Usuario {

    private String nome;
    private long cpf;
    private String endereco;

    /**
     *
     * @param nome
     * @param cpf
     * @param endereco
     */
    public Usuario(String nome, long cpf, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
    }

    /**
     *
     * @return nome do usuário
     */
    public String getNome(){
        return this.nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario)) return false;
        Usuario usuario = (Usuario) o;
        return cpf == usuario.cpf && Objects.equals(getNome(), usuario.getNome()) && Objects.equals(endereco, usuario.endereco);
    }
}