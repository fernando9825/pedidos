package com.fernando9825.pedidos.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.widget.Toast;

import com.fernando9825.pedidos.MainActivity;
import com.fernando9825.pedidos.R;
import com.fernando9825.pedidos.clientes.Client;
import com.fernando9825.pedidos.clientes.ClientManager;
import com.fernando9825.pedidos.clientes.ClientsAdapter;
import com.fernando9825.pedidos.pedidos.Pedidos;
import com.fernando9825.pedidos.pedidos.PedidosAdapter;
import com.fernando9825.pedidos.productos.Product;
import com.fernando9825.pedidos.productos.ProductManager;
import com.fernando9825.pedidos.productos.ProductsAdapter;
import com.jakewharton.processphoenix.ProcessPhoenix;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.EXTRA_TEXT;
import static com.fernando9825.pedidos.MainActivity.IP_SERVER;

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
    private List<Product> products;
    private List<Client> clients;
    private List<Pedidos> orders;

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


        // setting up binds
        recyclerView = root.findViewById(R.id.recyclerView);
        swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);


        // set initial recycler view properties
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        products = MainActivity.products;
        clients = MainActivity.clients;
        orders = MainActivity.pedidos;

        if (orders != null) {
            loadPedidosToRecycler();
        }

        // when changing tab this event is invoked
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {
                // Here we'll put a adapter to recyclerView, depending on the index
                products = MainActivity.products;
                clients = MainActivity.clients;
                fragmentNumberGlobal = Integer.parseInt(s.split(":")[1]) - 1;
                //textView.setText(MainActivity.products.get(0).getDescripcion());


                //swipeRefreshLayout.setRefreshing(false);
                workFlow();


                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {


                        switch (fragmentNumberGlobal) {
                            case PEDIDOS:
                                Toast.makeText(getContext(), "Pedidos", Toast.LENGTH_SHORT).show();

                                if (MainActivity.pedidos != null) {
                                    loadPedidosToRecycler();
                                } else {
                                    List<Pedidos> noPedidos = new ArrayList<>();
                                    noPedidos.add(new Pedidos("Aún sin pedidos, presione el botón para crear uno nuevo"));
                                    recyclerView.setAdapter(new PedidosAdapter(getContext(), noPedidos));
                                }

                                break;

                            case PRODUCTOS:


                                if (products != null) {
                                    loadProductsToRecycler();
                                } else {
                                    Toast.makeText(getContext(), "No se ha podido refrescar", Toast.LENGTH_SHORT).show();
                                    Intent nextIntent = new Intent(getContext(), MainActivity.class);
                                    nextIntent.putExtra(EXTRA_TEXT, "Hello!");
                                    ProcessPhoenix.triggerRebirth(getContext(), nextIntent);
                                }

                                break;

                            case CLIENTES:

                                if (clients != null) {
                                    loadClientsToRecycler();
                                } else {
                                    Toast.makeText(getContext(), "No se ha podido refrescar", Toast.LENGTH_SHORT).show();
                                    Intent nextIntent = new Intent(getContext(), MainActivity.class);
                                    nextIntent.putExtra(EXTRA_TEXT, "Hello!");
                                    ProcessPhoenix.triggerRebirth(getContext(), nextIntent);
                                }

                                break;
                        }

                        swipeRefreshLayout.setRefreshing(false);


                    }


                });

            }
        });

        return root;
    }

    private void workFlow() {
        // by default setting an adapter to the recycler view
        List<Product> noProduct = new ArrayList<>();
        noProduct.add(new Product(null));
        switch (fragmentNumberGlobal) {
            case PEDIDOS:
                if (orders != null) {
                    loadPedidosToRecycler();
                } else {
                    List<Pedidos> noPedidos = new ArrayList<>();
                    noPedidos.add(new Pedidos("Aún sin pedidos, presione el botón para crear uno nuevo"));
                    recyclerView.setAdapter(new PedidosAdapter(getContext(), noPedidos));
                }


                break;

            case PRODUCTOS:

                if (products != null) {
                    loadProductsToRecycler();

                } else {
                    noProduct.remove(0);
                    noProduct.add(new Product("Aún sin productos, deslice hace abajo para refrescar"));
                    recyclerView.setAdapter(new ProductsAdapter(getContext(), noProduct));

                }


                break;

            case CLIENTES:

                if (clients != null) {
                    loadClientsToRecycler();
                } else {

                    noProduct.remove(0);
                    noProduct.add(new Product("Aún sin clientes, deslice hace abajo para refrescar"));
                    recyclerView.setAdapter(new ProductsAdapter(getContext(), noProduct));
                }

                break;
        }
    }

    private void loadPedidosToRecycler() {
        // getting adapter for the recycler view
        //swipeRefreshLayout.setRefreshing(false);
        //swipeRefreshLayout.setEnabled(false);
        PedidosAdapter adapter2 = new PedidosAdapter(getContext(), orders);
        recyclerView.setAdapter(adapter2);
        swipeRefreshLayout.setRefreshing(false); // adapter on, so set it to false
    }

    private void loadProductsToRecycler() {
        // getting adapter for the recycler view
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setEnabled(false);
        ProductsAdapter adapter2 = new ProductsAdapter(getContext(), products);
        recyclerView.setAdapter(adapter2);
        swipeRefreshLayout.setRefreshing(false); // adapter on, so set it to false
    }

    private void loadClientsToRecycler() {
        // getting adapter for the recycler view
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout.setEnabled(false);
        ClientsAdapter adapter2 = new ClientsAdapter(getContext(), clients);
        recyclerView.setAdapter(adapter2);
        swipeRefreshLayout.setRefreshing(false); // adapter on, so set it to false
    }

    private void getDataFromServer() {
        // fetch products and clients data from server
        if (products == null || clients == null) {
            Toast.makeText(getContext(), "Intentando obtener información desde: " + IP_SERVER,
                    Toast.LENGTH_LONG).show();
        }

        if (products == null) {
            ProductManager productManager = new ProductManager(getContext());
            productManager.loadProductsToLocalDB();
        }


        if (clients == null) {
            ClientManager clientManager = new ClientManager(getContext());
            clientManager.loadClientsToLocalDB();

        }


    }



}