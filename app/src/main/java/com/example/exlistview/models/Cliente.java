package com.example.exlistview.models;

public class Cliente {
    private Integer codigo;
    private String nome;
    private String email;
    private int semNome;
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
        return (nome.isEmpty() ? "<SEM NOME("+ semNome +")>" : nome);
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

    public int getSemNome() {
        return semNome;
    }

    public void setSemNome(int semNome) {
        this.semNome = semNome;
    }
}
