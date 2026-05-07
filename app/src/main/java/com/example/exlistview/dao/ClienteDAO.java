package com.example.exlistview.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;

import com.example.exlistview.models.Cliente;
import com.example.exlistview.util.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private ConnectionFactory conexao;
    private SQLiteDatabase banco;

    public ClienteDAO(Context context) {
        conexao = new ConnectionFactory(context, "dbProjetoLV.db",null,1);
        banco = conexao.getWritableDatabase();
    }

    public void insert(Cliente cliente){
        if(getById(cliente.getCodigo()).getCodigo() != null)
            throw new SQLiteConstraintException("Codigo em uso!");
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("email", cliente.getEmail());
        String args[] ={cliente.getCodigo().toString()};
        values.put("codigo", cliente.getCodigo());
        banco.insert("tbcliente", null, values);
    }

    public void update(Cliente cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("email", cliente.getEmail());
        String args[] = {cliente.getCodigo().toString()};
        banco.update("tbcliente", values,"codigo=?",args);
    }

    public void delete(Cliente cliente){
        String args[] = {cliente.getCodigo().toString()};
        banco.delete("tbcliente","codigo=?",args);
    }

    public List<Cliente> getAll(){
        // contabilizar quantos clientes sem nome existem
        int semNome = 0;
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = banco.query("tbcliente", new String[]{"codigo", "nome", "email"},
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            Cliente a = new Cliente();
            a.setCodigo(cursor.getInt(0));
            a.setNome((cursor.getString(1)));
            a.setEmail((cursor.getString(2)));
            if(a.getNome().isEmpty())
                a.setSemNome(semNome++);
            clientes.add(a);
        }
        return clientes;
    }


    public Cliente getById(Integer ra){
        String args[] ={String.valueOf(ra)};
        Cursor cursor = banco.query("tbcliente", new String[]{"codigo", "nome", "email"},
                "codigo=?",args,null, null,null);
        cursor.moveToFirst();
        Cliente cliente = new Cliente();
        if(cursor.getCount() > 0){
            cliente.setCodigo(cursor.getInt(0));
            cliente.setNome((cursor.getString(1)));
            cliente.setEmail((cursor.getString(2)));
        }
        return cliente;
    }

}
