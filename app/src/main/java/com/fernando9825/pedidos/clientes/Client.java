package com.fernando9825.pedidos.clientes;

public class Client {

    public static String URL_CLIENT = "http://ppdm.herokuapp.com/clientes";
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;

    public Client(int id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.direccion = direccion;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Client() {

    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
