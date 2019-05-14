package com.fernando9825.pedidos.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fernando9825.pedidos.productos.Product;

import java.util.ArrayList;
import java.util.List;

public class SQLiteProducts extends SQLiteOpenHelper {

    public static final String PRODUCTS = "productos";
    private final String PK = "PRIMARY KEY AUTOINCREMENT DEFAULT 1";

    private String product_table = "CREATE TABLE " + PRODUCTS + "(id INTEGER " + PK +
            ", descripcion text, barcode text, precio real, image text)";

    public SQLiteProducts(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(product_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public List<Product> getLocalProducts() {
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor filas = bd.rawQuery("SELECT * FROM " + PRODUCTS, null);

        if (filas.getCount() == 0) {
            bd.close();
            return null;
        }

        List<Product> productList = new ArrayList<>();

        while (filas.moveToNext()) {

            try {
                Product product = new Product(
                        Integer.parseInt(filas.getString(0)), // id
                        filas.getString(1), // descripcion
                        filas.getString(2), // barcode
                        Double.parseDouble(filas.getString(3)), // precio
                        filas.getString(4)); // img
                productList.add(product);
            } catch (Exception e) {

            }

        }

        bd.close();

        return productList;
    }
}
