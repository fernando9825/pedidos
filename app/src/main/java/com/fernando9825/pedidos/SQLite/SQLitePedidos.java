package com.fernando9825.pedidos.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fernando9825.pedidos.pedidos.Pedidos;

import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a bd con SQLiteOpenHelper
 * SQLiteOpenHelper: nos permite diseñar, crear, actualizar la base de datos.
 */

public class SQLitePedidos extends SQLiteOpenHelper {


    public static final String PEDIDOS = "pedidos";
    private final String PK = "PRIMARY KEY AUTOINCREMENT DEFAULT 1";

    private String pedido_table = "CREATE TABLE IF NOT EXISTS " + PEDIDOS + "(id INTEGER " + PK +
            ", producto text, cliente text, cantidad text, fecha text)";

    public SQLitePedidos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public List<Pedidos> getLocalPedidos() {

        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor filas = bd.rawQuery("SELECT * FROM " + PEDIDOS, null);

        if (filas.getCount() == 0) {
            bd.close();
            return null;
        }

        List<Pedidos> pedidosList = new ArrayList<>();

        while (filas.moveToNext()) {
            Pedidos pedidos = new Pedidos(
                    Integer.parseInt(filas.getString(0)),
                    filas.getString(1),
                    filas.getString(2),
                    filas.getString(3),
                    filas.getString(4)
            );
            pedidosList.add(pedidos);
        }

        bd.close();

        return pedidosList;
    }

    //deben crearse inicialmente estos 2 metodos  onCreate(SQLiteDatabase db), onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    @Override
    public void onCreate(SQLiteDatabase db) {
        //el metodo  ejecuta una sentencia para crear la tabla con sus campos y tipos
        db.execSQL(pedido_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
