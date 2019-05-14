package com.fernando9825.pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class PedidoActivity extends AppCompatActivity {

    private EditText cantidad;


    private Spinner comboPersonas;
    private Spinner comboProductos;

    ArrayList<String> listaPersonas;
    ArrayList<String> listaProductos;


    TextView txtNombre,txtPrecio, txtDireccion1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pedido_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        cantidad = findViewById(R.id.txtcantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtNombre = findViewById(R.id.txtNombre);


        //para clientes
        txtDireccion1 = findViewById(R.id.txtDireccionClient);
        obtenerClientes();
        obtenerProductos();
        comboPersonas = findViewById(R.id.spinnerClient);
        ArrayAdapter<CharSequence> adaptadorClientes=new ArrayAdapter
                (this ,android.R.layout.simple_spinner_item, listaPersonas);


        comboProductos = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adaptador=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item,listaProductos);
        comboPersonas.setPrompt("Seleccione Cliente");
        comboProductos.setPrompt("Seleccione Producto");
        comboProductos.setAdapter(adaptador);
        comboPersonas.setAdapter(adaptadorClientes);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    txtDireccion1.setText("Direccion:   "+ MainActivity.clients.get(position-1).getDireccion());

                }else{
                    txtDireccion1.setText("");
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        comboProductos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

                if (position!=0){
                    txtNombre.setText(MainActivity.products.get(position-1).getDescripcion());
                    txtPrecio.setText("Precio:   " + MainActivity.products.get(position - 1).getPrecio() + "\n");

                }else{
                    txtNombre.setText("");
                    txtPrecio.setText("");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Haz un pedido! :D", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    public void hacerPedido(View view){
        //procede a hacer el pedido
        String cant = cantidad.getText().toString();

        Toast.makeText(this, "Pedido Hecho :D",
                Toast.LENGTH_SHORT).show();

        //despues de hacer el pedido
        Intent intent = new Intent (view.getContext(), MainActivity.class);
        startActivityForResult(intent, 0);
    }

    public void limpiar(View view){
        comboPersonas.setSelection(0);
        comboProductos.setSelection(0);
        txtNombre.setText("");
        txtPrecio.setText("Precio:   No Seleccionado\n");
        txtDireccion1.setText("Direccion:   No Seleccionado");
        cantidad.setText("");
        cantidad.setHint("$ 0.0");

        Toast.makeText(this, "Campos Limpiados",
                Toast.LENGTH_SHORT).show();
    }

    private void obtenerClientes() {
        listaPersonas=new ArrayList<String>();
        listaPersonas.add("CLIENTES");

        for(int i=0;i<MainActivity.clients.size();i++){
            listaPersonas.add(MainActivity.clients.get(i).getNombre());
        }

    }

    private void obtenerProductos() {
        listaProductos=new ArrayList<String>();
        listaProductos.add("PRODUCTOS");

        for(int i=0;i<MainActivity.products.size();i++){
            listaProductos.add(MainActivity.products.get(i).getDescripcion());
        }

    }

}
