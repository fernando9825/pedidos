package com.fernando9825.pedidos.pedidos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fernando9825.pedidos.SQLite.SQLitePedidos;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PedidosManager extends AppCompatActivity {

    public List<Pedidos> pedidosList;
    private Context context;

    Cursor consultaRegistros;

    public PedidosManager(Context context) {
        this.context = context;
    }

    public void loadPedidos() {

    }

}