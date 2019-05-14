package com.fernando9825.pedidos.productos;

/**
 * Created by Belal on 10/18/2017.
 */

public class Product {
    public static String URL_PRODUCT = "http://ppdm.herokuapp.com/productos";
    private int id;
    private String descripcion;
    private String barcode;
    private double precio;
    private String image;

    public Product(int id, String descripcion, String barcode, double precio, String image) {
        this.id = id;
        this.descripcion = descripcion;
        this.barcode = barcode;
        this.precio = precio;
        this.image = image;
    }

    public Product(String descripcion) {
        this.descripcion = descripcion;
        this.image = "nada";
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getBarcode() {
        return barcode;
    }


    public double getPrecio() {
        return precio;
    }

    public String getImage() {
        return image;
    }
}
