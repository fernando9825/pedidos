package com.fernando9825.pedidos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.fernando9825.pedidos.SQLite.AdminSQLiteOpenHelper;
import com.fernando9825.pedidos.productos.Product;
import com.fernando9825.pedidos.productos.ProductManager;
import com.fernando9825.pedidos.productos.ProductsAdapter;
import com.fernando9825.pedidos.ui.main.SectionsPagerAdapter;
import com.google.gson.JsonIOException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    public ProductManager productManager;

    private RecyclerView rv;
    private SwipeRefreshLayout swipeRefreshLayout;
    public static List<Product> products;
    private ProductsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        //swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        //rv = (RecyclerView) findViewById(R.id.recyclerView);
        //LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
        //View view = layoutInflater.inflate(R.layout.recycler_view_product_list, rv );

        //rv.setHasFixedSize(true);
        //rv.setLayoutManager(new LinearLayoutManager(this));
        //swipeRefreshLayout.setOnRefreshListener(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });


        // on app start load all products to local DB
        ProductManager productManager = new ProductManager(this);
        productManager.loadProductsToLocalDB();


        products = getLocalProductList();

        //Toast.makeText(this, products.get(0).getDescripcion(), Toast.LENGTH_SHORT).show();

        //setUpData();

    }


    // swipe on refresh method
    @Override
    public void onRefresh() {
        getData();
    }

    private void getData() {
        swipeRefreshLayout.setRefreshing(true);
        getLocalProductList();
        swipeRefreshLayout.setRefreshing(false);
    }

    private void setUpData() {
        adapter = new ProductsAdapter(this, products);
        rv.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    private List<Product> getLocalProductList() {

        List<Product> product = new ArrayList<>();

        try {

            Toast.makeText(this, "Datos Cargados con Exito desde la base de Datos Local!!", Toast.LENGTH_LONG).show();
            AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(getBaseContext(), "productos", null, 1);
            product = adminSQLiteOpenHelper.baseDatosLocal();

            Toast.makeText(this, "(Si no carga nada es porque su tabla no exite, Sincronize con el Servidor primero en caso de ser asi )", Toast.LENGTH_LONG).show();
        } catch (JsonIOException e) {
            e.printStackTrace();
            Toast.makeText(this, "La base o tabla que busca no existe", Toast.LENGTH_SHORT).show();

        }

        return product;

    }
}