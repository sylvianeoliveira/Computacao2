//Sylviane Oliveira - 120021865
/**
 * Representa uma fração de forma explícita, guardando numerador e denominador inteiros.
 * Frações equivalentes (matematicamente) devem ser consideradas equals().
 */
public class Fracao {

    private int numerador;
    private int denominador;
    private boolean sinal = false;
    /**
     * Cria uma fração, baseada em seu numerador e denominador.
     * O sinal da fração será inferido a partir do sinal
     * do numerador e do denominador.
     *
     * @param numerador o numerador
     * @param denominador o denominador
     */
    public Fracao(int numerador, int denominador) {

        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero!!");
        }

        this.numerador = numerador;
        this.denominador = denominador;
        
        if (numerador * denominador >= 0){
           this.sinal = true;
        }
        if (numerador < 0) this.numerador *= -1;
        if (denominador < 0) this.denominador *= -1;
    }

    /**
     * Retorna o sinal da fração.
     *
     * @return true, se for positiva ou nula (zero);
     *         false, se for negativa.
     */
    public boolean getSinal() {
        return this.sinal;
    }

    /**
     * Retorna o (valor absoluto do) numerador desta fração, ou seja, sempre não-negativo
     * @return o numerador
     */
    public int getNumerador() {
        return this.numerador;
    }

    /**
     * Retorna o (valor absoluto do) denominador desta fração, ou seja, sempre positivo
     * @return o numerador
     */
    public int getDenominador() {
        return this.denominador;
    }

    /**
     * Retorna o valor numérico da fração (com o sinal correto).
     *
     * @return um float, com o valor na melhor precisão possível
     *         Ex.: -1/3 vai retornar -0.33333333
     */
    public float getValorNumerico() {
        if (!sinal){
            return (float)this.numerador/this.denominador * -1;
        }
        return (float)this.numerador/this.denominador;
    }

    /**
     * Retorna uma fração equivalente à esta fração,
     * e que não pode mais ser simplificada (irredutível).
     *
     * @return um outro objeto Fracao, se esta fração for redutível;
     *         esta própria fração (this), se ela já for irredutível
     */
    public Fracao getFracaoGeratriz() {
        int divisor = AritmeticaUtils.mdc(this.numerador, this.denominador);
        if (divisor == 1) return this;
        int novoNumerador = this.numerador / divisor;
        int novoDenominador = this.denominador / divisor;
        if (!sinal) novoNumerador *= -1;
        return new Fracao(novoNumerador, novoDenominador);
    }

    @Override
    public String toString() {
        String strFracao = "";
        if (this.numerador == 0) return "0";
        if (!sinal) strFracao += "-";
        strFracao += this.numerador;
        if (denominador != 1){
            strFracao += "/" + this.denominador;
        }
        return strFracao;
    }

    @Override
    public boolean equals(Object f) {
        if (this == f) return true;
        if (f == null || getClass() != f.getClass()) return false;
        Fracao fracao = (Fracao) f;
        Fracao g1 = this.getFracaoGeratriz();
        Fracao g2 = fracao.getFracaoGeratriz();
        if (g1.sinal == g2.sinal && g1.numerador == g2.numerador && g1.denominador
        == g2.denominador) return true;
        else return false;
    }
}