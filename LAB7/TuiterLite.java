import java.util.*;

/**
 *  Esta classe implementa um sistema de mensagens curtas estilo Twitter.
 *  É preciso cadastrar um usuário, identificado pelo seu e-mail, para que tuítes possam ser feitos.
 *  Usuários começam como iniciantes, depois são promovidos a senior e a ninja, em função do número de tuítes.
 *  Existe um tamanho máximo permitido por mensagem (constante TAMANHO_MAXIMO_TUITES).
 *  As mensagens podem conter hashtags (palavras iniciadas por #), que são detectadas automaticamente.
 *  Os tuítes podem conter, além da mensagem de texto, um anexo qualquer.
 *  Há um método para retornar, a qualquer momento, qual a hashtag mais usada em toda a história do sistema.
 */
public class TuiterLite {

    public static int TAMANHO_MAXIMO_TUITES = 120;
    private Usuario possivelUsuario;
    private Set<Usuario> usuarios;
    private Set<Tuite> todosTuites;
    private Map<String, Integer> quantidadebyHashtag;

    public TuiterLite() {
        usuarios = new HashSet<>();
        todosTuites = new HashSet<>();
        quantidadebyHashtag = new HashMap<>();
    }

    /**
     * Cadastra um usuário, retornando o novo objeto Usuario criado.
     * @param nome O nome do usuário.
     * @param email O e-mail do usuário (precisa ser único no sistema).
     * @return O Usuario criado.
     */
    public Usuario cadastrarUsuario(String nome, String email) {  // throws UsuarioJaExisteException {
        possivelUsuario = new Usuario(nome, email);
        if (usuarios.contains(possivelUsuario)) return null;
        usuarios.add(possivelUsuario);
        return possivelUsuario;
    }

    /**
     *
     * @param usuario O autor do tuíte
     * @param texto O texto desejado
     * @return Um "tuíte", que será devidamente publicado no sistema
     *
     * PS.: Se o texto exceder o limite pré-definido, ou o usuário não estiver cadastrado, return null
     */
    public Tuite tuitarAlgo(Usuario usuario, String texto) {
        if (texto.length() > TAMANHO_MAXIMO_TUITES || !usuarios.contains(usuario)) return null;
        Set<String> hashtags = identificaHashtags(texto);
        Tuite tuite = new Tuite(usuario, texto, hashtags);
        todosTuites.add(tuite);
        registrarHashtags(hashtags);
        return tuite;
    }

    private Set<String> identificaHashtags(String texto) {
        String[] textoBloco = texto.split(" ");
        Set<String> hashtags = new HashSet<>();
        for(String s : textoBloco){
            if(s.charAt(0) == '#') {
                hashtags.add(s);
            }
        }
        return hashtags;
    }

    private void registrarHashtags(Set<String> hashtags) {
        for(String s : hashtags){
            if (!quantidadebyHashtag.containsKey(s)) {
                quantidadebyHashtag.put(s, 0);
            }
            quantidadebyHashtag.put(s, quantidadebyHashtag.get(s) + 1);
        }
    }
    /**
     * Retorna a hashtag mais comum dentre todas as que já apareceram.
     * A cada tuíte criado, hashtags devem ser detectadas automaticamente para que este método possa funcionar.
     * @return A hashtag mais comum, ou null se nunca uma hashtag houver sido tuitada.
     */
    public String getHashtagMaisComum() {
        if(quantidadebyHashtag.size() == 0) return null;
        Integer maiorValor = 0;
        String stringMaisFrequente = new String();
        for(String s : quantidadebyHashtag.keySet()){
            Integer valor = quantidadebyHashtag.get(s);
            if (valor > maiorValor){
                stringMaisFrequente = s;
            }
        }
        return stringMaisFrequente;
    }
}