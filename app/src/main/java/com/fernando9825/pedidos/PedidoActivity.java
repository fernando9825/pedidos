package com.fernando9825.pedidos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fernando9825.pedidos.SQLite.SQLitePedidos;

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
                    txtPrecio.setText("Precio:" + MainActivity.products.get(position - 1).getPrecio() + "\n");

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
        fab.setEnabled(false);
        fab.setVisibility(View.GONE); // este no es un error


    }

    public void hacerPedido(View view){

        try {
            SQLitePedidos pedidos = new SQLitePedidos(this, SQLitePedidos.PEDIDOS, null, 1);
            SQLiteDatabase db = pedidos.getWritableDatabase();
            //dato del pedido
            String producto = comboProductos.getSelectedItem().toString();
            String cliente = comboPersonas.getSelectedItem().toString();
            String cant = cantidad.getText().toString();
            double precio = Double.parseDouble(txtPrecio.getText().toString().split(":")[1]);
            double total = Integer.parseInt(cant) * precio;
            String id_pedido = generarPedidoID();
            //inserta el pedido
            ContentValues registro = new ContentValues();
            registro.put("id_pedido", id_pedido);
            registro.put("cliente", cliente);
            registro.put("producto", producto);
            registro.put("cantidad", Integer.parseInt(cant));
            registro.put("precio", precio);
            registro.put("total", total);
            db.insert(SQLitePedidos.PEDIDOS, null, registro);
            db.close();


            Toast.makeText(this, "Pedido realizado",
                    Toast.LENGTH_SHORT).show();

            //despues de hacer el pedido
            Intent intent = new Intent(view.getContext(), MainActivity.class);
            startActivityForResult(intent, 0);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Ocurrio un error. ",
                    Toast.LENGTH_SHORT).show();
        }


    }

    private String generarPedidoID() {
        StringBuilder idPedido = new StringBuilder();
        for (int i = 0; i < 36; i++) {
            int aleatorio = (int) Math.floor(Math.random() * 36);
            switch (aleatorio) {
                case 0:
                    idPedido.append("A");
                    break;
                case 1:
                    idPedido.append("B");
                    break;
                case 2:
                    idPedido.append("C");
                    break;
                case 3:
                    idPedido.append("D");
                    break;
                case 4:
                    idPedido.append("E");
                    break;
                case 5:
                    idPedido.append("F");
                    break;
                case 6:
                    idPedido.append("G");
                    break;
                case 7:
                    idPedido.append("H");
                    break;
                case 8:
                    idPedido.append("I");
                    break;
                case 9:
                    idPedido.append("J");
                    break;
                case 10:
                    idPedido.append("K");
                    break;
                case 11:
                    idPedido.append("L");
                    break;
                case 12:
                    idPedido.append("M");
                    break;
                case 13:
                    idPedido.append("N");
                    break;
                case 14:
                    idPedido.append("O");
                    break;
                case 15:
                    idPedido.append("P");
                    break;
                case 16:
                    idPedido.append("Q");
                    break;
                case 17:
                    idPedido.append("R");
                    break;
                case 18:
                    idPedido.append("S");
                    break;
                case 19:
                    idPedido.append("T");
                    break;
                case 20:
                    idPedido.append("U");
                    break;
                case 21:
                    idPedido.append("V");
                    break;
                case 22:
                    idPedido.append("W");
                    break;
                case 23:
                    idPedido.append("X");
                    break;
                case 24:
                    idPedido.append("Y");
                    break;
                case 25:
                    idPedido.append("Z");
                    break;
                case 26:
                    idPedido.append("0");
                    break;
                case 27:
                    idPedido.append("1");
                    break;
                case 28:
                    idPedido.append("2");
                    break;
                case 29:
                    idPedido.append("3");
                    break;
                case 30:
                    idPedido.append("4");
                    break;
                case 31:
                    idPedido.append("5");
                    break;
                case 32:
                    idPedido.append("6");
                    break;
                case 33:
                    idPedido.append("7");
                    break;
                case 34:
                    idPedido.append("8");
                    break;
                case 35:
                    idPedido.append("9");
                    break;
            }
        }

        return idPedido.toString();
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