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

    private final String PK_pedidos = "PRIMARY KEY";
    private final String PK = "PRIMARY KEY AUTOINCREMENT DEFAULT 1";

    private String pedido_table = "CREATE TABLE " + PEDIDOS + "(id_pedido text " + PK_pedidos +
            ", cliente text, producto text, fecha DEFAULT (datetime('now','localtime')), cantidad int, precio real, total real)";


    //private String pedido_detalle = "CREATE TABLE IF NOT EXISTS " + PEDIDOS_DETALLE + "(id INTEGER " + PK +
    //        ", id_pedido text, producto text, cantidad int)";

    public SQLitePedidos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public List<Pedidos> getLocalPedidos() {

        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor filas_pedido = bd.rawQuery("SELECT * FROM " + PEDIDOS, null);

        if (filas_pedido.getCount() == 0) {
            bd.close();
            return null;
        }

        List<Pedidos> pedidosList = new ArrayList<>();

        /*
        0 --> id_pedido text
        1 --> cliente text
        2 --> producto text
        3 --> fecha date
        4 --> cantidad int
        5 --> precio real
        6 --> total real
     */

        try {
            while (filas_pedido.moveToNext()) {

                Pedidos pedidos = new Pedidos(
                        filas_pedido.getString(0), // id_pedido
                        filas_pedido.getString(2), // producto
                        filas_pedido.getString(1), // cliente
                        Integer.parseInt(filas_pedido.getString(4)), // cantidad
                        filas_pedido.getString(3), // fecha
                        Double.parseDouble(filas_pedido.getString(5)), // precio
                        Double.parseDouble(filas_pedido.getString(6)) // total
                );
                pedidosList.add(pedidos);
            }

            bd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return pedidosList;
    }

    public void borrarPedidos() {
        SQLiteDatabase bd = this.getWritableDatabase();

        bd.delete(SQLitePedidos.PEDIDOS, null, null);
    }

    //deben crearse inicialmente estos 2 metodos  onCreate(SQLiteDatabase db), onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    @Override
    public void onCreate(SQLiteDatabase db) {
        //el metodo  ejecuta una sentencia para crear la tabla con sus campos y tipos
        db.execSQL(pedido_table);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
