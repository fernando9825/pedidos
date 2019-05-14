package com.fernando9825.pedidos.pedidos;

public class Pedidos {

    private String id_pedido;
    private String producto;
    private String cliente;
    private int cantidad;
    private String fecha;
    private double precio;
    private double total;

    public Pedidos(String id_pedido, String producto, String cliente, int cantidad, String fecha, double precio, double total) {
        this.id_pedido = id_pedido;
        this.producto = producto;
        this.cliente = cliente;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.precio = precio;
        this.total = total;

    }

    public Pedidos(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(String id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
