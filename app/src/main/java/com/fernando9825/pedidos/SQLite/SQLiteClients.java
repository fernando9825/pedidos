package com.fernando9825.pedidos.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fernando9825.pedidos.clientes.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Acceso a bd con SQLiteOpenHelper
 * SQLiteOpenHelper: nos permite diseñar, crear, actualizar la base de datos.
 */

public class SQLiteClients extends SQLiteOpenHelper {


    public static final String CLIENTS = "clientes";
    private final String PK = "PRIMARY KEY AUTOINCREMENT DEFAULT 1";

    private String client_table = "CREATE TABLE " + CLIENTS + "(id INTEGER " + PK +
            ", nombre text, direccion text, telefono text)";

    public SQLiteClients(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    public List<Client> getLocalClients() {
        SQLiteDatabase bd = this.getWritableDatabase();
        Cursor filas = bd.rawQuery("SELECT * FROM " + CLIENTS, null);

        if (filas.getCount() == 0) {
            bd.close();
            return null;
        }

        List<Client> clientList = new ArrayList<>();

        while (filas.moveToNext()) {
            Client client = new Client(
                    Integer.parseInt(filas.getString(0)),
                    filas.getString(1),
                    filas.getString(2),
                    filas.getString(3)
            );
            clientList.add(client);
        }

        bd.close();

        return clientList;
    }

    //deben crearse inicialmente estos 2 metodos  onCreate(SQLiteDatabase db), onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    @Override
    public void onCreate(SQLiteDatabase db) {
        //el metodo  ejecuta una sentencia para crear la tabla con sus campos y tipos
        db.execSQL(client_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
