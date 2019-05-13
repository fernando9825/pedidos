package com.fernando9825.pedidos;

<<<<<<< HEAD
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
=======
import android.content.SharedPreferences;
>>>>>>> master
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.fernando9825.pedidos.SQLite.SQLiteClients;
import com.fernando9825.pedidos.SQLite.SQLitePedidos;
import com.fernando9825.pedidos.SQLite.SQLiteProducts;
import com.fernando9825.pedidos.clientes.Client;
import com.fernando9825.pedidos.clientes.ClientManager;
import com.fernando9825.pedidos.pedidos.Pedidos;
import com.fernando9825.pedidos.pedidos.PedidosManager;
import com.fernando9825.pedidos.productos.Product;
import com.fernando9825.pedidos.productos.ProductManager;
import com.fernando9825.pedidos.ui.main.SectionsPagerAdapter;
import com.google.gson.JsonIOException;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    public ProductManager productManager;
    public static List<Product> products;
    public static List<Client> clients;
<<<<<<< HEAD
    public static List<Pedidos> pedidos;
    Context context;

=======
    public static final String IP_SERVER = "https://ppdm.herokuapp.com";
    public static boolean firstTime = true;
    public static boolean productsDownloaded = false;
    public static boolean clientsDownloaded = false;
    public static final String products_key = "products_downloaded";
    public static final String clients_key = "clients_downloaded";
    public static SharedPreferences settings;
>>>>>>> master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        final String PREFS_NAME = "MyPrefsFile";

        settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean(products_key, false)) {
            productsDownloaded = true;
        }

        if (settings.getBoolean(clients_key, false)) {
            clientsDownloaded = true;
        }




        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        //.setAction("Action", null).show();

                Intent intent = new Intent (view.getContext(), PedidoActivity.class);
                startActivityForResult(intent, 0);

            }
        });

        if (localDataBaseIsEmpty()) {
            getDataFromServer();
        } else {
            products = getLocalProductList();
            clients = getLocalClientList();
<<<<<<< HEAD
            pedidos = getLocalPedidosList();

=======

            if (products == null) {
                ProductManager productManager = new ProductManager(this);
                productManager.loadProductsToLocalDB();

            }

            if (clients == null) {
                ClientManager clientManager = new ClientManager(this);
                clientManager.loadClientsToLocalDB();

            }
>>>>>>> master
        }

    }


    // if it returns true, means that products and clients exists in the local database
    private boolean localDataBaseIsEmpty() {
        return getLocalProductList() == null && getLocalClientList() == null;
    }

    private List<Pedidos> getLocalPedidosList(){
        List<Pedidos> pedidos = new ArrayList<>();

        try {
            SQLitePedidos sqLitePedidos = new SQLitePedidos(this,
                    SQLitePedidos.PEDIDOS, null, 1);
            pedidos = sqLitePedidos.getLocalPedidos();

        } catch (JsonIOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return pedidos;
    }

    private List<Product> getLocalProductList() {

        List<Product> product = new ArrayList<>();

        try {

            SQLiteProducts sqLiteProducts = new SQLiteProducts(this,
                    SQLiteProducts.PRODUCTS, null, 1);
            product = sqLiteProducts.getLocalProducts();

        } catch (JsonIOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return product;

    }

    private List<Client> getLocalClientList() {

        List<Client> client = new ArrayList<>();

        try {

            SQLiteClients sqLiteClients = new SQLiteClients(this,
                    SQLiteClients.CLIENTS, null, 1);
            client = sqLiteClients.getLocalClients();

        } catch (JsonIOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return client;

    }

    private void getDataFromServer() {
        // fetch products and clients data from server
        Toast.makeText(this, "Intentando obtener información desde: " + IP_SERVER,
                Toast.LENGTH_LONG).show();
        ProductManager productManager = new ProductManager(this);
        productManager.loadProductsToLocalDB();


        ClientManager clientManager = new ClientManager(this);
        clientManager.loadClientsToLocalDB();

<<<<<<< HEAD
        PedidosManager pedidosManager = new PedidosManager(this);
        pedidosManager.loadPedidos();
=======

    }


>>>>>>> master

    }
}