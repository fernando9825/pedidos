package com.fernando9825.pedidos.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fernando9825.pedidos.R;
import com.fernando9825.pedidos.productos.Product;
import com.fernando9825.pedidos.productos.ProductManager;
import com.fernando9825.pedidos.productos.ProductsAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

//import com.fernando9825.pedidos.productos.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    private List<Product> productList = new ArrayList<>();
    private Gson gson = new Gson();

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
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        //final TextView textView = root.findViewById(R.id.section_label);

        final SwipeRefreshLayout swipeRefreshLayout = root.findViewById(R.id.swipeRefreshLayout);
        final RecyclerView recyclerView = root.findViewById(R.id.itemsRecyclerView);

        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String s) {
                // Here we'll put a adapter to recyclerView, depending on the index
                final int fragmentNumber = Integer.parseInt(s.split(":")[1]) - 1;

                ProductManager.loadProducts(getContext(), recyclerView);

                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // Refresh items
                        //refreshItems();
                        Toast.makeText(getContext(), SectionsPagerAdapter.TAB_TITLES[fragmentNumber],
                                Toast.LENGTH_SHORT).show();

                        ProductManager.loadProducts(getContext(), recyclerView);

                        refreshItems();
                    }

                    void refreshItems() {
                        // Load items
                        // ...

                        //crear el  adaptador y asignarlo al  recyclerview
                        ProductsAdapter adapter = new ProductsAdapter(getContext(), ProductManager.productList);
                        recyclerView.setAdapter(adapter);


                        // Load complete
                        onItemsLoadComplete();
                    }

                    void onItemsLoadComplete() {
                        // Update the adapter and notify data set changed
                        // ...

                        // Stop refresh animation
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });


            }
        });
        return root;
    }


}