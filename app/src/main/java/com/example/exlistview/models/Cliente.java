package com.example.exlistview.models;

import com.example.exlistview.dao.ClienteDAO;

public class Cliente {
    private Integer codigo;
    private String nome;
    private String email;

    public Cliente(int codigo, String nome, String email) {
        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
    }

    public Cliente() {
    }

    @Override
    public String toString() {
        return
                //"\nID:: " + (codigo == null ? " " : codigo) +
                (nome.isEmpty() ? "SEM NOME("+ codigo +")" : nome.toUpperCase());
                //"\n\tEmail: " + (email.isEmpty() ? "N/A" : email) + "\n";
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
}
