package com.fernando9825.pedidos.productos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fernando9825.pedidos.SQLite.AdminSQLiteOpenHelper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProductManager extends AppCompatActivity {

    // list of products
    //private List<Product> productList = new ArrayList<>();
    private Context context;

    // constructor
    public ProductManager(Context context) {
        this.context = context;
    }


    // get products
    public void loadProductsToLocalDB() {
        /*
         * Crear  un String Request
         * El tipo de peticion es GET definido como primer parametro
         * La URL  es definida como primer parametro
         * Entonces tenemos  un Response Listener y un Error Listener
         * En el response listener obtenemos el  JSON como un String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Product.URL_PRODUCT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            List<Product> productList = new ArrayList<>();
                            Gson gson = new Gson();
                            Toast.makeText(context, "conectando ", Toast.LENGTH_LONG).show();
                            //convertir  el string a json array object
                            JSONArray array = new JSONArray(response);
                            productList = Arrays.asList(gson.fromJson(response, Product[].class));

                            Toast.makeText(context, "guardando en base de datos local ", Toast.LENGTH_LONG).show();
                            AdminSQLiteOpenHelper adminSQLiteOpenHelper = new AdminSQLiteOpenHelper(context, "productos", null, 1);
                            //establece el metod para hacer que podamos escribir sobre la bd creada
                            SQLiteDatabase bd = adminSQLiteOpenHelper.getWritableDatabase();

                            ContentValues registro = new ContentValues();
                            String cadena = "";

                            for (int i = 0; i < productList.size(); i++) {
                                registro.put("descripcion", productList.get(i).getDescripcion());
                                registro.put("barcode", productList.get(i).getBarcode());
                                registro.put("costo", productList.get(i).getCosto());
                                registro.put("precio", productList.get(i).getPrecio());
                                registro.put("image", productList.get(i).getImage());
                                bd.insert("productos", null, registro);
                                cadena += productList.get(i).getId() + " : " + productList.get(i).getDescripcion() + "\n";

                                registro.clear();
                            }


                            //crear el  adaptador y asignarlo al  recyclerview
                            // ProductsAdapter adapter = new ProductsAdapter(MainActivity.this, adminSQLiteOpenHelper.baseDatosLocal());
                            // recyclerView.setAdapter(adapter);

                            Toast.makeText(context, "Guardado Exitoso!!! ", Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "No se ha podido conectar ", Toast.LENGTH_SHORT).show();
                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(context).add(stringRequest);
    }


}
