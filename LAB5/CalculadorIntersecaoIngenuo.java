import java.util.ArrayList;
import java.util.List;

public class CalculadorIntersecaoIngenuo implements CalculadorIntersecao {

    @Override
    public List<Usuario> obterIntersecao(List<Usuario> lista1, List<Usuario> lista2) {
        List<Usuario> intersecao = new ArrayList<>();
        for (Usuario usuarioDaPrimeiraLista : lista1){
            for(Usuario usuarioDaSegundaLista : lista2){
                if (usuarioDaPrimeiraLista.equals(usuarioDaSegundaLista)){
                    intersecao.add(usuarioDaPrimeiraLista);
                    break;
                }
            }
        }
        return intersecao;
    }
}