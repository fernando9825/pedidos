package com.fernando9825.pedidos.pedidos;

public class Pedidos {

    private int id;
    private String producto;
    private String cliente;
    private String cantidad;
    private String fecha;

    public Pedidos(int id, String producto, String cliente, String cantidad, String fecha) {
        this.id = id;
        this.producto = producto;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.fecha = fecha;

    }


    public int getId() {
        return id;
    }
    public String getProducto() {
        return producto;
    }
    public String getCliente() {
        return cliente;
    }
    public String getCantidad() {
        return cantidad;
    }
    public String getFecha() {
        return fecha;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
    public void setCantidad(String cantidad) { this.cantidad = cantidad; }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
