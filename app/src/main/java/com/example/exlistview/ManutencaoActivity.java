package com.example.exlistview;

import static android.widget.Toast.LENGTH_SHORT;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exlistview.dao.ClienteDAO;
import com.example.exlistview.models.Cliente;

public class ManutencaoActivity extends AppCompatActivity {
    private Button btnSalvar, btnExcluir, btnSair;
    private EditText edtCodigo, edtNome, edtEmail;
    private TextView txtCodigo;
    private ClienteDAO dao;
    private Cliente cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manutencao);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtCodigo = findViewById(R.id.edtCodigoManu);
        edtNome = findViewById(R.id.edtNomeManu);
        edtNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                btnSalvar.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnSalvar.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSalvar.setEnabled(true);
            }
        });
        edtEmail = findViewById(R.id.edtEmailManu);
        edtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                btnSalvar.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnSalvar.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSalvar.setEnabled(true);
            }
        });
        txtCodigo = findViewById(R.id.txtCodigo);
        btnSalvar = findViewById(R.id.btnAlterar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alterar();
            }
        });
        btnExcluir = findViewById(R.id.btnExcluir);
        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluir();
            }
        });
        btnSair = findViewById(R.id.btnSairManu);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sair();
            }
        });
        dao = new ClienteDAO(this);
        exibirCliente();
    }
    public void exibirCliente(){
        Intent it = getIntent();
        int ra = it.getIntExtra("codigo", -1);
        cliente = dao.getById(ra);
        edtCodigo.setText(String.valueOf(cliente.getCodigo()));
        txtCodigo.setText(String.valueOf(cliente.getCodigo()));
        edtNome.setText(cliente.getNome());
        edtEmail.setText(cliente.getEmail());
    }

    public void excluir(){
        new AlertDialog.Builder(this)
                .setTitle("Excluir cliente?")
                .setMessage("Deseja prosseguir?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dao.delete(cliente);
                        edtCodigo.setEnabled(false);
                        edtNome.setEnabled(false);
                        edtEmail.setEnabled(false);
                        btnSalvar.setEnabled(false);
                        btnExcluir.setEnabled(false);
                        Toast.makeText(
                                ManutencaoActivity.this,
                                "Aluno deletado",
                                LENGTH_SHORT)
                                .show();
                        finish();
                    }})
                .setNegativeButton(android.R.string.no, null).show();

    }

    public void alterar(){
        int codigo = Integer.parseInt(edtCodigo.getText().toString());
        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        dao.update(new Cliente(codigo,nome,email));
        Toast.makeText(this, "Cliente atualizado!", LENGTH_SHORT).show();
        btnSalvar.setEnabled(false);
    }

    public void sair(){
        finish();
    }
}