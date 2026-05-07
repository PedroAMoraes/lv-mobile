package com.example.exlistview;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exlistview.dao.ClienteDAO;
import com.example.exlistview.models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lsvClientes;
    private List<Cliente> clientes = new ArrayList<>();
    private ClienteDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listarClientes();

        lsvClientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cliente c = clientes.get(position);
                Intent it = new Intent(MainActivity.this, ManutencaoActivity.class);
                it.putExtra("codigo",c.getCodigo());
                startActivity(it);
            }
        });

    }

    public void listarClientes(){
        dao = new ClienteDAO(this);
        clientes = dao.getAll();
        lsvClientes = findViewById(R.id.lsvClientes);

        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<Cliente>(
                this,
                android.R.layout.simple_list_item_1,
                clientes
        );
        lsvClientes.setAdapter(adaptador);
    }

    public void onAddAlunoClick(MenuItem menuItem){
        Intent it = new Intent(this, AddCliente.class);
        startActivity(it);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listarClientes();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }


}