package com.foliaco;

import java.util.UUID;

public class Pedido {

    private String  id;
    private String nombre;
    private double precio;

    public Pedido(String nombre, double precio) {
        this.id = UUID.randomUUID().toString();
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
        return "Pedido{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}

