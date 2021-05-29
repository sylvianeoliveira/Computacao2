import java.util.Objects;

public class Periodo {

    private int ano;

    private int semestre;  // 1 ou 2

    public Periodo(int ano, int semestre) {

        if (semestre < 1 || semestre > 2) {
            throw new RuntimeException("Semestre inválido!!!!");
        }

        this.ano = ano;
        this.semestre = semestre;
    }

    public int getAno() {
        return ano;
    }

    public int getSemestre() {
        return semestre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Periodo)) return false;
        Periodo periodo = (Periodo) o;
        return getAno() == periodo.getAno() && getSemestre() == periodo.getSemestre();
    }

}