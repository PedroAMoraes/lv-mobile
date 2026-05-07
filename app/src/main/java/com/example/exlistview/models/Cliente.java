package com.example.exlistview.models;

public class Cliente {
    private Integer codigo;
    private String nome;
    private String email;
    private int numeroSemNome;
    public Cliente(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public Cliente() {
    }

    @Override
    public String toString() {
        // identificar se tem ou não nome definido
        if(nome.isEmpty()){
            if(numeroSemNome == 0)
                return "SEM NOME"; // caso seja o primeiro/unico identificado sem nome
            // caso não seja o primeiro/unico sem nome identificadp
            return "SEM NOME(" + numeroSemNome + ")";
        }
        // caso tenha nome
        return nome;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumeroSemNome() {
        return numeroSemNome;
    }

    public void setNumeroSemNome(int numeroSemNome) {
        this.numeroSemNome = numeroSemNome;
    }
}
