package models;

import services.AtletaService;

import java.util.Objects;

public class Atleta {
    private String nome;
    private Enum<PaisesValidos> pais;
    private Enum<SexoPermitido> sexo;
    private int idade;
    private double melhorTempo;

    public Atleta(String nome, Enum<PaisesValidos> pais,Enum<SexoPermitido> sexo, int idade, double melhorTempo) {
        this.nome = nome;
        this.pais = pais;
        this.sexo = sexo;
        this.idade = idade;
        this.melhorTempo = melhorTempo;
    }

    public String getNome() {
        return nome;
    }

    public Enum<PaisesValidos> getPais() {
        return pais;
    }

    public int getIdade() {
        return idade;
    }

    public double getMelhorTempo() {
        return melhorTempo;
    }

    @Override
    public String toString() {
        return "Atleta{" +
                "nome='" + nome + '\'' +
                ", pais='" + pais + '\'' +
                ", idade=" + idade +
                ", melhorTempo=" + melhorTempo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atleta atleta = (Atleta) o;
        return Double.compare(melhorTempo, atleta.melhorTempo) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(melhorTempo);
    }
}
