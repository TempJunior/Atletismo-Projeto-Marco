package models;

import java.util.Objects;

public class Atleta {
    private String nome;
    private String pais;
    private String sexo;
    private int idade;
    private double melhorTempo;
    private String carteiraDeCadastro;

    public Atleta(String nome, String pais,String sexo, int idade, double melhorTempo, String carteiraDeCadastro) {
        this.nome = nome;
        this.pais = pais;
        this.sexo = sexo;
        this.idade = idade;
        this.melhorTempo = melhorTempo;
        this.carteiraDeCadastro = carteiraDeCadastro;
    }

    public String getNome() {
        return nome;
    }

    public String getPais() {
        return pais;
    }

    public int getIdade() {
        return idade;
    }

    public double getMelhorTempo() {
        return melhorTempo;
    }

    public String getCarteiraDeCadastro() {
        return carteiraDeCadastro;
    }

    @Override
    public String toString() {
        return "Atleta{" +
                "nome='" + nome + '\'' +
                ", pais=" + pais +
                ", sexo=" + sexo +
                ", idade=" + idade +
                ", melhorTempo=" + melhorTempo +
                ", carteiraDeCadastro='" + carteiraDeCadastro + '\'' +
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
