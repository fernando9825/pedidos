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
    public static final String PEDIDOS_DETALLE = "pedidos";

    private final String PK_pedidos = "PRIMARY KEY";
    private final String PK = "PRIMARY KEY AUTOINCREMENT DEFAULT 1";

    private String pedido_table = "CREATE TABLE IF NOT EXISTS " + PEDIDOS + "(id_pedido text " + PK_pedidos +
            ", cliente text, producto text, fecha DATE DEFAULT (datetime('now','localtime')))";

    private String pedido_detalle = "CREATE TABLE IF NOT EXISTS " + PEDIDOS_DETALLE + "(id INTEGER " + PK +
            ", id_pedido text, producto text, cantidad int)";

    public SQLitePedidos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public List<Pedidos> getLocalPedidos() {

        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor filas_pedido = bd.rawQuery("SELECT * FROM " + PEDIDOS, null);
        Cursor filas_detalle = bd.rawQuery("SELECT * FROM " + PEDIDOS_DETALLE, null);

        if (filas_pedido.getCount() == 0 || filas_detalle.getColumnCount() == 0) {
            bd.close();
            return null;
        }

        List<Pedidos> pedidosList = new ArrayList<>();

        while (filas_pedido.moveToNext()) {
            Pedidos pedidos = new Pedidos(
                    filas_pedido.getString(0), // id_pedido
                    filas_pedido.getString(1), // cliente
                    filas_pedido.getString(2), // producto
                    Integer.parseInt(filas_detalle.getString(3)), // cantidad
                    filas_pedido.getString(4) // fecha
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
        db.execSQL(pedido_detalle);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
