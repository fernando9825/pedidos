package com.fernando9825.pedidos.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fernando9825.pedidos.productos.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a bd con SQLiteOpenHelper
 * SQLiteOpenHelper: nos permite diseñar, crear, actualizar la base de datos.
 */

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {


    private String tabla_articulo = "CREATE TABLE productos(id INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1, descripcion text, " +
            "barcode text, costo real, precio real, image text)";

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public List<Product> baseDatosLocal() {
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor filas = bd.rawQuery("select * from productos", null);

        if (filas.getCount() == 0) {
            return null;
        }

        List<Product> productList = new ArrayList<>();

        while (filas.moveToNext()) {
            Product product = new Product(Integer.parseInt(filas.getString(0)), filas.getString(1), filas.getString(2), Double.parseDouble(filas.getString(3)), Double.parseDouble(filas.getString(4)), filas.getString(5));
            productList.add(product);
        }

        bd.close();

        return productList;
    }

    //deben crearse inicialmente estos 2 metodos  onCreate(SQLiteDatabase db), onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    @Override
    public void onCreate(SQLiteDatabase db) {
        //el metodo  ejecuta una sentencia para crear la tabla con sus campos y tipos
        db.execSQL(tabla_articulo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
