// Sylviane Oliveira - 120021865 - LAB1 - Comp2 - 2020.2
import java.util.Scanner;

public class Primos{
    /**
     * Retorna um array contendo exatameente os números primos em [1, n].
     * @param n O maior número a ser considerado
     * @return um array de inteiros com os primos no intervalo dado.
     */

    public static int[] limpaVetor(int[] vetor){
        /**
          * Retorna um novo vetor igual ao anterior, mas sem o espaço não utilizado
        */
        int contador = 0;
        for(int i=0; i < vetor.length; i++){ // Calcula o número de espaços no vetor antes do primeiro 0
            if (vetor[i] == 0) break;
            contador += 1;
        }
        int[] novoVetor = new int[contador];
        for (int i=0; i < contador; i++){ // Copia os elementos diferentes de 0 para um novo vetor
            novoVetor[i] = vetor[i];
        }
        return novoVetor;
    }

    public static int[] obterPrimos(int n){
        /**
          * Retorna uma lista de primos de 1 até n sem utilizar o crivo
         */
        int i;
        int[] primos = new int[n/2+1];
        int k = 0;
        for (i=2; i <= n; i++){
            int cont = 1;
            for (int j=2; j <= (int) Math.sqrt(i); j++){ // Verifica se i possui um divisor diferente de 1
                if (i % j == 0){
                    cont = 0;
                    break;
                }
            }
            if (cont == 1){ // Adiciona o número primo na lista primos
                primos[k] = i;
                k++;
            }
        }
        primos = limpaVetor(primos); // Retira o espaço não utilizado do vetor
        return primos;
    }
    
    public static int[] obterPrimosViaCrivo(int n){
        /**
          * Retorna uma lista de primos de 1 até n utilizando o crivo de Erastóstenes
         */
        int limite = (int) Math.sqrt(n);
        int[] ehComposto = new int[n+1];

        for(int i=2; i <= limite; i++){
            if (ehComposto[i] == 1) continue;
            for(int j = i*i; j < n + 1; j+=i){
                ehComposto[j] = 1;
            }
        }
        int[] primos = new int[n+1];
        int k=0;

        for(int i = 2; i < n+1; i++){
            if (ehComposto[i] == 0){
                primos[k] = i;
                k++;
            }
        }
        primos = limpaVetor(primos); //Retira o espaço não utilizado do vetor
        return primos;
    }

    public static void main(String[] args){
        /**
          * Compara o tempo de execução dos dois métodos de obtenção de primos implementados
          * no intervalo de 1 a n
        */
        for(int n=1_000; n <= 10_000_000; n*=10){
            // Calcula o tempo do método sem o crivo
            long inicio = System.currentTimeMillis();
            int[] primos = obterPrimos(n);
            long duracao = System.currentTimeMillis() - inicio;

            System.out.printf("Duracao sem crivo da execucao dos primos de 1 a %d: %.9f segundos\n", n , duracao / 1000.0);

            // Calcula o tempo do método com o crivo
            inicio = System.currentTimeMillis();
            primos = obterPrimosViaCrivo(n);
            duracao = System.currentTimeMillis() - inicio;

            System.out.printf("Duracao com crivo da execucao dos primos de 1 a %d: %.9f segundos\n\n", n,  duracao / 1000.0);
        }
    }
}