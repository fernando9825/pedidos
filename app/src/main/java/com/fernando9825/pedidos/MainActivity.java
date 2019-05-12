package com.fernando9825.pedidos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.fernando9825.pedidos.SQLite.AdminSQLiteOpenHelper;
import com.fernando9825.pedidos.productos.Product;
import com.fernando9825.pedidos.productos.ProductManager;
import com.fernando9825.pedidos.ui.main.SectionsPagerAdapter;
import com.google.gson.JsonIOException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public ProductManager productManager;
    public static List<Product> products = new ArrayList<>();
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // load all data or refuse to open application
        if (localDataBaseIsEmpty()) {
            getDataFromServer();
        } else {
            products = getLocalProductList();

        }

        if (products == null) {
            recreate(); // restart app until in fetch data
        } else {
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
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                }
            });
        }

    }


    // if it returns true, means that products exists in the local database
    private boolean localDataBaseIsEmpty() {
        return getLocalProductList() == null;
    }

    private List<Product> getLocalProductList() {

        List<Product> product = new ArrayList<>();

        try {

            AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(this, "productos", null, 1);
            product = adminSQLiteOpenHelper.baseDatosLocal();

        } catch (JsonIOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return product;

    }

    private void getDataFromServer() {
        ProductManager productManager = new ProductManager(this);
        productManager.loadProductsToLocalDB();
        products = getLocalProductList();
    }



}