package com.fernando9825.pedidos.productos;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProductManager extends AppCompatActivity {

    public static List<Product> productList = new ArrayList<>();
    static private Gson gson = new Gson();
    public static RecyclerView recyclerView;

    // get products
    static public void loadProducts(final Context context, RecyclerView recyclerView) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Product.URL_PRODUCT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //convertir  el string a json array object
                            JSONArray array = new JSONArray(response);
                            productList = Arrays.asList(gson.fromJson(response, Product[].class));
                            Log.i("PlaceHolderFragment", productList.size() + " productos cargados.");
                            Toast.makeText(context, "Termina carga: " + productList.size() + " productos cargados.", Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "No se ha podido conectar", Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(context).add(stringRequest);
    }

}
