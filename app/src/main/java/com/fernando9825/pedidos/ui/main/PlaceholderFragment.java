package com.fernando9825.pedidos.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernando9825.pedidos.MainActivity;
import com.fernando9825.pedidos.R;
import com.fernando9825.pedidos.productos.Product;
import com.fernando9825.pedidos.productos.ProductsAdapter;

import java.util.ArrayList;
import java.util.List;

//import com.fernando9825.pedidos.productos.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public int fragmentNumberGlobal; // var to get the actual fragment
    TextView textView;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private List<Product> products = new ArrayList<>();
    private final int PEDIDOS = 0,
            PRODUCTOS = 1,
            CLIENTES = 2;


    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);


    }


    //Here, we can know which fragment is the current by the index
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_main, container, false);
        //textView = root.findViewById(R.id.section_label);
        products = MainActivity.products;

        // setting up binds
        recyclerView = root.findViewById(R.id.recyclerView);
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);


        // set initial recycler view properties
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // here we have to get data on app start load all products to local DB
                /*if(localDataBaseIsEmpty()) {
                    ProductManager productManager = new ProductManager(getContext());
                    productManager.loadProductsToLocalDB();
                    products = productManager.productList;
                }


                // getting adapter for the recycler view
                ProductsAdapter adapter2 = new ProductsAdapter(getContext(), products);
                recyclerView.setAdapter(adapter2);
                swipeRefreshLayout.setRefreshing(false); // adapter on, so set it to false
                */


            }


        });


        // when changing tab this event is invoked
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {
                // Here we'll put a adapter to recyclerView, depending on the index

                fragmentNumberGlobal = Integer.parseInt(s.split(":")[1]) - 1;
                //textView.setText(MainActivity.products.get(0).getDescripcion());

                //swipeRefreshLayout.setRefreshing(false);

                // by default setting an adapter to the recycler view
                List<Product> noProduct = new ArrayList<>();
                noProduct.add(new Product(null));
                switch (fragmentNumberGlobal) {
                    case PEDIDOS:
                        noProduct.remove(0);
                        noProduct.add(new Product("Aún sin pedidos, presione el botón para crear uno nuevo"));
                        recyclerView.setAdapter(new ProductsAdapter(getContext(), noProduct));
                        break;

                    case PRODUCTOS:
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        loadProductsToRecycler();

                        break;

                    case CLIENTES:
                        noProduct.remove(0);
                        noProduct.add(new Product("Aún sin clientes, deslice hacia abajo para refrescar"));
                        recyclerView.setAdapter(new ProductsAdapter(getContext(), noProduct));
                        break;
                }
            }
        });

        return root;
    }

    private void loadProductsToRecycler() {
        // getting adapter for the recycler view
        ProductsAdapter adapter2 = new ProductsAdapter(getContext(), products);
        recyclerView.setAdapter(adapter2);
        swipeRefreshLayout.setRefreshing(false); // adapter on, so set it to false
    }



}