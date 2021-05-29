import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CalculadorTest {

    private static final int MAX = 10000;
    private CalculadorIntersecaoViaBuscaBinaria calculador1;
    private CalculadorIntersecaoIngenuo calculador2;
    private CalculadorIntersecaoEsperto calculador3;
    private Usuario pessoa1;
    private Usuario pessoa2;
    private Usuario pessoa3;
    private Usuario pessoa4;
    private Usuario pessoa5;

    @Before
    public void setUp() {
        calculador1 = new CalculadorIntersecaoViaBuscaBinaria();
        calculador2 = new CalculadorIntersecaoIngenuo();
        calculador3 = new CalculadorIntersecaoEsperto();
        pessoa1 = new Usuario(1234, calculador1);
        pessoa2 = new Usuario(4567, calculador2);
        pessoa3 = new Usuario(3234, calculador3);
        pessoa4 = new Usuario(5754, calculador2);
        pessoa5 = new Usuario(8674, calculador1);
    }

    @Test
    public void TestarAdicionarAmigo(){
        pessoa1.adicionarAmigo(pessoa2);
        pessoa1.adicionarAmigo(pessoa3);
        pessoa1.adicionarAmigo(pessoa4);
        pessoa1.adicionarAmigo(pessoa5);
        List<Usuario> esperado = new ArrayList<>();
        esperado.add(pessoa2);
        esperado.add(pessoa3);
        esperado.add(pessoa4);
        esperado.add(pessoa5);
        assertEquals("A lista de amigos deve conter todos os amigos adicionados", esperado, pessoa1.getAmigos());
    }

    @Test
    public void TestarIntercesaoDeAmigos(){
        List<Usuario> intercesao = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            pessoa1.adicionarAmigo(new Usuario(i*2,calculador1));
            pessoa2.adicionarAmigo(new Usuario(i, calculador2));
            pessoa3.adicionarAmigo(new Usuario(i*2, calculador3));
            if(i*2 < MAX){
                intercesao.add(new Usuario(i*2, calculador1));
            }
        }
        //BuscaBinária
        assertEquals("A lista de amigos em comum deve estar correta", 10, pessoa1.obterQuantidadeDeAmigosEmComum(pessoa2));
        //Ingenuo
        assertEquals("A lista de amigos em comum deve estar correta", 10, pessoa2.obterQuantidadeDeAmigosEmComum(pessoa1));
        //Esperto
        assertEquals("A lista de amigos em comum deve estar correta", 10, pessoa3.obterQuantidadeDeAmigosEmComum(pessoa2));
    }

    @Test
    public void TestarInterssecaoComMuitasAmigos(){
        List<Usuario> intercesao = new ArrayList<>();
        for(int i = 0; i < MAX; i++){
            pessoa1.adicionarAmigo(new Usuario(i*2,calculador1));
            pessoa2.adicionarAmigo(new Usuario(i, calculador2));
            pessoa3.adicionarAmigo(new Usuario(i*2, calculador3));

            if(i*2 < MAX){
                intercesao.add(new Usuario(i*2, calculador1));
            }
        }
        long inicio = System.currentTimeMillis();
        //BuscaBinária
        assertEquals("A lista de amigos em comum deve estar correta", MAX/2, pessoa1.obterQuantidadeDeAmigosEmComum(pessoa2));
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Calculo da interseção por busca binária = %.3f\n", duracao / 1000f);

        inicio = System.currentTimeMillis();
        //Ingenuo
        assertEquals("A lista de amigos em comum deve estar correta", MAX/2, pessoa2.obterQuantidadeDeAmigosEmComum(pessoa1));
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Calculo da interseção por método ingênuo = %.3f\n", duracao / 1000f);

        inicio = System.currentTimeMillis();
        //Esperto
        assertEquals("A lista de amigos em comum deve estar correta", MAX/2, pessoa3.obterQuantidadeDeAmigosEmComum(pessoa2));
        duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Calculo da interseção por método esperto = %.3f\n", duracao / 1000f);
    }
}