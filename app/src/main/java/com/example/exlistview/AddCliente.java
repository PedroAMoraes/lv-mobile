package com.example.exlistview;

import static android.widget.Toast.LENGTH_SHORT;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exlistview.dao.ClienteDAO;
import com.example.exlistview.models.Cliente;

public class AddCliente extends AppCompatActivity {
    private Button btnAdd;
    private EditText edtCodigo, edtNome, edtEmail;
    private ClienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtCodigo = findViewById(R.id.edtCliente);
        edtCodigo.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                btnAdd.setEnabled(true);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                btnAdd.setEnabled(true);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnAdd.setEnabled(true);
            }
        });
        edtNome = findViewById(R.id.edtNome);
        edtEmail = findViewById(R.id.edtEmail);
        btnAdd = findViewById(R.id.btnAdicionar);
    }
    public void adicionar(View v){
        try{
            int codigo = Integer.parseInt(edtCodigo.getText().toString());
            String nome = edtNome.getText().toString();
            String email = edtEmail.getText().toString();
            dao = new ClienteDAO(this);
            Cliente c = new Cliente(codigo, nome, email);
            dao.insert(c);
            limpar();
            Toast.makeText(this, "Cliente adicionado!", LENGTH_SHORT).show();
        } catch (NumberFormatException e){
            Toast.makeText(this,"Insira um codigo!",LENGTH_SHORT).show();
            btnAdd.setEnabled(false);
        } catch (SQLiteConstraintException sqlce){
            Toast.makeText(this,sqlce.getMessage(),LENGTH_SHORT).show();
            btnAdd.setEnabled(false);
        }
    }
    public void limpar(){
        edtCodigo.setText("");
        edtNome.setText("");
        edtEmail.setText("");
    }
    public void voltar(View v){
        finish();
    }

}