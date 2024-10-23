package com.foliaco;

public class Pedido {
    private String id;
    private String nombre;
    private double precio;

    public Pedido() { }

    public Pedido(String id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return "Pedido{id='" + id + "', nombre='" + nombre + "', precio=" + precio + "}";
    }
}
