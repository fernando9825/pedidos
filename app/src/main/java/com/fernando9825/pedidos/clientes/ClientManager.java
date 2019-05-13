package com.fernando9825.pedidos.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fernando9825.pedidos.SQLite.SQLiteClients;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// volley imports

public class ClientManager {

    // list of products
    public List<Client> clientList;
    private Context context;

    public ClientManager(Context context) {
        this.context = context;
    }

    // get products
    public void loadClientsToLocalDB() {
        /*
         * Crear  un String Request
         * El tipo de peticion es GET definido como primer parametro
         * La URL  es definida como primer parametro
         * Entonces tenemos  un Response Listener y un Error Listener
         * En el response listener obtenemos el  JSON como un String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Client.URL_CLIENT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            clientList = new ArrayList<>();
                            List<Client> clients;
                            Gson gson = new Gson();

                            //convertir  el string a json array object
                            JSONArray array = new JSONArray(response);
                            clients = Arrays.asList(gson.fromJson(response, Client[].class));
                            clientList = clients;

                            SQLiteClients sqLiteClients = new SQLiteClients(context,
                                    SQLiteClients.CLIENTS, null, 1);
                            //establece el metod para hacer que podamos escribir sobre la bd creada
                            SQLiteDatabase bd = sqLiteClients.getWritableDatabase();

                            ContentValues registro = new ContentValues();

                            for (int i = 0; i < clients.size(); i++) {
                                registro.put("nombre", clients.get(i).getNombre());
                                registro.put("direccion", clients.get(i).getDireccion());
                                registro.put("telefono", clients.get(i).getTelefono());
                                bd.insert(SQLiteClients.CLIENTS, null, registro);

                                registro.clear();
                            }

                            bd.close();


                            Toast.makeText(context, "Se han descargado los datos del servidor", Toast.LENGTH_LONG).show();

                            /*if (!MainActivity.productsDownloaded && !MainActivity.clientsDownloaded) {
                                MainActivity.settings.edit().putBoolean(MainActivity.clients_key, true).commit();
                                Intent nextIntent = new Intent(context, MainActivity.class);
                                nextIntent.putExtra(EXTRA_TEXT, "Hello!");
                                ProcessPhoenix.triggerRebirth(context, nextIntent);
                            }*/

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


    public List<Client> getLocalClientList() {

        List<Client> client = new ArrayList<>();

        try {

            SQLiteClients sqLiteClients = new SQLiteClients(context,
                    SQLiteClients.CLIENTS, null, 1);
            client = sqLiteClients.getLocalClients();

        } catch (JsonIOException e) {
            e.printStackTrace();
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        return client;

    }
}
